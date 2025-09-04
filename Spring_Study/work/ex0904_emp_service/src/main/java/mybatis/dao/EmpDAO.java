package mybatis.dao;

import mybatis.vo.EmpVO;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class EmpDAO {

    @Autowired
    private SqlSessionTemplate ss;

    //사원들을 목록으로 반환(EmpVO[] 또는 List<EmpVO>)하는 기능
    public EmpVO[] getAll(){
        // 팩토리를 통해 SqlSession을 얻어야 한다.
        List<EmpVO> list = ss.selectList("emp.all");
        EmpVO[] ar = null;
        if(list != null && list.size() > 0){
            ar = new EmpVO[list.size()];
            list.toArray(ar);
        }
        return ar;
    }


    //검색기능
    public EmpVO[] search(Map<String,String> map){
        // 팩토리를 통해 SqlSession을 얻어야 한다.
        List<EmpVO> list = ss.selectList("emp.search", map);
        EmpVO[] ar = null;
        if(list != null && list.size() > 0){
            ar = new EmpVO[list.size()];
            list.toArray(ar);
        }
        return ar;
    }

    public int addEmp(EmpVO vo){
        // emp.add라는 맵퍼를 호출하기 위해 EmpVO가 필요합니다.
        return ss.insert("emp.add", vo);

    }
}
