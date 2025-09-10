package com.sist.ex0910_jpa1.store;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "category1_t")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category1JPO {
    @Id
    @GeneratedValue
    private int idx;
    private String cName;
    private String desc;
    private int status;

    @OneToMany
    @JoinColumn(name = "category1")
    private List<ProductJPO> pList;
}
