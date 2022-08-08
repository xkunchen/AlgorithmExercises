package 剑指offer突击;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/6/8 18:03<br/>
 *
 * @author xkunchen<br />
 */


import java.util.ArrayDeque;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/number-of-recent-calls/
 *    	[剑指 Offer II 042]最近请求次数
 */
public class II042numberOfRecentCalls {

}
class RecentCounter {

    Queue<Integer> queue;

    public RecentCounter() {
        queue = new ArrayDeque<Integer>();
    }

    public int ping(int t) {
        queue.offer(t);
        while (queue.peek() < t - 3000) {
            queue.poll();
        }
        return queue.size();
    }


    public static void main(String[] args) {

    }
}
