package mybatis.dao;

import mybatis.servise.fService;
import mybatis.vo.EmpVO;
import org.apache.ibatis.session.SqlSession;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmpDAO {

    public static EmpVO[] getAll(){
        SqlSession ss = fService.getFactory().openSession();
        EmpVO[] ar = null;
        List<EmpVO> list = ss.selectList("emp.all");
        if(list != null && !list.isEmpty()){
            ar = new EmpVO[list.size()];
            list.toArray(ar);
        }
        ss.close();
        return ar;
    }

    public static int add(EmpVO vo){
        int cnt = 0;
        SqlSession ss = fService.getFactory().openSession();
        cnt = ss.insert("emp.add", vo);
        if(cnt > 0)
            ss.commit();
        else
            ss.rollback();
        ss.close();

        return cnt;
    }

    public static EmpVO[] search(String searchType, String searchValue){
        SqlSession ss = fService.getFactory().openSession();

        EmpVO[] ar = null;
        Map<String, String> map = new HashMap<>(); // 상속받아서
        map.put("searchType", searchType);
        map.put("searchValue", searchValue);

        List<EmpVO> list = ss.selectList("emp.search", map);
        if(list != null && !list.isEmpty()){
            ar = new EmpVO[list.size()];
            list.toArray(ar);
        }
        return ar;
    }
}
