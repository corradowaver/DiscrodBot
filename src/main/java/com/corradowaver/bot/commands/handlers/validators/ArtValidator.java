package com.corradowaver.bot.commands.handlers.validators;

public class ArtValidator {
  private static final int STRING_MAX_LENGTH = 62;

  public static boolean isValidForOutput(String text) {
    int length = text.indexOf("\n");
    return (length > -1 && length < STRING_MAX_LENGTH);
  }
}
