package mybatis.dao;

import mybatis.service.fService;
import mybatis.vo.EmpVO;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class EmpDAO {
    // 사원들을 모두 목록으로 반환(EmpVO[] 또는 List<EmpVO>)하는 기능
    public static List<EmpVO> getAll() {
        // 팩토리를 통해 SqlSession을 얻어내야 한다.
        SqlSession ss = fService.getFactory().openSession();
        List<EmpVO> list = ss.selectList("emp.all");
        ss.close();
        return list;
    }

    public static int addEmp(String empno, String ename, String job, String hiredate){
        // emp.add라는 맵퍼를 호출하기 위해 EmpVO가 필요합니다.
        EmpVO vo = new EmpVO();
        // 인자로 받은 empno, ename, job, hiredate를 생성한 vo에 저장
        vo.setEmpno(empno);
        vo.setEname(ename);
        vo.setJob(job);
        vo.setHiredate(hiredate);

        SqlSession ss = fService.getFactory().openSession();
        int cnt = ss.insert("emp.add", vo);

        if(cnt > 0)
            ss.commit();
        else
            ss.rollback();

        ss.close();
        return cnt;

    }
}
