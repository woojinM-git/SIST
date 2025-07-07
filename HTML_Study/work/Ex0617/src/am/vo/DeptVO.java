package am.vo;

import java.util.List;

public class DeptVO {
    private String deptno, dname, loc_code;

    private List<EmpVO> list;

    public List<EmpVO> getList() {
        return list;
    }

    public void setList(List<EmpVO> list) {
        this.list = list;
    }

    public String getDeptno() {
        return deptno;
    }

    public void setDeptno(String deptno) {
        this.deptno = deptno;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getLoc_code() {
        return loc_code;
    }

    public void setLoc_code(String loc_code) {
        this.loc_code = loc_code;
    }
}
