package steps.auth;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pojo.Student;
import providers.dataProviders.StudentProvider;
import utils.RequestsUtils;
import utils.ResponseUtils;

public class RegisterStep {
    @When("Register random student")
    public void registerRandomStudent() {
        Student student = StudentProvider.getRandomStudent();
        RequestsUtils.post("auth/register", student);
    }

    @Then("Validate student registration response")
    public void validateStudentRegistrationResponse() {
        ResponseUtils.validateResponseAgainstJSONSchema("schemas/authSchemas/getStudentRegistrationResponseSchema.json");
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
