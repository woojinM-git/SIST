package bbs.action;

import bbs.dao.BbsDAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import mybatis.vo.BbsVO;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

public class SaveAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String viewPath = null;

        // 요청시 contentType을 얻어낸다.
        String enc_type = request.getContentType(); // ContentType으로 구분해야 한다.

        if(enc_type == null) { // get방식으로 오면 null이 저장되고 다시 correct.jsp로 보냄
            viewPath = "correct.jsp";
        } else if(enc_type.startsWith("multipart")){
            try{
                ServletContext application = request.getServletContext(); // ServletContext가 있어야 절대경로를 구함
                String reqlPath = application.getRealPath("/bbs_upload");

                // 첨부파일과 다른 파라미터들을 받기위해 MultipartRequest 생성
                MultipartRequest mr = new MultipartRequest(request, reqlPath, 1024*1024*5, "utf-8", new DefaultFileRenamePolicy());


                String title = mr.getParameter("title");
                String content = mr.getParameter("content");
                String b_idx = mr.getParameter("b_idx");

                BbsVO bvo = BbsDAO.getBbs(b_idx);
                request.setAttribute("bvo", bvo);

                int cnt = BbsDAO.correct(title, content, b_idx);
                if(cnt > 0) { // 저장됌
                    viewPath = "correct.jsp";
                    System.out.println("저장됌");
                } else { // 저장안됌
                    viewPath = "correct.jsp";
                    System.out.println("저장안댐");
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        return viewPath;
    }
}
