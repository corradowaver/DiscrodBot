package com.corradowaver.bot.commands.handlers.entities;

public class ImageBody {
  private String name;
  private String URL;

  public ImageBody(String name, String url) {
    this.name = name;
    URL = url;
  }

  public void setURL(String URL) {
    this.URL = URL;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getURL() {
    return URL;
  }

  public String getName() {
    return name;
  }
}
