package steps;

import utils.RequestsUtils;
import utils.ResponseUtils;
import utils.SharedTestData;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpStatus;

import pojo.Student;
import providers.LoginBodyProvider;

public class LoginStep {

    @When("Login by valid {} password and {} email")
    public void loginByValidPasswordAndEmail(String password, String email) {
        String body = LoginBodyProvider.getLoginBody(password, email);
        RequestsUtils.post("auth/login", body);
    }

    @Then("Validate response values")
    public void validateResponseValues() {
        int statusCode = ResponseUtils.getStatusCodeFromResponse();
        if (statusCode == HttpStatus.SC_OK) {
            ResponseUtils.validateResponseAgainstJSONSchema("schema/getLoginRequestSchema.json");
            SharedTestData.setToken(ResponseUtils.getAuthTokenFromResponseHeader());
        } else {
            ResponseUtils.validateResponseAgainstJSONSchema("schema/getLoginErrorSchema.json");
        }
    }

    @When("Register random student")
    public void registerRandomStudent() {
        Student student = Student.getRandomStudent();
        RequestsUtils.post("auth/register", student);
    }

    @Then("Validate student registration response")
    public void validateStudentRegistrationResponse() {
        ResponseUtils.validateResponseAgainstJSONSchema("schema/getStudentRegistrationResponseSchema.json");
    }

    @When("Check registration fail if provided {} firstname, {} lastname, {} email, {} password is not correct")
    public void checkRegistrationFailIfProvidedFirstnameLastnameEmailPasswordIsNotCorrect(String firstname, String lastname, String email, String password) {
        Student student = new Student(firstname, lastname, email, password);
        RequestsUtils.post("auth/register", student);
        ResponseUtils.getResponse().extract().response().prettyPrint();
    }

    @When("Check registration fail if data is not correct {string} firstname, {string} lastname, {string} email, {string} password")
    public void checkRegistrationFailIfDataIsNotCorrectFirstnameLastnameEmailPassword(String firstname, String lastname, String email, String password) {
        Student student = new Student(firstname, lastname, email, password);
        RequestsUtils.post("auth/register", student);
    }
}
