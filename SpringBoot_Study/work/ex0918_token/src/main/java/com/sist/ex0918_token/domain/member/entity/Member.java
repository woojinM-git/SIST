package com.sist.ex0918_token.domain.member.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sist.ex0918_token.global.jpa.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class Member extends BaseEntity {
    private String mid, mname;

    // 비밀번호는 외부로 가는 것이 보안상
    // 좋지 않기 때문에 JSON으로 변환을 하지 못하게 설정
    // @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonIgnore
    private String mpwd;
    private String accessToken;

    @Column(name = "refresh_token", length = 1024)
    private String refreshToken;
}
