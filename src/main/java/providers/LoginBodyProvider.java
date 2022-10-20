package providers;

import utils.ResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class LoginBodyProvider {
    private final static Logger logger = LoggerFactory.getLogger(ResponseUtils.class);

    public static String getLoginBody(String password, String email)  {
        logger.info("The email is {} password is {}", email, password);
        TemplateManager templateManager = new TemplateManager();
        Map<String, String> params = new HashMap<>();
        params.put("psd", password);
        params.put("email", email);
        logger.info("The params are {}", params);
        return templateManager.processTemplate("login", params);
    }
}
