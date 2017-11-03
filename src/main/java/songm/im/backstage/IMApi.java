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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.reflect.TypeToken;

import songm.im.backstage.ApiException.ErrorCode;
import songm.im.backstage.entity.Result;
import songm.im.backstage.entity.Token;
import songm.im.backstage.utils.CodeUtils;
import songm.im.backstage.utils.HttpUtil;
import songm.im.backstage.utils.JsonUtils;

/**
 * API
 * 
 * @author zhangsong
 *
 */
public class IMApi {

    private static final Logger LOG = LoggerFactory.getLogger(IMApi.class);
    
    private static final String ENCODING = "utf-8";
    private static IMApi instance;

    private String uri;
    private String key;
    private String secret;

    private static void _instance() {
        synchronized (IMApi.class) {
            if (instance == null) {
                instance = new IMApi();
            }
        }
    }
    
    public static IMApi getInstance() {
        _instance();
        if (instance.uri == null
                || instance.key == null
                || instance.secret == null) {
            throw new IllegalArgumentException("init error");
        }
        return instance;
    }

    public static void init(String key, String secret, String uri) {
        _instance();
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
    public Token getToken(String uid, String nick, String avatar)
            throws ApiException {
        StringBuilder sb = new StringBuilder();
        sb.append("uid=").append(uid);
        sb.append("&nick=").append(CodeUtils.encodURL(nick, ENCODING));
        sb.append("&avatar=").append(CodeUtils.encodURL(avatar, ENCODING));

        String res = null;
        try {
            String url = uri + "/token?" + sb.toString();
             HttpURLConnection conn = HttpUtil.createHttpConnection(
                    key, secret, url);
            HttpUtil.setConnection("method", "GET", conn);
            HttpUtil.setBodyParameter(sb, conn);
            res = HttpUtil.returnResult(conn);
        } catch (HttpConnException e) {
            LOG.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
        
        Type type = new TypeToken<Result<Token>>() { }.getType();
        Result<Token> r = JsonUtils.fromJson(res, type);
        if (!r.getSucceed()) {
            throw new ApiException(ErrorCode.valueOf(r.getErrorCode()),
                    r.getErrorDesc());
        }
        return r.getData();
    }

}
