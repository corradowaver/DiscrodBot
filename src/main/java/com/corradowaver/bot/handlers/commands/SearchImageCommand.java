package com.corradowaver.bot.handlers.commands;

import com.corradowaver.bot.handlers.commands.messages.ImageMessages;
import com.corradowaver.bot.handlers.services.images.ImageService;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;
import java.util.Objects;

import static com.corradowaver.bot.listeners.MessageListener.getArgs;

public class SearchImageCommand implements GuildGirlCommand {

  @Override
  public MessageEmbed execute(GuildMessageReceivedEvent event) {
    List<String> args = getArgs(event);
    MessageEmbed response;
    if (!args.isEmpty()) {
      try {
        String searchQuery = String.join(" ", args);
        String nickname = Objects.requireNonNull(event.getMember()).getAsMention();
        response = ImageMessages.getImageMessage(nickname, ImageService.searchImages(searchQuery));
      } catch (Exception e) {
        e.printStackTrace();
        response = ImageMessages.getErrorMessage(e.getLocalizedMessage());
      }
    } else {
      response = ImageMessages.getInvalidArgumentsMessage();
    }
    return response;
  }
}
