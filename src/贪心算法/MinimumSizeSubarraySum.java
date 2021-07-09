package 贪心算法;

/**
 * @Author: xkunchen
 * @Description:
 * @Data: 2021/7/8
 **/

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/minimum-size-subarray-sum/
 * 长度最小的子数组
 */
public class MinimumSizeSubarraySum {
    public int minSubArrayLen(int s, int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        int start = 0, end = 0;
        int sum = 0;
        while (end < n) {
            sum += nums[end];
            while (sum >= s) {
                ans = Math.min(ans, end - start + 1);
                sum -= nums[start];
                start++;
            }
            end++;
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    public static void main(String[] args) {
        int[] nums = {2,3,1,2,4,1,3};
        MinimumSizeSubarraySum m=new MinimumSizeSubarraySum();
        m.minSubArrayLen(7,nums);
    }
}
