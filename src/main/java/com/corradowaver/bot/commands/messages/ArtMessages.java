package com.corradowaver.bot.commands.messages;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;
import java.util.Objects;

public class ArtMessages {

  private ArtMessages() {
  }

  public static MessageEmbed getMessage(GuildMessageReceivedEvent event, String text) {
    EmbedBuilder response = new EmbedBuilder();
    response.setDescription("```" + text + "```");
    response.setColor(Color.BLACK);
    return response.build();
  }

  public static MessageEmbed getErrorMessage(String error) {
    EmbedBuilder response = new EmbedBuilder();
    response.setTitle("\uD83D\uDD34 Runtime Error");
    response.setDescription("Service currently unavailable\n" + error);
    response.setColor(Color.RED);
    return response.build();
  }

  public static MessageEmbed getInvalidArgumentMessage(GuildMessageReceivedEvent event) {
    String nickname = Objects.requireNonNull(event.getMember()).getAsMention();
    EmbedBuilder response = new EmbedBuilder();
    response.setTitle("\uD83D\uDD34 Too long");
    response.setDescription("I tried to make it, but it too huge for me ," + nickname + " senpai.");
    response.setColor(Color.RED);
    return response.build();
  }
}
