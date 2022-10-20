package steps.categories;

import io.cucumber.java.en.Then;
import manager.CategoryIdManager;
import org.assertj.core.api.Assertions;
import utils.RequestsUtils;
import utils.ResponseUtils;
import utils.SharedTestData;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CategoryId {
    @Then("Find category or subcategory by {int}")
    public void findCategoryOrSubcategoryBy(int id) {
        RequestsUtils.getById("categories/{id}", SharedTestData.getToken(), id);
    }

    @Then("Get all categories by id")
    public void getAllCategoriesById() throws SQLException {
        CategoryIdManager categoryIdManager = new CategoryIdManager();
        SharedTestData.setListcategoriesId(categoryIdManager.getAllCategoriesId());
    }

    @Then("Find category or subcategory by id")
    public void findCategoryOrSubcategoryById() {
        int  listcategoryId = SharedTestData.getCategoryId();
        RequestsUtils.getById("categories/{id}", SharedTestData.getToken(), SharedTestData.getCategoryId());

    }

    @Then("Validate categoryid success response values")
    public void validateCategoryidSuccessResponseValues() {
        ResponseUtils.validateResponseAgainstJSONSchema("schema/getCategoryByIdRequest.json");
    }

//    @Then("Set category")
//    public void setCategory() {
//        SharedTestData.setCategoriesId(RequestsUtils.getById(););
//    }
}
