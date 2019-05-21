package demo.demoweb;

public class Demo {
    public static void main(String[] args) {
        User user = new User();
        GetProxyUtil getProxyUtil = new GetProxyUtil(user);
        UserInterface proxy = getProxyUtil.getProxy();
        proxy.eat();
    }

}
