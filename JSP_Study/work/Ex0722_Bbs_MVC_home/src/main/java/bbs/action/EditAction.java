package bbs.action;

import bbs.dao.BbsDAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import mybatis.vo.BbsVO;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

public class EditAction implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // 먼저 요청시 contentType을 얻어낸다.
        String enc_type = request.getContentType();

        String viewPath = null;
        if(enc_type.startsWith("application")){
            //view.jsp에서 [수정]버튼을 클릭한 경우 이때는
            // 수정화면으로 이동해야 함!
            // 그럼 먼저 수정하고자 하는 게시물을 얻어내야 한다.
            String b_idx = request.getParameter("b_idx");
            BbsVO vo = BbsDAO.getBbs(b_idx);

            request.setAttribute("vo", vo);
            viewPath = "edit.jsp";// 여기서 forward되므로 이쪽으로 넘어오는
            // 파라미터들(b_idx, cPage)은 그대로 유지되어 edit.jsp로 간다.
        }else if(enc_type.startsWith("multipart")){
            //edit.jsp에서 값을 수정한 후 DB에 UPDATE를 수행하길 원하는 경우
            //첨부파일을 처리하기 위해 bbs_upload폴더의 절대경로가 필요하다.
            ServletContext application = request.getServletContext();
            try {
                String realPath = application.getRealPath("/bbs_upload");

                //첨부파일과 다른 파라미터들을 받기위해 MultipartRequest생성
                MultipartRequest mr = new MultipartRequest(request,realPath,
                        1024*1024*5, "utf-8",
                        new DefaultFileRenamePolicy());
                //이때 첨부파일이 있다면 realPath경로에 저장된 상태다.

                //나머지 파라미터들 얻기(title, writer, content)
                String title = mr.getParameter("title");

                String content = mr.getParameter("content");
                String bname = mr.getParameter("bname");
                String b_idx = mr.getParameter("b_idx");
                String cPage = mr.getParameter("cPage");

                //첨부파일이 있다면 fname과 oname을 얻어내야 한다.
                File f = mr.getFile("file");
                String fname = null;
                String oname = null;
                if( f != null ){
                    fname = f.getName();// 현재 저장된 파일명
                    oname = mr.getOriginalFileName("file");// 원래 파일명
                }
                String ip = request.getRemoteAddr();// 요청자의 IP

                //DB에 수정
                BbsDAO.edit(b_idx, title, content, fname, oname, ip);

                viewPath = "Controller?type=view&b_idx="+b_idx+"&cPage="+cPage;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return  viewPath;
    }
}
