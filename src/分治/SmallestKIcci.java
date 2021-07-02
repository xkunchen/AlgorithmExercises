package 分治;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Author: xkunchen
 * @Description:
 * @Data: 2021/7/1
 **/

/**
 * https://leetcode-cn.com/problems/smallest-k-lcci/
 * 面试题 17.14. 最小K个数
 */
//总结：最大的收获是PriorityQueue 有序队列的用法
public class SmallestKIcci {
    public int[] smallestK(int[] arr, int k) {
        int[] vec = new int[k];
        if (k == 0) { // 排除 0 的情况
            return vec;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(new Comparator<Integer>() {
            public int compare(Integer num1, Integer num2) {
                return num2 - num1;
            }
        });
        for (int i = 0; i < k; ++i) {
            queue.offer(arr[i]);
        }
        for (int i = k; i < arr.length; ++i) {
            if (queue.peek() > arr[i]) {
                queue.poll();
                queue.offer(arr[i]);
            }
        }
        for (int i = 0; i < k; ++i) {
            vec[i] = queue.poll();
        }
        return vec;
    }
}
