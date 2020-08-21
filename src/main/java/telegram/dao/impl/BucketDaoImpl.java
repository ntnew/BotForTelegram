package telegram.dao.impl;

import telegram.dao.BucketDao;
import telegram.entity.Bucket;
import telegram.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static telegram.DBProcessor.DBProcessor.getStatement;
import static telegram.entity.BucketMapper.buildBucketObject;

public class BucketDaoImpl implements BucketDao {
    @Override
    public List<Bucket> findAllForChatId(long chatId) {
        List<Bucket> list = new ArrayList<>();
        String sql ="SELECT * FROM pizzapptelegram.bucket WHERE chatId=" + chatId;
        ResultSet resultSet = null;
        try {
            resultSet = getStatement().executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("1");
        }

        while(true){
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("2");
            }
            try {
                list.add(buildBucketObject(resultSet));
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("3");
            }
        }
        try {
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("5");
        }
        return list;
    }

    @Override
    public void deleteById(int id) {
        String sql ="Delete FROM pizzapptelegram.bucket WHERE id=" + id;
        try {
            getStatement().execute(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void deleteByProduct(String product, long chatId) {
        String sql ="Delete FROM pizzapptelegram.bucket WHERE product LIKE '" + product + "' AND chatId = " +chatId;
        try {
            getStatement().execute(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void deleteAllForChatId(long chatId) {
        String sql ="Delete FROM pizzapptelegram.bucket WHERE chatId = " +chatId;
        try {
            getStatement().execute(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void addNewProductToChatId(long chatId, Product product) {
        String sql = "insert into pizzapptelegram.bucket values(default," + chatId +", '" +product.getName()+"', "+
                product.getPrice()+ ")";
        try {
            getStatement().execute(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
