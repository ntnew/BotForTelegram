package telegram.dao;

import telegram.entity.Order;

import java.util.List;

public interface OrderDao {
    void createNew(long chatId, String products, double fullPrice);

    void addType(long chatId, String type);

    void addAddress(long chatId, String address);

    void addPay(long chatId, String pay);

    void addRealName(long chatId, String realName);

    void addPhone(long chatId, String phone);

    void setDateTime(long chatId);

    void addNotice(long chatId, String notice);

    Order findByChatId(long chatId);

    List<Order> findDoneByChatId(long chatId);

    void deleteUndone(long chatId);
}
