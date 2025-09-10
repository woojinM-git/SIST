package com.sist.ex0910_jpa2.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.ex0910_jpa2.repository.EmpRepo;
import com.sist.ex0910_jpa2.store.Emp;

@Service
public class EmpService {
    
    @Autowired
    private EmpRepo empRepo;

    public List<Emp> findAll(){
        return empRepo.findAll();
    }

    public Optional<Emp> findbyEmpno(Long empno){
        return empRepo.findByEmpno(empno);
    }

    public List<Emp> findByDeptno(String deptno){
        return empRepo.findByDeptno(deptno);
    }

    public List<Emp> findByJobAndDeptno(String job, String deptno){
        return empRepo.findByJobAndDeptno(job, deptno);
    }

    public List<Emp> findByJobLikeAndDeptno(String job, String deptno){
        return empRepo.findByJobLikeAndDeptno(job, deptno);
    }

    public List<Emp> findByJobContainingAndDeptno(String job, String deptno){
        return empRepo.findByJobContainingAndDeptno(job, deptno);
    }

    public List<Emp> findByEnameStartingWith(String ename){
        return empRepo.findByEnameStartingWith(ename);
    }

    public List<Emp> findBySalLessThanEqualOrderByHiredateDesc(String sal){
        return empRepo.findBySalLessThanEqualOrderByHiredateDesc(sal);
    }
}
