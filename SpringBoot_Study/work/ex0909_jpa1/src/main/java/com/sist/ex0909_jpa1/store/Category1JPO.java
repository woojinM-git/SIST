package com.sist.ex0909_jpa1.store;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "category1_t")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category1JPO {
    
    @Id
    @GeneratedValue
    private int idx;
    private String cName;
    private String desc;
    private int status;
}
