package utils;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

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
        logger.info(response.extract().asPrettyString());
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
    public static void getById(String endpoint, String token,int id) {
        response = RestAssured
                .given()
                .header("Authorization", token)
                .pathParam("id", id)
                .when()
                .get(endpoint)
                .then();
        logger.info(response.extract().body().asPrettyString());
    }

    public static void post(String endpoint, Object body) {
        response = RestAssured
                .given()
                .spec(getRequestSpecification())
                .body(body)
                .when()
                .post(endpoint)
                .then();
        logger.info(response.extract().body().asPrettyString());
    }

    public static void multipartPost(String endpoint, String token, String filePath, String title) {
        response = RestAssured
                .given()
                .header("Authorization", token)
                .contentType("multipart/form-data")
                .multiPart("image", new File(filePath), "multipart/form-data")
                .multiPart("title", title, "multipart/form-data")
                .log()
                .all()
                .post(endpoint)
                .then();
        logger.info(response.extract().body().asPrettyString());
    }

    public static void multipartPost(String endpoint, String token, String id, String title, String filePath) {
        response = RestAssured
                .given()
                .header("Authorization", token)
                .contentType("multipart/form-data")
                .multiPart("id", id, "multipart/form-data")
                .multiPart("title", title, "multipart/form-data")
                .multiPart("image", new File(filePath), "multipart/form-data")
                .log()
                .all()
                .post(endpoint)
                .then();
        logger.info(response.extract().body().asPrettyString());
    }

    public static void multipartPostWithoutImage(String endpoint, String token, String title) {
        response = RestAssured
                .given()
                .header("Authorization", token)
                .contentType("multipart/form-data")
                .multiPart("title", title, "multipart/form-data")
                .log()
                .all()
                .post(endpoint)
                .then();
        logger.info(response.extract().body().asPrettyString());
    }
    public static void getCategoryBySubCategoryTitle(String endpoint, String token, String title) {
        response = RestAssured
                .given()
                .header("Authorization", token)
                .queryParam("title", title)
                .log()
                .all()
                .get(endpoint)
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

    private static RequestSpecification getRequestSpecificationMultiPart() {
        RequestSpecBuilder spec = new RequestSpecBuilder();
        return spec
                .setContentType(ContentType.MULTIPART)
                .setAccept(ContentType.MULTIPART)
                .build();
    }
}
