package com.sist.ex0902_jdom;

import data.vo.MemberVO;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MemberControl {
    /*
        오픈API서비의 URL같은 경로가 멤버변수로 선언되어야 하지만
        우리는 내부에 있는 XML문서를 접근하여 마치 오픈 API에서 결과를
        받는것 처럼 가정하자
     */

    @Autowired
    private ServletContext application;

    MemberVO[] ar = null;

    @RequestMapping("t1")
    public ModelAndView test1() throws Exception{ // 예외처리 해야함 (builder.build 때문)
        ModelAndView mv = new ModelAndView();

        // 준비된 문서의 절대경로 지정하자!
        String realPath = application.getRealPath("/resources/pm/data/member.xml");

        // JDOM파서를 이용하여 로드(파싱)하자!
        SAXBuilder builder = new SAXBuilder();

        // 준비된 builder를 통해 결과인 xml자원을 문서화(Document) 시킨다.

        Document doc = builder.build(realPath);

        // 메모리상에 존재하는 xml 문서(Document 객체)의 루트를 얻어낸다.
        Element root = doc.getRootElement(); // members
//        System.out.println("ROOT: "+root.getName());

        // 루트의 자식들 중 태그명이 member인 자식들 모두 가져온다.
        List<Element> list = root.getChildren("member"); // 3
//        System.out.println("list.size: "+list.size());

        // 위에서 얻은 list를 배열로 만들어 보자
        ar = null;
        if(list != null && list.size() > 0){
            ar = new MemberVO[list.size()];
            int i = 0; // 배열의 index값으로 사용할 변수
            for(Element e : list){
                String name = e.getChildText("name");
                String email = e.getChildText("email");
                String phone = e.getChildText("phone");

                MemberVO mvo = new MemberVO();
                mvo.setName(name);
                mvo.setEmail(email);
                mvo.setPhone(phone);

                ar[i] = mvo;
                i++;
            }
        }
        mv.addObject("ar", ar);
        mv.setViewName("member");

        return mv;
    }

    @PostMapping("/search")
    @ResponseBody
    public Map<String, Object> search(String type, String value) {
        Map<String, Object> map = new HashMap<>();
        // 파라미터 잘 받았는지 확인
        System.out.println("type: " + type + ", value: " + value);

        // 멤버변수에 완성되어 있는 MemberVO[]의 값을 하나씩 빼 변수에 저장한다.

        // 각각의 Element들을 MemberVO로 생성해야함
        System.out.println(ar.length);
        if(ar != null && ar.length > 0){ // 멤버변수의 ar 값이 들어있을때만 수행
            for(int i = 0; i < ar.length; i++){
                String name = ar[i].getName();
                String email = ar[i].getEmail();
                String phone = ar[i].getPhone();

                // 배열에 저장할 MemberVO를 생성
                MemberVO m = new MemberVO();
                m.setName(name);
                m.setEmail(email);
                m.setPhone(phone);

                boolean chk = false;
                switch (type){ // 0, 1, 2
                    case "1":
                        if(email.contains(value)) // email에 searchValue의 값이 포함되었다면
                            chk = true;
                        break;
                    case "0":
                        if(name.contains(value)) // email에 searchValue의 값이 포함되었다면
                            chk = true;
                        break;
                    case "2":
                        if(phone.contains(value)) // email에 searchValue의 값이 포함되었다면
                            chk = true;
                        break;
                } // switch의 끝

                // chk가 true일때만 배열에 저장
                if(chk){
                    System.out.println(chk);
                    // switch를 지나서 통과한 ar[i]에 저장
                    ar[i] = m;
                }else {
                    // 이미 배열에 값이 담겨있어서 chk가 false인 경우에는 null을 보내야 의도대로 나옴
                    ar[i] = null;
                }
            } // for end
        }
        map.put("ar", ar);
        map.put("length", ar.length);

        return map;
    }
}
