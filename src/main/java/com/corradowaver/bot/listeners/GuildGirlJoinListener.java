package com.corradowaver.bot.listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.Objects;

public class GuildGirlJoinListener extends ListenerAdapter {

  private static final String JOIN_MESSAGE = "We've been waiting for you, [nickname] senpai.";

  public void onGuildMemberJoin(GuildMemberJoinEvent event) {
    EmbedBuilder message = new EmbedBuilder();
    message.setTitle("\uD83C\uDF08 Welcome Stranger");
    var nickname = Objects.requireNonNull(event.getMember()).getAsMention();
    message.setDescription(JOIN_MESSAGE.replace("[nickname]", nickname));
    message.setColor(Color.GREEN);
    event.getGuild().getDefaultChannel().sendMessage(message.build()).queue();
  }
}

