package telegram.service;

import telegram.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllByType(String type);

    Product getById(int id);

    void addToBucket(long chatId);
}
