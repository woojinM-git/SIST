package mybatis.dao;

import mybatis.vo.BbsVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BbsDAO {

    @Autowired
    private SqlSessionTemplate ss;

    // 총 게시물의 수를 반환
    public int getTotalCount(String bname){
        return ss.selectOne("bbs.totalCount", bname);
    }
    
    // 목록 반환
    public BbsVO[] getList(String bname, int begin, int end){
        BbsVO[] ar = null;

        Map<String, Object> map = new HashMap<>();
        map.put("bname", bname);
        map.put("begin", begin);
        map.put("end", end);
//        System.out.println(map);

        List<BbsVO> list = ss.selectList("bbs.list", map);
//        System.out.println("test2");
        if(list != null && list.size() > 0){
            ar = new BbsVO[list.size()];
            list.toArray(ar); // list에 있는 모든 항목들을 배열 ar에 복사
        }
        return ar;
    }
    
    // 저장
    public int add(BbsVO vo){
        int cnt = 0;
        return ss.insert("bbs.add", vo);
    }

    // 기본키를 인자로 하여 게시물 가져오기
    public BbsVO getBbs(String b_idx){
        BbsVO vo = null;
        return ss.selectOne("bbs.getBbs", b_idx);
    }

    // 수정
    public int correct(String subject, String content, String b_idx){
        System.out.println("BbsDAO.correct() : " + subject + " " + content + " " + b_idx);
        BbsVO vo = new BbsVO();
        vo.setSubject(subject);
        vo.setContent(content);
        vo.setB_idx(b_idx);

        return ss.delete("bbs.correct", vo);
    }
    
    // 삭제
    public int delBbs(String b_idx){
        return ss.delete("bbs.del", b_idx);
    }

    // 조회수 증가
    public int hit(String b_idx){
        return ss.delete("bbs.hit", b_idx);
    }
}
