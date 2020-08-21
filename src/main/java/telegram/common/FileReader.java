package telegram.common;

import java.io.IOException;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

public class FileReader {
    public static String readTxt(String fileName){
        String message ="";
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(System.getProperty("user.dir") + "\\src\\main\\content\\" + fileName));
            for (String s: lines) {
                message = message + s + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return message;
    }

    public static void createFile(String text, long chatId){


        try {
            Files.write(Paths.get(System.getProperty("user.dir") + "\\src\\main\\content\\orders\\" +
                    chatId +".txt"),text.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
