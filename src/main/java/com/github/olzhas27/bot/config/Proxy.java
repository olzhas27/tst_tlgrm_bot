package com.github.olzhas27.bot.config;

import com.github.olzhas27.util.PropertyUtil;
import lombok.Getter;
import lombok.val;
import org.telegram.telegrambots.bots.DefaultBotOptions;

@Getter
public class Proxy {
    private final String host;
    private final int port;
    private final DefaultBotOptions.ProxyType proxyType;

    public Proxy() {
        val PROXY_PROPERTIES_FILE = "proxy.properties";
        val properties = PropertyUtil.getProperties(PROXY_PROPERTIES_FILE);
        host = properties.getProperty("proxy.host", "127.0.0.1");
        port = Integer.parseInt(properties.getProperty("proxy.port", "9150"));
        proxyType = DefaultBotOptions.ProxyType.valueOf(properties.getProperty("proxy.type", "SOCKS5"));
    }
}
