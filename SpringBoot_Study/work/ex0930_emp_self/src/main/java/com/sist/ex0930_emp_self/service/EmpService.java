package com.sist.ex0930_emp_self.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.ex0930_emp_self.mapper.EmpMapper;
import com.sist.ex0930_emp_self.vo.EmpVO;

@Service
public class EmpService {
    @Autowired
    private EmpMapper empMapper;

    public List<EmpVO> getlist(){
        return empMapper.list();
    }

}
