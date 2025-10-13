package com.sist.backend.store;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "dept") // Entity의 name 지정 -> 실제 존재하는 테이블 이름 넣기
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
// 위는 어노테이션을 사용할 때 Hibernate관련 필드를 직렬화에서 제외시킨다.
// 이는 양방향 관계에서 순환 참조 문제를 해결한다.
public class Dept {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 값이 바뀌자마자 바뀐 고유키를 받고싶다면 지정
    private Long deptno;
    private String dname;
    private int loc_code;

    // 양방향 관계 설정 - mappedBy는 관계의 주체가 되는 Dept에서
    // 참조되는 Emp의 속성(멤버변수)을 의미함
    // 주 테이블(부모)의 외래 키가 자식 엔티티(자식 테이블)에 존재 한다고 알고,
    // 자식 테이블의 외래 키를 기준으로 관계를 매핑한다.
    // 즉, Dept 엔티티는 부모로서 Emp 엔티티의 dept라는 멤버변수를 기준으로
    // 관계를 정의한다.
    @OneToMany(mappedBy = "dept", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"dept"}) // Dept 엔티티 기준으로 참조하는 Emp의 무한참조를 막는다
    private List<Emp> elist;
    // CasecadeType.ALL : 모든 작업(저장, 병합, 삭제 등)을 자식 객체에도 적용
    // FetchType.LAZY : 필요할 때 연관된 엔티티를 로딩하도록 지정
    //      예) Dept엔티티가 로딩될 때 Emp엔티티는 실제로 필요할 때까지 로딩 안한다.
    private int length = 1;

    public void setElist(List<Emp> elist) {
        this.elist = elist;   // elist 자체도 세팅
        this.length = elist != null ? elist.size() : 0;
    }
}
