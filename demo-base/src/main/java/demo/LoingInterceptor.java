package demo;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class LoingInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setHeader("Access-Control-Allow-Credentials", "true");
        Cookie[] cookies = request.getCookies();
        if(null != cookies){
            for(Cookie cookie: cookies){
                if(cookie.getName() == "userName"){
                    String value = cookie.getValue();
                    if(value.equals("8")){
                        PrintWriter writer = response.getWriter();
                        writer.print("请先完成登录");
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
