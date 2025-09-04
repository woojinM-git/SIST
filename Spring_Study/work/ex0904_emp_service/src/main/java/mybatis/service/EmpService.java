package mybatis.service;

import mybatis.dao.EmpDAO;
import mybatis.vo.EmpVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmpService implements EmpMapper{
    @Autowired
    private EmpDAO empDAO;

    @Override
    public EmpVO[] all() {
        return empDAO.getAll();
    }

    @Override
    public int add(EmpVO vo) {
        return empDAO.addEmp(vo);
    }

    @Override
    public EmpVO[] search(String type, String value) {
        //인자로 받은 type과 value를 Map구조에 searchType과 searchValue라는
        //이름으로 저장하자!
        Map<String, String> map = new HashMap<>();
        map.put("searchType", type);
        map.put("searchValue", value);

        return empDAO.search(map);
    }

}
