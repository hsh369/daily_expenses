package com.example.dailyexpenses.bot;

import com.example.dailyexpenses.commands.AddCommand;
import com.example.dailyexpenses.commands.DailyCommand;
import com.example.dailyexpenses.commands.HelpCommand;
import com.example.dailyexpenses.commands.StartCommand;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static com.example.dailyexpenses.controller.ProductController.*;
import static com.example.dailyexpenses.controller.UserController.getUser;
import static com.example.dailyexpenses.front.InlineMarkupClass.getSaveCancel;

public class DailyExpensesBot extends TelegramLongPollingCommandBot {

    private final String BOT_NAME;
    private final String BOT_TOKEN;
    private final ResponseMessage RESPONSE_MESSAGE;
    private final static HashMap<Long, Integer> USERS_STEP = new HashMap<>();

    public static int getUSERS_STEP(long chatId) {
        if (!USERS_STEP.containsKey(chatId)) {
            setUSERS_STEP(chatId, 0);
        }
        return USERS_STEP.get(chatId);
    }

    public static void setUSERS_STEP(long chatId, int step) {
        USERS_STEP.put(chatId, step);
    }

    public DailyExpensesBot(String botName, String botToken) {
        this.BOT_NAME = botName;
        this.BOT_TOKEN = botToken;
        this.RESPONSE_MESSAGE = new ResponseMessage();

        register(new StartCommand("start", "Start"));
        register(new HelpCommand("help", "HelperCommand"));
        register(new AddCommand("add", "AddProduct"));
        register(new DailyCommand("daily", "DailyProducts"));
    }

    @Override
    public String getBotUsername() {
        return this.BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return this.BOT_TOKEN;
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            Long chatId = message.getChatId();
            if (message.hasText()) {
                switch (getUSERS_STEP(chatId)) {
                    case 0 -> {
                        RESPONSE_MESSAGE.getRESPONSE_USELESS().setChatId(String.valueOf(chatId));
                        executeSM(RESPONSE_MESSAGE.getRESPONSE_USELESS());
                    }
                    case 3 -> {
                        setUSERS_STEP(chatId, 4);
                        addProName(chatId, message.getText());
                        RESPONSE_MESSAGE.getEM_FOR_PRO_Amount().setChatId(String.valueOf(chatId));
                        executeSM(RESPONSE_MESSAGE.getEM_FOR_PRO_Amount());
                    }
                    case 4 -> {
                        if (isNumeric(message.getText())) {
                            setUSERS_STEP(chatId, 5);
                            addProAmount(chatId, new BigDecimal(message.getText()));
                            RESPONSE_MESSAGE.getEM_FOR_PRO_Price().setChatId(String.valueOf(chatId));
                            executeSM(RESPONSE_MESSAGE.getEM_FOR_PRO_Price());
                        } else {
                            RESPONSE_MESSAGE.getSM_WRONG_INPUT().setChatId(String.valueOf(chatId));
                            executeSM(RESPONSE_MESSAGE.getSM_WRONG_INPUT());
                        }
                    }
                    case 5 -> {
                        if (isNumeric(message.getText())) {
                            setUSERS_STEP(chatId, 1);
                            addProPrice(chatId, new BigDecimal(message.getText()));

                            SendMessage sendMessage = new SendMessage();
                            sendMessage.setChatId(String.valueOf(chatId));
                            sendMessage.setText(getProductDot(chatId));
                            sendMessage.setReplyMarkup(getSaveCancel());
                            executeSM(sendMessage);
                        } else {
                            RESPONSE_MESSAGE.getSM_WRONG_INPUT().setChatId(String.valueOf(chatId));
                            executeSM(RESPONSE_MESSAGE.getSM_WRONG_INPUT());
                        }
                    }
                    default -> {
                        RESPONSE_MESSAGE.getRESPONSE_TO_CONTINUE().setChatId(String.valueOf(chatId));
                        executeSM(RESPONSE_MESSAGE.getRESPONSE_TO_CONTINUE());
                    }
                }
            } else {
                RESPONSE_MESSAGE.getRESPONSE_NO_TXT().setChatId(String.valueOf(chatId));
                executeSM(RESPONSE_MESSAGE.getRESPONSE_NO_TXT());
            }
        }
        if (update.hasCallbackQuery()) {

            CallbackQuery callback = update.getCallbackQuery();
            Message message = callback.getMessage();
            Long chatId = message.getChatId();
            Integer messageId = message.getMessageId();
            String data = callback.getData();

            if (List.of("housing", "transportation", "food", "utilities", "insurance", "medical_healthcare", "personal_spending", "saving_investing_debt_payments", "recreation_entertainment", "miscellaneous").contains(data)) {
                setUSERS_STEP(chatId, 2);
                addProductType(chatId, callback.getData(), new Date());
                RESPONSE_MESSAGE.getEM_FOR_PAY_TYPE().setMessageId(messageId);
                RESPONSE_MESSAGE.getEM_FOR_PAY_TYPE().setChatId(String.valueOf(chatId));
                executeEM(RESPONSE_MESSAGE.getEM_FOR_PAY_TYPE());
            }
            if (data.equals("Cash") || data.equals("Card")) {
                setUSERS_STEP(chatId, 3);
                addProPay(chatId, data);
                RESPONSE_MESSAGE.getEM_FOR_PRO_NAME().setMessageId(messageId);
                RESPONSE_MESSAGE.getEM_FOR_PRO_NAME().setChatId(String.valueOf(chatId));
                executeEM(RESPONSE_MESSAGE.getEM_FOR_PRO_NAME());
            }
            if (data.equals("saveButton")) {
                addProductToDB(chatId,getCurrentProDto(chatId));
                setUSERS_STEP(chatId, 0);
                RESPONSE_MESSAGE.getEM_FOR_SAVE().setChatId(String.valueOf(chatId));
                RESPONSE_MESSAGE.getEM_FOR_SAVE().setMessageId(messageId);
                executeEM(RESPONSE_MESSAGE.getEM_FOR_SAVE());
            }
            if (data.equals("cancelButton")) {
                setUSERS_STEP(chatId, 0);
                deleteCurrentPro(chatId);
                RESPONSE_MESSAGE.getEM_FOR_CANCEL().setChatId(String.valueOf(chatId));
                RESPONSE_MESSAGE.getEM_FOR_CANCEL().setMessageId(messageId);
                executeEM(RESPONSE_MESSAGE.getEM_FOR_CANCEL());
            }
            if(data.equals("overall")){
                setUSERS_STEP(chatId, 0);
                SendMessage sendMessage=new SendMessage();
                sendMessage.setChatId(String.valueOf(chatId));
                sendMessage.setText("Sorry unable now!");
                executeSM(sendMessage);
            }
            if(data.equals("one_by_one")){
                SendMessage sendMessage=new SendMessage();
                sendMessage.setChatId(String.valueOf(chatId));
                sendMessage.setText(getUser(chatId).getProducts().toString());
                executeSM(sendMessage);
            }
        }
    }





    private void executeSM(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void executeEM(EditMessageText editMessageText) {
        try {
            execute(editMessageText);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private boolean isNumeric(String strNum) {
        try {
            BigDecimal d = new BigDecimal(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}