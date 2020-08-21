package telegram.Threads;

import telegram.entity.Bucket;
import telegram.service.BucketService;
import telegram.service.OrderService;

import java.util.List;

public class DeleteUndone extends Thread {

    private final OrderService orderService;
    private final long chatId;
    public DeleteUndone(OrderService orderService, long chatId){
        this.orderService = orderService;
        this.chatId = chatId;
    }
    @Override
    public void run() {
        orderService.deleteUndone(chatId);
    }
}
