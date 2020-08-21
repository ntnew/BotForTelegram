package telegram.service.ServiceImpl;

import telegram.dao.OrderDao;
import telegram.entity.Order;
import telegram.service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;

    public OrderServiceImpl(OrderDao orderDao){
        this.orderDao = orderDao;
    }

    @Override
    public void createNew(long chatId, String products, double fullPrice) {
        orderDao.createNew(chatId, products, fullPrice);
    }

    @Override
    public void addType(long chatId, String type) {
        orderDao.addType(chatId, type);
    }

    @Override
    public void addAddress(long chatId, String address) {
        orderDao.addAddress(chatId,address);
    }

    @Override
    public void addPay(long chatId, String pay) {
        orderDao.addPay(chatId, pay);
    }

    @Override
    public void addRealName(long chatId, String realName) {
        orderDao.addRealName(chatId, realName);
    }

    @Override
    public void addPhone(long chatId, String phone) {
        orderDao.addPhone(chatId, phone);
    }

    @Override
    public void setDateTime(long chatId) {
        orderDao.setDateTime(chatId);
    }

    @Override
    public void addNotice(long chatId, String notice) {
        orderDao.addNotice(chatId, notice);
    }

    @Override
    public Order findByChatId(long chatId) {
        return orderDao.findByChatId(chatId);
    }

    @Override
    public List<Order> findDoneByChatId(long chatId) {
        return orderDao.findDoneByChatId(chatId);
    }

    @Override
    public void deleteUndone(long chatId) {
        orderDao.deleteUndone(chatId);
    }
}
