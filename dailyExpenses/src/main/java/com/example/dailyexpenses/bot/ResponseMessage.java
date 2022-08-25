package com.example.dailyexpenses.bot;

import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

import static com.example.dailyexpenses.front.InlineMarkupClass.getInlineForPayType;

public class ResponseMessage {

    private final SendMessage RESPONSE_NO_TXT;
    private final SendMessage RESPONSE_TO_CONTINUE;
    private final SendMessage RESPONSE_USELESS;
    private final EditMessageText EM_FOR_PAY_TYPE;
    private final EditMessageText EM_FOR_PRO_NAME;
    private final EditMessageText EM_FOR_CANCEL;
    private final EditMessageText EM_FOR_SAVE;
    private final SendMessage EM_FOR_PRO_Amount;
    private final SendMessage EM_FOR_PRO_Price;
    private final SendMessage SM_WRONG_INPUT;



    public ResponseMessage() {
        RESPONSE_NO_TXT = new SendMessage();
        RESPONSE_USELESS = new SendMessage();
        RESPONSE_NO_TXT.setText(EmojiParser.parseToUnicode(":memo:") + "Please send only text messages!");
        RESPONSE_USELESS.setText("Unreachable statement!\nPlease use *commands*");
        RESPONSE_USELESS.enableMarkdown(true);

        EM_FOR_PAY_TYPE = new EditMessageText();
        EM_FOR_PAY_TYPE.enableMarkdown(true);
        EM_FOR_PAY_TYPE.setReplyMarkup(getInlineForPayType());
        EM_FOR_PAY_TYPE.setText("*Choose your payment type:*");


        RESPONSE_TO_CONTINUE = new SendMessage();
        RESPONSE_TO_CONTINUE.enableMarkdown(true);
        RESPONSE_TO_CONTINUE.setText("*Do not send message!\uD83D\uDE45\u200D♂️*\nChoose one of the above.\uD83D\uDC46");
        
        EM_FOR_PRO_NAME = new EditMessageText();
        EM_FOR_PRO_NAME.enableMarkdown(true);
        EM_FOR_PRO_NAME.setText("*✏️Send me description or name of spending :*");

        EM_FOR_PRO_Amount = new SendMessage();
        EM_FOR_PRO_Amount.enableMarkdown(true);
        EM_FOR_PRO_Amount.setText("*\uD83E\uDDEESend me amount or number:*");

        EM_FOR_PRO_Price = new SendMessage();
        EM_FOR_PRO_Price.enableMarkdown(true);
        EM_FOR_PRO_Price.setText("*\uD83D\uDCB0 Send me price:*");

        SM_WRONG_INPUT = new SendMessage();
        SM_WRONG_INPUT.enableMarkdown(true);
        SM_WRONG_INPUT.setText("*Wrong Input!*\n Please write a number only!\uD83D\uDD22");

        EM_FOR_CANCEL = new EditMessageText();
        EM_FOR_CANCEL.enableMarkdown(true);
        EM_FOR_CANCEL.setText("*Process was canceled !*\uD83D\uDDD1");
        EM_FOR_SAVE = new EditMessageText();
        EM_FOR_SAVE.enableMarkdown(true);
        EM_FOR_SAVE.setText("*Expense saved successfully!*✅");
    }

    public SendMessage getRESPONSE_NO_TXT() {
        return RESPONSE_NO_TXT;
    }

    public SendMessage getRESPONSE_USELESS() {
        return RESPONSE_USELESS;
    }

    public EditMessageText getEM_FOR_PAY_TYPE() {
        return EM_FOR_PAY_TYPE;
    }

    public SendMessage getRESPONSE_TO_CONTINUE() {
        return RESPONSE_TO_CONTINUE;
    }

    public EditMessageText getEM_FOR_PRO_NAME() {
        return EM_FOR_PRO_NAME;
    }

    public SendMessage getEM_FOR_PRO_Amount() {
        return EM_FOR_PRO_Amount;
    }

    public SendMessage getEM_FOR_PRO_Price() {
        return EM_FOR_PRO_Price;
    }

    public SendMessage getSM_WRONG_INPUT() {
        return SM_WRONG_INPUT;
    }

    public EditMessageText getEM_FOR_CANCEL() {
        return EM_FOR_CANCEL;
    }

    public EditMessageText getEM_FOR_SAVE() {
        return EM_FOR_SAVE;
    }
}
