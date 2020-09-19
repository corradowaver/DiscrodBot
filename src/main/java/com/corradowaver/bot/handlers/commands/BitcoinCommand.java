package com.corradowaver.bot.handlers.commands;

import com.corradowaver.bot.handlers.commands.messages.BitcoinMessages;
import com.corradowaver.bot.handlers.entities.BitcoinBody;
import com.corradowaver.bot.handlers.services.bitcoin.BitcoinService;
import com.mashape.unirest.http.exceptions.UnirestException;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;

public class BitcoinCommand implements GuildGirlCommand {

  @Override
  public MessageEmbed execute(GuildMessageReceivedEvent event) {
    try {
      List<BitcoinBody> rates = BitcoinService.getRates();
      return BitcoinMessages.getMessage(event, rates);
    } catch (UnirestException e) {
      e.printStackTrace();
      return BitcoinMessages.getErrorMessage(e.getLocalizedMessage());
    }
  }
}
