package telegram.Threads;

import telegram.entity.Product;
import telegram.service.BucketService;
import telegram.service.ProductService;

public class AddToBucket extends Thread {
    private final BucketService bucketService;
    private final ProductService productService;
    private final long chatId;
    private final int prodId;
    public AddToBucket(ProductService productService, BucketService bucketService, long chatId, int prodId){
        this.productService = productService;
        this.chatId = chatId;
        this.bucketService = bucketService;
        this.prodId = prodId;
    }
    @Override
    public void run() {
        Product product = productService.getById(prodId);
        bucketService.addNewProductToChatId(chatId, product);
        System.out.println("добавил");
    }
}
