package com.github.olzhas27.bot;

import com.github.olzhas27.bot.config.BotCredentials;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
public class Bot extends TelegramLongPollingBot {
    private final BotCredentials credentials;

    Bot(BotCredentials credentials, DefaultBotOptions options) {
        super(options);
        this.credentials = credentials;
    }

    @Override
    public String getBotUsername() {
        return credentials.getUserName();
    }

    @Override
    public String getBotToken() {
        return credentials.getToken();
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
