package demo.demoservice;

import demo.Result;
import demo.demodao.pojo.User;

public interface UserService {
    public int saveUser(User user);
    public User queryUser(String id);
    public Result queryUser(User user);
    public Result<String> addUser(User user);
    public Result<User> getUsers();
    public Result delUser(Long id);
    public Result updateUser(User user);
    public Result getUserById(Long id);
}
