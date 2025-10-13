package com.sist.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sist.backend.store.Emp;

@Repository
public interface EmpRepository extends JpaRepository<Emp, Long> {
    Optional<Emp> findByEmpno(Long empno);
    List<Emp> findByDeptno(String deptno);
    List<Emp> findByJobAndDeptno(String job, String deptno);

    @Query(value = "select * from emp where job like concat('%', ?1, '%') and deptno=:?2", nativeQuery = true)
    List<Emp> findByJobListAndDeptno(String job, String deptno);

    List<Emp> findByJobContainingAndDeptno(String job, String deptno);
}
