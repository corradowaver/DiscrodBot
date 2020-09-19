package com.corradowaver.bot.handlers.commands;

import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public interface GuildGirlCommand {
  MessageEmbed execute(GuildMessageReceivedEvent event);
}
