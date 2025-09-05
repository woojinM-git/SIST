package com.sist.ex0905_emp.mybatis.service;

import com.sist.ex0905_emp.mybatis.mapper.EmpMapper;
import com.sist.ex0905_emp.mybatis.vo.EmpVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpService {

    // 필요한 맵퍼(인터페이스)들 지정
    @Autowired
    private EmpMapper empMapper;
    
    // 컨트롤러에서 필요로 하는 메서드 정의
    public EmpVO[] total() {
        EmpVO[] ar = null;
        List<EmpVO> list = empMapper.total();
        if(list != null && !list.isEmpty()){
            ar = new EmpVO[list.size()];
            list.toArray(ar);
        }
        return ar;
    }
}
