package com.foopod.rest.helper;

import java.net.URL;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class HTTPRequester{

  public HTTPRequester(){

  }

  public String makeRequest(String requestMethod, String urlToRead) {
      URL url;
      HttpURLConnection conn;
      BufferedReader br;
      String line;
      StringBuilder result = new StringBuilder();
      try {
         url = new URL(urlToRead);
         conn = (HttpURLConnection) url.openConnection();
         conn.setRequestMethod(requestMethod);
         br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
         while ((line = br.readLine()) != null) {
            result.append(line);
         }
         br.close();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (Exception e) {
         e.printStackTrace();
      }
      return result.toString();
   }
}
