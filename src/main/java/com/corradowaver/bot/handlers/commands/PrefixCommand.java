package com.corradowaver.bot.handlers.commands;

import com.corradowaver.bot.GuildGirlBot;
import com.corradowaver.bot.handlers.commands.messages.PrefixMessages;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;

import static com.corradowaver.bot.listeners.MessageListener.getArgs;

public class PrefixCommand implements GuildGirlCommand {

  @Override
  public MessageEmbed execute(GuildMessageReceivedEvent event) {
    List<String> args = getArgs(event);
    String regex = "^[a-zA-Z0-9!#?*%$^]+$";
    if (!args.isEmpty() && args.get(0).matches(regex)) {
      String newPrefix = args.get(0);
      GuildGirlBot.setPrefix(newPrefix);
      return PrefixMessages.getSuccessMessage();
    }
    return PrefixMessages.getInvalidArgumentsMessage();
  }
}
