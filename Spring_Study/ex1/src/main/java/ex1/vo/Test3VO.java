package ex1.vo;

public class Test3VO {
    private String name;
    private int age;
    private boolean isMember;

    public Test3VO(String name, int age, boolean isMember) {
        this.name = name;
        this.age = age;
        this.isMember = isMember;
        System.out.println("Create Test3VO");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isMember() {
        return isMember;
    }

    public void setMember(boolean member) {
        isMember = member;
    }
}
