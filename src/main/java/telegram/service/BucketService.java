package telegram.service;

import telegram.entity.Bucket;
import telegram.entity.Order;
import telegram.entity.Product;

import java.util.List;

public interface BucketService {

    String findAllForChatId(long id);

    List<Bucket> findAllForChatIdList(long id);

    void deleteById(int id);

    void deleteByProduct(String product, long chatId);

    void deleteAllForChatId(long chatId);

    void addNewProductToChatId(long chatId, Product product);
}
