package com.sist.ex0829_json;

import mybatis.dao.DataDAO;
import mybatis.vo.DataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import spring.input.ImgVO;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Controller
public class JsonControl {

    @Autowired
    private DataDAO dataDAO;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ServletContext application;

    private String editor_img = "/resources/editor_img";

    @RequestMapping("/ex1")
    public ModelAndView test1() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("ex1"); // WEB-INF/view/ex1.jsp 의미

        return mv;
    }

    @RequestMapping("/ex2")
    public String test2() {

        return "ex2";
    }

//    @RequestMapping(value="/ex3", method = RequestMethod.POST)
    @RequestMapping("/ex3")
    @ResponseBody
    public Map<String, String> test3(){
        Map<String, String> map = new HashMap<>();
        map.put("name", "마루치");
        map.put("email", "maru@korea.com");
        return map;
    }

    @RequestMapping("/editor")
    public String editor(){
        return "editor";
    }

    @RequestMapping(value="saveImage", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> saveImage(ImgVO vo){
        // json처리가 용이한 Map구조를 미리 선언하자!
        Map<String, String> map = new HashMap<>();

        // 전달되어 오는 이미지파일은 vo라는 인자에 저장되어 있다. (ImgVO에 저장된거임)
        MultipartFile f = vo.getUpload();
        // 파일객체는 첨부를 하지 않아도 무조건 생성해서 null이 들어가지 않는다. !!!!!!!!!!!!!!!!!!!!!!!
        if(f != null) {
            // 넘어온 파일이 있는 경우
            // 파일을 저장할 위치(editor_img)를 절대경로화 시킨다.
            String realPath = application.getRealPath(editor_img); // /resources/editor_img 로 지정되어있음

            // 저장할 위치를 준비했으니 파일을 저장하자!
            try {
                f.transferTo(new File(realPath, f.getOriginalFilename())); //
                map.put("fname", f.getOriginalFilename());
            }catch (Exception e){
                e.printStackTrace();
            }
            // 현재 파일이 저장된 서버경로(url)을 문자열로 만들자
            // 예) localhost:8080/resources/editor_img/
            String c_path = request.getContextPath(); // localhost:8080/
            map.put("url", c_path + editor_img + "/");
        }

        return map;
        // JSON으로 보내기 위해 현재 메서드 위에 @ResponseBody를 지정했다.
        // {"fname":"test.gif", "url":"localhost:8080/resources/editor_img/"
    }

    @RequestMapping(value="write", method = RequestMethod.POST)
    public ModelAndView write(DataVO vo){
        // 전달되는 파라미터들이 모두 DataVO에 저장된 상태임
        ModelAndView mv = new ModelAndView();

        // 폼에 enctype이 multipart로 시작하는지 확인하고 싶다면...
        String c_type = request.getContentType();
        if(c_type.startsWith("multipart")){
            // 파일첨부가 된 폼객체로부터 요청이 된 경우라면
            MultipartFile f = vo.getSss(); // 파일
            String fname = null; // 이름 얻을 준비
            if(f.getSize() > 0) {
                // 첨부된 파일을 원하는 위치에 저장해야함
                String realPath = application.getRealPath("resources/upload");
                fname = f.getOriginalFilename(); // 파일이름받아놓기
                try{
                    f.transferTo(new File(realPath, fname)); // 파일 업로드됨
                    // DB에 저장할 수 있도록 파일명 vo에 저장
                    vo.setFile_name(fname);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            vo.setIp(request.getRemoteAddr()); // ip 설정

            // DB에 저장
            dataDAO.add(vo);
        }
        mv.setViewName("redirect:/editor");
        return mv;
    }
}
