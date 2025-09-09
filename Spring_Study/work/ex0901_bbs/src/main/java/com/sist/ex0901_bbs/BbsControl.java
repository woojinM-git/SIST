package com.sist.ex0901_bbs;

import bbs.util.Paging;
import mybatis.dao.BbsDAO;
import mybatis.vo.BbsVO;
import mybatis.vo.ImgVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
public class BbsControl {
    @Autowired
    private BbsDAO bbsDAO;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private ServletContext application;

    @Autowired
    private HttpSession session;

    int numPerPage = 7;
    int pagePerBlock = 5;

    // 첨부파일들이 저장되는 곳
    private String upload_path = "/resources/upload";

    // 에디터에서 추가한 이미지파일들이 저장될 곳
    private String editor_path = "/resources/editor_img/";

    // 목록 보여주기위한 영역
    @RequestMapping("list")
    public ModelAndView list(String bname, String cPage){
        System.out.println(cPage);
        ModelAndView mv = new ModelAndView();

        // 페이징 기법 -------------------------
        if(bname == null || bname.equals(""))
            bname = "BBS";
        // 생성하면서 인자를 2개 넣어줘야함
        Paging page = new Paging(numPerPage, pagePerBlock);
        //                         게시물의 수, 블럭당 보여질 페이지 수
        int totalRecord = bbsDAO.getTotalCount(bname);
        page.setTotalCount(totalRecord);
        if(cPage == null)
            cPage = "1";

        page.setNowPage(Integer.parseInt(cPage)); // begin, end, startPage, endPage 구해짐
        // ------------------------------------

        // 뷰 페이지에서 표현할 게시물 목록 가져오기
        BbsVO[] ar = bbsDAO.getList(bname, page.getBegin(), page.getEnd());

        // 뷰 페이지에서 표현할 정보들을 mv에 저장
        mv.addObject("ar", ar);
        mv.addObject("page", page);
        mv.addObject("bname", bname);

        mv.setViewName("list");// 뷰 페이지 설정

        return mv;
    }

    // 글 보기
    @GetMapping("/view")
    public ModelAndView view(String bname, String b_idx, String cPage) {
        ModelAndView mv = new ModelAndView();
        ArrayList<BbsVO> list = null;
        // 세션으로부터 이름이 r_list라는 이름으로 저장된 객체를 얻어낸다.
        Object obj = session.getAttribute("r_list");

        // obj가 null이 아니면 obj를 형변환해서 list에 저장하자
        if(obj != null)
            list = (ArrayList<BbsVO>) obj;
        else {
            // 처음으로 게시판 들어온 경우
            list = new ArrayList<>();
            // 새로 생성된 경우일때만 session에 저장
            session.setAttribute("r_list", list);
        }

        // 이제 list에서 인자로 받은 b_idx값과 같은 값을 가진 BbsVO를
        // list에서 검색한다.
        boolean check = false; // 이 false가 계속 유지된다면 찾지 못한 경우
        for(BbsVO bvo : list) {
            if(bvo.getB_idx().equals(b_idx)) {
                check = true;
                break;
            }
        } // for end
        // check 변수의 값이 false면 조회수 증가
        if(!check) {
            bbsDAO.hit(b_idx); // 조회수 증가
        }

        BbsVO vo = bbsDAO.getBbs(b_idx);
        if(vo != null) {
            if(!check)
                list.add(vo); // 다음에 같은 게시물을 클릭하면 조회수 증가를 하지 않는다.
            // 검색된 vo를 뷰페이지에서 봐야 하므로 mv에 저장
            mv.addObject("vo", vo);
        }
        mv.setViewName("/view");
        return mv;
    }

    // 글쓰기 화면 이동
    @RequestMapping("/write")
    public String write(){ // forward로 전달되어 인자를 받지않아도 그대로 전달됌
        return "write";
    }

    @RequestMapping(value="saveImage", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> saveImage(ImgVO vo){
        // json처리가 용이한 Map구조를 미리 선언하자!
        Map<String, String> map = new HashMap<>();

        // 전달되어 오는 이미지파일은 vo라는 인자에 저장되어 있다. (ImgVO에 저장된거임)
        MultipartFile f = vo.getUpload();
        // 파일객체는 첨부를 하지 않아도 무조건 생성해서 null이 들어가지 않느다. !!!!!!!!!!!!!!!!!!!!!!!
        if(f != null) {
            // 넘어온 파일이 있는 경우
            // 파일을 저장할 위치(editor_img)를 절대경로화 시킨다.
            String realPath = application.getRealPath(editor_path); // /resources/editor_img 로 지정되어있음

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
            map.put("url", c_path + editor_path + "/");
        }

        return map;
    }

    // 글저장
    @RequestMapping(value="upload", method = RequestMethod.POST)
    public ModelAndView add(BbsVO vo){
        // 전달되는 파라미터들이 모두 DataVO에 저장된 상태임
        ModelAndView mv = new ModelAndView();

        // 폼에 enctype이 multipart로 시작하는지 확인하고 싶다면...
        String c_type = request.getContentType();
        if(c_type.startsWith("multipart")){
            // 파일첨부가 된 폼객체로부터 요청이 된 경우라면
            MultipartFile f = vo.getSss(); // 파일
            String fname = null; // 이름 얻을 준비
            if(f != null) {
                System.out.println("f != null");
                // 첨부된 파일을 원하는 위치에 저장해야함
                String realPath = application.getRealPath("/resources/upload");
                fname = f.getOriginalFilename(); // 파일이름받아놓기
                try{
                    f.transferTo(new File(realPath, fname)); // 파일 업로드됨
                    // DB에 저장할 수 있도록 파일명 vo에 저장
                    vo.setFile_name(fname);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else{
                System.out.println("f == null");
            }
            vo.setIp(request.getRemoteAddr()); // ip 설정

            // DB에 저장
            bbsDAO.add(vo);
        }
        mv.setViewName("redirect:/list");
        // redirect를 사용하기 위해서는 ModelAndView를 사용해야 한다.
        return mv;
    }

    // 삭제
    @RequestMapping("/del")
    public ModelAndView view(String b_idx) {
        ModelAndView mv = new ModelAndView();
        int i = bbsDAO.delBbs(b_idx);
        // 글이 삭제되면 목록으로 가야함
        mv.setViewName("redirect:/list");
        return mv;
    }

    // 수정화면 이동
    @RequestMapping("/edit")
    public ModelAndView write(BbsVO vo) {
        ModelAndView mv = new ModelAndView();
        BbsVO getvo = bbsDAO.getBbs(vo.getB_idx()); // 기본키 인자로 게시물 가져옴
        mv.addObject("vo", getvo);
        mv.setViewName("/edit");
        return mv;
    }

    // 수정
    @RequestMapping("/correct")
    public ModelAndView correct(BbsVO vo) {
        ModelAndView mv = new ModelAndView();
        bbsDAO.correct(vo);
        // 수정이 완료되면 다시 해당 게시글을 보여줘야함
        mv.setViewName("redirect:/view?b_idx=" + vo.getB_idx());
        return mv;
    }

    @PostMapping("download")
    public ResponseEntity<Resource> download(String f_name){
        // 파일들이 저장되어 있는 곳 (bbs_upload)를 절대경로화 시키자
        String realPath = application.getRealPath(upload_path+f_name);
        File f = new File(realPath); //파일 객체를 만드는 이유 = 존재 여부 확인을 위해
        if(f.exists()){ // 존재여부 확인
            // 존재할 경우에만 요청한 곳으로 파일을 보내줘야 한다.
            byte[] buf = new byte[4096];
            int size = -1;
            // 파일을 다운로드에 필요한 스트림 준비
            BufferedInputStream bis = null;
            FileInputStream fis = null;
            BufferedOutputStream bos = null;
            ServletOutputStream sos = null; // 응답을 하는 것이 접속자의
            // 컴퓨터로 다운로드를 시켜야 하기 때문에 response를 통해
            // OutputStream을 얻어내야 응답으로 다운로드가 되는 것이다.
            // 그래서 response로 얻어내는 스트림이 ServletOutputStream이므로
            // sos를 선언했다.
            try{
                // 접속자 화면에 다운로드 창 보여주기
                response.setContentType("application/x-msdownload");
                response.setHeader("Content-Disposition",
                        "attachment;filename=" + new String(f_name.getBytes("8859_1")));
                // 다운로드로 읽어낼 스트림 준비
                fis = new FileInputStream(f);
                bis = new BufferedInputStream(fis);

                // response를 통해 다운로드할 스트림을 얻어낸다.
                sos = response.getOutputStream();
                bos = new BufferedOutputStream(sos);

                while((size = bis.read(buf)) != -1){ // 저장할 파일이 있으면
                    bos.write(buf, 0, size);
                    bos.flush();
                }
            }catch(Exception e){
                e.printStackTrace();
            } finally {
                try{
                    if(fis != null)
                        fis.close();
                    if(bis != null)
                        bis.close();
                    if(sos != null)
                        sos.close();
                    if(bos != null)
                        bos.close();
                }catch (Exception e){

                }
            }
        }// if end
        return null;
    }
}
