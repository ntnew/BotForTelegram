package telegram.dao.impl;

import telegram.dao.UserDao;
import telegram.entity.Bucket;
import telegram.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static telegram.DBProcessor.DBProcessor.getStatement;
import static telegram.entity.BucketMapper.buildBucketObject;
import static telegram.entity.UserMapper.buildUserObject;

public class UserDaoImpl implements UserDao {
    @Override
    public void saveNewUser(User user) {
        String sql ="INSERT INTO pizzapptelegram.users VALUES(" + user.getChatId() + ", '" + user.getUserName()+ "', Default, Default);" ;
        try {
            getStatement().execute(sql);
        } catch (SQLException e) {
            System.out.println("user already exists");
        }
    }

    @Override
    public User findByChatId(long chatId) {
        User user;
        String sql ="SELECT * FROM pizzapptelegram.users WHERE chatId = " + chatId;
        ResultSet resultSet = null;
        try {
            resultSet = getStatement().executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("1");
        }


        user = buildUserObject(resultSet);

        try {
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }
}
