package com.corradowaver.bot.commands.handlers;

import com.corradowaver.bot.GuildGirlBot;
import com.corradowaver.bot.commands.handlers.entities.BitcoinBody;
import com.corradowaver.bot.commands.messages.*;
import com.corradowaver.bot.commands.services.art.ArtService;
import com.corradowaver.bot.commands.services.bitcoin.BitcoinService;
import com.corradowaver.bot.commands.services.images.ImageService;
import com.corradowaver.bot.commands.services.music.PlayManager;
import com.mashape.unirest.http.exceptions.UnirestException;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.corradowaver.bot.commands.handlers.validators.ArtValidator.isValidForOutput;
import static com.corradowaver.bot.commands.handlers.validators.PlayValidator.isUrl;

public class EventHandler {

  private EventHandler() {
  }

  public static MessageEmbed getPrefixCommandMessage(GuildMessageReceivedEvent event) {
    List<String> args = getArgs(event);
    String regex = "^[a-zA-Z0-9!#?*%$^]+$";
    if (!args.isEmpty() && args.get(0).matches(regex)) {
      String newPrefix = args.get(0);
      GuildGirlBot.setPrefix(newPrefix);
      return PrefixMessages.getSuccessMessage();
    }
    return PrefixMessages.getInvalidArgumentsMessage();
  }

  public static MessageEmbed getSearchImageCommandMessage(GuildMessageReceivedEvent event) {
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

  public static String getLightCommandMessage(GuildMessageReceivedEvent event) {
    String TURN_ON_LIGHT = "TURN ON LIGHT";
    try {
      File monitor = new File("/dev/ttyACM0");
      FileWriter fooWriter = new FileWriter(monitor, false); // true to append
      fooWriter.write(TURN_ON_LIGHT);
      fooWriter.close();
      return "now one little diode is glowing for you, senpai";
    } catch (IOException ex) {
      ex.printStackTrace();
      return "oh no, i could not light the diode...";
    }
  }

  public static MessageEmbed getPingCommandMessage(GuildMessageReceivedEvent event) {
    long messageTime = event.getMessage().getTimeCreated().toInstant().toEpochMilli();
    long now = OffsetDateTime.now().toInstant().toEpochMilli();
    long latency = now - messageTime;
    return PingMessages.getMessage(latency);
  }

  public static MessageEmbed getBitcoinCommandMessage(GuildMessageReceivedEvent event) {
    try {
      List<BitcoinBody> rates = BitcoinService.getRates();
      return BitcoinMessages.getMessage(event, rates);
    } catch (UnirestException e) {
      e.printStackTrace();
      return BitcoinMessages.getErrorMessage(e.getLocalizedMessage());
    }
  }

  public static MessageEmbed getArtCommandMessage(GuildMessageReceivedEvent event) {
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

  public static String getJoinCommandMessage(GuildMessageReceivedEvent event) {
    AudioManager audioManager = event.getGuild().getAudioManager();
    if (audioManager.isConnected()) {
      return "already here";
    }
    GuildVoiceState memberVoiceState = Objects.requireNonNull(event.getMember()).getVoiceState();
    assert memberVoiceState != null;
    if (!memberVoiceState.inVoiceChannel()) {
      return "join a voice channel first";
    }
    VoiceChannel voiceChannel = memberVoiceState.getChannel();
    Member selfMember = event.getGuild().getSelfMember();
    assert voiceChannel != null;
    if (!selfMember.hasPermission(voiceChannel, Permission.VOICE_CONNECT)) {
      return "missing permission to join";
    }
    audioManager.openAudioConnection(voiceChannel);
    return "joined";
  }

  public static String getLeaveCommandMessage(GuildMessageReceivedEvent event) {
    AudioManager audioManager = event.getGuild().getAudioManager();
    if (!audioManager.isConnected()) {
      return "i'm not here";
    }
    VoiceChannel voiceChannel = audioManager.getConnectedChannel();
    assert voiceChannel != null;
    if (!voiceChannel.getMembers().contains(event.getMember())) {
      return "join my voice channel first";
    }
    audioManager.closeAudioConnection();
    return "left this chat";
  }

  public static String getPlayCommandMessage(GuildMessageReceivedEvent event) {
    List<String> args = getArgs(event);
    if (args.isEmpty()) {
      return "provide url";
    }
    String trackUrl = args.get(0);
    if (!isUrl(trackUrl)) {
      return "provide valid url";
    }
    PlayManager manager = PlayManager.getInstance();
    manager.loadAndPlay(event.getChannel(), trackUrl);
    manager.getGuildMusicManager(event.getGuild()).player.setVolume(50);
    return "now playing";
  }

  public static String getUnknownCommandMessage() {
    return "sorry, i don't know this command";
  }

  private static List<String> getArgs(GuildMessageReceivedEvent event) {
    return Arrays.stream(event.getMessage().getContentRaw().split("\\s+"))
        .skip(1)
        .collect(Collectors.toList());
  }
}
