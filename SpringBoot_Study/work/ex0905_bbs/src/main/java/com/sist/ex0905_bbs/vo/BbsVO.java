package com.sist.ex0905_bbs.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BbsVO {
    private String b_idx, subject, writer, content, file_name, ori_name, pwd, write_date, ip, hit, bname, status;
}
