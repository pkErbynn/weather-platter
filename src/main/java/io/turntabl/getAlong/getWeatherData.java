package io.turntabl.getAlong;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;


public class getWeatherData {
    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://datapoint.metoffice.gov.uk/public/data/val/wxobs/all/json/sitelist?res=3hourly&key=ca3ee938-3ade-405e-adc1-38990741404b"))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String resp = response.body();
        JsonElement jsonElement = new JsonParser().parse(resp);

        /////////////// not streamable  /////////
        JsonArray locationElement = jsonElement
                .getAsJsonObject().get("Locations")
                .getAsJsonObject().get("Location")
                .getAsJsonArray();  // since it's an array
//        System.out.println(">>>> " + locationElement;
//        for (JsonElement i: locationElement) {
//            System.out.println(i);
//        }
//        jsonElement.stream......can not stream on it just still json obj


        ///////////////  collaborating to be streamable    ///////////
        Type listType = new TypeToken<List<JsonElement>>() {}.getType();
        List<JsonElement> locationList = new Gson().fromJson(locationElement, listType);  // putting json elements into jva list
//        System.out.println(">>> " + locationList.get(0));   // now, can index accessible

//        List<JsonElement> elevation = locationList.stream()
//                .filter(e -> e.getAsJsonObject().keySet().contains("latitude"))
//                .map(e -> e.getAsJsonObject().get("elevation"))
//                .collect(Collectors.toList());
//        System.out.println(">>>> " + elevation);

        // obsSource data filter
//        Set<JsonElement> obsSource = locationList.stream().filter(e -> e.getAsJsonObject().keySet().contains("obsSource")).collect(Collectors.toSet());
//        System.out.println(">>>> obs " + obsSource);
//        locationList.stream().filter(e -> e.getAsJsonObject().keySet().contains("obsSource")).forEach(e -> System.out.println(e));
//        long obsSource = locationList.stream().filter(e -> e.getAsJsonObject().keySet().contains("obsSource")).count();
//        locationList.stream().filter(e -> !e.getAsJsonObject().keySet().contains("obsSource")).forEach(System.out::println);
//        long obsSourceCount = locationList.stream().filter(e -> !e.getAsJsonObject().keySet().contains("obsSource")).count();
//        System.out.println(obsSourceCount);

        // getting only elevation values
//        locationList.stream().map(e -> e.getAsJsonObject().get("elevation")).forEach(System.out::println);

        // getting only keys
//        locationList.stream().map(e -> e.getAsJsonObject().keySet()).forEach(System.out::println);

//        locationList.stream().map(WeatherRecPojo::new).forEach(System.out::println);
        // removed obsSource property on creation
//        locationList.stream().filter(e -> !e.getAsJsonObject().keySet().contains("obsSource")).map(WeatherRecPojo::new).forEach(System.out::println);
//        Set<String> names = locationList.stream().filter(e -> !e.getAsJsonObject().keySet().contains("obsSource")).map(WeatherRecPojo::new).map(WeatherRecPojo::getName).collect(Collectors.toSet());
//        System.out.println(names.size());

//        JsonObject allWeatherForecastFromSites = getAllWeatherForecastFromSites("14");
//        System.out.println(allWeatherForecastFromSites);
//        JsonElement jsonElement1 = getAllWeatherForecastFromSites("14").get("Location");
//        System.out.println(jsonElement1);

        Stream<WeatherRecPojo> weatherRecPojoStream = locationList.stream().filter(w -> !w.isJsonNull()
                && w.getAsJsonObject().keySet().contains("elevation")
                && w.getAsJsonObject().keySet().contains("region")
                && w.getAsJsonObject().keySet().contains("unitaryAuthArea")
                && w.getAsJsonObject().keySet().contains("name")
        ).map(WeatherRecPojo::new);
        Set<JsonElement> collect = weatherRecPojoStream.map(e -> getAllWeatherForecastFromSites(e.getId()))         // comes with quotes eg..."scotland"...thus must eliminate "
                .map(e -> e.get("Location").getAsJsonObject().get("country"))
                .collect(Collectors.toSet());
        System.out.println(collect);
//        Set<String> collect = locationList.stream().map(WeatherRecPojo::new).map().collect(Collectors.toSet());
//        System.out.println(collect.size());
//        System.out.println(collect.contains("Surrey"));


    }

    public static JsonObject getAllWeatherForecastFromSites(String locid) {
        HttpRequest locationSpecificReq = HttpRequest.newBuilder()
                .uri(URI.create("http://datapoint.metoffice.gov.uk/public/data/val/wxfcs/all/json/" + locid + "?res=3hourly&key=ca3ee938-3ade-405e-adc1-38990741404b"))
                .build();
        HttpResponse<String> locationSpecReq = null;
        try {locationSpecReq = HttpClient.newHttpClient().send(locationSpecificReq, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {e.printStackTrace();}

        JsonElement jsonElement = new JsonParser().parse(locationSpecReq.body());   // parse/convert it to valid json object
        JsonObject asJsonObject = jsonElement.getAsJsonObject().get("SiteRep").getAsJsonObject().get("DV").getAsJsonObject();

        return asJsonObject;
    }

}




