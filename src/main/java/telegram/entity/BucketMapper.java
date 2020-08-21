package telegram.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BucketMapper  {
    public static Bucket buildBucketObject(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        long chatId = resultSet.getLong("chatId");
        String product = resultSet.getString("product");
        double price = resultSet.getDouble("price");
        Bucket bucket = new Bucket(id, chatId, product, price);
        return bucket;
    }
}
