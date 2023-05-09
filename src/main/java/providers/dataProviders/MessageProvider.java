package providers.dataProviders;

import lombok.experimental.UtilityClass;
import utils.PropertiesReader;

@UtilityClass
public final class MessageProvider {
    private final PropertiesReader propertiesReader = PropertiesReader
            .getInstance("messagesData.properties");

    public String getPropertyValue(String propertyName) {
        return propertiesReader.getPropety(propertyName);
    }
}
