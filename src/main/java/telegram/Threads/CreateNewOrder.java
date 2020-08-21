package telegram.Threads;

import telegram.entity.Bucket;
import telegram.service.BucketService;
import telegram.service.OrderService;
import telegram.service.UserService;

import java.util.List;

public class CreateNewOrder extends Thread{

    private final BucketService bucketService;
    private final OrderService orderService;
    private final long chatId;
    public CreateNewOrder(OrderService orderService,BucketService bucketService, long chatId1){
        this.orderService = orderService;
        this.chatId = chatId1;
        this.bucketService = bucketService;
    }
    @Override
    public void run() {
        String products = "";
        double fullPrice = 0.0;
        List<Bucket> list = bucketService.findAllForChatIdList(chatId);
        if(!list.isEmpty()){
            for (int i = 0; i < list.size(); i++ ){
                products = products + list.get(i).getProduct() + " ЦЕНА: " + list.get(i).getPrice() + "\n";
                fullPrice = fullPrice + list.get(i).getPrice();
            }
            orderService.createNew(chatId, products, fullPrice);
            System.out.println("Добавил");
        }
        else System.out.println("лист пуст");
    }
}
