package com.sist.ex0910_jpa1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sist.ex0910_jpa1.store.Category1JPO;

@Repository
public interface Category1Repository extends JpaRepository<Category1JPO, Integer> {
    
}
