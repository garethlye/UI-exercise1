package com.example.garethlye.uitest1;

/**
 * Created by garethlye on 17/02/2017.
 */

public class CountryData {


    private String countryName;
    private String cityName;
    private int checkboxImage;
    private boolean status;



    public CountryData(final String countryName, final String cityName, boolean status) {
        this.countryName = countryName;
        this.cityName = cityName;
        this.status = status;

        if(this.status){
            checkboxImage = R.drawable.ic_checkbox_selected;
        }
        else
            checkboxImage = R.drawable.ic_checkbox;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(final String countryName) {
        this.countryName = countryName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(final String cityName) {
        this.cityName = cityName;
    }

    public int getCheckboxImage() {
        return checkboxImage;
    }

    public void setCheckboxImage(int checkboxImage) {
        this.checkboxImage = checkboxImage;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(final boolean status) {
        this.status = status;
    }
}
