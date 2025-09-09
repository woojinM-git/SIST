package com.sist.ex0908_bbs.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.tomcat.util.file.ConfigurationSource.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.sist.ex0908_bbs.config.DbConfig;
import com.sist.ex0908_bbs.service.BbsService;
import com.sist.ex0908_bbs.service.CommService;
import com.sist.ex0908_bbs.util.FileRenameUtil;
import com.sist.ex0908_bbs.util.Paging;
import com.sist.ex0908_bbs.vo.BbsVO;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
public class BbsController {

    private final DbConfig dbConfig;

    private final CommService commService;

    @Autowired
    private HttpSession session; 
    
    @Autowired
    private BbsService bbsService;

    @Autowired
    private ServletContext application;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Value("${server.editor.path}")
    private String editor_img;

    @Value("${server.upload.path}")
    private String bbs_upload;

    private int numPerPage = 7; // 한 페이지당 보여질 게시물 수
    private int pagePerBlock = 5;

    BbsController(CommService commService, DbConfig dbConfig) {
        this.commService = commService;
        this.dbConfig = dbConfig;
    } // 한 블럭당 표현할 페이지 수

    @RequestMapping("/list")
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
                    vo.setOri_name(oname);
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
    
    // 게시물 보기기능
    // 조회수 증가처리, 읽은 게시물은 조회수 증가를 하지 않는다.
    // 조회수 증가처리는 세션을 활용하여 처리한다.
    @GetMapping("/view")
    public ModelAndView view(@RequestParam String bname, @RequestParam String b_idx, String cPage) {
        ModelAndView mv = new ModelAndView();

        // 세션에 read_list라는 이름으로 저장된 객체를 얻어내자!
        Object obj = session.getAttribute("read_list");
        ArrayList<BbsVO> list = null;
        
        // obj가 null이 아니면 obj를 형변환 시켜서 list에 담자
        if(obj != null){
            list = (ArrayList<BbsVO>) obj;
        } else {
            list = new ArrayList<>();
            session.setAttribute("read_list", list);
        }

        // 사용자가 선택한 게시물(b_idx)을 DB로부터 가져온다.
        BbsVO vo = bbsService.getBbs(b_idx);

        // vo를 list에서 찾아보자
        boolean chk = false; // false가 계속 유지된다면 조회수 증가
        for(BbsVO v : list) {
            if(v.getB_idx().equals(b_idx)) {
                chk = true; // 이미 읽은 게시물이다.
                break;
            }
        }

        // chk가 false를 유지하고 있다면 한번도 읽지 않은 게시물을 의미
        // 그러므로 조회수를 증가시키자 
        if(!chk){
            bbsService.hit(b_idx); // 조회수 증가!

            // 화면에 즉각적으로 방영하기 위해 먼저
            // 조회수를 얻어낸다.
            int hit = vo.getHit() + 1;

            vo.setHit(hit);

            list.add(vo);
        }
        mv.addObject("bname", bname);
        mv.addObject("cPage", cPage);
        mv.addObject("vo", vo);
        mv.setViewName(bname+"/view");

        return mv;
    }
    
    @PostMapping("/edit")
    public ModelAndView eidt(BbsVO vo ,String cPage) {
        ModelAndView mv = new ModelAndView();

        

        String c_type = request.getContentType();
        if(c_type.startsWith("multipart")){ // 멀티파트로 오는 폼 객체라면
            System.out.println("멀티파트");
            // 파일첨부가 된 폼 객체인지 확인하고
            MultipartFile f = vo.getFile(); // 파일
            String oname = null; // 이름 얻을 준비
            if(f.getSize() > 0) {
                System.out.println("f != null");
                // 첨부된 파일을 원하는 위치에 저장해야함
                String realPath = application.getRealPath(bbs_upload);
                oname = f.getOriginalFilename(); // 파일이름받아놓기
                String fname = FileRenameUtil.checkSameFileName(oname, realPath);
                try{
                    f.transferTo(new File(realPath, fname)); // 파일 업로드됨
                    // DB에 저장할 수 있도록 파일명 vo에 저장
                    vo.setFile_name(oname);
                    vo.setOri_name(fname);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else{
                System.out.println("f == null");
            }

            // DB에 저장
            int chk = bbsService.edit(vo);
            System.out.println("업데이트 :" + chk);

            mv.setViewName("redirect:view?bname="+vo.getBname()+"&b_idx="+vo.getB_idx()+"&cPage="+cPage);

        } else {
            System.out.println("멀티파트가 아님");
        // 멀티파트로 오는 폼 객체가 아니라면
        // 그냥 글 수정 화면을 보여준다
        // 인자로 받은 b_idx를 가지고 게시물을 얻어온다.
        vo = bbsService.getBbs(vo.getB_idx());

        mv.addObject("vo", vo);
        mv.addObject("bname", vo.getBname());
        mv.addObject("cPage", cPage);

        mv.setViewName(vo.getBname()+"/edit");

        }
        
        return mv;
    }
    
    @PostMapping("download")
    public ResponseEntity<Resource> download(String f_name){
        // 파일들이 저장되어 있는 곳 (bbs_upload)를 절대경로화 시키자
        String realPath = application.getRealPath(bbs_upload+f_name);
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
                        "attachment; filename=" + new String(f_name.getBytes(), "8859_1"));
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
