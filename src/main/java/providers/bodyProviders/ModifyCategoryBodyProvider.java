package providers.bodyProviders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ResponseUtils;

import java.util.HashMap;
import java.util.Map;

public class ModifyCategoryBodyProvider {
    private final static Logger logger = LoggerFactory.getLogger(ResponseUtils.class);

    public static String getCategoryBody(String title, Integer parentCategoryId) {
        logger.info("The title is {}, parentCategoryId is {}", title, parentCategoryId);
        TemplateManager templateManager = new TemplateManager();
        Map<String, Object> params = new HashMap<>();
        params.put("title", title);
        params.put("parentCategoryId", parentCategoryId);
        logger.info("The params are {}", params);
        return templateManager.processTemplate("categoryModify", params);
    }

    public static String getCategoryBody(String title) {
        logger.info("The title is {}", title);
        TemplateManager templateManager = new TemplateManager();
        Map<String, Object> params = new HashMap<>();
        params.put("title", title);
        logger.info("The params are {}", params);
        return templateManager.processTemplate("categoryModifyWithoutId", params);
    }
}
