package com.corradowaver.bot.commands.handlers.art;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class ArtSearchEngine {
  private final static String URL = "http://artii.herokuapp.com/make";

  public static String getAsciiArt(String text, String font) throws UnirestException {
    HttpResponse<String> request = Unirest.get(URL + "?text=" + text + "&font=" + font).asString();
    return request.getBody();
  }
}
