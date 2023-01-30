package models;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.ws.Response;

public class Restapi {
    String host;
    public Restapi() {
        //ha egy könyvtárba van akkor nem kell importálni
        this.host = "http://localhost:3000/";
    }
    
    //kivétel kezelése
    public String getVehiclesAsString()
    //kezelni kell a kivételt
    String response = null;
    {
     try {
        response = trygetVehiclesAsString();
        
     } catch (MalformedURLException e) {
        System.err.println("Hiba! Érvénytelen URL!");
     }
    return host;  
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
        System.out.println(responseCode);
        return null;
    }

    //ezt hivjuk a view-ban
    public ArrayList<Vehicle> getVehicles()
    {
        return null;
    }
}
