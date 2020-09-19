package com.corradowaver.bot.handlers.commands.messages;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;
import java.util.Objects;

/*
  Class holds pre-built MessageEmbed response for info command.
 */
public class InfoMessages {

  private static final String INFO_MESSAGE = "I'm here to help you, [nickname] senpai.";
  private static final String[] COMMANDS_LIST = {
      "`info`   - info",
      "`prefix` - change prefix",
      "`send`   - send picture",
      "`btc`    - current bitcoin rates",
      "`ping`   - server latency",
      "`art`    - make an ascii art"
  };

  private InfoMessages() {
  }

  public static MessageEmbed getMessage(GuildMessageReceivedEvent event) {
    EmbedBuilder info = new EmbedBuilder();
    info.setTitle("\uD83C\uDF08 Guild Girl Bot");
    var nickname = Objects.requireNonNull(event.getMember()).getAsMention();
    info.setDescription(INFO_MESSAGE.replace("[nickname]", nickname) +
        " There is a list of available commands:\n " +
        String.join("\n", COMMANDS_LIST));
    info.setColor(Color.GREEN);
    info.setFooter("powered by corradowaver");
    return info.build();
  }
}
