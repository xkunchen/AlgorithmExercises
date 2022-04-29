package 测试;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2021/11/26 10:36<br/>
 *
 * @author xkunchen<br />
 */
public class TestDate {
    //判断选择的日期是否是今天
    public static boolean isToday(long time) {
        return isThisTime(time, "yyyy-MM-dd");
    }

    //判断选择的日期是否是本周
    public static boolean isThisWeek(Date time) {
        Calendar calendar = Calendar.getInstance();
        int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        calendar.setTime(time);
        int paramWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        if (paramWeek == currentWeek) {
            return true;
        }
        return false;
    }


    //判断选择的日期是否是本月
    public static boolean isThisMonth(long time) {
        return isThisTime(time, "yyyy-MM");
    }

    public static boolean isThisTime(long time, String pattern) {
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String param = sdf.format(date);//参数时间
        String now = sdf.format(new Date());//当前时间
        if (param.equals(now)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat SIMPLEDATEFORMAT =new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < 10; i++) {
            String format = SIMPLEDATEFORMAT.format(new Date(randomDate(System.currentTimeMillis())));
            System.out.println(format);
        }
    }

    private static Long randomDate(Long nowDate){
        Random rand=new Random();
        return nowDate + (rand.nextInt(5)+10) * 60 * 1000;
    }
}
