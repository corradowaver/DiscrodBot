package com.corradowaver.bot.handlers.commands.messages;

import com.corradowaver.bot.handlers.entities.ImageBody;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;

/*
  Class holds pre-built MessageEmbed response for prefix command.
 */
public class ImageMessages {

  private ImageMessages() {
  }

  public static MessageEmbed getImageMessage(String nickname, ImageBody image) {
    EmbedBuilder response = new EmbedBuilder();
    response.setDescription("Your " + image.getName() + ". "
        + nickname + " senpai.");
    response.setImage(image.getURL());
    response.setColor(Color.GREEN);
    return response.build();
  }

  public static MessageEmbed getInvalidArgumentsMessage() {
    EmbedBuilder response = new EmbedBuilder();
    response.setTitle("\uD83D\uDD34 Invalid Arguments");
    response.setDescription("Ask me to send you pic by typing: \n" +
        "`[prefix]send [query]`");
    response.setColor(Color.RED);
    return response.build();
  }

  public static MessageEmbed getErrorMessage(String error) {
    EmbedBuilder response = new EmbedBuilder();
    response.setTitle("\uD83D\uDD34 Runtime Error");
    response.setDescription(error);
    response.setColor(Color.RED);
    return response.build();
  }
}
