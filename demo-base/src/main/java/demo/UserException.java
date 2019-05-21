package demo;


public class UserException extends BaseBusinessException{
    public UserException(ErrorEnum errorEnum) {
        super(errorEnum);
    }
}
