package com.sist.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.backend.mapper.EmpMapper;
import com.sist.backend.vo.EmpVO;

@Service
public class EmpService {
    @Autowired
    private EmpMapper empMapper;

    public List<EmpVO> getAll(){
        return empMapper.all();
    }
}
