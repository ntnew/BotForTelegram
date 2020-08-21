package telegram.DBProcessor;

import java.sql.*;

import static telegram.common.ReadProp.getProp;

public class DBProcessor {
    private final static String URL = "jdbc:mysql://localhost:3306/pizzapptelegram?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
    private final static String USERNAME = getProp("db.username");
    private final static String PASSWORD = getProp("db.password");;

    private Connection connection = null;
    public DBProcessor(){
        Driver driver;
        try {
            driver = new com.mysql.cj.jdbc.Driver();
        } catch (SQLException e){
            System.out.println("ошибка при создании драйвера");
            return;
        }
        try {
            DriverManager.registerDriver(driver);
        } catch (SQLException e){
            System.out.println("ошибка при регистрации драйвера");
            return;
        }
    }
    public Connection getConnection(String url, String userName, String password){
        try {
            connection = DriverManager.getConnection(url, userName, password);
            System.out.println("DaoMove");
            return connection;
        } catch (SQLException e) {
            System.out.println("ошибка подключения");
            System.out.println(e);
            return null;
        }
    }

    public static Statement getStatement() throws SQLException{
        DBProcessor db = new DBProcessor();
        Connection connection = db.getConnection( URL, USERNAME, PASSWORD);
        Statement statement = connection.createStatement();
        return statement;
    }
}
