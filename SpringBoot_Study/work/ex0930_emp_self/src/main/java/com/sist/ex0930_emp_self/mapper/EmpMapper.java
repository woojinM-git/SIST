package com.sist.ex0930_emp_self.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sist.ex0930_emp_self.vo.EmpVO;

@Mapper
public interface EmpMapper {
    // 총 게시물의 수를 반환
    List<EmpVO> list();
}
