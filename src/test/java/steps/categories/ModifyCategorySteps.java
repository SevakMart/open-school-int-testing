package steps.categories;

import pojo.Category;
import providers.bodyProviders.BodyProvider;
import providers.dataProviders.TestDataProvider;
import io.cucumber.java.en.Then;
import org.apache.commons.lang3.RandomStringUtils;
import org.assertj.core.api.Assertions;
import utils.api.RequestsUtils;
import utils.api.ResponseUtils;
import providers.dataProviders.SharedTestData;

import java.util.HashMap;
import java.util.Map;

public class ModifyCategorySteps {
    private Map<String, Object> params = new HashMap<>();

    @Then("Modify subcategory by title")
    public void modifySubcategoryByTitle() {
        int subCategoryId = SharedTestData.getSubCategoryId();
        params.put("title", "Modified" + RandomStringUtils.randomAlphabetic(3));
        params.put("parentCategoryId", SharedTestData.getCategoryId());
        String body = BodyProvider.getBody("categoryModify", params);
        RequestsUtils.patchCategoryByTitle("categories/{id}", body, subCategoryId);
    }

    @Then("Modify parent category by title")
    public void modifyParentCategoryByTitle() {
        params.put("title", "Modified" + RandomStringUtils.randomAlphabetic(3));
        String body = BodyProvider.getBody("categoryModifyWithoutId", params);
        RequestsUtils.patchCategoryByTitle("categories/{id}", body, SharedTestData.getCategoryId());
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
        params.put("title", "Modified" + RandomStringUtils.randomAlphabetic(3));
        params.put("parentCategoryId", SharedTestData.getCategoryId());
        String body = BodyProvider.getBody("categoryModify", params);
        RequestsUtils.patchCategoryByTitle("categories/{id}", body, -1);
    }

    @Then("Validate error message about invalid category")
    public void validateErrorMessageAboutInvalidCategory() {
        String message = "Incorrect argument";
        Assertions.assertThat(ResponseUtils.getStringFromResponse("message")).isEqualTo(message);
    }

    @Then("Modify category or subcategory by invalid parentCategoryId")
    public void modifyCategoryOrSubcategoryByInvalidParentCategoryId() {
        params.put("title", "Modified" + RandomStringUtils.randomAlphabetic(3));
        params.put("parentCategoryId", "-1");
        String body = BodyProvider.getBody("categoryModify", params);
        RequestsUtils.patchCategoryByTitle("categories/{id}", body, SharedTestData.getSubCategoryId());
    }

    @Then("Modify category or subcategory by invalid title")
    public void modifyCategoryOrSubcategoryByInvalidTitle() {
        params.put("title", "");
        String body = BodyProvider.getBody("categoryModifyWithoutId", params);
        RequestsUtils.patchCategoryByTitle("categories/{id}", body, SharedTestData.getCategoryId());
    }

    @Then("Modify category or subcategory by existing title")
    public void modifyCategoryOrSubcategoryByExistingTitle() {
        SharedTestData.setCategorytitle(ResponseUtils.getObjectFromResponse("", Category.class).getTitle());
        params.put("title", SharedTestData.getCategorytitle());
        String body = BodyProvider.getBody("categoryModifyWithoutId", params);
        RequestsUtils.patchCategoryByTitle("categories/{id}", body, SharedTestData.getCategoryId());
    }

    @Then("Validate error message about existing title")
    public void validateErrorMessageAboutExistingTitle() {
        String message = "The category with the same title already exists";
        Assertions.assertThat(ResponseUtils.getStringFromResponse("message")).isEqualTo(message);
    }
}
