
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;


import java.util.ArrayList;
import java.util.List;

    public class TgBotNail extends TelegramLongPollingBot {
        private static final String NAME_OF_BOT = "***";
        private static final String TOKEN = "***";
        private static final String MSG = "Отправьте свое местоположение \uD83D\uDC47:";
        private static final String MSG1 = "Выберите процедуру из списка \uD83D\uDC47:";

        public static void main(String[] args) {
            ApiContextInitializer.init();
            try {
                TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
                telegramBotsApi.registerBot(new TgBotNail());
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
        public void onUpdateReceived(Update update){
            if (update.hasMessage()){ //проверка есть ли сообщение
                updateHasMessage(update.getMessage());
            }else if(update.hasCallbackQuery()) {
                updateHasCallbackQuery(update.getCallbackQuery());
            }
        }

        private void updateHasMessage(Message message) {

            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(message.getChatId());
            if (message.hasLocation()){
                sendMessage.setText("/");
                try {
                    execute(sendMessage.setReplyMarkup(setInlineKeyboardMap()));
                } catch (TelegramApiException e){
                    e.printStackTrace();
                }
            }

            switch (message.getText().toLowerCase()) {

                case "/start":
                    sendMessage.setText("/");
                    try {
                        execute(sendMessage.setReplyMarkup(setInlineKeyboard()));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;

                case "главное меню":
                    sendMessage.setText("Главное меню");
                    try {
                        execute(sendMessage.setReplyMarkup(setInlineKeyboard()));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;

                case "массаж":
                    sendMessage.setText("button1");
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e){
                        e.printStackTrace();
                    }
                    break;
                case "контакты":
                    break;





                default:
                    sendMessage.setText("Что то пошло не так, пожалуйста повторите запрос");
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }

        private void updateHasCallbackQuery(CallbackQuery callbackQuery){
            if(callbackQuery.getData().equals(MSG)){
                try {
                    execute(new SendMessage().setChatId(callbackQuery.getMessage().getChatId())
                            .setText(callbackQuery.getData()).setReplyMarkup(setKeyboard()));

                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if(callbackQuery.getData().equals(MSG1)){
                try {
                    execute(new SendMessage().setChatId(callbackQuery.getMessage().getChatId())
                            .setText(MSG1).setReplyMarkup(setInlineKeyboard1()));
                } catch (TelegramApiException e){
                    e.printStackTrace();
                }
            } else if(callbackQuery.getData().equals("Контакты")){
                try {
                    execute(new SendMessage().setChatId(callbackQuery.getMessage().getChatId())
                            .setText("У вас есть вопросы, тогда свяжитесь с нами")
                            .setReplyMarkup(setInlineKeyboardContact()));
                } catch (TelegramApiException e){
                    e.printStackTrace();
                }
            } else if(callbackQuery.getData().equals("Позвонить")){
                try {
                    execute(new SendMessage().setChatId(callbackQuery.getMessage().getChatId())
                            .setText("/")
                            .setReplyMarkup(setInlineKeyboard()));
                } catch (TelegramApiException e){
                    e.printStackTrace();
                }
            }
        }
        //Инлайновая клавиатура Главного меню
        private InlineKeyboardMarkup setInlineKeyboard(){
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
        private InlineKeyboardMarkup setInlineKeyboard1(){
            List<List<InlineKeyboardButton>> inlineKeyboard2 = new ArrayList<>(); //Список рядов
            List<InlineKeyboardButton> inlineKeyboardRow = new ArrayList<>(); //объект ряда

            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton()
                    .setCallbackData("Маникюр и педикюр")
                    .setText("\uD83D\uDC85 Маникюр и педикюр");

            inlineKeyboardRow.add(inlineKeyboardButton1);


            List<InlineKeyboardButton> inlineKeyboardRow1 = new ArrayList<>();
            InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton()
                    .setCallbackData("Массаж")
                    .setText("\uD83D\uDC86\u200D♀️ Массаж");
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

            inlineKeyboard2.add(inlineKeyboardRow);
            inlineKeyboard2.add(inlineKeyboardRow1);
            /*inlineKeyboard2.add(inlineKeyboardRow2);*/
            inlineKeyboard2.add(inlineKeyboardRow3);


            return new InlineKeyboardMarkup().setKeyboard(inlineKeyboard2);
        }

        private InlineKeyboardMarkup setInlineKeyboardContact() {
            List<List<InlineKeyboardButton>> inlineKeyboard4 = new ArrayList<>(); //Список рядов
            List<InlineKeyboardButton> inlineKeyboardRow = new ArrayList<>(); //объект ряда

            InlineKeyboardButton inlineKeyboardButtonCon = new InlineKeyboardButton()
                    .setCallbackData("TG contact")
                    .setText("\uD83D\uDCAC Написать нам в Telegram").setUrl("tg://resolve?domain=8"); //поменять
            inlineKeyboardRow.add(inlineKeyboardButtonCon);


            List<InlineKeyboardButton> inlineKeyboardRow1 = new ArrayList<>();
            InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton()
                    .setCallbackData("Позвонить")
                    .setText("\uD83D\uDCDE Позвоните нам");
            inlineKeyboardRow1.add(inlineKeyboardButton2);


            List<InlineKeyboardButton> inlineKeyboardRow2 = new ArrayList<>();
            InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton()
                    .setCallbackData("Инстаграм1")
                    .setText("\uD83D\uDCF8 Инстаграм студии").setUrl("/");
            inlineKeyboardRow2.add(inlineKeyboardButton3);

            List<InlineKeyboardButton> inlineKeyboardRow3 = new ArrayList<>();
            InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton()
                    .setCallbackData("Инстраграм2")
                    .setText("\uD83D\uDCF7 Инстаграм обучения").setUrl("/");
            inlineKeyboardRow3.add(inlineKeyboardButton4);

            inlineKeyboard4.add(inlineKeyboardRow);
            inlineKeyboard4.add(inlineKeyboardRow1);
            inlineKeyboard4.add(inlineKeyboardRow2);
            inlineKeyboard4.add(inlineKeyboardRow3);


            return new InlineKeyboardMarkup().setKeyboard(inlineKeyboard4);
        }

        private InlineKeyboardMarkup setInlineKeyboardMap(){
            List<List<InlineKeyboardButton>> inlineKeyboard3 = new ArrayList<>(); //Список рядов
            List<InlineKeyboardButton> inlineKeyboardRow = new ArrayList<>(); //объект ряда

            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton()
                    .setCallbackData("Маршрут")
                    .setText("Проложить маршрут")
                    .setUrl("https://www.google.com/maps/dir/?api=1&destination=46.58055550,50.80197400");

            inlineKeyboardRow.add(inlineKeyboardButton1);
            inlineKeyboard3.add(inlineKeyboardRow);


            return new InlineKeyboardMarkup().setKeyboard(inlineKeyboard3);
        }

        public ReplyKeyboardMarkup setKeyboard() {
            List<KeyboardRow> keyboard = new ArrayList<>();
            /////////////////////////////////////////////////
            KeyboardRow keyboardRow_1 = new KeyboardRow();

            KeyboardButton keyboardButton_1_1 = new KeyboardButton().setText("Отправь мне местополоение").setRequestLocation(true);
            keyboardRow_1.add(keyboardButton_1_1);

            keyboard.add(keyboardRow_1);
            ///////////////////////////////////////////
            KeyboardRow keyboardRow_2 = new KeyboardRow();

            KeyboardButton keyboardButton_2_1 = new KeyboardButton().setText("Главное меню");
            keyboardRow_2.add(keyboardButton_2_1);

            keyboard.add(keyboardRow_2);

            return new ReplyKeyboardMarkup()
                    .setResizeKeyboard(true) // дозволити, змінювати розмір, до комфортного, дефолтне значення false (кнопки займають всю площу стандартної клавіатури )
                    .setOneTimeKeyboard(true) // після натискання кнопки, клавіатура скривається
                    .setKeyboard(keyboard);
        }

}
