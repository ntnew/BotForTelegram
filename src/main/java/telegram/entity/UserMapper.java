package telegram.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper {
    public static User buildUserObject(ResultSet resultSet)  {
        long chatId;
        try {
            chatId = resultSet.getLong("chatId");
        } catch (SQLException throwables) {
            return null;
        }
        String userName = null;
        try {
            userName = resultSet.getString("userName");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String realName = null;
        try {
            realName = resultSet.getString("realName");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String phone = null;
        try {
            phone = resultSet.getString("phone");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new User( chatId, userName, realName, phone);
    }
}
