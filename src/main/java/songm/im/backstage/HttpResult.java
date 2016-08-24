package songm.im.backstage;

/**
 * 返回数据的封装
 * 
 * @author zhangsong
 *
 */
public class HttpResult {

    private int code;
    private String result;

    public HttpResult(int code, String result) {
        this.code = code;
        this.result = result;
    }

    public int getHttpCode() {
        return code;
    }

    public String getResult() {
        return result;
    }

    @Override
    public String toString() {
        return String.format("{\"code\":\"%s\",\"result\":%s}", code, result);
    }
}
