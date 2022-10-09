package business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class SharedData {
    private final static Logger logger = LoggerFactory.getLogger(SharedData.class);
    private static String token;
    public static String getToken() {
        logger.info("GetToken is {}",token);
        return token;
    }
    public static void setToken(String token) {
        SharedData.token = token;
        logger.info("SetToken is {}",SharedData.token);
    }
}