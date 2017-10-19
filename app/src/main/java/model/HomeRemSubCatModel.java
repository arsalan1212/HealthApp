package model;

/**
 * Created by Arsalan khan on 10/18/2017.
 */

public class HomeRemSubCatModel {

    private String detailID;
    private String subID;
    private String titleHeading;
    private String description;
    private String image;

    public HomeRemSubCatModel(String detailID, String subID, String titleHeading, String description, String image) {
        this.detailID = detailID;
        this.subID = subID;
        this.titleHeading = titleHeading;
        this.description = description;
        this.image = image;
    }

    public String getDetailID() {
        return detailID;
    }

    public String getSubID() {
        return subID;
    }

    public String getTitleHeading() {
        return titleHeading;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }
}
