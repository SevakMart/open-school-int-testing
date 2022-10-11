package business;

import com.github.fge.jsonschema.SchemaVersion;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.ValidatableResponse;

public class ResponseUtils {
    public static ValidatableResponse getResponse() {
        return RequestsUtils.getResponse();
    }

    public static <T> T getObjectFromResponse(Class <T> type) {
        return getResponse()
                .extract()
                .as(type);
    }

    public static int getIntFromResponse(String path) {
        return getResponse()
                .extract()
                .jsonPath()
                .getInt(path);
    }

    public static String getStringFromResponse(String path) {
        return getResponse()
                .extract()
                .jsonPath()
                .getString(path);
    }

    public static String getAuthTokenFromResponseHeader() {
        return getResponse()
                .extract()
                .header("Authorization");
    }

    public static int getStatusCodeFromResponse() {
        return getResponse()
                .extract()
                .statusCode();
    }
    public static void validateResponseAgainstJSONSchema( String filepath){
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