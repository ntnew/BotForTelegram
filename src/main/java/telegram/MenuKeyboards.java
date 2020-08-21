package telegram;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import telegram.entity.Product;
import telegram.service.ProductService;

import java.util.ArrayList;
import java.util.List;

public class MenuKeyboards {
    public static SendMessage setFirstKeyboard(long chatId){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton5 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("\uD83C\uDF55 Пицца \uD83C\uDF55");
        inlineKeyboardButton1.setCallbackData("pizza");
        inlineKeyboardButton2.setText("\uD83C\uDF57 Закуски \uD83C\uDF56");
        inlineKeyboardButton2.setCallbackData("snacks");
        inlineKeyboardButton3.setText("\uD83C\uDF79 Напитки \uD83C\uDF79");
        inlineKeyboardButton3.setCallbackData("drinks");
        inlineKeyboardButton4.setText("\uD83C\uDF45 Соусы и Дипы \uD83C\uDF36");
        inlineKeyboardButton4.setCallbackData("sauce");
        inlineKeyboardButton5.setText("\uD83C\uDF70 Десерты \uD83E\uDDC1");
        inlineKeyboardButton5.setCallbackData("desserts");
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow4 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow5 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        keyboardButtonsRow2.add(inlineKeyboardButton2);
        keyboardButtonsRow3.add(inlineKeyboardButton3);
        keyboardButtonsRow4.add(inlineKeyboardButton4);
        keyboardButtonsRow5.add(inlineKeyboardButton5);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        rowList.add(keyboardButtonsRow3);
        rowList.add(keyboardButtonsRow4);
        rowList.add(keyboardButtonsRow5);
        inlineKeyboardMarkup.setKeyboard(rowList);
        return new SendMessage().setChatId(chatId).setText("__________Меню:__________").setReplyMarkup(inlineKeyboardMarkup);
    }
    public static EditMessageText setFoodKeyboard(long chatId, int messageId, ProductService productService, String type){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<Product> list = productService.getAllByType(type);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        for(int i=0; i<list.size();i++){
            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
            inlineKeyboardButton1.setText(list.get(i).toString());
            if(type.equals("pizza")) inlineKeyboardButton1.setCallbackData("pizza"+list.get(i).getId());
            else if (type.equals("drink"))inlineKeyboardButton1.setCallbackData("drink"+list.get(i).getId());
            else if (type.equals("snack")) inlineKeyboardButton1.setCallbackData("snack"+list.get(i).getId());
            else if (type.equals("sauce")) inlineKeyboardButton1.setCallbackData("sauce"+list.get(i).getId());
            else if (type.equals("dessert")) inlineKeyboardButton1.setCallbackData("dessert"+list.get(i).getId());
            List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
            keyboardButtonsRow1.add(inlineKeyboardButton1);
            rowList.add(keyboardButtonsRow1);

        }
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton2.setText("\uD83D\uDD19 Назад");
        inlineKeyboardButton2.setCallbackData("backToMenu");
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow2.add(inlineKeyboardButton2);
        rowList.add(keyboardButtonsRow2);
        inlineKeyboardMarkup.setKeyboard(rowList);
        EditMessageText editMessageText = new EditMessageText().setChatId(chatId).setMessageId(messageId).setReplyMarkup(inlineKeyboardMarkup);
        if(type.equals("pizza")) editMessageText.setText("__________Пицца:_________");
        else if (type.equals("drink")) editMessageText.setText("__________Напитки:_________");
        else if (type.equals("snack")) editMessageText.setText("__________Закуски:_________");
        else if (type.equals("sauce")) editMessageText.setText("_______Соусы и дипы:______");
        else if (type.equals("dessert")) editMessageText.setText("_______Десерты:______");
        return editMessageText;
    }
    public static EditMessageText BackToMenuKeyboard(long chatId, int messageId){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton5 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("\uD83C\uDF55 Пицца \uD83C\uDF55");
        inlineKeyboardButton1.setCallbackData("pizza");
        inlineKeyboardButton2.setText("\uD83C\uDF57 Закуски \uD83C\uDF56");
        inlineKeyboardButton2.setCallbackData("snacks");
        inlineKeyboardButton3.setText("\uD83C\uDF79 Напитки \uD83C\uDF79");
        inlineKeyboardButton3.setCallbackData("drinks");
        inlineKeyboardButton4.setText("\uD83C\uDF45 Соусы и Дипы \uD83C\uDF36");
        inlineKeyboardButton4.setCallbackData("sauce");
        inlineKeyboardButton5.setText("\uD83C\uDF70 Десерты \uD83E\uDDC1");
        inlineKeyboardButton5.setCallbackData("desserts");
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow4 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow5 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        keyboardButtonsRow2.add(inlineKeyboardButton2);
        keyboardButtonsRow3.add(inlineKeyboardButton3);
        keyboardButtonsRow4.add(inlineKeyboardButton4);
        keyboardButtonsRow5.add(inlineKeyboardButton5);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        rowList.add(keyboardButtonsRow3);
        rowList.add(keyboardButtonsRow4);
        rowList.add(keyboardButtonsRow5);
        inlineKeyboardMarkup.setKeyboard(rowList);
        return new EditMessageText()
                .setChatId(chatId).setText("__________Меню:__________")
                .setReplyMarkup(inlineKeyboardMarkup)
                .setMessageId(messageId);
    }

    public static EditMessageText SoloProductKeyboard(long chatId, int messageId, ProductService productService, int prodId){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        Product product = productService.getById(prodId);
        int maxCounter = 43;
        int plusCounter = prodId+1;
        if(plusCounter>43) plusCounter =43;
        int minusCounter = prodId-1;
        if(minusCounter<1) minusCounter = 1;
        EditMessageText editMessageText = new EditMessageText();
        editMessageText.setChatId(chatId)
                .setText(product.toFullString())
                .setReplyMarkup(inlineKeyboardMarkup)
                .setMessageId(messageId);
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton()
                .setText("✅ В корзину")
                .setCallbackData("addToBucket"+product.getId());
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton()
                .setText("\uD83D\uDD19 Назад")
                .setCallbackData("backToMenu");
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow2.add(inlineKeyboardButton2);
        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton()
                .setText("<<")
                .setCallbackData("<<"+minusCounter);
        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton()
                .setText(prodId + "/"+ maxCounter)
                .setCallbackData("no");
        InlineKeyboardButton inlineKeyboardButton5 = new InlineKeyboardButton()
                .setText(">>")
                .setCallbackData(">>"+plusCounter);
        List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();
        keyboardButtonsRow3.add(inlineKeyboardButton3);
        keyboardButtonsRow3.add(inlineKeyboardButton4);
        keyboardButtonsRow3.add(inlineKeyboardButton5);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow3);
        rowList.add(keyboardButtonsRow2);
        inlineKeyboardMarkup.setKeyboard(rowList);
        return editMessageText;
    }

    public static EditMessageText postAddToBucketKeyboard(long chatId, int messageId, ProductService productService, int prodId){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        Product product = productService.getById(prodId);
        EditMessageText editMessageText = new EditMessageText();
        editMessageText.setChatId(chatId)
                .setText(product.getName() + " добавлено в корзину!")
                .setReplyMarkup(inlineKeyboardMarkup)
                .setMessageId(messageId);
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton()
                .setText("\uD83D\uDED2 Оставить в корзине и выбрать ещё")
                .setCallbackData("backToMenu");
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton()
                .setText("\uD83D\uDCE6 Оформить заказ")
                .setCallbackData("toBucket");
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow2.add(inlineKeyboardButton2);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
//        if(product.getType().equals("pizza")){
//            InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton()
//                    .setText("Положить добавки!")
//                    .setCallbackData("additives");
//            List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();
//            keyboardButtonsRow3.add(inlineKeyboardButton3);
//            rowList.add(keyboardButtonsRow3);
//        }
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        inlineKeyboardMarkup.setKeyboard(rowList);
        return editMessageText;
    }
    public static EditMessageText additivesKeyboard(long chatId, int messageId, ProductService productService){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        List<Product> list = productService.getAllByType("additives");
        EditMessageText editMessageText = new EditMessageText();
        editMessageText.setChatId(chatId)
                .setText("Выберите добавки")
                .setReplyMarkup(inlineKeyboardMarkup)
                .setMessageId(messageId);
        for(int i=0; i<list.size(); i++){
            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton()
                    .setText(list.get(i).toString())
                    .setCallbackData("addAdditive"+list.get(i).getId());
            List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
            keyboardButtonsRow1.add(inlineKeyboardButton1);
            rowList.add(keyboardButtonsRow1);
        }


        return editMessageText;
    }
}
