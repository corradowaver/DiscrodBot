package com.corradowaver.bot.handlers.commands;

import com.corradowaver.bot.handlers.commands.messages.LightMessages;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LightCommand implements GuildGirlCommand {

  @Override
  public MessageEmbed execute(GuildMessageReceivedEvent event) {
    String TURN_ON_LIGHT = "TURN ON LIGHT";
    try {
      File monitor = new File("/dev/ttyACM0");
      FileWriter fooWriter = new FileWriter(monitor, false);
      fooWriter.write(TURN_ON_LIGHT);
      fooWriter.close();
      return LightMessages.getLightOnMessage(event);
    } catch (IOException ex) {
      ex.printStackTrace();
      return LightMessages.getLightNotWorksMessage();
    }
  }
}
