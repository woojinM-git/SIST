package ex1.vo;

public class Test2VO {
    private String str;
    private int value;

    public Test2VO(String str) {
        this.str = str;
        System.out.println("Create Test2VO");
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
