package com.corradowaver.bot.commands.handlers.validators;

import java.net.MalformedURLException;
import java.net.URL;

public class PlayValidator {

  public static boolean isUrl(String string) {
    try {
      new URL(string);
      return true;
    } catch (MalformedURLException e) {
      return false;
    }
  }
}
