package songm.im.backstage;

import java.net.HttpURLConnection;

import songm.im.backstage.ApiException.ErrorCode;
import songm.im.backstage.entity.Token;
import songm.im.backstage.utils.HttpUtil;
import songm.im.backstage.utils.JsonUtils;

public class IMApi {

    // private static final String UTF8 = "utf-8";
    private static IMApi mInstance;

    private String uri;
    private String key;
    private String secret;

    public static IMApi getInstance() {
        if (mInstance == null) {
            mInstance = new IMApi();
        }
        if (mInstance.uri == null || mInstance.key == null
                || mInstance.secret == null) {
            throw new IllegalArgumentException("初始化参数错误");
        }
        return mInstance;
    }

    public static void init(String key, String secret, String uri) {
        if (mInstance == null) {
            mInstance = new IMApi();
        }
        mInstance.uri = uri;
        mInstance.key = key;
        mInstance.secret = secret;
    }

    /**
     * 获取Token
     * 
     * @param uid
     * @param nick
     * @param avatar
     * @return
     * @throws ApiException
     */
    public Token getToken(String uid, String nick, String avatar)
            throws ApiException {
        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(key, secret,
                uri + "/api/token");

        StringBuilder sb = new StringBuilder();
        sb.append("uid=").append(uid);
        sb.append("&nick=").append(nick);
        sb.append("&avatar").append(avatar);
        HttpUtil.setBodyParameter(sb, conn);

        HttpResult shr = HttpUtil.returnResult(conn);
        if (shr.getHttpCode() != 200) {
            throw new ApiException(ErrorCode.AAA, "网络异常");
        }
        Token sr = (Token) JsonUtils.fromJson(shr.getResult().getBytes(),
                Token.class);
        return sr;
    }

}
