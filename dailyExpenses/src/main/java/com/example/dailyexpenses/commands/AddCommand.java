package com.example.dailyexpenses.commands;

import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static com.example.dailyexpenses.bot.DailyExpensesBot.getUSERS_STEP;
import static com.example.dailyexpenses.bot.DailyExpensesBot.setUSERS_STEP;
import static com.example.dailyexpenses.front.InlineMarkupClass.getInlineForProType;

public class AddCommand extends BotCommand {

    private final SendMessage sendMessage = new SendMessage();
    private final SendMessage sendMessage1=new SendMessage();


    public AddCommand(String identifier, String description) {
        super(identifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        if (getUSERS_STEP(chat.getId())==0) {
            setUSERS_STEP(chat.getId(),1);
            try {
                absSender.execute(getSendMessage(chat.getId()));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else {
            try {
                absSender.execute(getSendMessage1(chat.getId()));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    private SendMessage getSendMessage(long chatId) {
        sendMessage.setText("*Choose Your Type of Payment:*");
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setReplyMarkup(getInlineForProType());
        sendMessage.enableMarkdown(true);
        return sendMessage;
    }

    private SendMessage getSendMessage1(long chatId) {
        sendMessage1.setText("*Unable!*");
        sendMessage1.setChatId(String.valueOf(chatId));
        sendMessage1.enableMarkdown(true);
        return sendMessage1;
    }
}
/*
 new idea map for(chatId, userStep)
* Product adding steps contains:
* userStep 0: general step to use all commands
* userStep 1: after choosing product type you can choose only payment type
* userStep 2: choose payment type
* */