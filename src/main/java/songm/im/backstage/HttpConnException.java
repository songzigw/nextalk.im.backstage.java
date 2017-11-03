package songm.im.backstage;

public class HttpConnException extends Exception {

    private static final long serialVersionUID = 5819698054801719955L;

    public HttpConnException(String message) {
        super(message);
    }
    
    public HttpConnException(Throwable e) {
        super(e);
    }
}
