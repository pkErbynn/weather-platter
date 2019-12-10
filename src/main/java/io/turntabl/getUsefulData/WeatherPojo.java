package io.turntabl.getUsefulData;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class WeatherPojo {
    private String elevation;
    private String id;
    private String latitude;
    private String longitude;
    private String name;
    private String unitaryAuthArea;

    public WeatherPojo() {
    }

    public WeatherPojo(JsonElement element) {
        setElevation(element.getAsJsonObject().get("elevation").toString().replaceAll("\"", ""));
        setUnitaryAuthArea(element.getAsJsonObject().get("unitaryAuthArea").toString().replaceAll("\"", ""));
        setName(element.getAsJsonObject().get("name").toString().replaceAll("\"", ""));
        setLongitude(element.getAsJsonObject().get("longitude").toString().replaceAll("\"", ""));
        setLatitude(element.getAsJsonObject().get("longitude").toString().replaceAll("\"", ""));
        setId(element.getAsJsonObject().get("id").toString().replaceAll("\"", ""));
    }

    // Getter Methods

    public String getElevation() {
        return elevation;
    }

    public String getId() {
        return id;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getName() {
        return name;
    }

    public String getUnitaryAuthArea() {
        return unitaryAuthArea;
    }

    // Setter Methods

    public void setElevation(String elevation) {
        this.elevation = elevation;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setUnitaryAuthArea(String unitaryAuthArea) {
        this.unitaryAuthArea = unitaryAuthArea;
    }

    @Override
    public String toString() {
        return "WeatherPojo{" +
                "elevation='" + elevation + '\'' +
                ", id='" + id + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", name='" + name + '\'' +
                ", unitaryAuthArea='" + unitaryAuthArea + '\'' +
                '}';
    }
}