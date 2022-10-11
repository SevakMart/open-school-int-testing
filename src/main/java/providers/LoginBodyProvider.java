package providers;

import java.util.HashMap;
import java.util.Map;

public class LoginBodyProvider {
    public static String getLoginBody(String password, String email) {
        TemplateManager templateManager = new TemplateManager();
        Map<String, Object> params = new HashMap<>();
        params.put("psd", password);
        params.put("email", email);
        return templateManager.processTemplate("login", params);
    }
}