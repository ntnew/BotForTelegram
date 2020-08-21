package telegram;


import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import telegram.dao.impl.BucketDaoImpl;
import telegram.dao.impl.OrderDaoImpl;
import telegram.dao.impl.ProductDaoImpl;
import telegram.dao.impl.UserDaoImpl;
import telegram.service.ServiceImpl.BucketServiceImpl;
import telegram.service.ServiceImpl.OrderServiceImpl;
import telegram.service.ServiceImpl.ProductServiceImpl;
import telegram.service.ServiceImpl.UserServiceImpl;

public class App {


    public static void main( String[] args ) {

        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            System.out.println("Connected");
            telegramBotsApi.registerBot(
                    new Bot(new BucketServiceImpl(new BucketDaoImpl()), new UserServiceImpl(new UserDaoImpl()),
                            new OrderServiceImpl(new OrderDaoImpl()), new ProductServiceImpl(new ProductDaoImpl())));
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
            System.out.println("Disconnected in main");
        }
    }
}
