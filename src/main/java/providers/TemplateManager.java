package providers;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class TemplateManager {
    private Configuration config;
    private static final String TEMPLATE_DIRECTORY = "src/main/resources/";

    public TemplateManager() {
        config = new Configuration(Configuration.VERSION_2_3_31);
        config.setDefaultEncoding("UTF-8");
        config.setTemplateLoader(new StringTemplateLoader());
    }

    private Template loadTemplate(String templateName, String templatePath) {
        try {
            String templateContent = new String(Files.readAllBytes(Paths.get(templatePath)));
            ((StringTemplateLoader) config.getTemplateLoader()).putTemplate(templateName, templateContent);
            return config.getTemplate(templateName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String processTemplate(String templateName, Map<String, String> data) {
        Template template = loadTemplate(templateName, TEMPLATE_DIRECTORY + templateName + ".ftl");
        try (StringWriter writer = new StringWriter()) {
            template.process(data, writer);
            return writer.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}