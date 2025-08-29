package spring.input;

import org.springframework.web.multipart.MultipartFile;

public class ImgVO {
    // 파라미터 이름과 동일한 이름으로 멤버변수를 선언
    private MultipartFile upload;

    public MultipartFile getUpload() {
        return upload;
    }

    public void setUpload(MultipartFile upload) {
        this.upload = upload;
    }
}
