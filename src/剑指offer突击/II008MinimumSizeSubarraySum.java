package 剑指offer突击;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/4/26 10:07<br/>
 *
 * @author xkunchen<br />
 */


import java.util.Arrays;

/**
 *    [剑指 Offer II 008]和大于等于 target 的最短子数组
 *    https://leetcode-cn.com/problems/minimum-size-subarray-sum/
 */
public class II008MinimumSizeSubarraySum {
    public int minSubArrayLen(int target, int[] nums) {
        if (nums.length==0){
            return 0;
        }
        if (nums.length==1&&nums[0]>=target){
            return 1;
        }
        int left=0;
        int right=0;
        int sum=nums[left];
        int minLen=Integer.MAX_VALUE;
        while (right<nums.length){
            if (sum>=target&&left<right){
                minLen=Math.min(minLen,right-left+1);
                sum-=nums[left];
                left++;
            }else {
                if (sum>=target&&left==right){
                    minLen=Math.min(minLen,right-left+1);
                }
                right++;
                if (right==nums.length){
                    break;
                }
                sum+=nums[right];

            }
        }
        return minLen!=Integer.MAX_VALUE?minLen:0;
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

    public int minSubArrayLen3(int s, int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                if (sum >= s) {
                    ans = Math.min(ans, j - i + 1);
                    break;
                }
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
    public int minSubArrayLen4(int s, int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        int[] sums = new int[n + 1];
        // 为了方便计算，令 size = n + 1
        // sums[0] = 0 意味着前 0 个元素的前缀和为 0
        // sums[1] = A[0] 前 1 个元素的前缀和为 A[0]
        // 以此类推
        for (int i = 1; i <= n; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }
        for (int i = 1; i <= n; i++) {
            int target = s + sums[i - 1];
            int bound = Arrays.binarySearch(sums, target);
            if (bound < 0) {
                bound = -bound - 1;
            }
            if (bound <= n) {
                ans = Math.min(ans, bound - (i - 1));
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    public static void main(String[] args) {
        II008MinimumSizeSubarraySum i=new II008MinimumSizeSubarraySum();
        i.minSubArrayLen2(4,new int[]{1,4,4});
    }
}
