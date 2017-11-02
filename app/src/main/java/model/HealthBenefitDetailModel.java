package model;

/**
 * Created by Arsalan khan on 10/30/2017.
 */

public class HealthBenefitDetailModel {

   String benefits,richSource;

    public HealthBenefitDetailModel(String benefits, String richSource) {
        this.benefits = benefits;
        this.richSource = richSource;
    }

    public String getBenefits() {
        return benefits;
    }

    public String getRichSource() {
        return richSource;
    }
}
