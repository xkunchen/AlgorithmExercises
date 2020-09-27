package 动态规划;
//你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都围成一圈，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋
//装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
// 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
// 示例 1:
// 输入: [2,3,2]
//输出: 3
//解释: 你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
// 示例 2:
// 输入: [1,2,3,1]
//输出: 4
//解释: 你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
//     偷窃到的最高金额 = 1 + 3 = 4 。
// Related Topics 动态规划
// 👍 383 👎 0

import java.util.Arrays;

/**
 * 打家劫舍 II
 *         https://leetcode-cn.com/problems/house-robber-ii/description/
 *         动态规划
 */
public class HouseRobberII {
    //分为两种情况有，第一个选择，和第一个不选择
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        if (length == 2) {
            return Math.max(nums[0],nums[1]);
        }
        //选择开头
        int[] dp = new int[length-1];
        //不选择开头
        int[] dp2 = new int[length-1];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        dp2[0] = nums[1];
        dp2[1] = Math.max(nums[1], nums[2]);
        for (int i = 2; i < length; i++) {
            if (i!=nums.length-1){
                dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
            }
            if (i!=2){
                dp2[i-1] = Math.max(dp2[i - 3] + nums[i], dp2[i - 2]);
            }
        }
        return Math.max(dp[length - 2],dp2[length-2]);
    }
    //一样的结果
    public int rob2(int[] nums) {
        if(nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];
        return Math.max(myRob(Arrays.copyOfRange(nums, 0, nums.length - 1)),
                myRob(Arrays.copyOfRange(nums, 1, nums.length)));
    }
    private int myRob(int[] nums) {
        int pre = 0, cur = 0, tmp;
        for(int num : nums) {
            tmp = cur;
            cur = Math.max(pre + num, cur);
            pre = tmp;
        }
        return cur;
    }
    public static void main(String[] args) {
        HouseRobberII h=new HouseRobberII();
        int arr[]={2,100,9,3,100};
        System.out.println(h.rob(arr));
    }
}
