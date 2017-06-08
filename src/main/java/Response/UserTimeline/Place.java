package main.java.Response.UserTimeline;

import com.google.gson.annotations.SerializedName;

public class Place {

    @SerializedName("full_name")
    private String fullName;

    @SerializedName("country_code")
    private String countryCode;

    private String country;

    public String getCountryCode() {
        return countryCode;
    }

    public String getCountry() {
        return country;
    }

    public String getFullName() {
        return fullName;
    }
}
