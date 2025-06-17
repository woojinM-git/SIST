package pm.vo;

import java.util.List;

public class LocVO {
    private String loc_code, city;

    List<DeptVO> list;

    public List<DeptVO> getList() {
        return list;
    }

    public void setList(List<DeptVO> list) {
        this.list = list;
    }

    public String getLoc_code() {
        return loc_code;
    }

    public void setLoc_code(String loc_code) {
        this.loc_code = loc_code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
