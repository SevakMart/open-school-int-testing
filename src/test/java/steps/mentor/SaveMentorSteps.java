package steps.mentor;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.Mentor;
import providers.bodyProviders.BodyProvider;
import providers.dataProviders.SharedTestData;
import providers.dataProviders.TestDataProvider;
import utils.api.RequestsUtils;
import utils.api.ResponseUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SaveMentorSteps {

    private final Map<String, Object> params = new HashMap<>();

    private final static Logger logger = LoggerFactory.getLogger(RequestsUtils.class);

    @When("Login by valid {string} email and {string} password who needs to save mentor")
    public void loginByValidEmailAndPasswordWhoNeedsToSaveMentor(String email, String password) {
        params.put("psd", TestDataProvider.getPropertyValue(password));
        params.put("email", TestDataProvider.getPropertyValue(email));
        String body = BodyProvider.getBody("login", params);
        RequestsUtils.post("auth/login", body);
        SharedTestData.setToken(ResponseUtils.getAuthTokenFromResponseHeader());
    }

    @Then("Get authorized user's id")
    public void getUserSId() {
        SharedTestData.setUserId(ResponseUtils.getIntFromResponse("id"));
    }

    @Then("Save mentor for user")
    public void saveMentorForUser() {
        List<?> list;
        RequestsUtils.post("users/" + SharedTestData.getUserId() + "/mentors/" +
                TestDataProvider.getPropertyValue("mentorId"), "");
        list = ResponseUtils.getListFromResponseWithoutType("mentors.");
        SharedTestData.setUsersSavedMentorsList(list);
        logger.info("The saved mentors' list is {}", list);

    }

    @Then("Validate saved mentor's response body by jsonSchema")
    public void validateSavedMentorSResponseBodyByJsonSchema() {
        ResponseUtils.validateResponseAgainstJSONSchema("schemas/usersSchemas/SavedmentorSchema.json");
    }

    @Then("Save mentor for user by invalid Mentor Id")
    public void saveMentorForUserByInvalidMentorId() {
        RequestsUtils.post("users/" + SharedTestData.getUserId() + "/mentors/" +
                TestDataProvider.getPropertyValue("invalidMentorId"), "");
    }

    @Then("Validate saved mentor's response message when mentor Id is invalid")
    public void validateSavedMentorSResponseMessageWhenMentorIdIsInvalid() {
        Assertions.assertThat(ResponseUtils.getStringFromResponse("message")).isEqualTo("Incorrect argument");
    }

    @Then("Get saved mentors with authorized user's id")
    public void getSavedMentorsWithAuthorizedUserSId() {
        RequestsUtils.get("mentors/" + SharedTestData.getUserId(), SharedTestData.getToken());
    }

    @Then("Validate saved mentor's by userId response body by jsonSchema")
    public void validateSavedMentorSByUserIdResponseBodyByJsonSchema() {
        ResponseUtils.validateResponseAgainstJSONSchema("schemas/usersSchemas/GetSavedMentorsByUsersIdSchema.json");
    }

    @Then("Get saved mentors filling invalid userId")
    public void getSavedMentorsFillingInvalidUserId() {
        RequestsUtils.get("mentors/" + -1, SharedTestData.getToken());
    }

    @Then("Validate  get saved mentor's by invalid userId response error message")
    public void validateGetSavedMentorSByInvalidUserIdResponseErrorMessage() {
        Assertions.assertThat(ResponseUtils.getStringFromResponse("message")).isEqualTo("Incorrect argument");
    }

    @Then("Get saved mentors without naming")
    public void getSavedMentorsWithoutNaming() {
        RequestsUtils.get("mentors/searched/" + SharedTestData.getUserId(), SharedTestData.getToken());
    }

    @Then("Get saved mentors by name")
    public void getSavedMentorsByName() {
        params.put("name", "Arevik");
        RequestsUtils.getByQueryParams("mentors/searched/" + SharedTestData.getUserId(), params);
    }

    @Then("Get saved mentors by {}")
    public void getSavedMentorsBy(String name) {
        String newName = name.substring(1, name.length() - 1);
        logger.info("The name is {}", newName);
        params.put("name", newName);
        RequestsUtils.getByQueryParams("mentors/searched/" + SharedTestData.getUserId(), params);
    }

    @Then("Validate the count of mentors by name from response")
    public void validateTheCountOfMentorsByNameFromResponse() {
        List<Mentor> list;
        list = ResponseUtils.getListFromResponse("content.", Mentor.class);
        Assertions.assertThat(list.size()).isGreaterThan(0);
    }

    @Then("Validate the response name is the same as the one being searched for")
    public void validateTheResponseNameIsTheSameAsTheOneBeingSearchedFor() {
        String responseName = ResponseUtils.getObjectFromResponse("content[0]", Mentor.class).getName();
        Assertions.assertThat(responseName).isEqualTo(TestDataProvider.getPropertyValue("savedMentorName"));
    }

    @Then("Validate if the response body contains all saved mentors")
    public void validateIfTheResponseBodyContainsAllSavedMentors() {
        List<Mentor> list;
        list = ResponseUtils.getListFromResponse("content.", Mentor.class);
        Assertions.assertThat(list.size()).isEqualTo(0);
    }

    @Then("Get saved mentors name")
    public void getSavedMentorsName() {
        params.put("name", TestDataProvider.getPropertyValue("savedMentorName"));
        RequestsUtils.getByQueryParams("mentors/searched/" + SharedTestData.getUserId(), params);
    }

    @Then("Save mentor for user who already has a saved mentor")
    public void saveMentorForUserWhoAlreadyHasASavedMentor() {
        RequestsUtils.post("users/" + SharedTestData.getUserId() + "/mentors/" +
                TestDataProvider.getPropertyValue("anotherMentorId"), "");
    }

    @Then("Validate that the response body is not empty")
    public void validateThatTheResponseBodyIsNotEmpty() {
        Assertions.assertThat(ResponseUtils.getListFromResponse("content.", Mentor.class)).isNotEmpty();
    }
}
