package com.sist.ex0918_token.domain.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.sist.ex0918_token.domain.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{

    Optional<Member> findByMid(String mid);

    // refreshToken을 저장할 수 있는 기능을 Repositoty에 정의해야 함
    Optional<Member> findByRefreshToken(String refreshToken);

    @Modifying
    @Transactional
    @Query("UPDATE Member m SET m.refreshToken = :refreshToken WHERE m.b_idx = :b_idx")
    void updateRefreshToken(@Param("b_idx") Long b_idx, @Param("refreshToken")
    String refreshToken);
}
