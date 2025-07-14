package mybatis.dao;

import mybatis.service.fService;
import mybatis.vo.MemoVO;
import org.apache.ibatis.session.SqlSession;

import java.util.List;


public class MemoDAO {

    public static List<MemoVO> getAll() {
        SqlSession ss = fService.getFactory().openSession();
        List<MemoVO> list = ss.selectList("memo.all");
        ss.close();
        return list;
    }

    public static int addMemo(String writer, String content, String ip) {

        System.out.println("test2");
        MemoVO mvo = new MemoVO();

        mvo.setWriter(writer);
        mvo.setContent(content);
        mvo.setIp(ip);

        SqlSession ss = fService.getFactory().openSession(); // openSession(true) 를 하면 자동 커밋인데 좋은 방법이 아님
        int cnt = ss.insert("memo.add", mvo); // 값이 변경된 레코드의 수 -> 2가 반환되면 레코드가 2줄 변경된거임

        if(cnt > 0) {
            ss.commit();
        }
        else {
            ss.rollback();
        }

        ss.close();
        return cnt; // 값이 저장되면 1이 넘어감
    }

}
