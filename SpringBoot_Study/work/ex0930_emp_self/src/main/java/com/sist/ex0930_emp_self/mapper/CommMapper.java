package com.sist.ex0930_emp_self.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sist.ex0930_emp_self.vo.CommVO;

@Mapper
public interface CommMapper {
    
    List<CommVO> commList(String b_idx);

    int addComm(CommVO vo);
}