package com.github.olzhas27.bot;

import com.github.olzhas27.bot.config.BotCredentials;
import com.github.olzhas27.bot.config.Proxy;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;

@Slf4j
public class BotFactory {
    public static ParrotBot newParrotBot() {
        val credentials = BotCredentials.DEFAULT;
        val botOptions = getBotOptions();
        return new ParrotBot(credentials, botOptions);
    }

    private static DefaultBotOptions getBotOptions() {
        val botOptions = ApiContext.getInstance(DefaultBotOptions.class);
        val proxy = new Proxy();
        botOptions.setProxyHost(proxy.getHost());
        botOptions.setProxyPort(proxy.getPort());
        botOptions.setProxyType(proxy.getProxyType());
        return botOptions;
    }
}
