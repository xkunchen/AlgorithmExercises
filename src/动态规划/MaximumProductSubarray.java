package 动态规划;
//给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
// 示例 1:
// 输入: [2,3,-2,4]
//输出: 6
//解释: 子数组 [2,3] 有最大乘积 6。
// 示例 2:
// 输入: [-2,0,-1]
//输出: 0
//解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
// Related Topics 数组 动态规划
// 👍 770 👎 0
/**
 * 乘积最大子数组
 *         https://leetcode-cn.com/problems/maximum-product-subarray/description/
 *         动态规划
 */
public class MaximumProductSubarray {
    public int maxProduct(int[] nums) {
        int maxValue=Integer.MIN_VALUE;
        int arr[]=new int[nums.length];
        for (int i=0;i<nums.length;i++){
            maxValue=Math.max(maxValue,nums[i]);
            arr[i]=nums[i];
            for (int j = i+1; j < nums.length; j++) {
                arr[j]=arr[j-1]*nums[j];
                maxValue=Math.max(maxValue,arr[j]);
            }
        }
        return maxValue;
    }

    //优化空间的动态规划，与我的动态规划对比
    public int maxProduct2(int[] nums) {
        int maxF = nums[0], minF = nums[0], ans = nums[0];
        int length = nums.length;
        for (int i = 1; i < length; ++i) {
            int mx = maxF, mn = minF;
            maxF = Math.max(mx * nums[i], Math.max(nums[i], mn * nums[i]));
            minF = Math.min(mn * nums[i], Math.min(nums[i], mx * nums[i]));
            ans = Math.max(maxF, ans);
        }
        return ans;
    }
    public static void main(String[] args) {
        MaximumProductSubarray m=new MaximumProductSubarray();
        int arr[]=new int[]{2,3,-2,4};
        m.maxProduct2(arr);
    }
}
