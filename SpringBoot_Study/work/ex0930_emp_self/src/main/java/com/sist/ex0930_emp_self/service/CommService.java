package com.sist.ex0930_emp_self.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.ex0930_emp_self.mapper.CommMapper;
import com.sist.ex0930_emp_self.vo.CommVO;

@Service
public class CommService {
    @Autowired
    private CommMapper commMapper;

    public int addComm(CommVO vo){
        return commMapper.addComm(vo);
    }
}
