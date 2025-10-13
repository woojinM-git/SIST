package com.sist.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sist.backend.store.Dept;

@Repository
public interface DeptRepository extends JpaRepository<Dept, Long>{
    Optional<Dept> findByDeptno(Long deptno);
}