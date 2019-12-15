package io.turntabl.gsonSer;
/*
* converting Java object to JSON document using Google's Java library called Gson.
* This library allows you to convert both Java object to JSON String and a JSON document to Java object.
*  Serialization in the context of Gson means mapping a Java object to its JSON representation
*
* How?
* add the dependency in gradle / pom.xml file.
* Create the object to be converted into JSON
* Create the object of Gson, helps to convert Java object to Gson
* Call Gson.toJSon(object) to convert the object into JSON String.
* */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class PojoToJson {
    private String fname;
    private String lname;
    private int age;
    private String phone;
    private String city;

    public PojoToJson(String fname, String lname, int age, String phone, String city) {
        this.fname = fname;
        this.lname = lname;
        this.age = age;
        this.phone = phone;
        this.city = city;
    }

    public String getFname() {
        return fname;
    }

    @Override
    public String toString() {
        return "PojoToJson{" +
                "fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", age=" + age +
                ", phone=" + phone +
                ", city='" + city + '\'' +
                '}';
    }
}


class Run{
    public static void main(String[] args) {
        PojoToJson pojo = new PojoToJson("John", "null", 24, "+233-55-347-2114", null);

        // Json Serialization
        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        String gsonPojo = gson.toJson(pojo);
        System.out.println(gsonPojo);

        // Json To Object
        PojoToJson backToPojo = gson.fromJson(gsonPojo, PojoToJson.class);
        System.out.println(backToPojo);
        System.out.println("fname: " + backToPojo.getFname());


        String weather;

    }
}
