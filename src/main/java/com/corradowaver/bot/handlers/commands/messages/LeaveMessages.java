package com.corradowaver.bot.handlers.commands.messages;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Objects;

public class LeaveMessages {

  private static final String NOT_HERE_MESSAGE = "I'm not here";
  private static final String JOIN_A_VOICE_CHANNEL_MESSAGE = "Join a voice channel first, [nickname] >:(";
  private static final String LEFT_MESSAGE = "Ok, I left this chat, bye";

  private LeaveMessages() {
  }

  public static MessageEmbed getNotHereMessage(GuildMessageReceivedEvent event) {
    EmbedBuilder info = new EmbedBuilder();
    info.setTitle("\uD83C\uDF08 Guild Girl Bot");
    info.setDescription(NOT_HERE_MESSAGE);
    return info.build();
  }

  public static MessageEmbed getJoinAVoiceChannel(GuildMessageReceivedEvent event) {
    EmbedBuilder info = new EmbedBuilder();
    info.setTitle("\uD83C\uDF08 Guild Girl Bot");
    var nickname = Objects.requireNonNull(event.getMember()).getAsMention();
    info.setDescription(JOIN_A_VOICE_CHANNEL_MESSAGE.replace("[nickname]", nickname));
    return info.build();
  }

  public static MessageEmbed getLeftMessage() {
    EmbedBuilder info = new EmbedBuilder();
    info.setTitle("\uD83C\uDF08 Guild Girl Bot");
    info.setDescription(LEFT_MESSAGE);
    return info.build();
  }
}
