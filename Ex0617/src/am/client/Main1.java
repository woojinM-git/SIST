package am.client;

import am.vo.EmpVO;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class Main1 {
    public static void main(String[] args) throws IOException {
        Reader r = Resources.getResourceAsReader(
                "am/config/conf.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(r);
        r.close();
        //--------------------------------------------

        SqlSession ss = factory.openSession();
        List<EmpVO> list = ss.selectList("emp.all");

        for(EmpVO vo : list){
            System.out.printf("%s, %s, %s", vo.getEmpno(), vo.getEname(), vo.getJob());
            if(vo.getDvo() != null)
                System.out.printf(", %s", vo.getDvo().getDname());
            System.out.println();
        }
        ss.close();
    }
}
