package com.github.olzhas27;

import com.github.olzhas27.bot.BotFactory;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

@Slf4j
public class TestTelegarmBotApp {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        val telegramBotsApi = new TelegramBotsApi();
        try {
            val botSession = telegramBotsApi.registerBot(BotFactory.newBot());
        } catch (TelegramApiRequestException e) {
            log.error("Some errors", e);
        }
    }
}
