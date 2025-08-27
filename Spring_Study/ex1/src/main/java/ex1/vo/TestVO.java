package ex1.vo;

public class TestVO {
    private String msg; // 외부에서는 property개념이 멤버변수다.

    public TestVO() { // 기본 생성자
        System.out.println("TestVO생성");
    }

    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
