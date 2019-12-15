package io.turntabl.getAlong;

import com.google.gson.JsonElement;

public class WeatherRecPojo {
    private String elevation;
    private String id;
    private String latitude;
    private String longitude;
    private String name;
    private String unitaryAuthArea;

    public WeatherRecPojo() {
    }

    public WeatherRecPojo(JsonElement element) {

    }

    public String getElevation() {
        return elevation;
    }

    public void setElevation(String elevation) {
        this.elevation = elevation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnitaryAuthArea() {
        return unitaryAuthArea;
    }

    public void setUnitaryAuthArea(String unitaryAuthArea) {
        this.unitaryAuthArea = unitaryAuthArea;
    }

    @Override
    public String toString() {
        return "WeatherRecPojo{" +
                "elevation='" + elevation + '\'' +
                ", id='" + id + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", name='" + name + '\'' +
                ", unitaryAuthArea='" + unitaryAuthArea + '\'' +
                '}';
    }
}
