package 测试;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2021/12/3 13:56<br/>
 *
 * @author xkunchen<br />
 */
public class TestLock {

    private static final ReentrantReadWriteLock READ_WRITE_LOCK = new ReentrantReadWriteLock();
    private static final ReentrantReadWriteLock.WriteLock WRITE_LOCK = READ_WRITE_LOCK.writeLock();
    private static final ReentrantReadWriteLock.ReadLock READ_LOCK = READ_WRITE_LOCK.readLock();
    private static final AtomicReference<Integer> reference = new AtomicReference<>();
    public static void main(String[] args) {
        Day monday = Day.MONDAY;
        System.out.println(monday.toString());

    }
}
enum Day {
    MONDAY, TUESDAY, WEDNESDAY,
    THURSDAY, FRIDAY, SATURDAY, SUNDAY
}
