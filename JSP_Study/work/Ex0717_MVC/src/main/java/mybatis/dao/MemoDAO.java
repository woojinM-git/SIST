package mybatis.dao;

import mybatis.service.fService;
import mybatis.vo.MemoVO;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class MemoDAO {

    public static MemoVO[] getAll(){
        MemoVO[] ar = null;
        SqlSession ss = fService.getFactory().openSession();

        List<MemoVO> list = ss.selectList("memo.all");
        if(list != null && !list.isEmpty()){
            ar = new MemoVO[list.size()];
            list.toArray(ar);
        }
        ss.close();
        return ar;
    }
}
