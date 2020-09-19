package com.corradowaver.bot.handlers.commands.messages;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Objects;

public class LightMessages {

  private static final String LIGHT_ON_MESSAGE = "Now one little diode is glowing for you [nickname], senpai";
  private static final String LIGHT_NOT_WORKS_MESSAGE = "Oh no, i could not light the diode...";

  private LightMessages() {
  }

  public static MessageEmbed getLightOnMessage(GuildMessageReceivedEvent event) {
    EmbedBuilder info = new EmbedBuilder();
    info.setTitle("\uD83C\uDF08 Guild Girl Bot");
    var nickname = Objects.requireNonNull(event.getMember()).getAsMention();
    info.setDescription(LIGHT_ON_MESSAGE.replace("[nickname]", nickname));
    return info.build();
  }

  public static MessageEmbed getLightNotWorksMessage() {
    EmbedBuilder info = new EmbedBuilder();
    info.setTitle("\uD83C\uDF08 Guild Girl Bot");
    info.setDescription(LIGHT_NOT_WORKS_MESSAGE);
    return info.build();
  }
}
