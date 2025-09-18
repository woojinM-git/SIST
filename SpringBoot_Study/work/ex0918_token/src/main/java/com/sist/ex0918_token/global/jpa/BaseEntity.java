package com.sist.ex0918_token.global.jpa;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder // 빌더패턴을 구현하기 위한 코드 간결화
                // 생성자에서 상속받은 필드도 빌더에서 사용할 수 있다.
// 접근제한 protected에 대한 접근력을 두고 생성자를 만듬
@MappedSuperclass // 부모에 대한 테이블 매핑을 하지 않는다.
@EntityListeners(AuditingEntityListener.class) // 엔티티와 매핑된 테이블의
                    // 데이터를 조작 수정과 이벤트에 대한 감지를 담당함
@ToString // toString 함수를 생성
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 생성과 추가시
                                                        // 생성된 기본키를 받고자 함
    private Long b_idx;

    @CreatedDate
    private String write_date;
}
