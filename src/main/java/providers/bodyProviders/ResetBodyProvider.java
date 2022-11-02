package providers.bodyProviders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ResponseUtils;

import java.util.HashMap;
import java.util.Map;

public class ResetBodyProvider {
    private final static Logger logger = LoggerFactory.getLogger(ResponseUtils.class);

    public static String getResetBody(String token, String password) {
        logger.info("The reset password token is {} new password is {}", token, password);
        TemplateManager templateManager = new TemplateManager();
        Map<String, Object> params = new HashMap<>();
        params.put("token", token);
        params.put("newPassword", password);
        params.put("confirmedPassword", password);
        logger.info("The params are {}", params);
        return templateManager.processTemplate("reset", params);
    }
}
