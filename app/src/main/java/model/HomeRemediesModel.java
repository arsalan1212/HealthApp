package model;

/**
 * Created by Arsalan khan on 10/18/2017.
 */

public class HomeRemediesModel {

    private String categoryID;
    private String subCategoryID;
    private String categoryName;


    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public void setSubCategoryID(String subCategoryID) {
        this.subCategoryID = subCategoryID;
    }

    public void setCategoryName(String categoryName) {
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
