package com.corradowaver.bot.handlers.commands;

import com.corradowaver.bot.handlers.commands.messages.PlayMessages;
import com.corradowaver.bot.handlers.services.music.PlayManager;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;

import static com.corradowaver.bot.handlers.validators.PlayValidator.isUrl;
import static com.corradowaver.bot.listeners.MessageListener.getArgs;

public class PlayCommand implements GuildGirlCommand {

  @Override
  public MessageEmbed execute(GuildMessageReceivedEvent event) {
    List<String> args = getArgs(event);
    if (args.isEmpty()) {
      return PlayMessages.getProvideUrlMessage();
    }
    String trackUrl = args.get(0);
    if (!isUrl(trackUrl)) {
      return PlayMessages.getProvideValidUrlMessage();
    }
    PlayManager manager = PlayManager.getInstance();
    manager.loadAndPlay(event.getChannel(), trackUrl);
    manager.getGuildMusicManager(event.getGuild()).player.setVolume(50);
    return PlayMessages.getNowPlayingMessage();
  }
}
