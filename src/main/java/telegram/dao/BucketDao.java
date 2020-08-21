package telegram.dao;

import telegram.entity.Bucket;
import telegram.entity.Product;

import java.util.List;

public interface BucketDao {

    List<Bucket> findAllForChatId(long chatId);

    void deleteById(int id);

    void deleteByProduct(String product, long chatId);

    void deleteAllForChatId(long chatId);

    void addNewProductToChatId(long chatId, Product product);

}
