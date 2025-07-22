package bbs.control;

import bbs.action.Action;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

@WebServlet("/Controller")
public class Controller extends HttpServlet {

    // Properties 파일의 경로를 저장하자!
    private String myParam = "/WEB-INF/action.properties";

    private Map<String, Action> actionMap;

    public Controller(){
        actionMap = new HashMap<>();
    }

    @Override
    public void init() throws ServletException {

        ServletContext application = this.getServletContext();
        String realPath = application.getRealPath(myParam);

        Properties prop = new Properties();

        FileInputStream fis = null;
        try{    
            // action.properties파일과 연결되는 스트림 준비
            fis = new FileInputStream(realPath);

            prop.load(fis);

            fis.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        Iterator<Object> it = prop.keySet().iterator();
        while(it.hasNext()){
            String key = (String) it.next();

            String value = prop.getProperty(key); // "emp.action.IndexAction"

            try {
                Action obj = (Action) Class.forName(value).newInstance();
                actionMap.put(key, obj);
            }catch (Exception e){
                e.printStackTrace();
            }
        } // while의 끝
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 요청시 한글처리
        request.setCharacterEncoding("UTF-8");
        
        // type이라는 파라미터 받기
        String type = request.getParameter("type");
        
        // type이 전달되지 않아 null을 가지면 index로 초기화 하자
        if(type == null)
            type = "list";
        
        // type으로 받은 값이 actionMap의 key로 사용되고 있으므로
        // 원하는 객체를 얻어내자!
        Action action = actionMap.get(type);

        String viewPath = action.execute(request, response);

        // viewPath가 null이면 현재 컨트롤러를 sendRedirect로
        // 다시 호출되도록 한다
        if(viewPath == null)
            response.sendRedirect("Controller");
        else{
            // forward로 이동!
            RequestDispatcher disp = request.getRequestDispatcher(viewPath); // viewPath가 RequestDispatcher 를 무조건 지나가야함 
            disp.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
