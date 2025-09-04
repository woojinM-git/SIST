package com.sist.ex0903;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URL;
import data.vo.AreaVO;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Controller
public class APIControl {

    @Autowired
    private HttpSession session;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ServletContext application;
    
    // 처음 /api에 들어올 때 수행 모든 값 가져오기
    @RequestMapping("/")
    public String index(Model model, String code, String cPage, String strartDate) {
        // 스프링이 Model객체를 request에 있는 것을 가져와서 인자로 넣어준다
        ModelAndView mv = new ModelAndView();

        // 공공데이터 openAPI를 호출하는 경로를 준비
        // https://apis.data.go.kr/B551011/KorService2/searchFestival2?serviceKey=LhiCoBsyuXwZI61qgC06QKymE3tvg4Xa0wwEJNFu3F1E9Gyyb0B7EMvfUHLXS0xt5Sls1sEu0iLjj4RZeAJMtA%3D%3D&MobileApp=AppTest&MobileOS=ETC&pageNo=1&numOfRows=100&eventStartDate=20250501&arrange=C&areaCode=&_type=xml
        StringBuffer sb = new StringBuffer("https://apis.data.go.kr/B551011/KorService2/searchFestival2?");
        String key = "LhiCoBsyuXwZI61qgC06QKymE3tvg4Xa0wwEJNFu3F1E9Gyyb0B7EMvfUHLXS0xt5Sls1sEu0iLjj4RZeAJMtA%3D%3D";
        String areaCode = null;

        if (code == null)
            areaCode = "1";
        else
            areaCode = code;

        if (cPage == null)
            cPage = "1";

        if (strartDate == null) {
            // "20250725" 이런 형식을 얻기 위해 형식객체가 필요함
            SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
            Calendar now = Calendar.getInstance(); // 현재 날짜가 구해

            strartDate = sf.format(now.getTime());
        }

        sb.append("serviceKey=");
        sb.append(key);
        sb.append("&MobileApp=AppTest");
        sb.append("&MobileOS=ETC");
        sb.append("&pageNo=");
        sb.append(cPage);
        sb.append("&numOfRows=100");
        sb.append("&eventStartDate=");
        sb.append(strartDate);
        sb.append("&arrange=C&areaCode=");
        sb.append(areaCode);
        sb.append("&_type=xml");

        try {
            // 브라우저 창에서 경로(URL)를 입력하고 요청하듯이 프로그램 상에서
            // 요청할 때는 URL객체를 만들어야 한다.
            URL url = new URL(sb.toString()); // 연결성을 가진거지 요청을 한것은 아님

            // 경로를 연결하는 객체
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

            // 응답 받을 데이터의 형식을 지정
            conn.setRequestProperty("Content-type", "application/xml");

            // 연결 - 요청
            conn.connect();

            // JDOM라이브러리에 있는 SAXBuilder를 통해 응답메세지를
            // XML문서화 시키기 위해 준비해야 한다.
            SAXBuilder builder = new SAXBuilder();

            // 응답되는 내용을 하나의 XML의 문서(Document)로 인식해야 한다.
            Document doc = builder.build(conn.getInputStream()); // xml 내용이 doc에 전부 담긴다. *********

            // 얻어낸 Document에서 root엘리먼트를 얻어낸다.
            Element root = doc.getRootElement();
//            System.out.println(root.getName()); // response

            // 루트안에 있는 body만 얻어낸다.
            Element body = root.getChild("body");

            // body안에 있는 items라는 요소를 얻어낸다.
            Element items = body.getChild("items");

            // items안에 존재하는 모든 item이라는 자식요소들을 얻어낸다.
            List<Element> item_list = items.getChildren("item");

            // item들을 JSP에서 표현하기 위해 배열로 변환하여 request에 저장하려 한다.
            AreaVO[] ar = new AreaVO[item_list.size()];
            int i = 0;
            for (Element item : item_list) {
                String addr1 = item.getChildText("addr1");
                String addr2 = item.getChildText("addr2");
                String firstimage = item.getChildText("firstimage");
                String firstimage2 = item.getChildText("firstimage2");
                String tel = item.getChildText("tel");
                String eventstartdate = item.getChildText("eventstartdate");
                String eventenddate = item.getChildText("eventenddate");
                String title = item.getChildText("title");
                String mapx = item.getChildText("mapx");
                String mapy = item.getChildText("mapy");
                AreaVO vo = new AreaVO(title, mapx, mapy, addr1, addr2, firstimage, firstimage2, tel, eventstartdate, eventenddate);
                ar[i++] = vo;
            } // for end

            // 배열에 모든 item들이 vo객체로 생성되어 저장된 상태다. 배열을
            // jsp에서 표현할 수 있도록 request에 저장하자
            model.addAttribute("ar", ar);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "index";
    }
}
