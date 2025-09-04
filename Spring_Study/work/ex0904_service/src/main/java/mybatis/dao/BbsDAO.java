package mybatis.dao;

import mybatis.vo.BbsVO;
import org.apache.ibatis.session.SqlSession;
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
    public  BbsVO[] getList(String bname, int begin, int end){
        BbsVO[] ar = null;

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("bname", bname);
        map.put("begin", begin);
        map.put("end", end);


        List<BbsVO> list = ss.selectList("bbs.list", map);
        if(list != null && list.size() > 0){
            ar = new BbsVO[list.size()];
            list.toArray(ar);// list에 있는 모든 항목들을 배열 ar에 복사한다.
        }

        return ar;
    }

    // 저장
    public int add(BbsVO vo){
        return ss.insert("bbs.add", vo);

    }

    // 기본키를 인자로 하여 게시물 가져오기
    public  BbsVO getBbs(String b_idx){
        return ss.selectOne("bbs.getBbs", b_idx);

    }



    // 수정
    public int edit(String b_idx, String title, String content,
                   String fname, String oname, String ip){
        Map<String, String> map = new HashMap<>();
        map.put("b_idx", b_idx);
        map.put("title", title);
        map.put("content", content);
        if(fname != null){
            map.put("fname",fname);
            map.put("oname",oname);
        }
        map.put("ip",ip);

        return ss.update("bbs.edit", map);

    }


    // 삭제
    public int delBbs(String b_idx){
        return ss.update("bbs.del", b_idx);

    }

    // 조회수 증가
    public int hit(String b_idx){
        return ss.update("bbs.hit", b_idx);

    }


}
