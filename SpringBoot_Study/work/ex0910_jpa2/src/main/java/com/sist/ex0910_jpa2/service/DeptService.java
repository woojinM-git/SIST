package com.sist.ex0910_jpa2.service;

import org.springframework.stereotype.Service;

import com.sist.ex0910_jpa2.repository.DeptRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeptService {
    
    private final DeptRepo deptRepo;

    public Object findAll(){
        return deptRepo.findAll();
    }

    public Object findByDeptno(Long deptno){
        return deptRepo.findByDeptno(deptno);
    }
}
