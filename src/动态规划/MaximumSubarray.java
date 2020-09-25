package 动态规划;
//给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
// 示例:
// 输入: [-2,1,-3,4,-1,2,1,-5,4]
//输出: 6
//解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
// 进阶:
// 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
// Related Topics 数组 分治算法 动态规划
// 👍 2464 👎 0
/**
 * 最大子序和
 *         https://leetcode-cn.com/problems/maximum-subarray/
 *         动态规划，分治
 */
/**
 * 思想：其实很简单，可以看代码理解
 */

public class MaximumSubarray {
    //动态规划，不用数组，因为只需前一次的结果，所以用一个数
    public int maxSubArray(int[] nums) {
        int pre = 0, maxAns = nums[0];
        for (int x : nums) {
            pre = Math.max(pre + x, x);
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;
    }

    public static void main(String[] args) {
        MaximumSubarray m=new MaximumSubarray();
        int arr[]=new int[]{-2,1,-3,4,-1,2,1,-5,4};
        m.maxSubArray2(arr);
    }
    //分治思想
    public class Status {
        public int lSum, rSum, mSum, iSum;

        public Status(int lSum, int rSum, int mSum, int iSum) {
            this.lSum = lSum;
            this.rSum = rSum;
            this.mSum = mSum;
            this.iSum = iSum;
        }
    }

    public int maxSubArray2(int[] nums) {
        return getInfo(nums, 0, nums.length - 1).mSum;
    }

    public Status getInfo(int[] nums, int l, int r) {
        if (l == r) {
            return new Status(nums[l], nums[l], nums[l], nums[l]);
        }
        int m = (l + r) >> 1;
        Status lSub = getInfo(nums, l, m);
        Status rSub = getInfo(nums, m + 1, r);
        return pushUp(lSub, rSub);
    }

    public Status pushUp(Status l, Status r) {
        int iSum = l.iSum + r.iSum;
        int lSum = Math.max(l.lSum, l.iSum + r.lSum);
        int rSum = Math.max(r.rSum, r.iSum + l.rSum);
        int mSum = Math.max(Math.max(l.mSum, r.mSum), l.rSum + r.lSum);
        return new Status(lSum, rSum, mSum, iSum);
    }
}
