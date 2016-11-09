package songm.im.backstage;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
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
    
    public IMApiTest() {
        IMApi.init(key, secret, uri);
        api = IMApi.getInstance();
    }
    
    @BeforeClass
    public static void beforeClass() {
        
    }

    @AfterClass
    public static void afterClass() {
        
    }
    
    @Before
    public void setUp() throws Exception {
        
    }
    
    @After
    public void tearDown() throws Exception {
        
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
            e.printStackTrace();
        }
        if (t != null) {
            System.out.println(t.toString());
        }
        Assert.assertNotNull(t);
    }
    
}
