package pm.client;

import pm.vo.DeptVO;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import pm.vo.LocVO;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class Main1 {
    public static void main(String[] args) throws IOException {
        Reader r = Resources.getResourceAsReader("pm/config/conf.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(r);
        r.close();
        //-------------------------------------
        SqlSession ss= factory.openSession();
        List<LocVO> list = ss.selectList("loc.all");
        for(LocVO vo : list) {
            System.out.printf("%s, %s, (%d)\r\n", vo.getLoc_code(), vo.getCity(), vo.getList().size());
            if (vo.getList() != null){
                for(DeptVO evo : vo.getList()){
                    System.out.printf("\t -%s, %s, %s\r\n", evo.getDeptno(), evo.getDname(), evo.getLoc_code());
                }
            }
        }

        ss.close();
    }
}
