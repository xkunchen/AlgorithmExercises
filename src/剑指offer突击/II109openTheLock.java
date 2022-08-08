package 剑指offer突击;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/6/30 15:15<br/>
 *
 * @author xkunchen<br />
 */

import java.util.*;

/**
 * https://leetcode-cn.com/problems/open-the-lock/
 *    	[剑指 Offer II 109]开密码锁
 */
public class II109openTheLock {
    public int openLock(String[] deadends, String target) {
        if ("0000".equals(target)) {
            return 0;
        }

        Set<String> dead = new HashSet<String>();
        for (String deadend : deadends) {
            dead.add(deadend);
        }
        if (dead.contains("0000")) {
            return -1;
        }

        int step = 0;
        Queue<String> queue = new LinkedList<String>();
        queue.offer("0000");
        Set<String> seen = new HashSet<String>();
        seen.add("0000");

        while (!queue.isEmpty()) {
            ++step;
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                String status = queue.poll();
                for (String nextStatus : get(status)) {
                    if (!seen.contains(nextStatus) && !dead.contains(nextStatus)) {
                        if (nextStatus.equals(target)) {
                            return step;
                        }
                        queue.offer(nextStatus);
                        seen.add(nextStatus);
                    }
                }
            }
        }

        return -1;
    }

    public char numPrev(char x) {
        return x == '0' ? '9' : (char) (x - 1);
    }

    public char numSucc(char x) {
        return x == '9' ? '0' : (char) (x + 1);
    }

    // 枚举 status 通过一次旋转得到的数字
    public List<String> get(String status) {
        List<String> ret = new ArrayList<String>();
        char[] array = status.toCharArray();
        for (int i = 0; i < 4; ++i) {
            char num = array[i];
            array[i] = numPrev(num);
            ret.add(new String(array));
            array[i] = numSucc(num);
            ret.add(new String(array));
            array[i] = num;
        }
        return ret;
    }

    public static void main(String[] args) {
        II109openTheLock i=new II109openTheLock();
        i.openLock(new String[]{"0201","0101","0102","1212","2002"},"0202");
    }

}
class Solution {
    public int openLock(String[] deadends, String target) {
        if ("0000".equals(target)) {
            return 0;
        }

        Set<String> dead = new HashSet<String>();
        for (String deadend : deadends) {
            dead.add(deadend);
        }
        if (dead.contains("0000")) {
            return -1;
        }

        PriorityQueue<AStar> pq = new PriorityQueue<AStar>((a, b) -> a.f - b.f);
        pq.offer(new AStar("0000", target, 0));
        Set<String> seen = new HashSet<String>();
        seen.add("0000");

        while (!pq.isEmpty()) {
            AStar node = pq.poll();
            for (String nextStatus : get(node.status)) {
                if (!seen.contains(nextStatus) && !dead.contains(nextStatus)) {
                    if (nextStatus.equals(target)) {
                        return node.g + 1;
                    }
                    pq.offer(new AStar(nextStatus, target, node.g + 1));
                    seen.add(nextStatus);
                }
            }
        }

        return -1;
    }

    public char numPrev(char x) {
        return x == '0' ? '9' : (char) (x - 1);
    }

    public char numSucc(char x) {
        return x == '9' ? '0' : (char) (x + 1);
    }

    // 枚举 status 通过一次旋转得到的数字
    public List<String> get(String status) {
        List<String> ret = new ArrayList<String>();
        char[] array = status.toCharArray();
        for (int i = 0; i < 4; ++i) {
            char num = array[i];
            array[i] = numPrev(num);
            ret.add(new String(array));
            array[i] = numSucc(num);
            ret.add(new String(array));
            array[i] = num;
        }
        return ret;
    }
}

class AStar {
    String status;
    int f, g, h;

    public AStar(String status, String target, int g) {
        this.status = status;
        this.g = g;
        this.h = getH(status, target);
        this.f = this.g + this.h;
    }

    // 计算启发函数
    public static int getH(String status, String target) {
        int ret = 0;
        for (int i = 0; i < 4; ++i) {
            int dist = Math.abs(status.charAt(i) - target.charAt(i));
            ret += Math.min(dist, 10 - dist);
        }
        return ret;
    }
}

