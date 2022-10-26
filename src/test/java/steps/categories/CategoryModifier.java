package steps.categories;

import io.cucumber.java.en.Then;
import providers.ModifyCategoryBodyProvider;
import utils.RequestsUtils;
import utils.ResponseUtils;
import utils.SharedTestData;

public class CategoryModifier {

    @Then("Modify category or subcategory by {string} and {int}")
    public void modifyCategoryOrSubcategoryByAnd(String title, int parentCategoryId) {
        String bodyWithParentId = ModifyCategoryBodyProvider.getLoginBody(title, parentCategoryId);
        String bodyWithoutParentId = ModifyCategoryBodyProvider.getLoginBody(title);
        int id = SharedTestData.getCategoryId();
        if (id == parentCategoryId) {
            RequestsUtils.patchCategoryByTitle("categories/{id}", bodyWithoutParentId, SharedTestData.getToken(), id);
        } else
            RequestsUtils.patchCategoryByTitle("categories/{id}", bodyWithParentId, SharedTestData.getToken(), id);
    }

    @Then("Modify category or subcategory by {string}")
    public void modifyCategoryOrSubcategoryBy(String imageFilePath) {
        RequestsUtils.patchCategoryByImage("categories/{id}/image", imageFilePath, SharedTestData.getToken(), SharedTestData.getCategoryId());
    }

    @Then("Validate response body by image")
    public void validateResponseBodyByJsonSchema() {
        ResponseUtils.validateResponseAgainstJSONSchema("schemas/categorySchemas/getModifiedCategoryByImageSchema.json");
    }

    @Then("Validate response body by title")
    public void validateResponseBodyByTitle() {
        ResponseUtils.validateResponseAgainstJSONSchema("schemas/categorySchemas/getModifiedCategoryByTitleSchema.json");
    }
}
