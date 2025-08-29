package mybatis.dao;

import mybatis.vo.DataVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataDAO {
    @Autowired
    private SqlSessionTemplate ss;

    // 저장 - 컨트롤객체에서 호출
    public int add(DataVO vo) {
        return ss.insert("data.add", vo);
    }
}
