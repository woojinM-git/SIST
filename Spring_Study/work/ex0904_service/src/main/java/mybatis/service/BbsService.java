package mybatis.service;

import mybatis.dao.BbsDAO;
import mybatis.vo.BbsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BbsService implements BbsMapper{

    @Autowired
    private BbsDAO bbsDAO;

    @Override
    public BbsVO[] list(String bname, int begin, int end) {
        return bbsDAO.getList(bname, begin, end);
    }
}
