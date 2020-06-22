import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendContact;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.*;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;


import java.util.ArrayList;


public class Bot extends TelegramLongPollingBot {
    private static final String NAME_OF_BOT = "Nailer";
    private static final String TOKEN = "1027299930:AAH402e03euOiovIjweuljxl7vQva5OC-vw";
    private static final String MSG = "Отправьте свое местоположение \uD83D\uDC47:";
    private static final String MSG1 = "Выберите процедуру из списка \uD83D\uDC47:";
    private static final String MSG2 = "Для записи оставьте мне свое номер телефона";
    private static final String lastBookingMsg = "Спасибо что пользуетесь услугами нашего салона, мы свяжемся с вами в ближайшее время";

    //куда посылать контакт
    private static final long bookingChat = 242229661;

    private static final String manicure0 = String.format("%s%s", "Маникюр (классический/комбинированный/аппаратный) ", "150 грн");
    private static final String manicure1 = String.format("%-57s%s", "гель лак ", "150 грн");
    private static final String manicure2 = String.format("%-41s%s", "гель лак без маникюра ", "180 грн");
    private static final String manicure3 = String.format("%-31s%s", "укрепление (полигель/гель лак) ", "100/150");
    private static final String manicure4 = String.format("%-52s%s", "френч омбре ", "50грн");
    private static final String manicure5 = String.format("%-44s%s", "наращивание ногтей ", "450грн");
    private static final String manicure6 = String.format("%-46s%s", "стилет/пайп/длина ", "550грн");
    private static final String manicure7 = String.format("%-49s%s", "коррекция ", "от 300 грн");
    private static final String manicure8 = String.format("%-35s%s", "педикюр комбинированный ", "230 грн");
    private static final String manicure9 = String.format("%-42s%s", "педикюр аппаратный ", "280 грн");
    private static final String manicure10 = String.format("%-63s%s", "лак ", "50 грн");
    private static final String manicure11 = String.format("%-36s%s", "акупунктурный массаж стоп ", "50 грн");

    private static final String massage0 = String.format("%-60s%-5s%s", "Комплексный лимфодренажный массаж лица, шеи и декольте", "75 мин ", "500 грн");
    private static final String massage1 = String.format("%-38s%-5s%s", "лицо", "60 мин ", "200 грн");
    private static final String massage2 = String.format("%-36s%-5s%s", "стопы", "30 мин ", "125 грн");
    private static final String massage3 = String.format("%-36s%-5s%s", "спина", "30 мин ", "190 грн");
    private static final String massage4 = String.format("%-32s%-5s%s", "лимфодренажный массаж тела", "80 мин ", "375 грн");
    private static final String massage5 = String.format("%-32s%-5s%s", "общий релаксирующий массаж", "60 мин ", "300 грн");

    private static final String waxing0 = String.format("%-31s%-4d%s", "голень/бедра", 150, "грн");
    private static final String waxing1 = String.format("%-29s%-4d%s", "ноги полностью", 250, "грн");
    private static final String waxing2 = String.format("%-32s%-4d%s", "руки до локтя", 120, "грн");
    private static final String waxing3 = String.format("%-29s%-4s%s", "руки полностью", 140, "грн");
    private static final String waxing4 = String.format("%-37s%-4d%s", "глубокое", 200, "грн");
    private static final String waxing5 = String.format("%-37s%-4d%s", "среднее", 170, "грн");
    private static final String waxing6 = String.format("%-36s%-4d%s", "классика", 150, "грн");
    private static final String waxing7 = String.format("%-29s%-4d%s", "живот/пояница", 100, "грн");
    private static final String waxing8 = String.format("%-29s%-4d%s", "живот дорожка", 50, "грн");
    private static final String waxing9 = String.format("%-33s%-4d%s", "в комплексе", 40, "грн");
    private static final String waxing10 = String.format("%-33s%-4d%s", "подмышки", 90, "грн");
    private static final String waxing11 = String.format("%-40s%-4d%s", "усики", 50, "грн");
    private static final String waxing12 = String.format("%s", "Бикини:");

    private static final String brows0 = String.format("%-31s%-4d%s", "моделирование", 100, "грн");
    private static final String brows1 = String.format("%-38s%-4d%s", "коррекция", 80, "грн");
    private static final String brows2 = String.format("%-30s%-4d%s", "окрашивание (хна)", 100, "грн");
    private static final String brows3 = String.format("%-22s%-7s%s", "окрашевание (краска)", "от 100", "грн");
    private static final String brows4 = String.format("%-38s%-4d%s", "биотатуаж", 150, "грн");

    public static void main(String[] args) {
        ApiContextInitializer.init();
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return NAME_OF_BOT;
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) { //проверка есть ли сообщение
            updateHasMessage(update.getMessage());
        } else if (update.hasCallbackQuery()) {
            updateHasCallbackQuery(update.getCallbackQuery());
        }
    }

    //проверка по нажатию
    private void updateHasMessage(Message message) {

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());

        if (message.hasLocation()) {
            sendMessage.setText("Адресс: Высоцкого 6\n" + "График работы с 9:00 - 20:00");
            try {
                execute(sendMessage.setReplyMarkup(setInlineKeyboardMap()));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        if (message.hasContact()) {
            try {
                execute(sendMessage.setText(lastBookingMsg).setReplyMarkup(mainMenu()));
                execute(sendMessage.setText(message.getContact().toString()).setChatId(bookingChat));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        switch (message.getText().toLowerCase()) {
            case "/start":
                sendMessage.setText(message.getFrom().getFirstName() + " " + "рад приветсовать вас в салоне ногтевого сервиса и эстетических процедур Nailer Studio. \n" +
                        "Мы находимся по адрессу : ул.Высоцкого, 6 (пос.Котовского)\n" +
                        "тел. 0931361745 | 0962019239\n" +
                        "inst: @kapustina_nailer | @nailer_studio");
                try {
                    execute(sendMessage.setReplyMarkup(setInlineKeyboard()));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
            case "\uD83D\uDEAA главное меню":
                sendMessage.setText("Главное меню");
                try {
                    execute(sendMessage.setReplyMarkup(setInlineKeyboard()));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
            default:
                sendMessage.setText("Что то пошло не так, пожалуйста повторите запрос");
                try {
                    execute(sendMessage.setReplyMarkup(setInlineKeyboard()));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    //ответ на запросы клавиатуры
    private void updateHasCallbackQuery(CallbackQuery callbackQuery) {
        switch (callbackQuery.getData()) {

            case MSG:
                try {
                    execute(new SendMessage().setChatId(callbackQuery.getMessage().getChatId())
                            .setText(callbackQuery.getData()).setReplyMarkup(setKeyboard()));

                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
            case MSG1:
                try {
                    execute(new SendMessage().setChatId(callbackQuery.getMessage().getChatId())
                            .setText(MSG1).setReplyMarkup(setInlineKeyboard1()));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
            case "Контакты":
                try {
                    execute(new SendMessage().setChatId(callbackQuery.getMessage().getChatId())
                            .setText("У вас есть вопросы, тогда свяжитесь с нами")
                            .setReplyMarkup(setInlineKeyboardContact()));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
            case "Позвонить": {
                try {
                    execute(new SendMessage().setChatId(callbackQuery.getMessage().getChatId())
                            .setText("Позвоните нам и мы с радостью ответим на все ваши вопросы:\n" +
                                    "\n" + "☎ +380931361745\n" + "\n" + "\uD83D\uDCF1 +380989944816")
                            .setReplyMarkup(mainMenu()));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            break;

            case "Главное меню": {
                try {
                    execute(new SendMessage().setChatId(callbackQuery.getMessage().getChatId())
                            .setText("\uD83D\uDEAA Главное меню")
                            .setReplyMarkup(setInlineKeyboard()));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            break;

            case "Маникюр и педикюр": {
                try {
                    execute(new SendMessage().setChatId(callbackQuery.getMessage().getChatId())
                            .setText("Мы приготовили для вас самые яркие, сочные цвета этого сезона\n" + "\n"));
                    execute(new SendPhoto().setPhoto("https://monosnap.com/direct/uGAYmbERbOGRQnHZzxXdUIEfT9CJA8")
                            .setChatId(callbackQuery.getMessage().getChatId()));

                    execute(new SendMessage().setChatId(callbackQuery.getMessage().getChatId())
                            .setText("Прайс на маникюр и педикюр")
                            .setReplyMarkup(setInlineKeyboardPriceNail()));

                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            break;

            case "Прайс Маникюр": {
                AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery()
                        .setCallbackQueryId(callbackQuery.getId())
                        .setText(callbackQuery.getMessage().getText())
                        .setShowAlert(false);
                EditMessageText editMessageText = new EditMessageText()
                        .setMessageId(callbackQuery.getMessage().getMessageId())
                        .setChatId(callbackQuery.getMessage().getChatId())
                        .setText("Наш прайс на маникюр\n" + "________________________________" + "\n" + manicure0 + "\n" +
                                manicure1 + "\n" + manicure2 + "\n" + manicure3 + "\n" + manicure4 + "\n" + manicure5 + "\n" + manicure6 + "\n" +
                                manicure7 + "\n" + manicure8 + "\n" + manicure9 + "\n" + manicure10 + "\n" + manicure11);
                EditMessageReplyMarkup editMessageReplyMarkup = new EditMessageReplyMarkup()
                        .setReplyMarkup(setInlineKeyboardBookingBrow())
                        .setMessageId(callbackQuery.getMessage().getMessageId())
                        .setChatId(callbackQuery.getMessage().getChatId());
                try {
                    execute(editMessageText);
                    execute(editMessageReplyMarkup);
                    execute(answerCallbackQuery);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            break;

            case "Маникюр Запись": {
                try {
                    execute(new SendMessage().setChatId(callbackQuery.getMessage().getChatId())
                            .setText(MSG2).setReplyMarkup(setKeyboardContact()));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

            case "Массаж": {
                try {
                    execute(new SendMessage().setChatId(callbackQuery.getMessage().getChatId())
                            .setText("Знакомьтесь Ирина, профессиональный массажист, подарит вам успокоенение души через прикосновение к телу"));
                    execute(new SendPhoto().setPhoto("https://monosnap.com/direct/m3pvBT2iSZSxKqko2bmFY2oysF0ZFT")
                            .setChatId(callbackQuery.getMessage().getChatId()));

                    execute(new SendMessage().setChatId(callbackQuery.getMessage().getChatId())
                            .setText("Прайс на массаж")
                            .setReplyMarkup(setInlineKeyboardPriceMassage()));

                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            break;

            case "Прайс Массаж": {
                AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery()
                        .setCallbackQueryId(callbackQuery.getId())
                        .setText(callbackQuery.getMessage().getText())
                        .setShowAlert(false);
                EditMessageText editMessageText = new EditMessageText()
                        .setMessageId(callbackQuery.getMessage().getMessageId())
                        .setChatId(callbackQuery.getMessage().getChatId())
                        .setText("Наш прайс на массаж\n" + "________________________________" + "\n" + massage0 + "\n" +
                                massage1 + "\n" + massage2 + "\n" + massage3 + "\n" + massage4 + "\n" + massage5);
                EditMessageReplyMarkup editMessageReplyMarkup = new EditMessageReplyMarkup()
                        .setReplyMarkup(setInlineKeyboardBookingBrow())
                        .setMessageId(callbackQuery.getMessage().getMessageId())
                        .setChatId(callbackQuery.getMessage().getChatId());
                try {
                    execute(editMessageText);
                    execute(editMessageReplyMarkup);
                    execute(answerCallbackQuery);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            break;

            case "Массаж Запись": {
                try {
                    execute(new SendMessage().setChatId(callbackQuery.getMessage().getChatId())
                            .setText(MSG2).setReplyMarkup(setKeyboardContact()));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            break;

            case "Брови": {
                try {
                    execute(new SendMessage().setChatId(callbackQuery.getMessage().getChatId())
                            .setText("Хорошо подобранная форма бровей, делает ваш взгляд более выразительным, а их цвет его подчеркивает.\n" +
                                    "Мастер Ксюша справиться с любой задачей"));
                    execute(new SendPhoto().setPhoto("https://monosnap.com/direct/RAbwsiuB2E5sGwg7WgRbjwsEiFiV7n ")
                            .setChatId(callbackQuery.getMessage().getChatId()));

                    execute(new SendMessage().setChatId(callbackQuery.getMessage().getChatId())
                            .setText("Прайс на брови")
                            .setReplyMarkup(setInlineKeyboardPriceBrow()));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            break;

            case "Прайс Брови": {
                AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery()
                        .setCallbackQueryId(callbackQuery.getId())
                        .setText(callbackQuery.getMessage().getText())
                        .setShowAlert(false);
                EditMessageText editMessageText = new EditMessageText()
                        .setMessageId(callbackQuery.getMessage().getMessageId())
                        .setChatId(callbackQuery.getMessage().getChatId())
                        .setText("Наш прайс на брови\n" + "________________________________" + "\n" + brows0 + "\n" +
                                brows1 + "\n" + brows2 + "\n" + brows3 + "\n" + brows4 + "\n");
                EditMessageReplyMarkup editMessageReplyMarkup = new EditMessageReplyMarkup()
                        .setReplyMarkup(setInlineKeyboardBookingBrow())
                        .setMessageId(callbackQuery.getMessage().getMessageId())
                        .setChatId(callbackQuery.getMessage().getChatId());
                try {
                    execute(editMessageText);
                    execute(editMessageReplyMarkup);
                    execute(answerCallbackQuery);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            break;

            case "Брови Запись": {
                try {
                    execute(new SendMessage().setChatId(callbackQuery.getMessage().getChatId())
                            .setText(MSG2).setReplyMarkup(setKeyboardContact()));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            break;

            case "Депиляция":
                try {
                    execute(new SendMessage().setChatId(callbackQuery.getMessage().getChatId())
                            .setText("Подготовка к лету в самом разгаре"));
                    execute(new SendPhoto().setPhoto("https://monosnap.com/direct/NMvhwtwxrTlqahMNNZjZuI78PSPYNS")
                            .setChatId(callbackQuery.getMessage().getChatId()));

                    execute(new SendMessage().setChatId(callbackQuery.getMessage().getChatId())
                            .setText("Прайс на депиляцию")
                            .setReplyMarkup(setInlineKeyboardPriceWaxing()));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;

            case "Прайс Депиляция": {
                AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery()
                        .setCallbackQueryId(callbackQuery.getId())
                        .setText(callbackQuery.getMessage().getText())
                        .setShowAlert(false);
                EditMessageText editMessageText = new EditMessageText()
                        .setMessageId(callbackQuery.getMessage().getMessageId())
                        .setChatId(callbackQuery.getMessage().getChatId())
                        .setText("Наш прайс на депиляци\n" + "________________________________" + "\n" + waxing0 + "\n" +
                                waxing1 + "\n" + waxing2 + "\n" + waxing3 + "\n" + "\n" + waxing12 + "\n" + waxing4 + "\n" + waxing5 + "\n"
                                + waxing6 + "\n" + waxing7 + "\n" + waxing8 + "\n" + waxing9 + "\n" + waxing10 + "\n"
                                + waxing11 + "\n");
                EditMessageReplyMarkup editMessageReplyMarkup = new EditMessageReplyMarkup()
                        .setReplyMarkup(setInlineKeyboardBookingBrow())
                        .setMessageId(callbackQuery.getMessage().getMessageId())
                        .setChatId(callbackQuery.getMessage().getChatId());
                try {
                    execute(editMessageText);
                    execute(editMessageReplyMarkup);
                    execute(answerCallbackQuery);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            break;

            case "Депиляция Запись": {
                try {
                    execute(new SendMessage().setChatId(callbackQuery.getMessage().getChatId())
                            .setText(MSG2).setReplyMarkup(setKeyboardContact()));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            break;
        }
    }

    //Инлайновая клавиатура Главного меню
    private InlineKeyboardMarkup setInlineKeyboard() {
        List<List<InlineKeyboardButton>> inlineKeyboard = new ArrayList<>(); //Список рядов
        List<InlineKeyboardButton> inlineKeyboardRow = new ArrayList<>(); //объект ряда

        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton()
                .setCallbackData(MSG1)
                .setText("\uD83D\uDCC6  Записаться");
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton()
                .setCallbackData("Контакты")
                .setText("\uD83D\uDCF1  Контакты");
        inlineKeyboardRow.add(inlineKeyboardButton);
        inlineKeyboardRow.add(inlineKeyboardButton1);

        List<InlineKeyboardButton> inlineKeyboardRow1 = new ArrayList<>();
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton()
                .setCallbackData(MSG)
                .setText("\uD83E\uDDED  Как добраться?");
        inlineKeyboardRow1.add(inlineKeyboardButton2);
        inlineKeyboard.add(inlineKeyboardRow);
        inlineKeyboard.add(inlineKeyboardRow1);

        return new InlineKeyboardMarkup().setKeyboard(inlineKeyboard);
    }

    //инлайновая клавиатура записи на процедуру
    private InlineKeyboardMarkup setInlineKeyboard1() {
        List<List<InlineKeyboardButton>> inlineKeyboard2 = new ArrayList<>(); //Список рядов
        List<InlineKeyboardButton> inlineKeyboardRow = new ArrayList<>(); //объект ряда

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton()
                .setCallbackData("Маникюр и педикюр")
                .setText("\uD83D\uDC85 Маникюр и педикюр");

        inlineKeyboardRow.add(inlineKeyboardButton1);


        List<InlineKeyboardButton> inlineKeyboardRow1 = new ArrayList<>();
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton()
                .setCallbackData("Массаж")
                .setText("\uD83D\uDC86\u200D♀ Массаж");
        inlineKeyboardRow1.add(inlineKeyboardButton2);


        List<InlineKeyboardButton> inlineKeyboardRow2 = new ArrayList<>();
        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton()
                .setCallbackData("Депиляция")
                .setText("\uD83D\uDC59 Депиляция");
        inlineKeyboardRow1.add(inlineKeyboardButton3);

        List<InlineKeyboardButton> inlineKeyboardRow3 = new ArrayList<>();
        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton()
                .setCallbackData("Брови")
                .setText("\uD83D\uDC41 Брови");
        inlineKeyboardRow3.add(inlineKeyboardButton4);

        List<InlineKeyboardButton> inlineKeyboardRow4 = new ArrayList<>();
        InlineKeyboardButton inlineKeyboardButton5 = new InlineKeyboardButton()
                .setCallbackData("Главное меню")
                .setText("\uD83D\uDEAA Главное меню");
        inlineKeyboardRow4.add(inlineKeyboardButton5);

        inlineKeyboard2.add(inlineKeyboardRow);
        inlineKeyboard2.add(inlineKeyboardRow1);

        inlineKeyboard2.add(inlineKeyboardRow3);
        inlineKeyboard2.add(inlineKeyboardRow4);


        return new InlineKeyboardMarkup().setKeyboard(inlineKeyboard2);
    }

    //клавиатура контактов
    private InlineKeyboardMarkup setInlineKeyboardContact() {
        List<List<InlineKeyboardButton>> inlineKeyboard4 = new ArrayList<>(); //Список рядов

        List<InlineKeyboardButton> inlineKeyboardRow = new ArrayList<>(); //объект ряда
        InlineKeyboardButton inlineKeyboardButtonCon = new InlineKeyboardButton()
                .setCallbackData("TG contact")
                .setText("\uD83D\uDCAC Написать нам в Telegram").setUrl("tg://resolve?domain=nailer_admin"); //поменять
        inlineKeyboardRow.add(inlineKeyboardButtonCon);


        List<InlineKeyboardButton> inlineKeyboardRow1 = new ArrayList<>();
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton()
                .setCallbackData("Позвонить")
                .setText("\uD83D\uDCDE Позвоните нам");
        inlineKeyboardRow1.add(inlineKeyboardButton2);


        List<InlineKeyboardButton> inlineKeyboardRow2 = new ArrayList<>();
        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton()
                .setCallbackData("Инстаграм1")
                .setText("\uD83D\uDCF8 Инстаграм студии").setUrl("https://instagram.com/nailer_studio?igshid=s5zdduhyzqst");
        inlineKeyboardRow2.add(inlineKeyboardButton3);

        List<InlineKeyboardButton> inlineKeyboardRow3 = new ArrayList<>();
        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton()
                .setCallbackData("Инстраграм2")
                .setText("\uD83D\uDCF7 Инстаграм обучения").setUrl("https://instagram.com/kapustina_nailer?igshid=10jfai8d386fe");
        inlineKeyboardRow3.add(inlineKeyboardButton4);

        List<InlineKeyboardButton> inlineKeyboardRow4 = new ArrayList<>();
        InlineKeyboardButton inlineKeyboardButton5 = new InlineKeyboardButton()
                .setCallbackData("Главное меню")
                .setText("\uD83D\uDEAA Главное меню");
        inlineKeyboardRow4.add(inlineKeyboardButton5);

        inlineKeyboard4.add(inlineKeyboardRow);
        inlineKeyboard4.add(inlineKeyboardRow1);
        inlineKeyboard4.add(inlineKeyboardRow2);
        inlineKeyboard4.add(inlineKeyboardRow3);
        inlineKeyboard4.add(inlineKeyboardRow4);

        return new InlineKeyboardMarkup().setKeyboard(inlineKeyboard4);
    }

    //глаавное меню
    private InlineKeyboardMarkup mainMenu() {
        List<List<InlineKeyboardButton>> inlineKeyboard3 = new ArrayList<>(); //Список рядов
        List<InlineKeyboardButton> inlineKeyboardRow = new ArrayList<>(); //объект ряда

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton()
                .setCallbackData("Главное меню")
                .setText("\uD83D\uDEAA Главное меню");

        inlineKeyboardRow.add(inlineKeyboardButton1);
        inlineKeyboard3.add(inlineKeyboardRow);

        return new InlineKeyboardMarkup().setKeyboard(inlineKeyboard3);
    }

    //маршрут построить
    private InlineKeyboardMarkup setInlineKeyboardMap() {
        List<List<InlineKeyboardButton>> inlineKeyboard3 = new ArrayList<>(); //Список рядов
        List<InlineKeyboardButton> inlineKeyboardRow = new ArrayList<>(); //объект ряда

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton()
                .setCallbackData("Маршрут")
                .setText("Проложить маршрут")
                .setUrl("https://www.google.com/maps/dir/?api=1&destination=46.58073500,30.80197400");

        inlineKeyboardRow.add(inlineKeyboardButton1);
        inlineKeyboard3.add(inlineKeyboardRow);

        List<InlineKeyboardButton> inlineKeyboardRow4 = new ArrayList<>();
        InlineKeyboardButton inlineKeyboardButton5 = new InlineKeyboardButton()
                .setCallbackData("Главное меню")
                .setText("\uD83D\uDEAA Главное меню");
        inlineKeyboardRow4.add(inlineKeyboardButton5);
        inlineKeyboard3.add(inlineKeyboardRow4);


        return new InlineKeyboardMarkup().setKeyboard(inlineKeyboard3);
    }

    //Прайс на Массаж
    private InlineKeyboardMarkup setInlineKeyboardPriceMassage() {
        List<List<InlineKeyboardButton>> inlineKeyboardPrice = new ArrayList<>(); //Список рядов
        List<InlineKeyboardButton> inlineKeyboardPriceRow = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton()
                .setCallbackData("Прайс Массаж")
                .setText("Прайс");
        inlineKeyboardPriceRow.add(inlineKeyboardButton1);
        inlineKeyboardPrice.add(inlineKeyboardPriceRow);

        List<InlineKeyboardButton> inlineKeyboardPriceRow1 = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton()
                .setCallbackData("Массаж Запись")
                .setText("Записаться");
        inlineKeyboardPriceRow1.add(inlineKeyboardButton2);
        inlineKeyboardPrice.add(inlineKeyboardPriceRow1);

        List<InlineKeyboardButton> inlineKeyboardRow4 = new ArrayList<>();
        InlineKeyboardButton inlineKeyboardButton5 = new InlineKeyboardButton()
                .setCallbackData(MSG1)
                .setText("\uD83D\uDD19 Выбрать процедуру");
        inlineKeyboardRow4.add(inlineKeyboardButton5);
        inlineKeyboardPrice.add(inlineKeyboardRow4);

        return new InlineKeyboardMarkup().setKeyboard(inlineKeyboardPrice);
    }

    //прайс ногти
    private InlineKeyboardMarkup setInlineKeyboardPriceNail() {
        List<List<InlineKeyboardButton>> inlineKeyboardPrice = new ArrayList<>(); //Список рядов
        List<InlineKeyboardButton> inlineKeyboardPriceRow = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton()
                .setCallbackData("Прайс Маникюр")
                .setText("Прайс");
        inlineKeyboardPriceRow.add(inlineKeyboardButton1);
        inlineKeyboardPrice.add(inlineKeyboardPriceRow);

        List<InlineKeyboardButton> inlineKeyboardPriceRow1 = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton()
                .setCallbackData("Маникюр Запись")
                .setText("Записаться");
        inlineKeyboardPriceRow1.add(inlineKeyboardButton2);
        inlineKeyboardPrice.add(inlineKeyboardPriceRow1);

        List<InlineKeyboardButton> inlineKeyboardRow4 = new ArrayList<>();
        InlineKeyboardButton inlineKeyboardButton5 = new InlineKeyboardButton()
                .setCallbackData(MSG1)
                .setText("\uD83D\uDD19 Выбрать процедуру");
        inlineKeyboardRow4.add(inlineKeyboardButton5);
        inlineKeyboardPrice.add(inlineKeyboardRow4);

        return new InlineKeyboardMarkup().setKeyboard(inlineKeyboardPrice);
    }

    //прайс депиляция
    private InlineKeyboardMarkup setInlineKeyboardPriceWaxing() {
        List<List<InlineKeyboardButton>> inlineKeyboardPrice = new ArrayList<>(); //Список рядов
        List<InlineKeyboardButton> inlineKeyboardPriceRow = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton()
                .setCallbackData("Прайс Депиляция")
                .setText("Прайс");
        inlineKeyboardPriceRow.add(inlineKeyboardButton1);
        inlineKeyboardPrice.add(inlineKeyboardPriceRow);

        List<InlineKeyboardButton> inlineKeyboardPriceRow1 = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton()
                .setCallbackData("Депиляция Запись")
                .setText("Записаться");
        inlineKeyboardPriceRow1.add(inlineKeyboardButton2);
        inlineKeyboardPrice.add(inlineKeyboardPriceRow1);

        List<InlineKeyboardButton> inlineKeyboardRow4 = new ArrayList<>();
        InlineKeyboardButton inlineKeyboardButton5 = new InlineKeyboardButton()
                .setCallbackData(MSG1)
                .setText("\uD83D\uDD19 Выбрать процедуру");
        inlineKeyboardRow4.add(inlineKeyboardButton5);
        inlineKeyboardPrice.add(inlineKeyboardRow4);

        return new InlineKeyboardMarkup().setKeyboard(inlineKeyboardPrice);
    }

    //прайс брови
    private InlineKeyboardMarkup setInlineKeyboardPriceBrow() {
        List<List<InlineKeyboardButton>> inlineKeyboardPrice = new ArrayList<>(); //Список рядов
        List<InlineKeyboardButton> inlineKeyboardPriceRow = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton()
                .setCallbackData("Прайс Брови")
                .setText("Прайс");
        inlineKeyboardPriceRow.add(inlineKeyboardButton1);
        inlineKeyboardPrice.add(inlineKeyboardPriceRow);

        List<InlineKeyboardButton> inlineKeyboardPriceRow1 = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton()
                .setCallbackData("Брови Запись")
                .setText("Записаться");
        inlineKeyboardPriceRow1.add(inlineKeyboardButton2);
        inlineKeyboardPrice.add(inlineKeyboardPriceRow1);

        List<InlineKeyboardButton> inlineKeyboardRow4 = new ArrayList<>();
        InlineKeyboardButton inlineKeyboardButton5 = new InlineKeyboardButton()
                .setCallbackData(MSG1)
                .setText("\uD83D\uDD19 Выбрать процедуру");
        inlineKeyboardRow4.add(inlineKeyboardButton5);
        inlineKeyboardPrice.add(inlineKeyboardRow4);

        return new InlineKeyboardMarkup().setKeyboard(inlineKeyboardPrice);
    }

    //запись на брови
    private InlineKeyboardMarkup setInlineKeyboardBookingBrow() {
        List<List<InlineKeyboardButton>> inlineKeyboardPrice = new ArrayList<>(); //Список рядов
        List<InlineKeyboardButton> inlineKeyboardPriceRow = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton()
                .setCallbackData("Брови Запись")
                .setText("Записаться");
        inlineKeyboardPriceRow.add(inlineKeyboardButton1);
        inlineKeyboardPrice.add(inlineKeyboardPriceRow);


        List<InlineKeyboardButton> inlineKeyboardRow4 = new ArrayList<>();
        InlineKeyboardButton inlineKeyboardButton5 = new InlineKeyboardButton()
                .setCallbackData(MSG1)
                .setText("\uD83D\uDD19 Выбрать процедуру");
        inlineKeyboardRow4.add(inlineKeyboardButton5);
        inlineKeyboardPrice.add(inlineKeyboardRow4);

        return new InlineKeyboardMarkup().setKeyboard(inlineKeyboardPrice);
    }

    //Клавиатура для маршрута
    public ReplyKeyboardMarkup setKeyboard() {
        List<KeyboardRow> keyboard = new ArrayList<>();
        /////////////////////////////////////////////////
        KeyboardRow keyboardRow_1 = new KeyboardRow();

        KeyboardButton keyboardButton_1_1 = new KeyboardButton().setText("Отправьте мне местоположение").setRequestLocation(true);
        keyboardRow_1.add(keyboardButton_1_1);

        keyboard.add(keyboardRow_1);
        ///////////////////////////////////////////
        KeyboardRow keyboardRow_2 = new KeyboardRow();

        KeyboardButton keyboardButton_2_1 = new KeyboardButton().setText("\uD83D\uDEAA Главное меню");
        keyboardRow_2.add(keyboardButton_2_1);

        keyboard.add(keyboardRow_2);

        return new ReplyKeyboardMarkup()
                .setResizeKeyboard(true) // дозволити, змінювати розмір, до комфортного, дефолтне значення false (кнопки займають всю площу стандартної клавіатури )
                .setOneTimeKeyboard(true) // після натискання кнопки, клавіатура скривається
                .setKeyboard(keyboard);
    }

    public ReplyKeyboardMarkup setKeyboardContact() {
        List<KeyboardRow> keyboard = new ArrayList<>();
        /////////////////////////////////////////////////
        KeyboardRow keyboardRow_1 = new KeyboardRow();

        KeyboardButton keyboardButton_1_1 = new KeyboardButton().setText("Отправить номер телефона").setRequestContact(true);
        keyboardRow_1.add(keyboardButton_1_1);

        keyboard.add(keyboardRow_1);
        ///////////////////////////////////////////
        KeyboardRow keyboardRow_2 = new KeyboardRow();

        KeyboardButton keyboardButton_2_1 = new KeyboardButton().setText("\uD83D\uDEAA Главное меню");
        keyboardRow_2.add(keyboardButton_2_1);

        keyboard.add(keyboardRow_2);

        return new ReplyKeyboardMarkup()
                .setResizeKeyboard(true) // дозволити, змінювати розмір, до комфортного, дефолтне значення false (кнопки займають всю площу стандартної клавіатури )
                .setOneTimeKeyboard(true) // після натискання кнопки, клавіатура скривається
                .setKeyboard(keyboard);
    }
}