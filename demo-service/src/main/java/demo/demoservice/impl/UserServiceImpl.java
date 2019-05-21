package demo.demoservice.impl;

import demo.ErrorEnum;
import demo.Result;
import demo.demodao.mapper.UserMapper;
import demo.demodao.pojo.User;
import demo.demodao.pojo.UserExample;
import demo.demoservice.UserService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@EnableCaching
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

    @Override
    public Result queryUser(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andNameEqualTo(user.getName()).andAddressEqualTo(user.getAddress());
        List<User> users = userMapper.selectByExample(userExample);
        if(users.size() == 1){
            return Result.isSuccess(users.get(0));
        }
        return Result.isError(ErrorEnum.USER_NOT_EXIST.name(),ErrorEnum.USER_NOT_EXIST.getErrorMsg());
    }

    @Override
    @CacheEvict(value = "users",key="'user'")
    public Result<String> addUser(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andNameEqualTo(user.getName()).andAddressEqualTo(user.getAddress());
        List<User> users = userMapper.selectByExample(userExample);
        if(users.size()>0){
            return Result.isError(ErrorEnum.USER_EXIST.name(),ErrorEnum.USER_EXIST.getErrorMsg());
        }
        int res = userMapper.insert(user);
        if(res<=0){
            return Result.isError(ErrorEnum.ID_IS_NULL.name(),ErrorEnum.ID_IS_NULL.getErrorMsg());
        }
        return Result.isSuccess(user.getName());
    }

    @Cacheable(value = "users",key="'user'")
    @Override
    public Result<User> getUsers() {
        UserExample userExample = new UserExample();
        try{
            List<User> users = userMapper.selectByExample(userExample);
            if(users.size() > 0){
                return Result.isSuccess(users);
            }
        }catch(Exception e){
            return Result.isError("SYSTEM_ERROR","系统异常，请稍后重试");
        }
        return Result.isError("DATA_IS_NULL","暂没有数据可供查询,请添加后重试");
    }


    @Override
    @CacheEvict(value = "users",key="'user'")
    public Result delUser(Long id) {
        try{
            int i = userMapper.deleteByPrimaryKey(id);
            if(i>0){
                return Result.isSuccess(null);
            }
        }catch(Exception e){
            return Result.isError("SYSTEM_ERROR","系统异常");
        }
        return Result.isError("DEL_USER_ERROR","数据删除失败");
    }

    @Override
    @CacheEvict(value = "users",key="'user'")
    public Result updateUser(User user) {
        try{
            userMapper.updateByPrimaryKey(user);
            return Result.isSuccess(null);
        }catch (Exception e){
            return Result.isError("SYSTEM_ERROR","系统异常");
        }
    }

    @Override
    public Result getUserById(Long id) {
        try{
            User user = userMapper.selectByPrimaryKey(id);
            return Result.isSuccess(user);
        }catch(Exception e){
            return Result.isError("SYSTEM_ERROR","系统异常");
        }
    }


}
