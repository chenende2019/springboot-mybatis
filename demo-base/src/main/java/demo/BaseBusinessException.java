package demo;

public class BaseBusinessException extends RuntimeException{

    private String message;
    private String code;

    public BaseBusinessException(ErrorEnum errorEnum){
        this(errorEnum.getErrorCode(),errorEnum.getErrorMsg());
    }

    public BaseBusinessException(String code,String message) {
        super(message);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
