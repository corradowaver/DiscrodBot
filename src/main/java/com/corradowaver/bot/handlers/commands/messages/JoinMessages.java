package com.corradowaver.bot.handlers.commands.messages;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Objects;

public class JoinMessages {

  private static final String ALREADY_HERE_MESSAGE = "I'm already here, [nickname] senpai.";
  private static final String JOIN_A_VOICE_CHANNEL_MESSAGE = "Join a voice channel first, [nickname] >:(";
  private static final String NO_PERMISSION_MESSAGE = "Oh no, I'm missing permission to join";
  private static final String JOINED_MESSAGE = "Joined";

  private JoinMessages() {
  }

  public static MessageEmbed getAlreadyHereMessage(GuildMessageReceivedEvent event) {
    EmbedBuilder info = new EmbedBuilder();
    info.setTitle("\uD83C\uDF08 Guild Girl Bot");
    var nickname = Objects.requireNonNull(event.getMember()).getAsMention();
    info.setDescription(ALREADY_HERE_MESSAGE.replace("[nickname]", nickname));
    return info.build();
  }

  public static MessageEmbed getJoinAVoiceChannel(GuildMessageReceivedEvent event) {
    EmbedBuilder info = new EmbedBuilder();
    info.setTitle("\uD83C\uDF08 Guild Girl Bot");
    var nickname = Objects.requireNonNull(event.getMember()).getAsMention();
    info.setDescription(JOIN_A_VOICE_CHANNEL_MESSAGE.replace("[nickname]", nickname));
    return info.build();
  }

  public static MessageEmbed getNoPermissionMessage() {
    EmbedBuilder info = new EmbedBuilder();
    info.setTitle("\uD83C\uDF08 Guild Girl Bot");
    info.setDescription(NO_PERMISSION_MESSAGE);
    return info.build();
  }

  public static MessageEmbed getJoinedMessage() {
    EmbedBuilder info = new EmbedBuilder();
    info.setTitle("\uD83C\uDF08 Guild Girl Bot");
    info.setDescription(JOINED_MESSAGE);
    return info.build();
  }
}
