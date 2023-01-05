package steps.mentor;

import io.cucumber.java.en.Then;
import org.assertj.core.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.Mentor;
import providers.dataProviders.TestDataProvider;
import utils.api.RequestsUtils;
import utils.api.ResponseUtils;
import providers.dataProviders.SharedTestData;
import io.cucumber.java.en.When;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MentorsStep {

    private final Map<String, Object> params = new HashMap<>();
    private final Logger logger = LoggerFactory.getLogger(MentorsStep.class);

    @When("Get all mentors")
    public void getAllMentors() {
        RequestsUtils.get("mentors", SharedTestData.getToken());
    }

    @When("Get all mentors by page")
    public void getAllMentorsByPage() {
        params.put("page", 1);
        RequestsUtils.getByQueryParams("mentors", params);
    }

    @When("Get all mentors by page and by size")
    public void getAllMentorsByPageAndBySize() {
        params.put("page", 1);
        params.put("size", 1);
        RequestsUtils.getByQueryParams("mentors", params);
    }

    @When("Get all mentors by page, size and sorting type")
    public void getAllMentorsByPageSizeAndSortingType() {
        params.put("page", 1);
        params.put("size", 1);
        params.put("sort", "id,desc");
        RequestsUtils.getByQueryParams("mentors", params);
    }

    @When("Get all mentors by size")
    public void getAllMentorsBySize() {
        params.put("size", TestDataProvider.getPropertyValue("sizeOfMentors"));
        RequestsUtils.getByQueryParams("mentors", params);
    }

    @Then("Validate the count of response elements' size")
    public void validateTheCountOfResponseElements() {
        String size = String.valueOf(ResponseUtils.getIntFromResponse("size"));
        Assertions.assertThat(size).isEqualTo(TestDataProvider.getPropertyValue("sizeOfMentors"));
    }

    @Then("Validate mentors response by JsonSchema")
    public void validateMentorsResponseByJsonSchema() {
        ResponseUtils.validateResponseAgainstJSONSchema("schemas/mentorsSchemas/GetAllMentorsRequestSchema.json");
    }

    @Then("Validate the default size of any page")
    public void validateTheDefaultSizeOfAnyPage() {
        Assertions.assertThat(ResponseUtils.getStringFromResponse("size"))
                .isEqualTo(TestDataProvider.getPropertyValue("defaultSizeOfMentorPage"));
    }

    @When("Get all mentors by page, size and default sorting type")
    public void getAllMentorsByPageSizeAndDefaultSortingType() {
        params.put("page", 1);
        params.put("size", 1);
        params.put("sort", "id,asc");
        RequestsUtils.getByQueryParams("mentors", params);
    }

    @Then("Validate the count of mentors from response")
    public void validateTheCountOfMentorsFromResponse() {
        List<Mentor> list = ResponseUtils.getListFromResponse("content.", Mentor.class);
        Assertions.assertThat(list.size()).isEqualTo(ResponseUtils
                .getIntFromResponse("numberOfElements"));
    }

    @When("Get mentors by {}")
    public void getMentorsBy(String name) {
        String newName = name.substring(1, name.length() - 1);
        logger.info("The name is {}", newName);
        params.put("name", newName);
        RequestsUtils.getByQueryParams("mentors/searched", params);
    }

    @When("Get all mentors by first page")
    public void getAllMentorsByFirstPage() {
        params.put("page", 0);
        RequestsUtils.getByQueryParams("mentors", params);
    }

    @Then("Get saved mentor by userId")
    public void getSavedMentorByUserId() {
        RequestsUtils.get("mentors/" + SharedTestData.getUserId(), SharedTestData.getToken());
    }

    @Then("Validate that response body returns empty content")
    public void validateThatResponseBodyReturnsEmptyContent() {
        Assertions.assertThat(ResponseUtils.getListFromResponseWithoutType("content.")).isEmpty();
        logger.info("The empty content is {}", ResponseUtils.getListFromResponseWithoutType("content."));
    }
}
