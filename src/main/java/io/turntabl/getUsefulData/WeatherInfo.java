package io.turntabl.getUsefulData;

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


public class WeatherInfo {
    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://datapoint.metoffice.gov.uk/public/data/val/wxobs/all/json/sitelist?res=3hourly&key=ca3ee938-3ade-405e-adc1-38990741404b"))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


         System.out.println(">>>> " + response.body());

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String n = gson.toJson(response.body());
        System.out.println("Gson >>> " + n);

        String resp = response.body();

        JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse(resp);
        JsonArray locationJson = jsonElement.getAsJsonObject().get("Locations").getAsJsonObject().get("Location").getAsJsonArray();

        /*System.out.println(locationJson);
        for (JsonElement loc : locationJson) {
            System.out.println(loc);
        }
        locationJson.size();*/

          Type listType = new TypeToken<List<JsonElement>>() {}.getType();
        List<JsonElement> yourList = new Gson().fromJson(locationJson, listType);

//...        Stream<JsonElement> jsonElementStream = yourList.stream().filter(w -> !w.getAsJsonObject().keySet().contains("obsSource") && !w.isJsonNull()yourList);
//        Stream<WeatherPojo> weatherPojoStream = yourList.stream().filter(w -> !w.isJsonNull()
//                && w.getAsJsonObject().keySet().contains("elevation")
//                && w.getAsJsonObject().keySet().contains("region")
//                && w.getAsJsonObject().keySet().contains("unitaryAuthArea")
//                && w.getAsJsonObject().keySet().contains("name")
//        ).map(WeatherPojo::new);

        // obs.forEach(System.out::println);
        // dataElement.map(WeatherPojo::new).map(WeatherPojo::getUnitaryAuthArea).collect(Collectors.toSet()).forEach(System.out::println);
        // System.out.println(obs.count());

        // jsonElementStream.map(WeatherPojo::new).forEach(System.out::println);
        //System.out.println(collect.contains("surrey"));

//        JsonArray locSpecParamArray = locSpecElement.getAsJsonObject().get("SiteRep").getAsJsonObject().get("Wx").getAsJsonObject().get("Param").getAsJsonArray();
//        System.out.println(locSpecParamArray);

//...        Set<JsonElement> collect = weatherPojoStream.map(w -> getAllForecastFromSites(w.getId())).
//                map(x -> x.get("Location").getAsJsonObject().get("country")).collect(Collectors.toSet());
//        System.out.println(collect);

    }

//    private static JsonObject getAllForecastFromSites(String locationId)  {
//        HttpRequest locationSpecificReq = HttpRequest.newBuilder()
//                .uri(URI.create("http://datapoint.metoffice.gov.uk/public/data/val/wxfcs/all/json/" + locationId + "?res=3hourly&key=ca3ee938-3ade-405e-adc1-38990741404b"))
//                .build();
//        HttpResponse<String> locationSpecReq = null;
//        try {
//            locationSpecReq = HttpClient.newHttpClient().send(locationSpecificReq, HttpResponse.BodyHandlers.ofString());
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
//        String locSpecBody = locationSpecReq.body();
//        JsonParser locParser = new JsonParser();

//        JsonElement locSpecElement = locParser.parse(locSpecBody);
//        JsonObject locObj = locSpecElement.getAsJsonObject().get("SiteRep").getAsJsonObject().get("DV").getAsJsonObject();
//        return locObj;yourList
//    }
}




