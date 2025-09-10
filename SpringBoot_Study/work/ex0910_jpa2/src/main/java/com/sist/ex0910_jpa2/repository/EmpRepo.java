package com.sist.ex0910_jpa2.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sist.ex0910_jpa2.store.Emp;

@Repository
public interface EmpRepo extends JpaRepository<Emp, Long> {
    
    // 검색된 데이터가 없을 때 Null이 아닌 Optional.empty()를 반환한다.
    // 그러므로 값이 없을 때 보다 안전하게 처리된다.

    Optional<Emp> findByEmpno(Long empno); // 사번검색
    List<Emp> findByDeptno(String deptno); // 부서번호 검색

    List<Emp> findByJobAndDeptno(String job, String deptno); // 직종 And 부서 검색

    // @Query는 Spring Data JPA에서 메서드에 직접 SQL 문을 작성한다는 의미
    // nativeQuery = true는 JPQL이 아닌 순수 SQL문을 사용한다는 뜻 
    @Query(value = "select * from emp where job like concat('%', ?1 , '%') and deptno = :deptno", nativeQuery = true)
    List<Emp> findByJobLikeAndDeptno(String job, String deptno); // 직종 And 부서 검색

    List<Emp> findByJobContainingAndDeptno(String job, String deptno); // 직종 And 부서 검색
    List<Emp> findByEnameStartingWith(String ename); // 직종 And 부서 검색

    // 문제:
    // 급여가 3000이하인 (<=) 사원들의 정보를 입사일(hiredate)이 최근 순으로 출력하기 위한
    // repository의 함수를 정의하자
    
    List<Emp> findBySalLessThanEqualOrderByHiredateDesc(String sal);
}
