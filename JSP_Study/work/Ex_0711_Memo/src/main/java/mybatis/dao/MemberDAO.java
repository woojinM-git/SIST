package mybatis.dao;

import mybatis.service.fService;
import mybatis.vo.MemVO;
import mybatis.vo.MemoVO;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.Map;

public class MemberDAO {

    public static MemVO login(String id, String pw){
        Map<String, String> map = new HashMap<>();
        map.put("u_id", id);
        map.put("u_pw", pw);

        // SQL문을 호출하기위해 필요한 객체 SqlSession 얻기
        SqlSession ss = fService.getFactory().openSession();
        MemVO mvo = ss.selectOne("member.login", map);
        ss.close();
        return mvo;
    }

}
