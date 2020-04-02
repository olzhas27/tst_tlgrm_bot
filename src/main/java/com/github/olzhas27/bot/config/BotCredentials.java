package com.github.olzhas27.bot.config;

import com.github.olzhas27.util.PropertyUtil;
import lombok.Getter;
import lombok.val;

import java.util.Properties;

@Getter
public class BotCredentials {
    public static final BotCredentials DEFAULT = new BotCredentials();
    private final String userName;
    private final String token;

    private BotCredentials() {
        val props = loadBotCredentials();
        userName = props.getProperty("bot.name");
        token = props.getProperty("bot.token");
    }

    private Properties loadBotCredentials() {
        var props = PropertyUtil.getProperties("app.properties");
        props = PropertyUtil.getProperties(props.getProperty("credentials.file", "credentials.properties"));
        return props;
    }
}
