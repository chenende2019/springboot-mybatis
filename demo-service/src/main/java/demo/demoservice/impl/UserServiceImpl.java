package demo.demoservice.impl;

import demo.demodao.mapper.UserMapper;
import demo.demodao.pojo.User;
import demo.demoservice.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public int saveUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    public User queryUser(String id) {
        if(!StringUtils.isEmpty(id)){
            long i = Long.parseLong(id);
            return userMapper.selectByPrimaryKey(i);
        }
        return null;
    }
}
