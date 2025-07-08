    package com.nizetic.yuumi;

    import org.json.JSONArray;
    import org.json.JSONObject;

    import java.io.BufferedReader;
    import java.io.InputStreamReader;
    import java.net.URL;
    import java.net.URLConnection;
    import java.util.ArrayList;

    public class NetFetch {
        public static String fetch(String adresa){
            String rezultat=null;
            try {
                rezultat="";
                URL url = new URL(adresa);
                URLConnection urlConnection = url.openConnection();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String line;
                while ((line = bufferedReader.readLine()) != null){
                    rezultat=rezultat+line+"\n";
                }
                bufferedReader.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return rezultat;
        }
    }
