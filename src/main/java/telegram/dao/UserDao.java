package telegram.dao;

import telegram.entity.User;

public interface UserDao {

    void saveNewUser(User user);

    User findByChatId(long chatId);
}
