package demo.demoweb;

import demo.ErrorEnum;
import demo.Result;
import demo.demodao.pojo.User;
import demo.demoservice.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/demo")
@PropertySource("classpath:config/test.properties")
public class UserController {
    @Resource
    private UserService userService;
    @Value("${errorCode}")
    private String errorCode;
    @Value("${errorMsg}")
    private String errorMsg;


    @RequestMapping("/add")
    @ResponseBody
    public Result add(String id) {
        if (StringUtils.isEmpty(id)) {
            return Result.isError(ErrorEnum.ID_IS_NULL.name(), ErrorEnum.ID_IS_NULL.getErrorMsg());
        }
        User user = userService.queryUser(id);
        return Result.isSuccess(user);
    }
}
