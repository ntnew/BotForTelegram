package telegram.service.ServiceImpl;

import telegram.dao.UserDao;
import telegram.entity.User;
import telegram.service.UserService;

public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao){
        this.userDao = userDao;
    }

    @Override
    public void saveNewUser(User user) {
        if(findByChatId(user.getChatId())==null){
            userDao.saveNewUser(user);
        }
    }

    @Override
    public User findByChatId(long chatId) {
        return userDao.findByChatId(chatId);
    }
}
