package mybatis.dao;

import mybatis.service.FactoryService;
import mybatis.vo.MemVO;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;

public class MemberDAO {

    //login.jsp에서 호출하는 로그인 함수
    public static MemVO login(String id, String pw){
        HashMap<String, String> map = new HashMap<>();
        map.put("m_id",id);
        map.put("m_pw",pw);

        //sql문을 호출하기 위해 필요한 객체 SqlSession얻기
        SqlSession ss = FactoryService.getFactory().openSession();
        MemVO mvo = ss.selectOne("member.login", map);
        ss.close();

        return mvo;
    }

    public static int registry(MemVO mvo){
        SqlSession ss = FactoryService.getFactory().openSession();
        int cnt = ss.insert("member.add", mvo);
        if(cnt > 0)
            ss.commit();
        else
            ss.rollback();
        ss.close();

        return cnt;
    }

    //아이디를 인자로 받아서 아이디 사용여부를 확인하는 기능
    public static boolean idCheck(String m_id) {
        SqlSession ss = FactoryService.getFactory().openSession();
        MemVO vo = ss.selectOne("member.id_check", m_id);
        ss.close();
        return (vo == null)?true:false;
    }
}
