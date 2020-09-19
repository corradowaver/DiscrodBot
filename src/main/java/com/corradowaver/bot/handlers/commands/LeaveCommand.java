package com.corradowaver.bot.handlers.commands;

import com.corradowaver.bot.handlers.commands.messages.LeaveMessages;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;

public class LeaveCommand implements GuildGirlCommand {

  @Override
  public MessageEmbed execute(GuildMessageReceivedEvent event) {
    AudioManager audioManager = event.getGuild().getAudioManager();
    if (!audioManager.isConnected()) {
      return LeaveMessages.getNotHereMessage(event);
    }
    VoiceChannel voiceChannel = audioManager.getConnectedChannel();
    assert voiceChannel != null;
    if (!voiceChannel.getMembers().contains(event.getMember())) {
      return LeaveMessages.getJoinAVoiceChannel(event);
    }
    audioManager.closeAudioConnection();
    return LeaveMessages.getLeftMessage();
  }
}
