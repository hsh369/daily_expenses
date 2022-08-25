package com.example.dailyexpenses.front;

import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class InlineMarkupClass {

    private static final InlineKeyboardMarkup INLINE_FOR_PRO_TYPE = new InlineKeyboardMarkup();
    private static final InlineKeyboardMarkup INLINE_FOR_PAY_TYPE = new InlineKeyboardMarkup();
    private static final InlineKeyboardMarkup INLINE_FOR_PRO_SAVE = new InlineKeyboardMarkup();
    private static final InlineKeyboardMarkup INLINE_FOR_DAILY = new InlineKeyboardMarkup();

    private static final InlineKeyboardButton B1 = new InlineKeyboardButton("Housing\uD83C\uDFE0", null, "housing", null, null, null, null, null);
    private static final InlineKeyboardButton B2 = new InlineKeyboardButton("Transportation\uD83D\uDE96", null, "transportation", null, null, null, null, null);
    private static final InlineKeyboardButton B3 = new InlineKeyboardButton("Food\uD83E\uDD57", null, "food", null, null, null, null, null);
    private static final InlineKeyboardButton B4 = new InlineKeyboardButton("Utilities \uD83D\uDD0C", null, "utilities", null, null, null, null, null);
    private static final InlineKeyboardButton B5 = new InlineKeyboardButton("Insurance \uD83C\uDFE6", null, "insurance", null, null, null, null, null);
    private static final InlineKeyboardButton B6 = new InlineKeyboardButton("Medical & Healthcare \uD83D\uDC8A", null, "medical_healthcare", null, null, null, null, null);
    private static final InlineKeyboardButton B7 = new InlineKeyboardButton("Personal Spending\uD83D\uDC51", null, "personal_spending", null, null, null, null, null);
    private static final InlineKeyboardButton B8 = new InlineKeyboardButton("Saving, Investing, & Debt Payments\uD83D\uDCB8", null, "saving_investing_debt_payments", null, null, null, null, null);
    private static final InlineKeyboardButton B9 = new InlineKeyboardButton("Recreation & Entertainment\uD83C\uDFAD", null, "recreation_entertainment", null, null, null, null, null);
    private static final InlineKeyboardButton B10 = new InlineKeyboardButton("Miscellaneous ♻", null, "miscellaneous", null, null, null, null, null);
    private static final InlineKeyboardButton B_CANCEL = new InlineKeyboardButton("Cancel❌", null, "cancelButton", null, null, null, null, null);
    private static final InlineKeyboardButton B11 = new InlineKeyboardButton("Cash \uD83D\uDCB5", null, "Cash", null, null, null, null, null);
    private static final InlineKeyboardButton B12 = new InlineKeyboardButton("Card \uD83D\uDCB3", null, "Card", null, null, null, null, null);
    private static final InlineKeyboardButton B_Save = new InlineKeyboardButton("Save ✅", null, "saveButton", null, null, null, null, null);
    private static final InlineKeyboardButton BoBo = new InlineKeyboardButton("One by one\uD83E\uDDE9", null, "one_by_one", null, null, null, null, null);
    private static final InlineKeyboardButton B_XML = new InlineKeyboardButton("Daily overall\uD83E\uDDFE", null, "overall", null, null, null, null, null);

    private static final List<InlineKeyboardButton> ROW1 = new ArrayList<>();
    private static final List<InlineKeyboardButton> ROW2 = new ArrayList<>();
    private static final List<InlineKeyboardButton> ROW3 = new ArrayList<>();
    private static final List<InlineKeyboardButton> ROW4 = new ArrayList<>();
    private static final List<InlineKeyboardButton> ROW5 = new ArrayList<>();
    private static final List<InlineKeyboardButton> ROW6 = new ArrayList<>();
    private static final List<InlineKeyboardButton> ROW7 = new ArrayList<>();
    private static final List<InlineKeyboardButton> ROW8 = new ArrayList<>();
    private static final List<InlineKeyboardButton> ROW9 = new ArrayList<>();
    private static final List<InlineKeyboardButton> ROW10 = new ArrayList<>();
    private static final List<InlineKeyboardButton> ROW11 = new ArrayList<>();

    private static final List<List<InlineKeyboardButton>> KEYBOARD1 = new ArrayList<>();
    private static final List<List<InlineKeyboardButton>> KEYBOARD2 = new ArrayList<>();
    private static final List<List<InlineKeyboardButton>> KEYBOARD3 = new ArrayList<>();
    private static final List<List<InlineKeyboardButton>> KEYBOARD4 = new ArrayList<>();

    public static InlineKeyboardMarkup getInlineForProType() {
        setButtonsToRow(ROW1, B1);
        setButtonsToRow(ROW1, B2);
        setButtonsToRow(ROW2, B3);
        setButtonsToRow(ROW2, B4);
        setButtonsToRow(ROW3, B5);
        setButtonsToRow(ROW3, B6);
        setButtonsToRow(ROW4, B7);
        setButtonsToRow(ROW4, B8);
        setButtonsToRow(ROW5, B9);
        setButtonsToRow(ROW5, B10);
        setButtonsToRow(ROW6, B_CANCEL);
        setRowsToKeyboard(KEYBOARD1, List.of(ROW1, ROW2, ROW3, ROW4, ROW5, ROW6));
        INLINE_FOR_PRO_TYPE.setKeyboard(KEYBOARD1);
        return INLINE_FOR_PRO_TYPE;
    }

    public static InlineKeyboardMarkup getInlineForPayType() {
        setButtonsToRow(ROW7, B11);
        setButtonsToRow(ROW7, B12);
        setRowsToKeyboard(KEYBOARD2, List.of(ROW7));
        INLINE_FOR_PAY_TYPE.setKeyboard(KEYBOARD2);
        return INLINE_FOR_PAY_TYPE;
    }

    public static InlineKeyboardMarkup getInlineForDaily(){
        setButtonsToRow(ROW10,BoBo);
        setButtonsToRow(ROW10,B_XML);
        setButtonsToRow(ROW11,B_CANCEL);
        setRowsToKeyboard(KEYBOARD4,List.of(ROW10,ROW11));
        INLINE_FOR_DAILY.setKeyboard(KEYBOARD4);
        return INLINE_FOR_DAILY;
    }

    public static InlineKeyboardMarkup getSaveCancel() {
        setButtonsToRow(ROW8, B_Save);
        setButtonsToRow(ROW9, B_CANCEL);
        setRowsToKeyboard(KEYBOARD3, List.of(ROW8, ROW9));
        INLINE_FOR_PRO_SAVE.setKeyboard(KEYBOARD3);
        return INLINE_FOR_PRO_SAVE;
    }

    private static void setButtonsToRow(List<InlineKeyboardButton> list, InlineKeyboardButton button) {
        if (!list.contains(button)) {
            list.add(button);
        }
    }

    private static void setRowsToKeyboard(List<List<InlineKeyboardButton>> keyboard, List<List<InlineKeyboardButton>> rows) {
        for (List<InlineKeyboardButton> row : rows) {
            if (!keyboard.contains(row)) {
                keyboard.add(row);
            }
        }
    }


}
