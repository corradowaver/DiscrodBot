package com.corradowaver.bot.handlers.commands;

import com.corradowaver.bot.handlers.commands.messages.PingMessages;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.time.OffsetDateTime;

public class PingCommand implements GuildGirlCommand {

  @Override
  public MessageEmbed execute(GuildMessageReceivedEvent event) {
    long messageTime = event.getMessage().getTimeCreated().toInstant().toEpochMilli();
    long now = OffsetDateTime.now().toInstant().toEpochMilli();
    long latency = now - messageTime;
    return PingMessages.getMessage(latency);
  }
}
