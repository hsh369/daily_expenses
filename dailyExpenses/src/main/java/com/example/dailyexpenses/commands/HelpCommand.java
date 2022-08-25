package com.example.dailyexpenses.commands;

import com.example.dailyexpenses.Utils;
import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static com.example.dailyexpenses.bot.DailyExpensesBot.getUSERS_STEP;
import static com.example.dailyexpenses.controller.UserController.getUsersNum;

public class HelpCommand extends ServiceCommands {
    public HelpCommand(String identifier, String description) {
        super(identifier, description);
    }


    private final SendMessage sendMessage = new SendMessage();

    private SendMessage getSendMessage(long chatId) {
        sendMessage.setText("*Unable!*");
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.enableMarkdown(true);
        return sendMessage;
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        if (getUSERS_STEP(chat.getId()) == 0) {
            sendAnswer(absSender, chat.getId(), this.getCommandIdentifier(), Utils.getUserName(user), getSTART_RESPONSE_M());
        } else {
            try {
                absSender.execute(getSendMessage(chat.getId()));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    private String getSTART_RESPONSE_M() {
        return "----------*Help Center*" + EmojiParser.parseToUnicode(":bulb:") + "----------\n" +
                "*1)* " + "Click menu button" + EmojiParser.parseToUnicode(":open_file_folder:") + "\n" +
                "*2)* " + "Select command you want" + EmojiParser.parseToUnicode(":mag:") + "\n" +
                "*3)* " + "Add your products using /add command" + EmojiParser.parseToUnicode(":heavy_plus_sign:") + "\n" +
                "*4)* " + "Get your spends information using other commands" + EmojiParser.parseToUnicode(":bar_chart:") + "\n" +
                "*5)* *Important:* You can *update* or *delete* only today's items! Choose them from /daily command.  \n"+
                "----------*About us*" + EmojiParser.parseToUnicode(":clipboard:") + "----------\n" +
                ">>>This is project of IUT student" + EmojiParser.parseToUnicode(":male_technologist:") + "\n" +
                ">>>Project Author: Khasanov Shakhboz\n" +
                ">>>Project year: " + EmojiParser.parseToUnicode(":two:") + EmojiParser.parseToUnicode(":zero:") + EmojiParser.parseToUnicode(":two:") + EmojiParser.parseToUnicode(":two:") + "\n" +
                ">>>The number of bot users: " + getUsersNum() + " " + EmojiParser.parseToUnicode(":boy:") + EmojiParser.parseToUnicode(":girl:") + "\n" +
                "------------------------------\n" +
                "☎️ Contact with @HShaxboz7 for any offers!" + "\n" +
                "------------------------------";
    }
}
