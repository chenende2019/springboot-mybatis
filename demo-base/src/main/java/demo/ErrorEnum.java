package demo;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public enum ErrorEnum {

    ID_IS_NULL("ID_IS_NULL","ID是不能为空的哟"),
    INSERT_USER_ERROR("INSERT_USER_ERROR","添加用户失败"),
    USER_EXIST("USER_EXIST","用户已存在"),
    PARAM_ERROR("PARAM_ERROR","参数有误，请重新输入"),
    USER_NOT_EXIST("USER_NOT_EXIST","用户不存在"),
    SYSTEM_EXCEPTION("SYSTEM_EXCEPTION","系统异常"),
    USER_NOT_LOGIN("USER_NOT_LOGIN","请登录后重新访问");

    private String errorCode;
    private String errorMsg;

    ErrorEnum(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
