package telegram.entity;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class OrderMapper {
    public static Order buildOrderObject(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        long chatId = resultSet.getLong("chatId");
        String realName = resultSet.getString("realName");
        String notice = resultSet.getString("notice");
        String address = resultSet.getString("address");
        String phone = resultSet.getString("phone");
        String products = resultSet.getString("products");
        String type = resultSet.getString("type");
        String pay = resultSet.getString("pay");
        Date date;
        try{
             date = resultSet.getDate("dateTime");
        }catch (Exception e){
            date = new Date(1451665447567L);
        }
        Time time = resultSet.getTime("dateTime");
        double price = resultSet.getDouble("price");

        return new Order(id, chatId,realName,notice,address,phone,products, price, type, pay, date, time);
    }
}
