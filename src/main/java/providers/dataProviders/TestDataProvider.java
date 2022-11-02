package providers.dataProviders;

import lombok.experimental.UtilityClass;
import utils.PropertiesReader;

@UtilityClass
public final class TestDataProvider {
    private final PropertiesReader propertiesReader = PropertiesReader
            .getInstance("testData.properties");

    public String getPropertyValue(String propertyName) {
        return propertiesReader.getPropety(propertyName);
    }
}
