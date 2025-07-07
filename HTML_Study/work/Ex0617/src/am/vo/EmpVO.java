package am.vo;

public class EmpVO {
    private String empno, ename, job, hiredate;
    private DeptVO dvo; // 현재 사원의 부서정보

    public String getEmpno() {
        return empno;
    }

    public void setEmpno(String empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getHiredate() {
        return hiredate;
    }

    public void setHiredate(String hiredate) {
        this.hiredate = hiredate;
    }

    public DeptVO getDvo() {
        return dvo;
    }

    public void setDvo(DeptVO dvo) {
        this.dvo = dvo;
    }
}
