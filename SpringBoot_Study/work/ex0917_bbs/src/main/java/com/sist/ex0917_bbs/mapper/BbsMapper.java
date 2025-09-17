package com.sist.ex0917_bbs.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sist.ex0917_bbs.vo.BbsVO;

@Mapper
public interface BbsMapper {
    // 총 게시물의 수를 반환
    int totalCount(Map<String, Object> map);

    // 원글들을 원하는 만큼 가져오는 기능
    List<BbsVO> list(String bname, String searchType, String searchValue, int begin, int end);

    // 원글 저장
    int add(BbsVO vo);

    // 인자받아 원글 검색
    BbsVO get_bbs(String b_idx);

    // 조회수 증가
    int hit(String b_idx);

    // 원글 수정
    int edit(Map<String, Object> map);

    // 원글 삭제
    int del(String b_idx);
}
