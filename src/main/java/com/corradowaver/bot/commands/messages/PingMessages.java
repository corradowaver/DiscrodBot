package com.corradowaver.bot.commands.messages;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;

/*
  Class holds pre-built MessageEmbed response for ping command.
 */
public class PingMessages {

  private PingMessages() {
  }

  public static MessageEmbed getMessage(long latency) {
    EmbedBuilder info = new EmbedBuilder();
    info.setTitle("\uD83C\uDF08 Pong");
    info.setDescription("Server latency: " + latency + "ms");
    info.setColor(Color.GREEN);
    info.setFooter("powered by corradowaver");
    return info.build();
  }
}
