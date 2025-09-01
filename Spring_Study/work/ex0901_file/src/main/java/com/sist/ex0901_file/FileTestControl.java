package com.sist.ex0901_file;

import file.input.ParamVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
public class FileTestControl {

    // 파일 첨부에 필요한 멤버변수들
    @Autowired
    private ServletContext application;

    @Autowired
    private HttpServletRequest request;

//    @Autowired
//    private ParamDAO paramDAO;
    
    // 파일이 저장될 위치 - ~webapp/resources/file_upload라는 폴더로 지정하자
    // 위치지정에 앞서 dispatcher-servlet.xml에서 webapp/resources를 등록해야 함
    private String upload_path = "/resources/file_upload";

    @RequestMapping("/write")
    public String writeForm() {
        return "write";
    }

    @RequestMapping(value="/write", method= RequestMethod.POST)
    public ModelAndView write(ParamVO vo) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/write");
        MultipartFile f = vo.getS_file();

        // 파일이 저장될 위치를 절대경로화 한다.
        String real_path = application.getRealPath(upload_path);
        String fname = null;
        if(f != null) {
            fname = f.getOriginalFilename();
            vo.setFile_name(fname); // 바뀐 파일명을 준다
//            vo.setIp(request.getRemoteAddr()); // 첨부한 사용자의 IP를 request로 얻어서 저장
            try {
                f.transferTo(new File(real_path + "/" + fname));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//        paramDAO.add(vo); // DB 저장은 if를 나가고 수행해야 한다.
        return mv;
    }
}
