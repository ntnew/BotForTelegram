package telegram.common;

import java.util.List;

public class FindPrevMsg {

    public static boolean findPrevMsg(List<String> list, String line){
        boolean s = false;
        for (int i=0; i<list.size(); i++){
            if(list.get(i).equals(line))
                s=true;
        }
        return s;
    }
    public static void deletePrevMsg(List<String> list, String line){
        for (int i=0; i<list.size(); i++){
            if(list.get(i).equals(line))
                list.remove(i);
        }
    }
}
