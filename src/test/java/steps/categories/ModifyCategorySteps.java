package steps.categories;

import config.TestDataProvider;
import io.cucumber.java.en.Then;
import org.apache.commons.lang3.RandomStringUtils;
import org.assertj.core.api.Assertions;
import providers.bodyProviders.ModifyCategoryBodyProvider;
import utils.RequestsUtils;
import utils.ResponseUtils;
import utils.SharedTestData;

public class ModifyCategorySteps {

    @Then("Modify category or subcategory by title")
    public void modifyCategoryOrSubcategoryByTitle() {
        int parentCategoryId = SharedTestData.getCategoryId();
        String bodyWithParentId = ModifyCategoryBodyProvider
                .getCategoryBody("Modified" + RandomStringUtils.randomAlphabetic(3), parentCategoryId);
        String bodyWithoutParentId = ModifyCategoryBodyProvider.getCategoryBody("Modified" + RandomStringUtils.randomAlphabetic(3));
        int id = SharedTestData.getCategoryId();
        if (id == parentCategoryId) {
            RequestsUtils.patchCategoryByTitle("categories/{id}", bodyWithoutParentId, id);
        } else
            RequestsUtils.patchCategoryByTitle("categories/{id}", bodyWithParentId, id);
    }

    @Then("Modify category or subcategory by image")
    public void modifyCategoryOrSubcategoryByImage() {
        RequestsUtils.patchCategoryByImage("categories/{id}/image",
                TestDataProvider.getPropertyValue("filePath"), SharedTestData.getCategoryId());
    }

    @Then("Validate response body by image")
    public void validateResponseBodyByJsonSchema() {
        ResponseUtils.validateResponseAgainstJSONSchema("schemas/categorySchemas/getModifiedCategoryByImageSchema.json");
    }

    @Then("Validate response body by title")
    public void validateResponseBodyByTitle() {
        ResponseUtils.validateResponseAgainstJSONSchema("schemas/categorySchemas/getModifiedCategoryByTitleSchema.json");
    }

    @Then("Modify category or subcategory by invalid category id")
    public void modifyCategoryOrSubcategoryByInvalidCategoryId() {
        RequestsUtils.patchCategoryByTitle("categories/{id}", ModifyCategoryBodyProvider.getCategoryBody("Modified" + RandomStringUtils.randomAlphabetic(3)), -1);
    }

    @Then("Validate error message about invalid category")
    public void validateErrorMessageAboutInvalidCategory() {
        String message = "Incorrect argument";
        Assertions.assertThat(ResponseUtils.getStringFromResponse("message").equals(message));
    }

    @Then("Modify category or subcategory by invalid parentCategoryId")
    public void modifyCategoryOrSubcategoryByInvalidParentCategoryId() {
        String bodyWithParentId = ModifyCategoryBodyProvider.getCategoryBody("Modified" + RandomStringUtils.randomAlphabetic(3), -1);
        RequestsUtils.patchCategoryByTitle("categories/{id}", bodyWithParentId, SharedTestData.getCategoryId());
    }

    @Then("Modify category or subcategory by invalid title")
    public void modifyCategoryOrSubcategoryByInvalidTitle() {
        String bodyWithoutParentId = ModifyCategoryBodyProvider.getCategoryBody("");
        RequestsUtils.patchCategoryByTitle("categories/{id}", bodyWithoutParentId, SharedTestData.getCategoryId());
    }
}
