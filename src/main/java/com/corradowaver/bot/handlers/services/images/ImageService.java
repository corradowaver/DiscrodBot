package com.corradowaver.bot.handlers.services.images;

import com.corradowaver.bot.handlers.entities.ImageBody;
import com.corradowaver.bot.config.PropsManager;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.net.ssl.HttpsURLConnection;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class ImageService {

  // Add your Bing Search V7 endpoint to your environment variables.
  static String path = "/bing/v7.0/images/search";

  public static ImageBody searchImages(String searchQuery) throws Exception {
    String host = PropsManager.getProps().getProperty("azure.api.host");
    String subscriptionKey = PropsManager.getProps().getProperty("azure.api.subscription.key");
    // construct URL of search request (endpoint + query string)
    URL url = new URL(host + path + "?q=" + URLEncoder.encode(searchQuery, StandardCharsets.UTF_8));
    HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
    connection.setRequestProperty("Ocp-Apim-Subscription-Key", subscriptionKey);

    // receive JSON body
    InputStream stream = connection.getInputStream();
    String response = new Scanner(stream).useDelimiter("\\A").next();

    // construct result object for return
    SearchResults results = new SearchResults(new HashMap<>(), response);

    // extract Bing-related HTTP headers
    Map<String, List<String>> headers = connection.getHeaderFields();
    for (String header : headers.keySet()) {
      if (header == null) continue;      // may have null key
      if (header.startsWith("BingAPIs-") || header.startsWith("X-MSEdge-")) {
        results.relevantHeaders.put(header, headers.get(header).get(0));
      }
    }

    stream.close();
    JsonObject json = JsonParser.parseString(results.jsonResponse).getAsJsonObject();
    //get the random image result from the JSON object, along with the total
    //number of images returned by the Bing Image Search API.
    JsonArray jsonResults = json.getAsJsonArray("value");
    int randomNumber = ThreadLocalRandom.current().nextInt(0, jsonResults.size());
    JsonObject random_result = (JsonObject) jsonResults.get(randomNumber);

    return new ImageBody(
        random_result.get("name").getAsString(),
        random_result.get("thumbnailUrl").getAsString());
  }

  static class SearchResults {
    HashMap<String, String> relevantHeaders;
    String jsonResponse;

    SearchResults(HashMap<String, String> headers, String json) {
      relevantHeaders = headers;
      jsonResponse = json;
    }
  }
}
