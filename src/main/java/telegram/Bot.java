package telegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import telegram.Threads.AddToBucket;
import telegram.Threads.ConfirmOrder;
import telegram.Threads.CreateNewOrder;
import telegram.Threads.DeleteUndone;
import telegram.entity.Order;
import telegram.entity.Product;
import telegram.entity.User;
import telegram.service.BucketService;
import telegram.service.OrderService;
import telegram.service.ProductService;
import telegram.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static telegram.Keyboards.*;
import static telegram.MenuKeyboards.*;
import static telegram.common.FileReader.readTxt;
import static telegram.common.FindPrevMsg.deletePrevMsg;
import static telegram.common.FindPrevMsg.findPrevMsg;
import static telegram.common.ReadProp.getProp;
import static telegram.common.TimeCheck.checkTime;


public class Bot extends TelegramLongPollingBot {

    private final BucketService bucketService;
    private final UserService userService;
    private final OrderService orderService;
    private final ProductService productService;
    public Bot(BucketService bucketService,UserService userService, OrderService orderService, ProductService productService){
        this.bucketService = bucketService;
        this.userService = userService;
        this.orderService = orderService;
        this.productService = productService;

    }

    ArrayList<String> checkPreviousMsg = new ArrayList<>();

    @Override
    public void onUpdateReceived(Update update){
        if(update.hasMessage()){
            if(update.getMessage().hasContact()){
                orderService.addPhone(update.getMessage().getChatId(), update.getMessage().getContact().getPhoneNumber());
                try {
                    execute(setFinConfirmButtons(update.getMessage().getChatId(),
                            orderService.findByChatId(update.getMessage().getChatId()).toString()));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

            else if(update.getMessage().hasText()){



                if(update.getMessage().getText().equals("/start")){
                    User user = new User();
                    user.setChatId(update.getMessage().getChatId());
                    user.setUserName(update.getMessage().getFrom().getUserName());
                    userService.saveNewUser(user);
                    sendKeyboardMsg(update.getMessage().getChatId());

                }

                else if(update.getMessage().getText().equals("\uD83D\uDE0E О нас")){
                    sendMsg(update.getMessage().getChatId(), readTxt("about.txt"));

                }
                else if(update.getMessage().getText().equals("\uD83D\uDDC2 Меню")){
                    try {
                        execute(setFirstKeyboard(update.getMessage().getChatId()));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
                else if(update.getMessage().getText().equals("\uD83D\uDCCB Мои заказы")){
                    List<Order> list = orderService.findDoneByChatId(update.getMessage().getChatId());
                    String s = "";
                    for(int i=0; i<list.size(); i++){
                        s = s + list.get(i).toFullString() + "\n-------------------------------------\n";
                    }
                    if(s.equals("")) s = "Заказов нет :(";
                    sendMsg(update.getMessage().getChatId(), s);
                }
                else if(update.getMessage().getText().equals("\uD83E\uDDFA Козина")){
                    DeleteUndone deleteUndone = new DeleteUndone(orderService,update.getMessage().getChatId());
                    deleteUndone.start();
                    try {
                        execute(setBucketKeyboard(update.getMessage().getChatId(),
                                bucketService.findAllForChatId(update.getMessage().getChatId())));
                    } catch (TelegramApiException e) {
                        System.out.println("ошибка в корзине");
                        e.printStackTrace();
                    }
                }
                else if(update.getMessage().getText().equals("✅ Подтвердить")){
                    ConfirmOrder confirmOrder = new ConfirmOrder(orderService, bucketService,update.getMessage().getChatId());
                    confirmOrder.start();
                    sendLastMsg(update.getMessage().getChatId(), "Ваш заказ был отправлен оператору! \n " +
                            "☎ Вскоре вам перезвонит оператор для подтверждения заказа!");
                }
                else if(update.getMessage().getText().equals("❌ Отмена заказа")){
                    sendKeyboardMsg(update.getMessage().getChatId());
                }
                else if(findPrevMsg(checkPreviousMsg,update.getMessage().getChatId()+"name")){
                    orderService.addRealName(update.getMessage().getChatId(), update.getMessage().getText());
                    sendMsg(update.getMessage().getChatId(), "Примечание:");
                    checkPreviousMsg.add(update.getMessage().getChatId()+"notice");
                    deletePrevMsg(checkPreviousMsg,update.getMessage().getChatId()+"name");
                }
                else if(findPrevMsg(checkPreviousMsg,update.getMessage().getChatId()+"address")){
                    orderService.addAddress(update.getMessage().getChatId(), update.getMessage().getText());
                    try {
                        execute(setPayKeyboard(update.getMessage().getChatId()));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    deletePrevMsg(checkPreviousMsg,update.getMessage().getChatId()+"address");
                }
                else if(findPrevMsg(checkPreviousMsg,update.getMessage().getChatId()+"notice")){
                    orderService.addNotice(update.getMessage().getChatId(), update.getMessage().getText());
                    deletePrevMsg(checkPreviousMsg,update.getMessage().getChatId()+"notice");
                    getPhone(update.getMessage().getChatId(), "Нажмите на кнопку снизу, чтобы отправить телефон. Не вводите его вручную!");
                }


                else {
                    sendMsg(update.getMessage().getChatId(),"я тебя не понимать " + update.getMessage().getChatId());
                }
            }
        }

        //Для Коллбэка
        else if(update.hasCallbackQuery()){
            Pattern p = Pattern.compile("pizza\\d");
            Matcher m = p.matcher(update.getCallbackQuery().getData());
            Pattern p2 = Pattern.compile("snack\\d");
            Matcher m2 = p2.matcher(update.getCallbackQuery().getData());
            Pattern p3 = Pattern.compile("drink\\d");
            Matcher m3 = p3.matcher(update.getCallbackQuery().getData());
            Pattern p4 = Pattern.compile("dessert\\d");
            Matcher m4 = p4.matcher(update.getCallbackQuery().getData());
            Pattern p5 = Pattern.compile("sauce\\d");
            Matcher m5 = p5.matcher(update.getCallbackQuery().getData());
            Pattern p6 = Pattern.compile("addToBucket\\d");
            Matcher addToBuck = p6.matcher(update.getCallbackQuery().getData());
            Pattern p7 = Pattern.compile("<<\\d");
            Matcher m7 = p7.matcher(update.getCallbackQuery().getData());
            Pattern p8 = Pattern.compile(">>\\d");
            Matcher m8 = p8.matcher(update.getCallbackQuery().getData());

            if(update.getCallbackQuery().getData().equals("edit")){
                try {
                    execute(setEditKeyboard(update.getCallbackQuery().getMessage().getChatId(),
                            bucketService.findAllForChatIdList(update.getCallbackQuery().getMessage().getChatId())));
                } catch (TelegramApiException e) {
                        e.printStackTrace();
                }
            }
            else if(update.getCallbackQuery().getData().equals("confirm")){
                if(checkTime()) {
                    CreateNewOrder thread = new CreateNewOrder(orderService, bucketService,
                            update.getCallbackQuery().getMessage().getChatId());
                    thread.start();
                    if (!bucketService.findAllForChatIdList(update.getCallbackQuery().getMessage().getChatId()).isEmpty()) {
                        try {
                            execute(setConfirmKeyboard(update.getCallbackQuery().getMessage().getChatId()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                    }
                }else sendLastMsg(update.getCallbackQuery().getMessage().getChatId(),
                        "Извините, мы сейчас не работаем.\n Режим работы\n" +
                                "ВС — ЧТ с 10:00 до 23:00\n" +
                                "ПТ — СБ с 10:00 до 01:00");
            }
            else if(update.getCallbackQuery().getData().equals("pickup")){
                orderService.addType(update.getCallbackQuery().getMessage().getChatId(), "Самовывоз");
                try {
                    execute(setPickupKeyboard(update.getCallbackQuery().getMessage().getChatId()));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            else if(update.getCallbackQuery().getData().equals("delivery")){
                orderService.addType(update.getCallbackQuery().getMessage().getChatId(), "Доставка");
                sendMsg(update.getCallbackQuery().getMessage().getChatId(), "Напишите полностью ваш адрес:");
                checkPreviousMsg.add(update.getCallbackQuery().getMessage().getChatId()+"address");
            }
            else if(update.getCallbackQuery().getData().equals("aurora")){
                try {
                    orderService.addAddress(update.getCallbackQuery().getMessage().getChatId(),"ул. Удмуртская 304в");
                    execute(setPayKeyboard(update.getCallbackQuery().getMessage().getChatId()));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            else if(update.getCallbackQuery().getData().equals("cash")){
                orderService.addPay(update.getCallbackQuery().getMessage().getChatId(), "Наличные");
                sendMsg(update.getCallbackQuery().getMessage().getChatId(), "Напишите ваше имя:");
                checkPreviousMsg.add(update.getCallbackQuery().getMessage().getChatId()+"name");
            }
            else if(update.getCallbackQuery().getData().equals("byCard")){
                orderService.addPay(update.getCallbackQuery().getMessage().getChatId(), "Картой");
                sendMsg(update.getCallbackQuery().getMessage().getChatId(), "Напишите ваше имя:");
                checkPreviousMsg.add(update.getCallbackQuery().getMessage().getChatId()+"name");
            }

            else if(update.getCallbackQuery().getData().replaceAll("\\d","").equals("delete")){
                System.out.println("удалил");
                bucketService.deleteById(Integer.parseInt(update.getCallbackQuery().getData().replaceAll("delete","")));
                try {
                    execute(setBucketKeyboard(update.getCallbackQuery().getMessage().getChatId(),
                            bucketService.findAllForChatId(update.getCallbackQuery().getMessage().getChatId())));
                } catch (TelegramApiException e) {
                    System.out.println("ошибка в корзине");
                    e.printStackTrace();
                }

            }
            else if(update.getCallbackQuery().getData().equals("pizza")){
                try {
                    execute(setFoodKeyboard(update.getCallbackQuery().getMessage().getChatId(),
                            update.getCallbackQuery().getMessage().getMessageId(), productService, "pizza"));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            else if(update.getCallbackQuery().getData().equals("snacks")){
                try {
                    execute(setFoodKeyboard(update.getCallbackQuery().getMessage().getChatId(),
                            update.getCallbackQuery().getMessage().getMessageId(), productService, "snack"));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

            }
            else if(update.getCallbackQuery().getData().equals("sauce")){
                try {
                    execute(setFoodKeyboard(update.getCallbackQuery().getMessage().getChatId(),
                            update.getCallbackQuery().getMessage().getMessageId(), productService, "sauce"));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            else if(update.getCallbackQuery().getData().equals("drinks")){
                try {
                    execute(setFoodKeyboard(update.getCallbackQuery().getMessage().getChatId(),
                            update.getCallbackQuery().getMessage().getMessageId(), productService, "drink"));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            else if(update.getCallbackQuery().getData().equals("desserts")){
                try {
                    execute(setFoodKeyboard(update.getCallbackQuery().getMessage().getChatId(),
                            update.getCallbackQuery().getMessage().getMessageId(), productService, "dessert"));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            else if(m.find()){
                int id = Integer.parseInt(update.getCallbackQuery().getData().replace("pizza", ""));
                try {
                    execute(SoloProductKeyboard(update.getCallbackQuery().getMessage().getChatId(),
                            update.getCallbackQuery().getMessage().getMessageId(),
                            productService,
                            id));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            else if(m2.find()){
                int id = Integer.parseInt(update.getCallbackQuery().getData().replace("snack", ""));
                try {
                    execute(SoloProductKeyboard(update.getCallbackQuery().getMessage().getChatId(),
                            update.getCallbackQuery().getMessage().getMessageId(),
                            productService,
                            id));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            else if(m3.find()){
                int id = Integer.parseInt(update.getCallbackQuery().getData().replace("drink", ""));
                try {
                    execute(SoloProductKeyboard(update.getCallbackQuery().getMessage().getChatId(),
                            update.getCallbackQuery().getMessage().getMessageId(),
                            productService,
                            id));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            else if(m4.find()){
                int id = Integer.parseInt(update.getCallbackQuery().getData().replace("dessert", ""));
                try {
                    execute(SoloProductKeyboard(update.getCallbackQuery().getMessage().getChatId(),
                            update.getCallbackQuery().getMessage().getMessageId(),
                            productService,
                            id));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            else if(m5.find()){
                int id = Integer.parseInt(update.getCallbackQuery().getData().replace("sauce", ""));
                try {
                    execute(SoloProductKeyboard(update.getCallbackQuery().getMessage().getChatId(),
                            update.getCallbackQuery().getMessage().getMessageId(),
                            productService,
                            id));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            else if(m7.find()){
                int id = Integer.parseInt(update.getCallbackQuery().getData().replace("<<", ""));
                try {
                    execute(SoloProductKeyboard(update.getCallbackQuery().getMessage().getChatId(),
                            update.getCallbackQuery().getMessage().getMessageId(),
                            productService,
                            id));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            else if(m8.find()){
                int id = Integer.parseInt(update.getCallbackQuery().getData().replace(">>", ""));
                try {
                    execute(SoloProductKeyboard(update.getCallbackQuery().getMessage().getChatId(),
                            update.getCallbackQuery().getMessage().getMessageId(),
                            productService,
                            id));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            else if(addToBuck.find()){
                int id = Integer.parseInt(update.getCallbackQuery().getData().replace("addToBucket", ""));
                AddToBucket addToBucket = new AddToBucket(productService, bucketService,
                        update.getCallbackQuery().getMessage().getChatId(), id);
                addToBucket.start();
                try {
                    execute(postAddToBucketKeyboard(update.getCallbackQuery().getMessage().getChatId(),
                            update.getCallbackQuery().getMessage().getMessageId(),
                            productService, id));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

            }
            else if(update.getCallbackQuery().getData().equals("additives")){
                try {
                    execute(BackToMenuKeyboard(update.getCallbackQuery().getMessage().getChatId(),
                            update.getCallbackQuery().getMessage().getMessageId()));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            else if(update.getCallbackQuery().getData().equals("backToMenu")){
                try {
                    execute(BackToMenuKeyboard(update.getCallbackQuery().getMessage().getChatId(),
                            update.getCallbackQuery().getMessage().getMessageId()));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            else if(update.getCallbackQuery().getData().equals("toBucket")){
                DeleteUndone deleteUndone = new DeleteUndone(orderService,update.getCallbackQuery().getMessage().getChatId());
                deleteUndone.start();
                try {
                    execute(setBucketKeyboard(update.getCallbackQuery().getMessage().getChatId(),
                            bucketService.findAllForChatId(update.getCallbackQuery().getMessage().getChatId())));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized void sendMsg(long chatId, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            System.out.println(e);
        }
    }
    public synchronized void sendLastMsg(long chatId, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);
        setButtons(sendMessage);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            System.out.println(e);
        }
    }
    public synchronized void sendKeyboardMsg(long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(readTxt("greeting.txt"));
        setButtons(sendMessage);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            System.out.println(e);
        }
    }
    public synchronized void getPhone(long chatId, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);
        askPhone(sendMessage);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            System.out.println(e);
        }
    }


    @Override
    public String getBotUsername() {
        return getProp("botUsername");
    }

    @Override
    public String getBotToken() {
        return getProp("botToken");
    }


}
