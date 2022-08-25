package com.example.dailyexpenses.commands;

import com.example.dailyexpenses.Utils;
import com.example.dailyexpenses.entity.UserEntity;
import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Date;

import static com.example.dailyexpenses.bot.DailyExpensesBot.getUSERS_STEP;
import static com.example.dailyexpenses.bot.DailyExpensesBot.setUSERS_STEP;
import static com.example.dailyexpenses.controller.UserController.*;


public class StartCommand extends ServiceCommands {

    public StartCommand(String identifier, String description) {
        super(identifier, description);
    }

    private final SendMessage sendMessage=new SendMessage();

    private SendMessage getSendMessage(long chatId) {
        sendMessage.setText("*Unable!*");
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.enableMarkdown(true);
        return sendMessage;
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String userName = Utils.getUserName(user);
        //TODO bu pastdagini o'chirvorish kk faqat serverga qoyiganidan keyin tugri ishlaydi
        setUSERS_STEP(chat.getId(),0);
        if (!checkUser(chat.getId())) {
            UserEntity user1 = new UserEntity(chat.getId(), userName, new Date());
            setUSERS_STEP(chat.getId(),0);
            addUser(user1);
        }
        if (getUSERS_STEP(chat.getId()) == 0) {
            sendAnswer(absSender, chat.getId(), this.getCommandIdentifier(), userName, getSTART_RESPONSE_M());
        } else {
            try {
                absSender.execute(getSendMessage(chat.getId()));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    private String getSTART_RESPONSE_M() {
        return "*Welcome to Daily Costs bot!* " + EmojiParser.parseToUnicode(":on:") + "\n"
                + "---------------------------------" + "\n"
                + EmojiParser.parseToUnicode(":one:") + "No any logs! " + EmojiParser.parseToUnicode(":iphone:") + "\n"
                + EmojiParser.parseToUnicode(":two:") + "No subscriptions for channels! " + EmojiParser.parseToUnicode(":no_entry:") + "\n"
                + EmojiParser.parseToUnicode(":three:") + "Fully free!   " + EmojiParser.parseToUnicode(":free:") + "\n"
                + EmojiParser.parseToUnicode(":four:") + "Easy to use! " + EmojiParser.parseToUnicode(":white_check_mark:") + "\n"
                + "---------------------------------" + "\n"
                + ">Use commands from the menu  " + EmojiParser.parseToUnicode(":arrow_upper_right:") + "\n"
                + "---------------------------------" + "\n"
                + ">Input and manage your spends! " + EmojiParser.parseToUnicode(":money_with_wings:") + "\n"
                + "---------------------------------" + "\n"
                + "Click to /help to get more instructions\n"
                + "---------------------------------\n"
                + "@DailyCostsBot  ///" + "  *Enjoy It!!! *" + EmojiParser.parseToUnicode(":wink:") + "\n"
                + "---------------------------------";

    }
}
