package com.sist.backend.store;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "emp") // Entity의 name 지정 -> 실제 존재하는 테이블 이름 넣기
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Emp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empno;
    private String ename, job, sal;
    private LocalDate hiredate;

    @Column(name = "deptno", insertable = false, updatable = false)
    private String deptno; // 추가 / 편집 시 자동으로 값을 지정 또는 수정 되지 못한다.

    @ManyToOne(fetch = FetchType.LAZY) // 필요할 때 까지 로드되지 않는다.
    @JoinColumn(name = "deptno", referencedColumnName = "deptno")
    @JsonIgnoreProperties({"elist"}) // 반대로 Emp에서는 Dept의 elist를 무시해야 참조가 안돼 배열 중복이 안됌
    private Dept dept;
}
