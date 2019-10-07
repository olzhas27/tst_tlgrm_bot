package com.github.olzhas27.bot;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
public class Bot extends TelegramLongPollingBot {
    private final String name;
    private final String token;

    Bot(String name, String token, DefaultBotOptions options) {
        super(options);
        this.name = name;
        this.token = token;
    }

    public String getBotToken() {
        return token;
    }

    public String getBotUsername() {
        return name;
    }

    public void onUpdateReceived(Update update) {
        val message = update.getMessage().getText();
        log.debug("received message: {}", message);
        sendMessage(update.getMessage().getChatId().toString(), message);
    }

    private void sendMessage(String chatId, String message) {
        val sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        try {
            execute(sendMessage);
            log.debug("sent message: {}", sendMessage.toString());
        } catch (TelegramApiException e) {
            log.error("Couldn't send message", e);
        }
    }
}
