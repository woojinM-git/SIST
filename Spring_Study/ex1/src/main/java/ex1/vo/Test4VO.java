package ex1.vo;

public class Test4VO {
    private String msg;
    private Test2VO t2;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Test2VO getT2() {
        return t2;
    }

    public void setT2(Test2VO t2) {
        System.out.println(t2.getStr()+": Test4VO의 setT2호출");
        this.t2 = t2;
    }
}
