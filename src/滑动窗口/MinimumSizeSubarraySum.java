package 滑动窗口;

/**
 * @Author: xkunchen
 * @Description:
 * @Data: 2021/7/17
 **/

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/minimum-size-subarray-sum/
 * 长度最小的子数组
 */
public class MinimumSizeSubarraySum {
    //看清题意，其和 ≥ target
    public int minSubArrayLen(int target, int[] nums) {
        // 哈希集合，记录每个字符是否出现过
        int n = nums.length;
        //和
        int sum=0;
        // 左指针
        int left = 0;
        //遍历中个数
        int count=0;
        //返回个数
        int ans = Integer.MAX_VALUE;
        //遍历数组
        for (int i = 0; i < n; ++i) {
            sum+=nums[i];
            count++;
            while (sum>=target){
                sum-=nums[left++];
                // 对比个数最小的
                ans = Math.min(ans, count);
                count--;
            }
        }
        return ans==Integer.MAX_VALUE?0:ans;
    }
    public int minSubArrayLen2(int s, int[] nums) {
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
        int[] nums = {1,2,3,4,5};
        MinimumSizeSubarraySum m=new MinimumSizeSubarraySum();
        m.minSubArrayLen(11,nums);
    }
}
