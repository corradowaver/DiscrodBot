package com.corradowaver.bot.listeners;

import com.corradowaver.bot.GuildGirlBot;
import com.corradowaver.bot.commands.messages.InfoMessages;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import static com.corradowaver.bot.commands.Commands.*;
import static com.corradowaver.bot.commands.handlers.EventHandler.*;

public class MessageListener extends ListenerAdapter {

  public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
    TextChannel channel = event.getChannel();
    setTypingStatus(channel);
    String[] args = event.getMessage().getContentRaw().split("\\s+");
    String message = args[0].toLowerCase();
    String prefix = GuildGirlBot.getPrefix();
    if (message.startsWith(prefix)) {
      String command = message.substring(prefix.length());
      switch (command) {
        case PREFIX -> sendResponse(channel, getPrefixCommandMessage(event));
        case INFO -> sendResponse(channel, InfoMessages.getMessage(event));
        case SEND -> sendResponse(channel, getSearchImageCommandMessage(event));
        case PING -> sendResponse(channel, getPingCommandMessage(event));
        case BTC -> sendResponse(channel, getBitcoinCommandMessage(event));
        case LIGHT -> sendResponse(channel, getLightCommandMessage(event));
        case ART -> sendResponse(channel, getArtCommandMessage(event));
        case JOIN -> sendResponse(channel, getJoinCommandMessage(event));
        case LEAVE -> sendResponse(channel, getLeaveCommandMessage(event));
        case PLAY -> sendResponse(channel, getPlayCommandMessage(event));
        default -> sendResponse(channel, getUnknownCommandMessage());
      }
    }
  }

  private void sendResponse(TextChannel channel, MessageEmbed messageEmbed) {
    channel.sendMessage(messageEmbed).queue();
  }

  private void sendResponse(TextChannel channel, String message) {
    channel.sendMessage(message).queue();
  }

  private void setTypingStatus(TextChannel channel) {
    channel.sendTyping().queue();
  }
}
