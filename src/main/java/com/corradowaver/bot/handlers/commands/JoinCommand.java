package com.corradowaver.bot.handlers.commands;

import com.corradowaver.bot.handlers.commands.messages.JoinMessages;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;

import java.util.Objects;

public class JoinCommand implements GuildGirlCommand {

  @Override
  public MessageEmbed execute(GuildMessageReceivedEvent event) {
    AudioManager audioManager = event.getGuild().getAudioManager();
    if (audioManager.isConnected()) {
      return JoinMessages.getAlreadyHereMessage(event);
    }
    GuildVoiceState memberVoiceState = Objects.requireNonNull(event.getMember()).getVoiceState();
    assert memberVoiceState != null;
    if (!memberVoiceState.inVoiceChannel()) {
      return JoinMessages.getJoinAVoiceChannel(event);
    }
    VoiceChannel voiceChannel = memberVoiceState.getChannel();
    Member selfMember = event.getGuild().getSelfMember();
    assert voiceChannel != null;
    if (!selfMember.hasPermission(voiceChannel, Permission.VOICE_CONNECT)) {
      return JoinMessages.getNoPermissionMessage();
    }
    audioManager.openAudioConnection(voiceChannel);
    return JoinMessages.getJoinedMessage();
  }
}
