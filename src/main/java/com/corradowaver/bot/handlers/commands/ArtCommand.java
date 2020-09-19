package com.corradowaver.bot.handlers.commands;

import com.corradowaver.bot.handlers.commands.messages.ArtMessages;
import com.corradowaver.bot.handlers.services.art.ArtService;
import com.mashape.unirest.http.exceptions.UnirestException;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;

import static com.corradowaver.bot.handlers.validators.ArtValidator.isValidForOutput;
import static com.corradowaver.bot.listeners.MessageListener.getArgs;

public class ArtCommand implements GuildGirlCommand {

  @Override
  public MessageEmbed execute(GuildMessageReceivedEvent event) {
    String FONT = "doom";
    List<String> args = getArgs(event);
    MessageEmbed response;
    if (!args.isEmpty()) {
      String text = String.join("+", args);
      try {
        String art = ArtService.getAsciiArt(text, FONT);
        if (isValidForOutput(art)) {
          response = ArtMessages.getMessage(event, art);
        } else {
          response = ArtMessages.getInvalidArgumentMessage(event);
        }
      } catch (UnirestException e) {
        e.printStackTrace();
        response = ArtMessages.getErrorMessage(e.getLocalizedMessage());
      }
    } else {
      response = ArtMessages.getInvalidArgumentMessage(event);
    }
    return response;
  }
}
