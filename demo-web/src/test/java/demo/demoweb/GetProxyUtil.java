package demo.demoweb;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class GetProxyUtil implements MethodInterceptor {
    private User user;

    public GetProxyUtil(User user) {
        this.user = user;
    }

    public UserInterface getProxy (){
         //生成代理对象
        Enhancer en = new Enhancer();
        //设置为谁代理
        en.setSuperclass(user.getClass());
        //代理要做什么
        en.setCallback(this);
        UserInterface userProxy =(UserInterface)en.create();
        return userProxy;
    }

    @Override
    public Object intercept(Object proxyobj, Method method, Object[] arg, MethodProxy methodProxy) throws Throwable {
        System.out.println("开启事务");
        Object res = methodProxy.invokeSuper(proxyobj, arg);
        System.out.println("关闭事务");
        return res;
    }
}
