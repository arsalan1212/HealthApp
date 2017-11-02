package model;

/**
 * Created by Arsalan khan on 11/2/2017.
 */

public class RichFoodDetailModel {

    private String imagePath;
    private String richFoodText;

    public RichFoodDetailModel(String richFoodText,String imagePath) {
        this.imagePath = imagePath;
        this.richFoodText = richFoodText;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getRichFoodText() {
        return richFoodText;
    }
}
