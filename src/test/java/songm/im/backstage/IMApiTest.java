package songm.im.backstage;

import songm.im.backstage.entity.Token;

public class IMApiTest {

    public static void main(String[] args) {
        IMApi.init("", "", "https://192.168.3.87/song-account-web/api");
        IMApi api = IMApi.getInstance();
        // System.setProperty("javax.net.ssl.trustStore",
        // "D:\\eclipse-j2ee\\workspace\\song-commons\\deploy\\songm.keystore");
        // System.setProperty("javax.net.ssl.trustStorePassword",
        // "ninety-nine");
        // System.setProperty("org.jboss.security.ignoreHttpsHost", "true");

        try {
            Token t = api.getToken("", "", "");
            System.out.println(t);
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }
}
