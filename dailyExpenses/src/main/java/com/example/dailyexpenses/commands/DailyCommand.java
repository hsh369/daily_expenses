package com.example.dailyexpenses.commands;

import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.groupadministration.SetChatPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static com.example.dailyexpenses.bot.DailyExpensesBot.getUSERS_STEP;
import static com.example.dailyexpenses.bot.DailyExpensesBot.setUSERS_STEP;
import static com.example.dailyexpenses.front.InlineMarkupClass.getInlineForDaily;


public class DailyCommand extends BotCommand {

    private final SendMessage sendMessage = new SendMessage();
    private final SendMessage sendMessage1=new SendMessage();

    public DailyCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        if (getUSERS_STEP(chat.getId())==0) {
            setUSERS_STEP(chat.getId(),2);
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
        sendMessage.setText("*Select type:*");
        sendMessage.enableWebPagePreview();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setReplyMarkup(getInlineForDaily());
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
