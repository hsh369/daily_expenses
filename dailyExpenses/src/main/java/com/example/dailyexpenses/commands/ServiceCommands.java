package com.example.dailyexpenses.commands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

abstract class ServiceCommands extends BotCommand {

    private final SendMessage message = new SendMessage();

    ServiceCommands(String identifier, String description) {
        super(identifier, description);
        message.enableMarkdown(true);
    }

    void sendAnswer(AbsSender absSender, Long chatId, String commandName, String userName, String text) {
        message.setChatId(chatId.toString());
        message.setText(text);
        try {
            absSender.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
