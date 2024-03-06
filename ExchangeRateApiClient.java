package org.example.gui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;

public class ExchangeRateApiClient {
    public HashMap<String, Double> map;
    String date;
    ExchangeRateApiClient()
    {
        date="";
       map=new HashMap<>();
       load();
    }
    public void load(){
        String apiKey = ""; //write your api key 
        String endpoint = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/USD"; //replace with your api link 
        try {
            // Create URL object
           URL url = new URI(endpoint).toURL();

           // Open connection
           HttpURLConnection connection = (HttpURLConnection) url.openConnection();

           // Set request method
           connection.setRequestMethod("GET");

           // Read response
           BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                if(line.contains("time_last_update_utc")){
                    date=line.substring(25,line.length()-7)+"UTC";
                }

                else if(line.contains("conversion_rates")){
                    while ((line = reader.readLine()) != null) {
                        if(line.length()<3) break;
                        String ctry = line.substring(3,6);
                        double cur=Double.parseDouble(line.substring(8,line.length()-1));
                        map.put(ctry,cur);
                    }
                }
            }
            reader.close();
            System.out.println(date);
           // Print response
           System.out.println(response.toString());

           // Close connection
           connection.disconnect();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        } catch (URISyntaxException e) {
           throw new RuntimeException(e);
       }
    }
    public HashMap<String, Double> getExchangeRates() {
        return map;
    }
}
