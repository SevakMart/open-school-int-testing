package steps.categories;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import utils.RequestsUtils;
import utils.ResponseUtils;
import utils.SharedTestData;

public class CategoryCreator {
    @When("Create Category where filePath is {string}, image is {string}")
    public void createCategoryWhereFilePathIsAndImage(String filePath, String title) {
        RequestsUtils.multipartPost("categories", SharedTestData.getToken(), filePath, title);
    }


    @When("Create new category with {string}")
    public void createNewCategoryWith(String title) {
        RequestsUtils.multipartPostWithoutImage("categories", SharedTestData.getToken(), title);
    }

    @Then("Validate new categories' response values")
    public void validateNewCategoriesResponseValues() {
        String message = "Category image is required";
        Assertions.assertThat(ResponseUtils.getStringFromResponse("image").equals(message));
    }

    @Then("Validate subcategory creation response JsonSchema")
    public void validateSubcategoryCreationResponseJsonSchema() {
        ResponseUtils.validateResponseAgainstJSONSchema("schemas/categorySchemas/getPostedSubCategoryRequestSchema.json");
    }

    @When("Create new category with parentId {string} path {string} title {string}")
    public void createNewCategoryWithParentIdPathTitle(String parentCategoryId, String filePath, String title) {
        RequestsUtils.multipartPost("categories", SharedTestData.getToken(), parentCategoryId, title, filePath);
    }
}
