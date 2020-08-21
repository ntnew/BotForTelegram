package telegram.entity;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class ProductMapper {
    public static Product buildProductObject(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String description = resultSet.getString("description");
        int price = resultSet.getInt("price");
        String type = resultSet.getString("type");

        return new Product(id, name, price, description, type);
    }
}
