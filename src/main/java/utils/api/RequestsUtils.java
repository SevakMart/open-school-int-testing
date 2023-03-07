package utils.api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import providers.dataProviders.SharedTestData;

import java.io.File;
import java.util.Map;


public class RequestsUtils {

    private final static Logger logger = LoggerFactory.getLogger(RequestsUtils.class);

    private static ValidatableResponse response;

    public static ValidatableResponse getResponse() {
        return response;
    }

    public static void get(String endpoint) {
        response = RestAssured
                .given()
                .when()
                .get(endpoint)
                .then();
        logger.info(response.extract().body().asPrettyString());
    }

    public static void get(String endpoint, String token) {
        response = RestAssured
                .given()
                .header("Authorization", token)
                .when()
                .get(endpoint)
                .then();
        logger.info(response.extract().body().asPrettyString());
    }

    public static void delete(String endpoint) {
        response = RestAssured
                .given()
                .header("Authorization", SharedTestData.getToken())
                .when()
                .delete(endpoint)
                .then();
        logger.info(response.extract().body().asPrettyString());
    }

    public static void post(String endpoint, Object body) {
        logger.info(endpoint, body);
        response = RestAssured
                .given()
                .header("Authorization", SharedTestData.getToken())
                .spec(getRequestSpecification())
                .body(body)
                .when()
                .post(endpoint)
                .then();
        logger.info(response.extract().body().asPrettyString());
    }

    public static void multipartPost(String endpoint, Map<String, Object> body, String filePath) {
        response = RestAssured
                .given()
                .header("Authorization", SharedTestData.getToken())
                .multiPart("image", new File(filePath), "multipart/form-data")
                .formParams(body)
                .post(endpoint)
                .then();
        logger.info(response.extract().body().asPrettyString());
    }

    public static void multipartPost(String endpoint, String key, String value) {
        response = RestAssured
                .given()
                .header("Authorization", SharedTestData.getToken())
                .contentType("multipart/form-data")
                .multiPart(key, value, "multipart/form-data")
                .post(endpoint)
                .then();

        logger.info(response.extract().body().asPrettyString());
    }

    public static void getByQueryParams(String endpoint, Map<String, Object> queryParam) {
        response = RestAssured
                .given()
                .header("Authorization", SharedTestData.getToken())
                .queryParams(queryParam)
                .get(endpoint)
                .then();
        logger.info(response.extract().body().asPrettyString());
    }

    public static void patchCategoryByTitle(String endpoint, Object body, int categoryId) {
        logger.info(endpoint, body, SharedTestData.getToken());
        response = RestAssured
                .given()
                .header("Authorization", SharedTestData.getToken())
                .pathParam("id", categoryId)
                .spec(getRequestSpecification())
                .body(body)
                .when()
                .patch(endpoint)
                .then();
        logger.info(response.extract().body().asPrettyString());
    }

    public static void patchCategoryByImage(String endpoint, String imageFilePath, int categoryId) {
        logger.info(endpoint, imageFilePath, SharedTestData.getToken());
        response = RestAssured
                .given()
                .header("Authorization", SharedTestData.getToken())
                .pathParam("id", categoryId)
                .contentType("multipart/form-data")
                .multiPart("image", new File(imageFilePath), "multipart/form-data")
                .patch(endpoint)
                .then();
        logger.info(response.extract().body().asPrettyString());
    }

    private static RequestSpecification getRequestSpecification() {
        RequestSpecBuilder spec = new RequestSpecBuilder();
        return spec
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .build();
    }

    public static void put(String endpoint, String body){
        logger.info(endpoint);
        response = RestAssured
                .given()
                .header("Authorization", SharedTestData.getToken())
                .spec(getRequestSpecification())
                .body(body)
                .when()
                .put(endpoint)
                .then();
        logger.info(response.extract().body().asPrettyString());
    }

    public static void delete(String endpoint, Map<String,Object> pathvar) {
        response = RestAssured
                .given()
                .header("Authorization", SharedTestData.getToken())
                .pathParams(pathvar)
                .when()
                .delete(endpoint)
                .then();
        logger.info(response.extract().body().asPrettyString());
    }
}
