package helpers;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TestHelperTest {
    private static final String userName = "testUserDetails";
    private static final String password = "testPassword";
    private static final String Base64 = "Basic dGVzdFVzZXJEZXRhaWxzOnRlc3RQYXNzd29yZA==";

    @Test
    public void shouldEncodeBase64userNameAndPassword() throws Exception {
        String encoded = TestHelper.encodeBase64(userName, password);
        assertEquals(encoded, Base64, "Base64 not produced");
    }
}
