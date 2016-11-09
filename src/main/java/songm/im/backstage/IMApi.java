/*
 * Copyright [2016] [zhangsong <songm.cn>].
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package songm.im.backstage;

import java.lang.reflect.Type;
import java.net.HttpURLConnection;

import com.google.gson.reflect.TypeToken;
import songm.im.backstage.entity.Result;

import songm.im.backstage.ApiException.ErrorCode;
import songm.im.backstage.entity.Token;
import songm.im.backstage.utils.HttpUtil;
import songm.im.backstage.utils.JsonUtils;

public class IMApi {

    // private static final String UTF8 = "utf-8";
    private static IMApi instance;

    private String uri;
    private String key;
    private String secret;

    public static IMApi getInstance() {
        if (instance == null) {
            instance = new IMApi();
        }
        if (instance.uri == null || instance.key == null || instance.secret == null) {
            throw new IllegalArgumentException("初始化参数错误");
        }
        return instance;
    }

    public static void init(String key, String secret, String uri) {
        if (instance == null) {
            instance = new IMApi();
        }
        instance.uri = uri;
        instance.key = key;
        instance.secret = secret;
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
    public Token getToken(String uid, String nick, String avatar) throws ApiException {
        StringBuilder sb = new StringBuilder();
        sb.append("uid=").append(uid);
        sb.append("&nick=").append(nick);
        sb.append("&avatar").append(avatar);
        
        String url = uri + "/token?" + sb.toString(); 
        HttpURLConnection conn = HttpUtil.createPostHttpConnection(key, secret, url);
        HttpUtil.setConnection("method", "GET", conn);
        // HttpUtil.setBodyParameter(sb, conn);

        HttpResult shr = HttpUtil.returnResult(conn);
        if (shr.getHttpCode() != 200) {
            throw new ApiException(ErrorCode.REQUEST, "请求异常");
        }
        Type type = new TypeToken<Result<Token>>() {}.getType();
        Result<Token> r = JsonUtils.fromJson(shr.getResult(), type);
        if (!r.getSucceed()) {
            throw new ApiException(ErrorCode.valueOf(r.getErrorCode()), r.getErrorDesc());
        }
        return r.getData();
    }

}
