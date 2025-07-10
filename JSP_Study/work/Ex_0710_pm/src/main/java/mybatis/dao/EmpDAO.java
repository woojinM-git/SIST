package mybatis.dao;

import mybatis.vo.EmpVO;
import mybatis.vo.MemVO;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmpDAO {
    private static SqlSessionFactory factory;

    // static 초기화 - 가장 빠르게 인식되어 1번 움직여서 static변수들을 초기화한다.
    static {
        try {
            // MyBatis 준비 ---------------------------------------
            Reader r = Resources.getResourceAsReader("mybatis/config/conf.xml");
            factory = new SqlSessionFactoryBuilder().build(r);
            r.close();
            //-------------------------------------------
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // 전체보기 기능
    public static EmpVO[] getAll() {
        EmpVO[] ar = null;
        SqlSession ss = factory.openSession();

        List<EmpVO> list = ss.selectList("emp.all");
        // list의 size 크기만큼 배열의 크기를 지정하자
        ar = new EmpVO[list.size()];
        /*
        int i = 0;
        for(EmpVO : list){
            ar[i] = vo;
            i++;
        }
         */
        ss.close();
        list.toArray(ar); // list에 있는 모든 요소가 ar에 복사
        return ar;
    }

    // 사번 검색
    public static EmpVO getEmp(String empno){
        SqlSession ss = factory.openSession();
        EmpVO vo = ss.selectOne("emp.get_emp", empno);
        ss.close();
        return vo;
    }
    
    // 동적쿼리를 사용한 검색기능
    public static EmpVO[] search(String searchType, String searchValue) {
        EmpVO[] ar = null;

        // 받은 인자를 emp.search라는 SQL 문에 전달하기 위해
        // Map 구조로 만들어야 한다.
        Map<String, String> map = new HashMap<>();
        map.put("searchType", searchType);
        map.put("searchValue", searchValue);

        SqlSession ss = factory.openSession();
        List<EmpVO> list = ss.selectList("emp.search", map);

        if(list != null) {
            ar = new EmpVO[list.size()];
            // list에 있는 모든 데이터를 ar에 복사한다.
            list.toArray(ar);
        }
        ss.close();
        return ar;
    }

    // 로그인 하는 함수
    public static MemVO login(String id, String pw){
        Map<String, String> map = new HashMap<>(); // 해시맵은 항상 동일
        map.put("id", id);
        map.put("pw", pw);
        SqlSession ss = factory.openSession();
        MemVO vo = ss.selectOne("mem.login", map);
        ss.close();
        return vo;
    }
    
}
