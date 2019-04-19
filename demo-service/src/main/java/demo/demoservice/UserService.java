package demo.demoservice;

import demo.demodao.pojo.User;

public interface UserService {
    public int saveUser(User user);
    public User queryUser(String id);
}
