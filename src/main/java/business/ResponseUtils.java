package business;

import io.restassured.response.ValidatableResponse;

public class ResponseUtils {
    public static ValidatableResponse getResponse() {
        return RequestsUtils.getResponse();
    }

    public static <T> T getObjectFromResponse(Class<T> type, String path) {
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
}