package hu.ait.android.multiactivitydemo.data;

import java.io.Serializable;

public class PersonalData implements Serializable{

    private String taxID;
    private String insuranceID;
    private int yearBorn;


    public PersonalData(String taxID, String insuranceID, int yearBorn) {
        this.taxID = taxID;
        this.insuranceID = insuranceID;
        this.yearBorn = yearBorn;
    }

    public String getTaxID() {
        return taxID;
    }

    public void setTaxID(String taxID) {
        this.taxID = taxID;
    }

    public String getInsuranceID() {
        return insuranceID;
    }

    public void setInsuranceID(String insuranceID) {
        this.insuranceID = insuranceID;
    }

    public int getYearBorn() {
        return yearBorn;
    }

    public void setYearBorn(int yearBorn) {
        this.yearBorn = yearBorn;
    }
}
