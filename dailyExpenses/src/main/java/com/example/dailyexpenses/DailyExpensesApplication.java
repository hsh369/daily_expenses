package com.example.dailyexpenses;

import com.example.dailyexpenses.bot.DailyExpensesBot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Map;

@SpringBootApplication
public class DailyExpensesApplication {

    public static void main(String[] args) {
        SpringApplication.run(DailyExpensesApplication.class, args);

        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new DailyExpensesBot("DailyCostsBot","5477940559:AAERqn9q1RgBSHM2qrNg6XMCWLZJcSgRb7M"));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        System.out.println("Successfully started");
    }

}
