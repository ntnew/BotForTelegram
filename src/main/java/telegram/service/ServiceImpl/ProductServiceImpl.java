package telegram.service.ServiceImpl;

import telegram.dao.OrderDao;
import telegram.dao.ProductDao;
import telegram.entity.Product;
import telegram.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private final ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao){
        this.productDao = productDao;
    }

    @Override
    public List<Product> getAllByType(String type) {
        return productDao.getAllByType(type);
    }

    @Override
    public Product getById(int id) {
        return productDao.getById(id);
    }

    @Override
    public void addToBucket(long chatId) {

    }
}
