package business;

import com.github.fge.jsonschema.SchemaVersion;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.ValidatableResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResponseUtils {
    private final static Logger logger = LoggerFactory.getLogger(ResponseUtils.class);
    public static ValidatableResponse getResponse() {
        return RequestsUtils.getResponse();
    }
    public static <T> T getObjectFromResponse(Class <T> type) {
        logger.info(getResponse().extract().asPrettyString());
        return getResponse()
                .extract()
                .as(type);
    }
    public static int getIntFromResponse(String path) {
        logger.info(getResponse().extract().asPrettyString());
        return getResponse()
                .extract()
                .jsonPath()
                .getInt(path);
    }
    public static String getStringFromResponse(String path) {
        logger.info(getResponse().extract().asPrettyString());
        return getResponse()
                .extract()
                .jsonPath()
                .getString(path);
    }

    public static String getAuthTokenFromResponseHeader() {
        logger.info(getResponse().extract().header("Authorization"));
        return getResponse()
                .extract()
                .header("Authorization");
    }

    public static int getStatusCodeFromResponse() {
        logger.info("StatusCode is {}",getResponse().extract().statusCode());
        return getResponse()
                .extract()
                .statusCode();
    }
    public static void validateResponseAgainstJSONSchema(String filepath){
        logger.info(getResponse().extract().response().body().asPrettyString());
        JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory
                .newBuilder()
                .setValidationConfiguration(ValidationConfiguration
                        .newBuilder()
                        .setDefaultVersion(SchemaVersion.DRAFTV4)
                        .freeze())
                .freeze();
        getResponse()
                .assertThat()
                .body(JsonSchemaValidator
                        .matchesJsonSchemaInClasspath(filepath)
                        .using(jsonSchemaFactory));
    }
}