package mybatis.dao;

import mybatis.vo.MemoVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component // 이걸 하지 않으면 Spring이 읽어들이지 못함
public class MemoDAO {
    // 톰켓이 구동될 때 리스터에 의해 생성된 SqlSessionTemplate이
    // 자동으로 멤버변수 ss에 저장되도록 하자!
    @Autowired
    private SqlSessionTemplate ss;

    public MemoVO[] getTotal() {
        MemoVO[] ar = null;
        List<MemoVO> list = ss.selectList("memo.total");
        if(list != null){
            ar = new MemoVO[list.size()];
            list.toArray(ar);
        }

        return ar;
    }

    // 저장
    public int add(MemoVO vo){
        return ss.insert("memo.add", vo);
    }
}
