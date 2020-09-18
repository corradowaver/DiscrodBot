package com.corradowaver.bot.commands.messages;

import com.corradowaver.bot.commands.handlers.entities.BitcoinBody;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;
import java.util.List;

public class BitcoinMessages {

  private BitcoinMessages() {
  }

  public static MessageEmbed getMessage(GuildMessageReceivedEvent event, List<BitcoinBody> rates) {
    EmbedBuilder response = new EmbedBuilder();
    response.setTitle("\uD83C\uDF08 Bitcoin Rates");
    StringBuilder description = new StringBuilder();
    for (BitcoinBody rate : rates) {
      description
          .append("\n")
          .append(rate.getSymbol()).append(" : ")
          .append(rate.getPrice());
    }

    response.setDescription(description.toString());
    response.setColor(Color.GREEN);
    return response.build();
  }

  public static MessageEmbed getErrorMessage(String error) {
    EmbedBuilder response = new EmbedBuilder();
    response.setTitle("\uD83D\uDD34 Runtime Error");
    response.setDescription("Service currently unavailable\n" + error);
    response.setColor(Color.RED);
    return response.build();
  }
}
