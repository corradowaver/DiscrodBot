package com.corradowaver.bot.handlers.commands.messages;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

public class PlayMessages {

  private static final String PROVIDE_URL_MESSAGE = "Please provide url";
  private static final String PROVIDE_VALID_URL_MESSAGE = "Please provide a valid url...";
  private static final String NOW_PLAYING_MESSAGE = "Now playing...";

  public static MessageEmbed getProvideUrlMessage() {
    EmbedBuilder info = new EmbedBuilder();
    info.setTitle("\uD83C\uDF08 Guild Girl Bot");
    info.setDescription(PROVIDE_URL_MESSAGE);
    return info.build();
  }

  public static MessageEmbed getProvideValidUrlMessage() {
    EmbedBuilder info = new EmbedBuilder();
    info.setTitle("\uD83C\uDF08 Guild Girl Bot");
    info.setDescription(PROVIDE_VALID_URL_MESSAGE);
    return info.build();
  }

  public static MessageEmbed getNowPlayingMessage() {
    EmbedBuilder info = new EmbedBuilder();
    info.setTitle("\uD83C\uDF08 Guild Girl Bot");
    info.setDescription(NOW_PLAYING_MESSAGE);
    return info.build();
  }
}
