package steps.categories;

import providers.dataProviders.TestDataProvider;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang3.RandomStringUtils;
import org.assertj.core.api.Assertions;
import pojo.Category;
import utils.api.RequestsUtils;
import utils.api.ResponseUtils;
import providers.dataProviders.SharedTestData;

import java.util.HashMap;
import java.util.Map;

public class CreateCategorySteps {
    @When("Create parentCategory with provided file")
    public void createParentCategoryWithProvidedFile() {
        Map<String, Object> body = new HashMap<>();
        body.put("title", "TestParent " + RandomStringUtils.randomAlphabetic(5));
        RequestsUtils.multipartPost("categories", body, TestDataProvider.getPropertyValue("filePath"));
        SharedTestData.setCategoryId(ResponseUtils.getObjectFromResponse("", Category.class).getId());
    }

    @When("Create subCategory")
    public void createSubCategory() {
        String parentCategoryId = String.valueOf(SharedTestData.getCategoryId());
        Map<String, Object> body = new HashMap<>();
        body.put("title", "TestSub " + RandomStringUtils.randomAlphabetic(5));
        body.put("parentCategoryId", parentCategoryId);
        RequestsUtils.multipartPost("categories", body, TestDataProvider.getPropertyValue("filePath"));
        SharedTestData.setSubCategoryId(ResponseUtils.getObjectFromResponse("", Category.class).getId());
    }

    @Then("Validate subcategory creation response JsonSchema")
    public void validateSubcategoryCreationResponseJsonSchema() {
        ResponseUtils.validateResponseAgainstJSONSchema("schemas/categorySchemas/getPostedSubCategoryRequestSchema.json");
    }

    @Then("Validate response by JsonSchema")
    public void validateResponseByJsonSchema() {
        ResponseUtils.validateResponseAgainstJSONSchema("schemas/categorySchemas/getPostedParentCategoryRequestSchema.json");
    }

    @When("Invalid filePath during parentCategory creation")
    public void invalidFilePathDuringParentCategoryCreation() {
        RequestsUtils.multipartPost("categories", "title", "FailTitle");
    }

    @When("Invalid title during parentCategory creation")
    public void invalidTitleDuringParentCategoryCreation() {
        RequestsUtils.multipartPost("categories", "title", "");
    }

    @Then("Validate new category creation without image failure response message")
    public void validateNewCategoryCreationWithoutImageFailureResponseMessage() {
        String message = "Category image is required";
        Assertions.assertThat(ResponseUtils.getStringFromResponse("image")).isEqualTo(message);
    }

    @Then("Validate new category creation without title failure response message")
    public void validateNewCategoryCreationWithoutTitleFailureResponseMessage() {
        String message = "Category title is required";
        Assertions.assertThat(ResponseUtils.getStringFromResponse("title")).isEqualTo(message);
    }

    @When("Fail parentCategory creatiion without admin role")
    public void failParentCategoryCreatiionWithoutAdminRole() {
        Map<String, Object> body = new HashMap<>();
        body.put("title", RandomStringUtils.randomAlphabetic(5));
        RequestsUtils.multipartPost("categories", body, TestDataProvider.getPropertyValue("filePath"));
    }
}
