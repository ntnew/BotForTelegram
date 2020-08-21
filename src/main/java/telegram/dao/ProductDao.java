package telegram.dao;

import telegram.entity.Product;

import java.util.List;

public interface ProductDao {
    List<Product> getAllByType(String type);

    Product getById(int id);

    void addToBucket(long chatId);
}
