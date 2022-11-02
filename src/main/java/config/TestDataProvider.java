package config;

public final class TestDataProvider {
    private static final PropertiesReader propertiesReader = PropertiesReader
            .getInstance("testData.properties");

    private TestDataProvider() {
    }

    public static String getPropertyValue(String propertyName) {
        return propertiesReader.getPropety(propertyName);
    }
}
