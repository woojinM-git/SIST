package com.sist.ex0918_token.domain.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sist.ex0918_token.domain.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{
    Optional<Member> findByMid(String mid);
}
