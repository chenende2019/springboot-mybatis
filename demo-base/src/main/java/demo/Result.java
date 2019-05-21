package demo;

public class Result<T>{
    private boolean success = true;
    private T data;
    private String errorCode;
    private String errorMes;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMes() {
        return errorMes;
    }

    public void setErrorMes(String errorMes) {
        this.errorMes = errorMes;
    }

    public static <T> Result isError(String errorCode,String errorMes){
        Result<T> objectResult = new Result<>();
        objectResult.success=false;
        objectResult.errorCode=errorCode;
        objectResult.errorMes=errorMes;
        return objectResult;
    }

    public static <T>Result isSuccess (T data){
        Result<T> objectResult = new Result<>();
        objectResult.data=data;
        return objectResult;
    }
}
