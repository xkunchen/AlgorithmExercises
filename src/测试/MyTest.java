package 测试;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2021/11/25 18:27<br/>
 *
 * @author xkunchen<br />
 */
public class MyTest {

    /**
     * 获取本周具体是周几的日期
     * @return
     */
    public static String  getTheDateByWeekName(String weekName) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar firstDayOfWeek = Calendar.getInstance(Locale.getDefault());
        firstDayOfWeek.setFirstDayOfWeek(Calendar.MONDAY);
        int day = firstDayOfWeek.get(Calendar.DAY_OF_WEEK);
        Integer weekNameIndex = getWeekNameIndex(weekName);
        if (weekNameIndex!=null){
            firstDayOfWeek.add(Calendar.DATE, 1-day+weekNameIndex);
            return format.format(firstDayOfWeek.getTime());
        }
        return null;
    }

    public static Integer getWeekNameIndex(String weekName) {
        switch (weekName){
            case "周一" :
                return 1;
            case "周二" :
                return 2;
            case "周三" :
                return 3;
            case "周四" :
                return 4;
            case "周五" :
                return 5;
            case "周六" :
                return 6;
            case "周日" :
                return 0;
        }
        return null;
    }
    public static Long getSecondsNextEarlyMorning() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return (cal.getTimeInMillis() - System.currentTimeMillis()) / 1000 / 60/60;
    }
    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        // 本周星期天的日期
        /*System.out.println(getTheDateByWeekName("周六"));*/
        System.out.println(cal.get(Calendar.YEAR));
    }

}
