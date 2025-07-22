package bbs.dao;

import mybatis.service.fService;
import mybatis.vo.BbsVO;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BbsDAO {
    // 총 게시물의 수를 반환
    public static int getTotalCount(String bname){
        SqlSession ss = fService.getFactory().openSession();
        int cnt = ss.selectOne("bbs.totalCount", bname);
        ss.close();
        return cnt;
    }
    
    // 목록 반환
    public static BbsVO[] getList(String bname, int begin, int end){
        BbsVO[] ar = null;

        Map<String, Object> map = new HashMap<>();
        map.put("bname", bname);
        map.put("begin", begin);
        map.put("end", end);
//        System.out.println(map);

        SqlSession ss = fService.getFactory().openSession();
        List<BbsVO> list = ss.selectList("bbs.list", map);
//        System.out.println("test2");
        if(list != null && list.size() > 0){
            ar = new BbsVO[list.size()];
            list.toArray(ar); // list에 있는 모든 항목들을 배열 ar에 복사
        }
        ss.close();
        return ar;
    }
    
    // 저장
    
    // 수정
    
    // 삭제
}
