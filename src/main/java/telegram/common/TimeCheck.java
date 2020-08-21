package telegram.common;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

public class TimeCheck {
    public static boolean checkTime(){
        boolean flag = false;
        Calendar c = Calendar.getInstance();
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        LocalTime time = LocalTime.now();
        if(isBetween(time, LocalTime.of(10, 0), LocalTime.of(23, 0))) return true;
        if(dayOfWeek==4 || dayOfWeek==5){
            if(isBetween(time, LocalTime.of(10, 0), LocalTime.of(23, 59))) return true;
            if(isBetween(time, LocalTime.of(0, 0), LocalTime.of(1, 0))) return true;
        }
        return flag;
    }
    private static boolean isBetween(LocalTime candidate, LocalTime start, LocalTime end) {
        return !candidate.isBefore(start) && !candidate.isAfter(end);
    }
}
