package com.github.olzhas27.bot;

import lombok.val;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Objects;
import java.util.Properties;

public class BotFactory {
    public static Bot newBot() {
        val props = new Properties();
        try (InputStream is = Bot.class.getClassLoader().getResourceAsStream("app.properties")) {
            props.load(is);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        val botOptions = ApiContext.getInstance(DefaultBotOptions.class);
        botOptions.setProxyHost(props.getProperty("proxy.host", "127.0.0.1"));
        val port = Integer.parseInt(props.getProperty("proxy.port", "9150"));
        botOptions.setProxyPort(port);
        // Select proxy type: [HTTP|SOCKS4|SOCKS5] (default: NO_PROXY)
        botOptions.setProxyType(DefaultBotOptions.ProxyType.SOCKS5);
        val name = props.getProperty("bot.name");
        val token = props.getProperty("bot.token");
        Objects.requireNonNull(name);
        Objects.requireNonNull(token);
        return new Bot(name, token, botOptions);
    }
}
