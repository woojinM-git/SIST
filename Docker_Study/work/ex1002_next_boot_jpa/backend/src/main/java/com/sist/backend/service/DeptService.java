package com.sist.backend.service;

import org.springframework.stereotype.Service;

import com.sist.backend.repository.DeptRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeptService {
    private final DeptRepository deptRepository;

    public Object findAll(){
        return deptRepository.findAll();
    }

    public Object findByDeptno(Long deptno){
        return deptRepository.findByDeptno(deptno);
    }
}
