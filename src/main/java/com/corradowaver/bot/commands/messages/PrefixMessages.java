package com.corradowaver.bot.commands.messages;

import com.corradowaver.bot.GuildGirlBot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;

/*
  Class holds pre-built MessageEmbed response for prefix command.
 */
public class PrefixMessages {

  private PrefixMessages() {
  }

  public static MessageEmbed getSuccessMessage() {
    EmbedBuilder info = new EmbedBuilder();
    info.setTitle("\uD83D\uDFE2 Prefix changed to `" + GuildGirlBot.getPrefix() + "`.");
    info.setColor(Color.GREEN);
    return info.build();
  }

  public static MessageEmbed getInvalidArgumentsMessage() {
    EmbedBuilder info = new EmbedBuilder();
    info.setTitle("\uD83D\uDD34 Invalid Arguments");
    info.setDescription("To change prefix type: \n" +
        "`[prefix]prefix [new prefix]`");
    info.setColor(Color.RED);
    return info.build();
  }
}
