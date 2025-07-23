package bbs.action;

import bbs.dao.BbsDAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

public class WriteAction implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String viewPath = null;

        // list.jsp에 있는 [글쓰기] 버튼을 클릭하면 get방식으로
        // 현재 객체를 수행한다. 이때 요청시 contentType을 얻어낸다. 분명
        // get 방식 null 값을 받게된다.
//        request.getMethod(); // 이걸로 post인지 get인지 알 수 있다.
        String enc_type = request.getContentType();
//        System.out.println(enc_type); //
        if(enc_type == null) {
            viewPath = "write.jsp";
        } else if(enc_type.startsWith("multipart")){ // multipart가 포함된 문자열이 나올때는 form에서 요청을 했다는 거임
            // 여기는 write.jsp에서 내용을 입력한 후 [보내기] 버튼을
            // 클릭했을 때 수행하는 곳
            // 첨부파일을 받아서 bbs_upload라는 폴더에 저장해야 함
            try{
                ServletContext application = request.getServletContext(); // ServletContext가 있어야 절대경로를 구함
                String reqlPath = application.getRealPath("/bbs_upload");
                
                // 첨부파일과 다른 파라미터들을 받기위해 MultipartRequest 생성
                MultipartRequest mr = new MultipartRequest(request, reqlPath, 1024*1024*5, "utf-8", new DefaultFileRenamePolicy());
                // 이때 첨부파일이 있다면 realPath에 경로에 저장된 상태다.

                // 나머지 파라미터들 얻기(title, writer, content)
                String title = mr.getParameter("title");
                String writer = mr.getParameter("writer");
                String content = mr.getParameter("content");
                String bname = mr.getParameter("bname");

                // 첨부파일이 있다면 fname과 oname을 얻어내야 한다.
                File f = mr.getFile("file");
                String fname = null;
                String oname = null;
                if(f != null){
                    fname = f.getName(); // 현재 저장된 파일명
                    oname = mr.getOriginalFileName("file"); // 원래이름
                }
                String ip = request.getRemoteAddr(); // 요청자의 IP
                
                // DB에 저장
                BbsDAO.add(title, writer, content, fname, oname, ip, bname);
//                viewPath = "Controller?type=list";
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return viewPath;
    }
}
