package mybatis.dao;

import mybatis.vo.EmpVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmpDAO {
    // 톰켓이 구동될 때 이미 생성된 SqlSessionTemplate이
    // 자동으로 ss에 저장되도록 한다.(autowire)
//    @Autowired 이거 오류임
    private SqlSessionTemplate ss;

    public SqlSessionTemplate getSs() {
        return ss;
    }

    public void setSs(SqlSessionTemplate ss) {
        this.ss = ss;
    }

    public EmpVO[] getTotal(){
        List<EmpVO> list = ss.selectList("emp.total");
        EmpVO[] ar = null;
        if(list != null) {
            ar = new EmpVO[list.size()];
            list.toArray(ar);
        }
//        ss.close(); 여기는 닫으면 오류임
        return ar;
    }
    
}
