package providers.bodyProviders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.api.ResponseUtils;

import java.util.HashMap;
import java.util.Map;

public class EmailBodyProvider {
    private final static Logger logger = LoggerFactory.getLogger(ResponseUtils.class);

    public static String getEmailBody(String email) {
        logger.info("The email is {}", email);
        TemplateManager templateManager = new TemplateManager();
        Map<String, Object> params = new HashMap<>();
        params.put("email", email);
        logger.info("The params are {}", params);
        return templateManager.processTemplate("email", params);
    }
}
