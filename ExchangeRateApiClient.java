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
    ExchangeRateApiClient()
    {
       map=new HashMap<>();
       load();
    }
    public void load(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:/Users/jsonn")); // replace with your path of jsonn file

            StringBuilder response = new StringBuilder();
            String line;
            
            while ((line = reader.readLine()) != null) {
                if(line.contains("conversion_rates")){
                    while ((line = reader.readLine()) != null) {
                        if(line.length()<3) break;
                        String ctry = line.substring(3,6);
                        double cur=Double.parseDouble(line.substring(8,line.length()-1));
                        map.put(ctry,cur);
                    }
                }
            }
            reader.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public HashMap<String, Double> getExchangeRates() {
        return map;
    }
}
