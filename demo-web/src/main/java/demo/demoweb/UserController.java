package demo.demoweb;

import com.alibaba.fastjson.JSON;
import demo.ErrorEnum;
import demo.Result;
import demo.UserException;
import demo.demodao.pojo.User;
import demo.demoservice.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/demo")
//@PropertySource("classpath:config/application-test.properties")
public class UserController {
    @Resource
    private UserService userService;
    @Value("${errorCode}")
    private String errorCode;
    @Value("${errorMsg}")
    private String errorMsg;

    @RequestMapping("/add")
    @ResponseBody
    public Result add(@Valid User user1) {
        String id = "2";
        if (StringUtils.isEmpty(id)) {
            return Result.isError(errorCode, errorMsg);
        }
        User user = userService.queryUser(id);
        return Result.isSuccess(user);
    }

    @CrossOrigin
    @RequestMapping("/login")
    @ResponseBody
    public Result login(User user, HttpServletResponse respone) {
        if(StringUtils.isEmpty(user.getName()) ||
            StringUtils.isEmpty(user.getAddress())){
            throw new UserException(ErrorEnum.PARAM_ERROR);
        }
        Cookie cookie = new Cookie("userName", "zhangsan");
        Cookie cookie2 = new Cookie("address", "HAINAN");
        cookie.setPath("/demo");
        respone.addCookie(cookie);
        respone.addCookie(cookie2);

        return userService.queryUser(user);
    }

    @CrossOrigin
    @RequestMapping("/register")
    @ResponseBody
    public Result<String> register(@Validated User user , BindingResult results) {
        if (results.hasErrors()) {
            String errorMsg = results.getFieldError().getDefaultMessage();
            return Result.isError("PARAM_ERROR",errorMsg);
        }
        if(user.getId()!=null){
            return updateUser(user,results);
        }
        return userService.addUser(user);
    }

    @CrossOrigin
    @RequestMapping("/getUsers")
    @ResponseBody
    public Result<User> getUsers() {
        return userService.getUsers();
    }

    @CrossOrigin
    @RequestMapping("/delUser")
    @ResponseBody
    public Result<User> delUser(String id) {
        Long userId = null;
        if(!StringUtils.isEmpty(id)){
            userId = Long.parseLong(id);
        }
        return userService.delUser(userId);
    }

    @CrossOrigin
    @RequestMapping("/updateUser")
    @ResponseBody
    public Result updateUser(@Valid User user,BindingResult result) {
        if(user.getId()==null){
            throw new UserException(ErrorEnum.ID_IS_NULL);
        }
        if(result.hasErrors()){
            return Result.isError(ErrorEnum.PARAM_ERROR.name(),result.getFieldError().getDefaultMessage());
        }
        return userService.updateUser(user);
    }

    @CrossOrigin
    @RequestMapping("/getUser")
    @ResponseBody
    public Result<User> getUser(String id) {
        List<Object> objects = new ArrayList<>(0);
        if(StringUtils.isEmpty(id)){
            throw new UserException(ErrorEnum.ID_IS_NULL);
        }
            long userId = Long.parseLong(id);
        return userService.getUserById(userId);
    }

    @CrossOrigin
    @RequestMapping("/upload")
    @ResponseBody
    public Result upLoad(@RequestParam("file") MultipartFile file) {
        //用来检测程序运行时间
        long  startTime=System.currentTimeMillis();
        System.out.println("fileName："+file.getOriginalFilename());

        String fileName = "/home/chenende/图片/upload/"+new Date().getTime()+file.getOriginalFilename();
        try {
            //获取输出流
            OutputStream os=new FileOutputStream(fileName);
            //获取输入流 CommonsMultipartFile 中可以直接得到文件的流
            InputStream is=file.getInputStream();
            int temp;
            //一个一个字节的读取并写入
            while((temp=is.read())!=(-1))
            {
                os.write(temp);
            }
            os.flush();
            os.close();
            is.close();

        } catch (Exception e) {
            return Result.isError("SYSTEM_ERROR","系统异常");
        }
        long  endTime=System.currentTimeMillis();
        System.out.println("方法一的运行时间："+String.valueOf(endTime-startTime)+"ms");
        return Result.isSuccess(fileName);
    }

}
