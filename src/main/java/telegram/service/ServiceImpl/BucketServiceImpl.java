package telegram.service.ServiceImpl;

import telegram.dao.BucketDao;
import telegram.entity.Bucket;
import telegram.entity.Product;
import telegram.service.BucketService;

import java.util.List;

public class BucketServiceImpl implements BucketService {

    private final BucketDao bucketDao;

    public BucketServiceImpl(BucketDao bucketDao){
        this.bucketDao = bucketDao;
    }

    @Override
    public String findAllForChatId(long id) {
        String s = "";
        Double fullPrice = 0.0;
        List <Bucket> list = bucketDao.findAllForChatId(id);
        for (int i = 0; i < list.size(); i++ ){
            s = s+ list.get(i).getProduct() + " ЦЕНА: " + list.get(i).getPrice() + "\n";
            fullPrice = fullPrice + list.get(i).getPrice();
        }
        if (s.equals("")) s="Корзина пуста :(";
        else s = s + "Сумма: " + fullPrice + " руб.";
        return s;
    }

    @Override
    public List<Bucket> findAllForChatIdList(long id) {
        return bucketDao.findAllForChatId(id);
    }

    @Override
    public void deleteById(int id) {
        bucketDao.deleteById(id);
    }

    @Override
    public void deleteByProduct(String product, long chatId) {
        bucketDao.deleteByProduct(product, chatId);
    }

    @Override
    public void deleteAllForChatId(long chatId) {
        bucketDao.deleteAllForChatId(chatId);
    }

    @Override
    public void addNewProductToChatId(long chatId, Product product) {
        bucketDao.addNewProductToChatId(chatId, product);
    }
}
