package model;

/**
 * Created by Arsalan khan on 10/18/2017.
 */

public class HomeRemediesModel {

    private String categoryID;
    private String subCategoryID;
    private String categoryName;

    public HomeRemediesModel(String categoryID, String subCategoryID, String categoryName) {
        this.categoryID = categoryID;
        this.subCategoryID = subCategoryID;
        this.categoryName = categoryName;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public String getSubCategoryID() {
        return subCategoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
