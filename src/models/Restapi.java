package models;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.xml.ws.Response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Restapi {
    String host;
    public Restapi() {
        //ha egy könyvtárba van akkor nem kell importálni
        this.host = "http://[::1]:3000/";
    }
    
    //kivétel kezelése
    public String getVehiclesAsString()
    {
    String response = null;
    try {
        response = trygetVehiclesAsString();
     }
    catch (MalformedURLException e) {
        System.err.println("Hiba! Érvénytelen URL!");
    }
    catch (IOException e) {
        System.err.println("Hiba! A Rest api elérése sikertelen");
    }
    return response;  
    }


    public String trygetVehiclesAsString() throws IOException,MalformedURLException
    //kezelni kell a kivételt
    {
        String endpoint="vehicles";
        String urlStr = this.host + endpoint;
        URL url = new URL(urlStr);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("GET");
        http.connect();
        //válaszkód
        int responseCode = http.getResponseCode();
        // System.out.println(responseCode);
        String text = null;
        if(responseCode == 200) {
            InputStream inputStream = http.getInputStream();
            text = this.convertStreamToString(inputStream);
            //stringet csinaljunk
        }
        else{
            text = "--hiba--";
        }
        return text;
    }

    private String convertStreamToString(InputStream inputStream)
    {
        Reader reader = new InputStreamReader(inputStream);
        Scanner scanner = new Scanner(reader);
        StringBuilder stringBuilder = new StringBuilder();
        while(scanner.hasNextLine())
        {
            stringBuilder.append(scanner.nextLine());
        }
        scanner.close();
        return stringBuilder.toString();
    }

    //ezt hivjuk a view-ban
    public ArrayList<Vehicle> getVehicles()
    {
        GsonBuilder builder = new GsonBuilder();
        Gson gson =builder.create();
        String text = getVehiclesAsString();
        Vehicle[] vehicle = gson.fromJson(text, Vehicle[].class);
        ArrayList<Vehicle> vehicleList =
        new ArrayList<>(Arrays.asList(vehicle));
        return vehicleList;
    }
}
