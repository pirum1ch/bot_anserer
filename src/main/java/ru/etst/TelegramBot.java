package ru.etst;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;
import java.util.Random;

public class TelegramBot extends TelegramLongPollingBot {

    final private String BOT_TOKEN = "6228111534:AAHZCc-H5bFLQAMni-aIcpfH8ezwJMfrHVE";
    final private String BOT_NAME = "pda_2023_Tristan_Bot";

    String [] array = {"Да",
            "Нет",
            "Конечно",
            "Ни за что",
            "Абсолютно точно",
            "Никак нет",
            "Точно",
            "Не уверен",
            "Возможно да",
            "Возможно нет"};

    public TelegramBot() {}

    @Override
    public void onUpdateReceived(Update update) {
        String response;
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message inMess = update.getMessage();
            String chatId = inMess.getChatId().toString();
            response = parseMessage(inMess.getText());
            SendMessage outMess = new SendMessage();
            outMess.setChatId(chatId);
            outMess.setText(response);

            try {
                execute(outMess);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String radAnswer(){
        Random random = new Random();
        int number = random.nextInt(0,9);
        return array[number];
    }


    //TODO генератор сообщений
    public String parseMessage(String textMsg) {
        String response;
        if (textMsg.equals("/start"))
            response = "Задавай свои вопросы боту!\n " +
                    "Чтобы получить ответ, вопрос должен быть: \n" +
                    " - сформулирован так, что бы на него можно было ответить только \"да\" или \"нет\";\n" +
                    " - должен содержать знак вопроса в конце предложения.";
        else if (textMsg.toLowerCase().contains("привет"))
            response = "Привет!";
        else if (textMsg.contains("?"))
            response = radAnswer();
        else
            response = "Сообщение не распознано";
        return response;
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public void onRegister() {
        super.onRegister();
    }
}
