package com.sist.ex0905_bbs.mapper;

import com.sist.ex0905_bbs.vo.BbsVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BbsMapper {

    List<BbsVO> total();

    List<BbsVO> search(String searchType, String searchValue);
}
