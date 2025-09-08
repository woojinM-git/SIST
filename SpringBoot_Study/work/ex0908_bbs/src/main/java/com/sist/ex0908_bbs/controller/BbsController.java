package com.sist.ex0908_bbs.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jersey.JerseyProperties.Servlet;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sist.ex0908_bbs.service.BbsService;
import com.sist.ex0908_bbs.service.CommService;
import com.sist.ex0908_bbs.util.FileRenameUtil;
import com.sist.ex0908_bbs.util.Paging;
import com.sist.ex0908_bbs.vo.BbsVO;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
public class BbsController {

    private final CommService commService;
    
    @Autowired
    private BbsService bbsService;

    @Autowired
    private ServletContext application;

    @Autowired
    private HttpServletRequest request;

    @Value("${server.editor.path}")
    private String editor_img;

    @Value("${server.upload.path}")
    private String bbs_upload;

    private int numPerPage = 7; // 한 페이지당 보여질 게시물 수
    private int pagePerBlock = 5;

    BbsController(CommService commService) {
        this.commService = commService;
    } // 한 블럭당 표현할 페이지 수

    @GetMapping("/list")
    public ModelAndView getBbsList(@RequestParam String bname, String cPage) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName(bname+"/list");

        int nowPage = 1;
        if(cPage != null && !cPage.equals("")) {
            nowPage = Integer.parseInt(cPage);
        }

        if(bname == null || bname.equals("")){
            bname = "BBS";
        }

        int totalCount = bbsService.getTotalCount(bname); // 총 게시물의 수

        // 페이징 객체 생성
        Paging page = new Paging(nowPage, totalCount, numPerPage, pagePerBlock, bname);

        BbsVO[] ar = bbsService.getList(bname, page.getBegin(), page.getEnd());

        mv.addObject("totalCount", totalCount);
        mv.addObject("bname", bname);
        mv.addObject("ar", ar);
        mv.addObject("page", page);
        mv.addObject("pageResult", page.getPagingHTML());

        return mv;
    }


    @GetMapping("/write")
    public ModelAndView goWrite(@RequestParam String bname, String cPage) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName(bname+"/write");

        mv.addObject("bname", bname);
        mv.addObject("cPage", cPage);
        return mv;
    }
    
    @PostMapping("/saveImage")
    public Map<String, Object> saveImg(@RequestBody MultipartFile upload) {
        // 반환형 준비
        Map<String, Object> map = new HashMap<>();
        
        // 첨부파일이 전달되어 왔다면
        if (upload.getSize() > 0 ){
            // 파일을 저장할 위치 (editor_img)를 절대경로화 시킨다.
            String realPath = application.getRealPath(editor_img);

            // 파일명을 얻어낸다.
            String oname = upload.getOriginalFilename();
            
            // 동일한 파일명이 있을 때만 파일명을 변경하자!
            String fname = FileRenameUtil.checkSameFileName(oname, realPath);

            try{
                upload.transferTo(new File(realPath, fname)); // 파일 저장
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 업로드 된 파일의 전체경로(파일명이 포함된 경로)를
            // map에 담자
            String url_path = request.getContextPath(); // sist.co.kr/

            // JSON형식으로 반환하기 위해 map에 저장해야함
            map.put("img_url", url_path + editor_img + fname);
        }

        return map;
    }
    
    // 글저장
    @PostMapping("/upload")
    public ModelAndView add(BbsVO vo, String bname){
        // 전달되는 파라미터들이 모두 DataVO에 저장된 상태임
        ModelAndView mv = new ModelAndView();

        // 폼에 enctype이 multipart로 시작하는지 확인하고 싶다면...
        String c_type = request.getContentType();
        if(c_type.startsWith("multipart")){
            // 파일첨부가 된 폼객체로부터 요청이 된 경우라면
            MultipartFile f = vo.getFile(); // 파일
            String oname = null; // 이름 얻을 준비
            if(f != null) {
                System.out.println("f != null");
                // 첨부된 파일을 원하는 위치에 저장해야함
                String realPath = application.getRealPath(bbs_upload);
                oname = f.getOriginalFilename(); // 파일이름받아놓기
                String fname = FileRenameUtil.checkSameFileName(oname, realPath);
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
            int chk = bbsService.add(vo);
            System.out.println("저장상태 :" + chk);
        }
        mv.addObject("bname", bname);
        mv.setViewName("redirect:/list");
        // redirect를 사용하기 위해서는 ModelAndView를 사용해야 한다.
        return mv;
    }
    

}
