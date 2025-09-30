package com.sist.ex0930_docker.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sist.ex0930_docker.service.CommService;
import com.sist.ex0930_docker.util.Paging;
import com.sist.ex0930_docker.vo.BbsVO;
import com.sist.ex0930_docker.service.BbsService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/board")
public class BbsControl {
    @Autowired
    private BbsService BbsService;

    @Autowired
    private CommService commService;

    private int numPerPage = 7;
    private int pagePerBlock = 5;

    @RequestMapping("/list")
    public Map<String, Object> list(@RequestParam("bname") String bname, String searchType, String searchValue, String cPage){
        int nowPage = 1;
        if(cPage != null) // cPage는 현재 사용자가 보고 있는 페이지의 페이징숫자
            nowPage = Integer.parseInt(cPage);

        // bname이 무조건 인자로 받기로 되어 있지만 공백일 때는
        // 기본값으로 "BBS"로 정하자
        if(bname.trim().length() == 0)
            bname = "BBS";

        // 페이징 기법을 위해 우선 총 게시물 수를 구하자
        int totalCount = BbsService.getTotalCount(bname, searchType, searchValue);

        // 페이징 객체 생성
        Paging page = new Paging(numPerPage, pagePerBlock);
        page.setTotalCount(totalCount); // 총 게시물 수를 지정
        page.setNowPage(nowPage); // 현재 페이지 값 지정

        // 이제 페이징 객체로부터 begin과 end 값을 얻어낸다.
        int begin = page.getBegin();
        int end = page.getEnd();

        // 화면에 표현할 게시물 목록 받기
        List<BbsVO> list = BbsService.getList(bname, searchType, searchValue, begin, end);

        // 반환할 Map 생성
        Map<String, Object> map = new HashMap<>();
        map.put("ar", list);
        map.put("bname", bname);
        map.put("nowPage", nowPage);
        map.put("totalCount", totalCount);
        map.put("totalPage", page.getTotalPage());
        map.put("length", list.size());

        return map;
    }

    @RequestMapping("/getBbs")
    public Map<String, Object> getBbs(@RequestParam String b_idx) {
        Map<String, Object> map = new HashMap<>();
        BbsVO vo = BbsService.getBbs(b_idx);
        map.put("vo", vo);
        return map;
    }

    @RequestMapping ("/add")
    public Map<String, Object> addBbs(@RequestBody BbsVO vo) {
        Map<String, Object> map = new HashMap<>();
        // 첨부파일 처리.... 스프링 예제 참고!


        int cnt = BbsService.addBbs(vo);
        map.put("cnt", cnt); // 보낸 Map(cnt)이 0보다 크면 저장이 됐다는 의미
        return map;
    }
}
