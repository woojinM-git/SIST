package jsonEx.input;

import jsonEx.output.EmpVO;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmpDAO {

    @Autowired
    private SqlSessionTemplate ss;

    public EmpVO[] getList(){
        EmpVO[] ar = null;
        List<EmpVO> list = ss.selectList("emp.list");
        if(list != null){
            ar = new EmpVO[list.size()];
            list.toArray(ar);
        }
        return ar;
    }
}
