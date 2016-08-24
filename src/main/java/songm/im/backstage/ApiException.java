package songm.im.backstage;


public class ApiException extends Exception {

    private static final long serialVersionUID = 9060028080362777992L;

    private ErrorCode errorCode;

    private String description;

    public ApiException(ErrorCode errorCode, String description) {
        super(errorCode + ":" + description);
        this.errorCode = errorCode;
        this.description = description;
    }

    public ApiException(ErrorCode errorCode, String description, Throwable cause) {
        super(errorCode + ":" + description, cause);
        this.errorCode = errorCode;
        this.description = description;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public String getDescription() {
        return description;
    }

    public static enum ErrorCode {
        AAA
    }
}
