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

    private static String uri = "http://127.0.0.1:17183/backstage";
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
        String nick = "song";
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
    
    @Test
    public void testGetTokenUid10001() {
        String uid = "10001";
        String nick = "张松1";
        String avatar = "1";
        Token t = null;
        try {
            t = api.getToken(uid, nick, avatar);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        if (t != null) {
            System.out.println(t.toString());
        }
    }
    
    @Test
    public void testGetTokenUid10002() {
        String uid = "10002";
        String nick = "张松2";
        String avatar = "2";
        Token t = null;
        try {
            t = api.getToken(uid, nick, avatar);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        if (t != null) {
            System.out.println(t.toString());
        }
    }
}
