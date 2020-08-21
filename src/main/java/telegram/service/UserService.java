package telegram.service;

import telegram.entity.User;

public interface UserService {

    void saveNewUser(User user);

    User findByChatId(long chatId);
}
