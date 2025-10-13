package com.sist.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sist.backend.repository.EmpRepository;
import com.sist.backend.store.Emp;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmpService {
    private final EmpRepository empRepository;

    public List<Emp> findAll(){
        return empRepository.findAll();
    }

    public Optional<Emp> findByEmpno(Long emp){
        return empRepository.findByEmpno(emp);
    }

    public List<Emp> findByDeptno(String deptno){
        return empRepository.findByDeptno(deptno);
    }

    public List<Emp> findByJobAndDeptno(String job, String deptno){
        return empRepository.findByJobAndDeptno(job, deptno);
    }

    public List<Emp> findByJobContainingAndDeptno(String job, String deptno){
        return empRepository.findByJobContainingAndDeptno(job, deptno);
    }
}
