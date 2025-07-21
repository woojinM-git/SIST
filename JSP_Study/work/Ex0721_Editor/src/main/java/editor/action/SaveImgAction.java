package editor.action;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

public class SaveImgAction implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // 파일을 저장할 위치(/upload_img)를 절대경로로 만들자
        ServletContext application = request.getServletContext(); // 기억하자
        String realPath = application.getRealPath("/upload_img");

        // cos라이브러리에 MultipartRequest객체 생성하면
        // 전달되어 오는 파일이 저장된다.
        try{
            MultipartRequest mr = new MultipartRequest(request, realPath, 1024*1024*5, "utf-8", new DefaultFileRenamePolicy());
            // 처음인자는 request, 두번째는 저장위치, 세번째는 받을 용량, 4: 저장 형식, 5: 자동 이름바꾸기
            // 저장된 파일명과 위치를 JSP에서 표현해야 하므로
            // 저장된 정확한 파일명을 얻어내자
            File f = mr.getFile("upload");
            String f_name = null;
            if(f != null){
                f_name = f.getName(); // 저장된 실제 파일명!
                // JSP에서 사용해야 하므로 request에 저장
                request.setAttribute("f_name", f_name);
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return "saveImg.jsp";
    }
}
