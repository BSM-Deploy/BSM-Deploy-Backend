package bssm.deploy.global.utils;

import lombok.experimental.UtilityClass;

import java.security.SecureRandom;
import java.util.HexFormat;

@UtilityClass
public class SecurityUtil {

    SecureRandom secureRandom = new SecureRandom();

    public String getRandomStr(int length) {
        byte[] randomBytes = new byte[length / 2];
        secureRandom.nextBytes(randomBytes);
        return HexFormat.of().formatHex(randomBytes);
    }
}
