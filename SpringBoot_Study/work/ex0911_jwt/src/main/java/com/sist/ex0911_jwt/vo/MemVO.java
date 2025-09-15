package com.sist.ex0911_jwt.vo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "member_t")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemVO {

    @Id
    private String mIdx;
    private String mName, mId, mPw;
    
}
