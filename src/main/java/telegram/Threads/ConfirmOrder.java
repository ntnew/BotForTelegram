package telegram.Threads;

import telegram.entity.Order;
import telegram.service.BucketService;
import telegram.service.OrderService;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import static telegram.common.sendLetter.sendLetter;

public class ConfirmOrder extends Thread {

    private final BucketService bucketService;
    private final OrderService orderService;
    private final long chatId;
    public ConfirmOrder (OrderService orderService,BucketService bucketService, long chatId1){
        this.orderService = orderService;
        this.chatId = chatId1;
        this.bucketService = bucketService;
    }
    @Override
    public void run() {
        Order order = orderService.findByChatId(chatId);
        order.setDate(new Date());
        sendLetter(order.toServiceString());
        orderService.setDateTime(chatId);
        bucketService.deleteAllForChatId(chatId);
    }
}
