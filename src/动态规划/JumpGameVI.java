package 动态规划;

/**
 * @Author: xkunchen
 * @Description:
 * @Data: 2021/7/8
 **/

import java.util.*;

/**
 * https://leetcode-cn.com/problems/jump-game-vi/
 * 跳跃游戏 VI
 */

/**
 * 有序队列的应用
 * 题的思想是动态规划，用有序队列存储结果，需要有两个信息，一个是数组下标，用来判断最大值的索引是否符合当前索引减去跳跃的步数
 * 如果不是那么就查找第二大的，以此类推，找出最终结果。
 */
public class JumpGameVI {
    //
    public int maxResult(int[] nums, int k) {
        //大顶堆
        Queue<int[]> queue = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]);
        //初始化大顶堆的堆顶元素
        queue.add(new int[]{nums[0],0});
        int ans=nums[0];
        for(int i=1;i<nums.length;i++){
            //判断堆顶元素是否在对应的范围内
            while(i-queue.peek()[1]>k){
                queue.poll();
            }
            ans=queue.peek()[0]+nums[i];
            queue.add(new int[]{ans,i});
        }
        //返回结果
        return ans;
    }
    //重要一步在while，队列里面的索引，永远大于当前动态数组是最后一个值
    public int maxResult2(int[] nums, int k) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        // 构造滑动窗口的索引所对应的队列，队首至队尾的索引依次增大，但对应dp数组中的值依次降低
        Deque<Integer> windowIndices = new LinkedList<>();
        for (int i = 1; i < nums.length; i++) {
            // 如果新的索引i所对应的元素dp[i - 1]大于队尾rear所对应的数组元素dp[rear]，就循环弹出队尾，直到新的元素i - 1能够成为队尾
            // 因为dp[rear] < dp[i - 1]且rear < i - 1，只要窗口继续向右移，dp[rear]就一定会被dp[i - 1]压在下面，不会成为窗口最大元素
            while (!windowIndices.isEmpty() && dp[i - 1] >= dp[windowIndices.peekLast()]) {
                windowIndices.pollLast();
            }
            windowIndices.offerLast(i - 1);
            if (windowIndices.peekFirst() < i - k) {
                windowIndices.pollFirst();
            }
            dp[i] = dp[windowIndices.peekFirst()] + nums[i];
        }

        return dp[nums.length - 1];
    }

    public static void main(String[] args) {
        JumpGameVI j=new JumpGameVI();
        int nums[]={10,-5,-2,4,0,3};
        j.maxResult(nums,3);
    }
}
