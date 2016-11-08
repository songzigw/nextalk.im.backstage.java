package songm.im.backstage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import songm.im.backstage.entity.Token;

public class IMApiTest {

    // System.setProperty("javax.net.ssl.trustStore",
    // "D:\\eclipse-j2ee\\workspace\\song-commons\\deploy\\songm.keystore");
    // System.setProperty("javax.net.ssl.trustStorePassword", "ninety-nine");
    // System.setProperty("org.jboss.security.ignoreHttpsHost", "true");

    private static String uri = "http://127.0.0.1:8080/songm.im/api";
    private static String key = "zhangsong";
    private static String secret = "1234567";
    
    private IMApi api;
    
    @Before
    public void setUp() throws Exception {
        IMApi.init(key, secret, uri);
        api = IMApi.getInstance();
    }
    
    @After
    public void tearDown() throws Exception {
        System.out.println("==========");
    }
    
    @Test
    public void testGetToken() {
        String uid = "10000";
        String nick = "zhangsong";
        String avatar = "";
        Token t = null;
        try {
            t = api.getToken(uid, nick, avatar);
        } catch (ApiException e) {
        }
        if (t != null) {
            System.out.println(t.getId());
        }
    }
    
}
