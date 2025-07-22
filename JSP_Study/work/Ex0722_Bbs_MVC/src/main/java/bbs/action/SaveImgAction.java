package bbs.action;

import bbs.action.Action;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

public class SaveImgAction implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // 파일을 저장할 위치(/editor_img)를 절대경로로 만들자
        ServletContext application = request.getServletContext(); // 기억하자
        String realPath = application.getRealPath("/editor_img");

        // cos라이브러리에 MultipartRequest객체 생성하면
        // 전달되어 오는 파일이 저장된다.
        try{
            MultipartRequest mr = new MultipartRequest(request, realPath, 1024*1024*5, "utf-8", new DefaultFileRenamePolicy());
            // 이때 파일은 이미 editor_img폴더에 업로드가 된 상태다.

            // 파일이 저장될 때 이름이 변경될 수 있기 때문에 저장된 파일의
            // 정확한 이름을 알아내야 한다.
            File f = mr.getFile("upload");
            String fname = null;
            if(f != null){
                fname = f.getName();
                request.setAttribute("f_name", fname);
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return "saveImg.jsp";
    }
}
