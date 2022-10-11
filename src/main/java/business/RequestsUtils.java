package business;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
public class RequestsUtils {
    private static ValidatableResponse response;

    public static ValidatableResponse getResponse() {
        return response;
    }

    public static void get(String endpoint){
         response = RestAssured
                    .given()
                    .when()
                    .get(endpoint)
                    .then();
    }

    public static void get(String endpoint, String header){
        response = RestAssured
                    .given()
                    .header("Authorization", header)
                    .when()
                    .get(endpoint)
                    .then();
    }

    public static void post(String endpoint, Object body){
        response = RestAssured
                    .given()
                    .spec(getRequestSpecification())
                    .body(body)
                    .when()
                    .post(endpoint)
                    .then();
    }
    private static RequestSpecification getRequestSpecification(){
        RequestSpecBuilder spec = new RequestSpecBuilder();
        return spec.setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .build();
    }
}