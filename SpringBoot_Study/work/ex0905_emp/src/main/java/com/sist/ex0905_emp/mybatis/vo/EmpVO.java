package com.sist.ex0905_emp.mybatis.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter // getter 생성
@Setter // setter 생성 
@NoArgsConstructor // 인자가 없는 기본생성자 생성
@AllArgsConstructor // VO에 존재하는 모든 멤버변수를 인자로 받는 생성자 생성
public class EmpVO {
    private String empno, ename, job, mgr, hiredate, sal, comm, deptno;
}
