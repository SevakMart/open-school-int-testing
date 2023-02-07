package steps.faqS;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.commons.lang3.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import providers.bodyProviders.BodyProvider;
import providers.dataProviders.SharedTestData;
import providers.dataProviders.TestDataProvider;
import utils.api.RequestsUtils;
import utils.api.ResponseUtils;

import java.util.HashMap;
import java.util.Map;

public class FaqS {
    private final Map<String, Object> params = new HashMap<>();
    private String body;

    private final static Logger logger = LoggerFactory.getLogger(RequestsUtils.class);

    @And("Create FAQs")
    public void createFAQs() {
        params.put("question", "Question" + RandomStringUtils.randomAlphabetic(3));
        params.put("answer", "Answer" + RandomStringUtils.randomAlphabetic(3));
        params.put("courseId", TestDataProvider.getPropertyValue("courseIdForAddingFAQs"));
        body = BodyProvider.getBody("faqS", params);
        RequestsUtils.post("courses/faqs", body);
        SharedTestData.setFaqsId(ResponseUtils.getIntFromResponse("id"));
        logger.info("Just created  FAQs's Id is {}", ResponseUtils.getIntFromResponse("id"));
    }

    @And("Validate response body by json schema")
    public void validateResponseBodyByJsonSchema() {
        ResponseUtils.validateResponseAgainstJSONSchema("schemas/faqS/addingFaqsResponseBodySchema.json");
    }

    @Then("Delete just created FAQs")
    public void deleteJustCreatedFAQs() {
        RequestsUtils.delete("courses/faqs/" + SharedTestData.getFaqsId());
    }

    @And("Update FAQs")
    public void updateFAQs() {
        params.put("question", TestDataProvider.getPropertyValue("faqsUpdatingQuestion"));
        params.put("answer", TestDataProvider.getPropertyValue("faqsUpdatingAnswer"));
        body = BodyProvider.getBody("faqS", params);
        RequestsUtils.put("courses/faqs/" + SharedTestData.getFaqsId(), body);
        SharedTestData.setFaqsId(ResponseUtils.getIntFromResponse("id"));
    }

    @And("Update FAQs question")
    public void updateFAQsQuestion() {
        params.put("answer", TestDataProvider.getPropertyValue("faqsUpdatingAnswer"));
        body = BodyProvider.getBody("faqS", params);
        RequestsUtils.put("courses/faqs/" + SharedTestData.getFaqsId(), body);
    }

    @And("Create FAQs without courseId argument")
    public void createFAQsWithoutCourseArgument() {
        params.put("question", "Question" + RandomStringUtils.randomAlphabetic(3));
        params.put("answer", "Answer" + RandomStringUtils.randomAlphabetic(3));
        body = BodyProvider.getBody("updateFAQs", params);
        RequestsUtils.post("courses/faqs", body);
    }

    @Then("Validate error message about creating FAQs by invalid request arguments")
    public void validateErrorMessageAboutCreatingFAQsByInvalidRequestArguments() {
        String actualResponseMessage = ResponseUtils.getStringFromResponse("courseId");
        logger.info("The actual response message is -> {}", actualResponseMessage);
        String expectedResponseMessage = "{argument.required}";
        logger.info("The expected response message is -> {}", expectedResponseMessage);
        Assertions.assertThat(actualResponseMessage).isEqualTo(expectedResponseMessage);
    }

    @And("Get all FAQs")
    public void getAllFAQs() {
        RequestsUtils.get("courses/faqs", SharedTestData.getToken());
    }

    @And("Update FAQs by not existed course")
    public void updateFAQsByNotExistedCourse() {
        params.put("question", TestDataProvider.getPropertyValue("faqsUpdatingQuestion"));
        params.put("answer", TestDataProvider.getPropertyValue("faqsUpdatingAnswer"));
        body = BodyProvider.getBody("updateFAQs", params);
        RequestsUtils.put("courses/faqs/" + 0, body);
    }

    @Then("Validate error message")
    public void validateErrorMessage() {
        String actualResponseMessage = ResponseUtils.getStringFromResponse("message");
        logger.info("The actual response message is -> {}", actualResponseMessage);
        String expectedResponseMessage = "Incorrect argument";
        logger.info("The expected response message is -> {}", expectedResponseMessage);
        Assertions.assertThat(actualResponseMessage).isEqualTo(expectedResponseMessage);
    }

    @And("Get all FAQs by page and size")
    public void getAllFAQsByPageAndSize() {
        params.put("page", 1);
        params.put("size", 1);
        RequestsUtils.getByQueryParams("courses/faqs", params);
    }

    @Then("Validate getting FAQs by json schema")
    public void validateGettingFAQsByJsonSchema() {
        ResponseUtils.validateResponseAgainstJSONSchema("schemas/faqS/gettingAllFaqsResponseBodySchema.json");
    }

    @Then("Check the error message")
    public void checkTheErrorMessage() {
        String actualResponseMessage = ResponseUtils.getStringFromResponse("message");
        logger.info("The actual response message is -> {}", actualResponseMessage);
        String expectedResponseMessage = "Access is denied";
        logger.info("The expected response message is -> {}", expectedResponseMessage);
        Assertions.assertThat(actualResponseMessage).isEqualTo(expectedResponseMessage);
    }

    @And("Get FAQs related to the selected courses")
    public void getFAQsRelatedToTheSelectedCourses() {
        RequestsUtils.get("courses/faqs/" + TestDataProvider.getPropertyValue("courseIdForAddingFAQs"), SharedTestData.getToken());
    }

    @And("Create FAQs which answer has {int}")
    public void createFAQsWhichAnswerHas(int symbolsCount) {
        params.put("question", "Question" + RandomStringUtils.randomAlphabetic(3));
        params.put("answer", "Answer " + RandomStringUtils.randomAlphabetic(symbolsCount));
        logger.info("The symbolsSize is -> {}", symbolsCount);
        params.put("courseId", TestDataProvider.getPropertyValue("courseIdForAddingFAQs"));
        body = BodyProvider.getBody("faqS", params);
        logger.info("Body is {}", body);
        RequestsUtils.post("courses/faqs", body);
        SharedTestData.setFaqsId(ResponseUtils.getIntFromResponse("id"));
        logger.info("Just created  FAQs's Id is {}", ResponseUtils.getIntFromResponse("id"));
    }

    @And("Validate error message about symbols size")
    public void validateErrorMessageAboutSymbolsSize() {
        String actualResponseMessage = ResponseUtils.getStringFromResponse("answer");
        logger.info("The actual response message is -> {}", actualResponseMessage);
        String expectedResponseMessage = "Maximum 500 characters are allowed";
        logger.info("The expected response message is -> {}", expectedResponseMessage);
        Assertions.assertThat(actualResponseMessage).isEqualTo(expectedResponseMessage);
    }

    @And("Create FAQs which answer does not contain {int} symbols")
    public void createFAQsWhichAnswerDoesNotContainSymbols(int symbolsCount) {
        params.put("question", "Question" + RandomStringUtils.randomAlphabetic(3));
        params.put("answer", "Answer " + RandomStringUtils.randomAlphabetic(symbolsCount));
        params.put("courseId", TestDataProvider.getPropertyValue("courseIdForAddingFAQs"));
        body = BodyProvider.getBody("faqS", params);
        logger.info("Body is {}", body);
        RequestsUtils.post("courses/faqs", body);
    }

    @And("Create FAQs without answer")
    public void createFAQsWithoutAnswer() {
        params.put("question", "Question" + RandomStringUtils.randomAlphabetic(3));
        params.put("courseId", TestDataProvider.getPropertyValue("courseIdForAddingFAQs"));
        body = BodyProvider.getBody("faqsWithoutAnswer", params);
        RequestsUtils.post("courses/faqs", body);
    }

    @Then("Validate error message about creating FAQs without answer")
    public void validateErrorMessageAboutCreatingFAQsWithoutAnswer() {
        String actualResponseMessage = ResponseUtils.getStringFromResponse("answer");
        logger.info("The actual response message is -> {}", actualResponseMessage);
        String expectedResponseMessage = "{argument.required}";
        logger.info("The expected response message is -> {}", expectedResponseMessage);
        Assertions.assertThat(actualResponseMessage).isEqualTo(expectedResponseMessage);
    }

    @And("Create FAQs without question")
    public void createFAQsWithoutQuestion() {
        params.put("answer", "Answer " + RandomStringUtils.randomAlphabetic(3));
        params.put("courseId", TestDataProvider.getPropertyValue("courseIdForAddingFAQs"));
        body = BodyProvider.getBody("faqsWithoutQuestion", params);
        logger.info("Body is {}", body);
        RequestsUtils.post("courses/faqs", body);
    }

    @Then("Validate error message about creating FAQs without question")
    public void validateErrorMessageAboutCreatingFAQsWithoutQuestion() {
        String actualResponseMessage = ResponseUtils.getStringFromResponse("question");
        logger.info("The actual response message is -> {}", actualResponseMessage);
        String expectedResponseMessage = "{argument.required}";
        logger.info("The expected response message is -> {}", expectedResponseMessage);
        Assertions.assertThat(actualResponseMessage).isEqualTo(expectedResponseMessage);
    }
}
