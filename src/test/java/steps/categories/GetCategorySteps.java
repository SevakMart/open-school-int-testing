package steps.categories;

import org.assertj.core.api.Assertions;
import pojo.Category;
import providers.dataProviders.TestDataProvider;
import utils.api.RequestsUtils;
import utils.api.ResponseUtils;
import providers.dataProviders.SharedTestData;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetCategorySteps {

    private Map<String, Object> params = new HashMap<>();
    @Then("Get all categories and relevant subcategories")
    public void getAllCategoriesAndRelevantSubcategories() {
        RequestsUtils.get("categories", SharedTestData.getToken());
    }

    @Then("Set Admin Token")
    public void setAdminToken() {
        SharedTestData.setToken(ResponseUtils.getAuthTokenFromResponseHeader());
    }

    @Then("Get category by title")
    public void findAllCategoriesByTitle() {
        params.put("title", TestDataProvider.getPropertyValue("categoryTitle"));
        RequestsUtils.getByQueryParams("categories/subcategories", params);
    }

    @When("Get all categories and relevant subcategories without login")
    public void getAllCategoriesAndRelevantSubcategoriesWithoutLogin() {
        RequestsUtils.get("categories");
    }

    @Then("Find category or subcategory by id")
    public void findCategoryOrSubcategoryById() {
        RequestsUtils.get("categories/" + SharedTestData.getCategoryId(), SharedTestData.getToken());
    }

    @Then("Validate categoryid success response values")
    public void validateCategoryidSuccessResponseValues() {
        ResponseUtils.validateResponseAgainstJSONSchema("schemas/categorySchemas/getCategoryByIdRequest.json");
    }

    @Then("Get all parent categories as list")
    public void getAllParentCategoriesAsList() {
        RequestsUtils.get("categories/parentCategories", SharedTestData.getToken());
    }

    @Then("Validate the count of response elements")
    public void validateTheCountOfResponseElements() {
        List<Category> list = ResponseUtils.getListFromResponse("content.", Category.class);
        Assertions.assertThat(list.size()).isEqualTo(ResponseUtils
                                            .getIntFromResponse("numberOfElements"));
    }

    @Then("Validate by jsonSchema")
    public void validateByJsonSchema() {
        ResponseUtils.validateResponseAgainstJSONSchema("schemas/categorySchemas/GetAllCategoryRequestSchema.json");
    }
}
