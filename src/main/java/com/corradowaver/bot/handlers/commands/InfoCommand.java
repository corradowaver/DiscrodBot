package com.corradowaver.bot.handlers.commands;

import com.corradowaver.bot.handlers.commands.messages.InfoMessages;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class InfoCommand implements GuildGirlCommand {


  @Override
  public MessageEmbed execute(GuildMessageReceivedEvent event) {
    return InfoMessages.getMessage(event);
  }
}
