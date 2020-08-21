package telegram.dao.impl;

import telegram.dao.OrderDao;
import telegram.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static telegram.DBProcessor.DBProcessor.getStatement;
import static telegram.entity.BucketMapper.buildBucketObject;
import static telegram.entity.OrderMapper.buildOrderObject;

public class OrderDaoImpl implements OrderDao {
    @Override
    public void createNew(long chatId, String products, double fullPrice) {
        String sql ="INSERT INTO pizzapptelegram.orders VALUES(default," + chatId + ", default, default, " +
                "default, default, '" + products + "', " + fullPrice + ", Default, default, default);" ;
        try {
            getStatement().execute(sql);
        } catch (SQLException e) {
            System.out.println("Ошибка при создании нового заказа");
        }
    }

    @Override
    public void addType(long chatId, String type) {
        String sql = "UPDATE pizzapptelegram.orders SET type = '"+ type + "' WHERE chatId = " + chatId + " AND dateTime = '0000-00-00 00:00:00';";
        try {
            getStatement().execute(sql);
        } catch (SQLException e) {
            System.out.println("user already exists");
        }
    }

    @Override
    public void addAddress(long chatId, String address) {
        String sql = "UPDATE pizzapptelegram.orders SET address = '"+ address + "' WHERE chatId = " + chatId + " AND dateTime = '0000-00-00 00:00:00';";
        try {
            getStatement().execute(sql);
        } catch (SQLException e) {
            System.out.println("user already exists");
        }
    }

    @Override
    public void addPay(long chatId, String pay) {
        String sql = "UPDATE pizzapptelegram.orders SET pay = '"+ pay + "' WHERE chatId = " + chatId + " AND dateTime = '0000-00-00 00:00:00';";
        try {
            getStatement().execute(sql);
        } catch (SQLException e) {
            System.out.println("user already exists");
        }
    }

    @Override
    public void addRealName(long chatId, String realName) {
        String sql = "UPDATE pizzapptelegram.orders SET realName = '"+ realName + "' WHERE chatId = " + chatId + " AND dateTime = '0000-00-00 00:00:00';";
        try {
            getStatement().execute(sql);
        } catch (SQLException e) {
            System.out.println("user already exists");
        }
    }

    @Override
    public void addPhone(long chatId, String phone) {
        String sql = "UPDATE pizzapptelegram.orders SET phone = '"+ phone + "' WHERE chatId = " + chatId + " AND dateTime = '0000-00-00 00:00:00';";
        try {
            getStatement().execute(sql);
        } catch (SQLException e) {
            System.out.println("user already exists");
        }
    }

    @Override
    public void setDateTime(long chatId) {
        String sql = "UPDATE pizzapptelegram.orders SET dateTime = now() WHERE chatId = " + chatId + " AND dateTime = '0000-00-00 00:00:00';";
        try {
            getStatement().execute(sql);
        } catch (SQLException e) {
            System.out.println("user already exists");
        }
    }

    @Override
    public void addNotice(long chatId, String notice) {
        String sql = "UPDATE pizzapptelegram.orders SET notice = '"+ notice + "' WHERE chatId = " + chatId + " AND dateTime = '0000-00-00 00:00:00';";
        try {
            getStatement().execute(sql);
        } catch (SQLException e) {
            System.out.println("user already exists");
        }
    }

    @Override
    public Order findByChatId(long chatId) {
        Order order = new Order();
        String sql ="SELECT * FROM pizzapptelegram.orders WHERE chatId = " + chatId + " AND dateTime = '0000-00-00 00:00:00';";
        ResultSet resultSet = null;
        try {
            resultSet = getStatement().executeQuery(sql);
            resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("1");
        }

        try {
            order = buildOrderObject(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        try {
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return order;
    }

    @Override
    public List<Order> findDoneByChatId(long chatId) {
        List<Order> list = new ArrayList<>();
        String sql ="SELECT * FROM pizzapptelegram.orders WHERE chatId = " + chatId + " AND dateTime > '0000-00-00 00:00:00';";
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
                list.add(buildOrderObject(resultSet));
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
    public void deleteUndone(long chatId) {
        String sql ="DELETE FROM pizzapptelegram.orders WHERE chatId = " + chatId + " AND dateTime = '0000-00-00 00:00:00';";

        try {
            getStatement().execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
