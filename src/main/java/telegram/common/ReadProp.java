package telegram.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class ReadProp {

    static String fileName = System.getProperty("user.dir") + "\\src\\main\\content\\bot.properties";


    public static String getProp(String name) {
        FileInputStream fileInputStream;
        Properties prop = new Properties();
        try {
            fileInputStream = new FileInputStream(fileName);
            prop.load(fileInputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (name.equals("email")) return prop.getProperty("email");
        else if (name.equals("botUsername")) return prop.getProperty("botUsername");
        else if (name.equals("botToken")) return prop.getProperty("botToken");
        else if(name.equals("db.username")) return prop.getProperty("db.username");
        else if(name.equals("db.password")) return prop.getProperty("db.password");

        return null;
    }
}
