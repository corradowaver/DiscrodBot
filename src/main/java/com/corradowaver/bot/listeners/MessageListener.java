package com.corradowaver.bot.listeners;

import com.corradowaver.bot.GuildGirlBot;
import com.corradowaver.bot.handlers.commands.*;
import com.corradowaver.bot.handlers.commands.messages.InfoMessages;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.corradowaver.bot.handlers.commands.messages.Commands.*;

public class MessageListener extends ListenerAdapter {

  private final Map<String, GuildGirlCommand> commands;

  public MessageListener() {
    commands = new HashMap<>();
    commands.put(INFO, new InfoCommand());
    commands.put(PREFIX, new PrefixCommand());
    commands.put(SEND, new SearchImageCommand());
    commands.put(PING, new PingCommand());
    commands.put(BTC, new BitcoinCommand());
    commands.put(ART, new ArtCommand());
    commands.put(JOIN, new JoinCommand());
    commands.put(LEAVE, new LeaveCommand());
    commands.put(PLAY, new PlayCommand());
    commands.put(LIGHT, new LightCommand());
  }

  public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
    TextChannel channel = event.getChannel();
    String[] args = event.getMessage().getContentRaw().split("\\s+");
    String message = args[0].toLowerCase();
    String prefix = GuildGirlBot.getPrefix();
    if (message.startsWith(prefix)) {
      String command = message.substring(prefix.length());
      try {
        setTypingStatus(channel);
        sendResponse(channel, commands.get(command).execute(event));
      } catch (NullPointerException ex) {
        sendResponse(channel, getUnknownCommandMessage());
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

  private String getUnknownCommandMessage() {
    return "sorry, i don't know this command";
  }

  public static List<String> getArgs(GuildMessageReceivedEvent event) {
    return Arrays.stream(event.getMessage().getContentRaw().split("\\s+"))
        .skip(1)
        .collect(Collectors.toList());
  }
}
