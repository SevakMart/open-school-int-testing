package steps.auth;

import dtos.UserRegistrationDto;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import manager.AuthManager;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import providers.bodyProviders.BodyProvider;
import providers.dataProviders.Endpoints;
import providers.dataProviders.SharedTestData;
import providers.dataProviders.TestDataProvider;
import utils.api.RequestsUtils;
import utils.api.ResponseUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class AccountVerificationStep {
    private final AuthManager authManager = new AuthManager();

    private final Map<String, Object> queryParam = new HashMap<>();
    private final static Logger logger = LoggerFactory.getLogger(RequestsUtils.class);

    @Given("Prepare user for account verification")
    public void prepareUserForAccountVerification() {
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto(
                TestDataProvider.getPropertyValue("firstName"),
                TestDataProvider.getPropertyValue("lastName"),
                TestDataProvider.getPropertyValue("email"),
                TestDataProvider.getPropertyValue("password"));
        logger.info("The user has these fields ->{}", userRegistrationDto);
        RequestsUtils.post(Endpoints.SIGN_UP.url, userRegistrationDto);
        SharedTestData.setUserId(ResponseUtils.getIntFromResponse("userId"));
        logger.info("The user is prepared");
    }

    @And("Verify account verification success status code")
    public void verifyAccountVerificationSuccessStatusCode() {
        Assertions.assertThat(ResponseUtils.getStatusCodeFromResponse()).isEqualTo(HttpStatus.SC_OK);
    }

    @Then("Delete the user")
    public void deleteTheUser() {
        authManager.deleteUserById(SharedTestData.getUserId());
        logger.info("The user is succesfully deleted from the DB");
    }

    @Then("Resend email as the previous has already expired")
    public void resendEmailAsThePreviousHasAlreadyExpired() {
        String email = TestDataProvider.getPropertyValue("email");
        logger.info("The email is -> {}", email);
        queryParam.put("email", email);
        RequestsUtils.getByQueryParams(Endpoints.RESEND_TO_EMAIL_VERIFICATION.url, queryParam);
    }

    @Then("Resend invalid email for verification")
    public void resendInvalidEmailForVerification() {
        String invalidEmail = TestDataProvider.getPropertyValue("invalidEmail");
        logger.info("The invalidEmail is -> {}", invalidEmail);
        queryParam.put("email", invalidEmail);
        RequestsUtils.getByQueryParams(Endpoints.RESEND_TO_EMAIL_VERIFICATION.url, queryParam);
    }

    @Then("Verify email")
    public void verifyEmail() {
        int userId = SharedTestData.getUserId();
        String token = authManager.getTokenForVerify(userId);
        queryParam.put("token", token);
        logger.info("The token is -> {}", token);
        String body = BodyProvider.getBody("token", queryParam);
        RequestsUtils.post(Endpoints.VERIFY_EMAIL.url, body);
    }

    @Then("Verify email with invalid token")
    public void verifyEmailWithInvalidToken() {
        int userId = SharedTestData.getUserId();
        queryParam.put("token", "-1");
        String body = BodyProvider.getBody("token", queryParam);
        RequestsUtils.post(Endpoints.VERIFY_EMAIL.url, body);
    }

    @Then("Validate error message about invalid token")
    public void validateErrorMessageAboutInvalidToken() {
        Assertions.assertThat(ResponseUtils.getStringFromResponse("message")).isEqualTo("Verification token is expired or not valid");
    }

    @Then("Verify email when the verification token is expired")
    public void verifyEmailWhenTheVerificationTokenIsExpired() throws InterruptedException {
        int userId = SharedTestData.getUserId();
        String token = authManager.getTokenForVerify(userId);
        logger.info("The token is -> {}", token);
        queryParam.put("token", token);
        String body = BodyProvider.getBody("token", queryParam);
        TimeUnit.SECONDS.sleep(180);
        RequestsUtils.post(Endpoints.VERIFY_EMAIL.url, body);
    }

    @Given("Prepare another user for account verification")
    public void prepareAnotherUserForAccountVerification() throws InterruptedException {
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto(
                TestDataProvider.getPropertyValue("firstName"),
                TestDataProvider.getPropertyValue("lastName"),
                TestDataProvider.getPropertyValue("email"),
                TestDataProvider.getPropertyValue("password"));
        logger.info("The user has these fields ->{}", userRegistrationDto);
        RequestsUtils.post(Endpoints.SIGN_UP.url, userRegistrationDto);
        SharedTestData.setUserId(ResponseUtils.getIntFromResponse("userId"));
        logger.info("The user is prepared");
    }

    @Then("Resend email to the verified user")
    public void resendEmailToTheVerifiedUser() {
        String email = TestDataProvider.getPropertyValue("email");
        logger.info("The already verified email is -> {}", email);
        queryParam.put("email", email);
        RequestsUtils.getByQueryParams(Endpoints.RESEND_TO_EMAIL_VERIFICATION.url, queryParam);
    }

    @And("Validate error message that this email is already verified")
    public void validateErrorMessageThatThisEmailIsAlreadyVerified() {
        Assertions.assertThat(ResponseUtils.getStringFromResponse("message")).isEqualTo("User already verified.");
    }
}
