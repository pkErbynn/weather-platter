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

//        locationList.stream().map(e -> new WeatherRecPojo()).forEach(System.out::println);

    }

}




