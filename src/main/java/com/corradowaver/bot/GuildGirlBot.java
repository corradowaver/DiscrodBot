package com.corradowaver.bot;

import com.corradowaver.bot.config.PropsManager;
import com.corradowaver.bot.listeners.GuildGirlJoinListener;
import com.corradowaver.bot.listeners.MessageListener;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class GuildGirlBot {

  private static JDA jda;

  private static String prefix = "!";

  public static void main(String[] args) throws LoginException {

    final String token = PropsManager.getProps().getProperty("discord.bot.token");

    jda = new JDABuilder(AccountType.BOT).setToken(token).build();
    jda.getPresence().setStatus(OnlineStatus.ONLINE);
    jda.getPresence().setActivity(Activity.listening("lofi radio"));
    jda.addEventListener(new MessageListener());
    jda.addEventListener(new GuildGirlJoinListener());
  }

  public static String getPrefix() {
    return prefix;
  }

  public static void setPrefix(String prefix) {
    GuildGirlBot.prefix = prefix.toLowerCase();
  }
}
