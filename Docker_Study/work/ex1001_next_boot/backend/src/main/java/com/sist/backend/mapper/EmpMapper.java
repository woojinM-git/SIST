package com.sist.backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sist.backend.vo.EmpVO;

@Mapper
public interface EmpMapper {
    List<EmpVO> all();
}
