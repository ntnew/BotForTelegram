package telegram.dao.impl;

import telegram.dao.ProductDao;
import telegram.entity.Product;
import telegram.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static telegram.DBProcessor.DBProcessor.getStatement;
import static telegram.entity.ProductMapper.buildProductObject;
import static telegram.entity.UserMapper.buildUserObject;

public class ProductDaoImpl implements ProductDao {
    @Override
    public List<Product> getAllByType(String type) {
        List<Product> list = new ArrayList<>();
        String sql ="SELECT * FROM pizzapptelegram.products WHERE type = '" + type + "';";
        ResultSet resultSet = null;
        try {
            resultSet = getStatement().executeQuery(sql);
            //resultSet.next();
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
                list.add(buildProductObject(resultSet));
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("3");
            }
        }


        try {
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    @Override
    public Product getById(int id) {
        Product product = new Product();
        String sql ="SELECT * FROM pizzapptelegram.products WHERE id = " + id;
        ResultSet resultSet = null;
        try {
            resultSet = getStatement().executeQuery(sql);
            resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("1");
        }


        try {
            product = buildProductObject(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return product;
    }

    @Override
    public void addToBucket(long chatId) {

    }
}
