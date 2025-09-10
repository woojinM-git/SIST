package com.sist.ex0910_jpa2.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.ex0910_jpa2.service.EmpService;
import com.sist.ex0910_jpa2.store.Emp;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;



@RestController
@RequiredArgsConstructor
@RequestMapping("/emp")
public class EmpCon {
    
    private final EmpService empService;

    @RequestMapping("/all")
    public Object findAll() {
        return empService.findAll();
    }
    
    @RequestMapping("/getEmp")
    public Optional<Emp> getEmp(@RequestParam("empno") Long empno) {
        return empService.findbyEmpno(empno);
    }
    
    // 직종과 부서검색을 and 조건으로 검색하고자 한다.
    @RequestMapping("/and")
    public List<Emp> and(@RequestParam String job, @RequestParam String deptno) {
        return empService.findByJobAndDeptno(job, deptno);
    }

    // 직종은 Like를 사용해서 직종과 부서검색을 and 조건으로 검색하고자 한다.
    @RequestMapping("/andLike")
    public List<Emp> andLike(@RequestParam String job, @RequestParam String deptno) {
        return empService.findByJobLikeAndDeptno(job, deptno);
    }

    // 직종은 Like를 사용해서 직종과 부서검색을 and 조건으로 검색하고자 한다.
    @RequestMapping("/andContaining")
    public List<Emp> andContaining(@RequestParam String job, @RequestParam String deptno) {
        return empService.findByJobContainingAndDeptno(job, deptno);
    }
    
    // 이름이 인자로 받은 
    @RequestMapping("/startWith")
    public List<Emp> startWith(@RequestParam String ename) {
        return empService.findByEnameStartingWith(ename);
    }
    
    @RequestMapping("/sal")
    public List<Emp> sal(@RequestParam String sal) {
        return empService.findBySalLessThanEqualOrderByHiredateDesc(sal);
    }
    

}
