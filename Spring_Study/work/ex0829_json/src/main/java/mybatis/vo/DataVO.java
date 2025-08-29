package mybatis.vo;

import org.springframework.web.multipart.MultipartFile;

public class DataVO {
    // 파라미터들과 이름이 같은 변수들 선언
    private String title, content, reg_date, ip;
    private MultipartFile sss;

    private String file_name, ori_name; // 저장할 테이블에 파일명들을 저장하는 컬럼이 있다면 같은이름으로 정의하자!

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getOri_name() {
        return ori_name;
    }

    public void setOri_name(String ori_name) {
        this.ori_name = ori_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReg_date() {
        return reg_date;
    }

    public void setReg_date(String reg_date) {
        this.reg_date = reg_date;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public MultipartFile getSss() {
        return sss;
    }

    public void setSss(MultipartFile sss) {
        this.sss = sss;
    }
}
