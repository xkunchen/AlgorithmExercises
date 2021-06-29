package 回溯;

/**
 * @Author: xkunchen
 * @Description:
 * @Data: 2021/6/29
 **/

import java.util.ArrayList;
import java.util.List;

/**
 *组合总和 Ⅳ
 * https://leetcode-cn.com/problems/combination-sum-iv/
 */
public class CombinationSumIV {
    //回溯
    public int combinationSum4(int[] nums, int target) {
        List<Integer> currentList=new ArrayList<>();
        int count= deal(nums,target,0,currentList,0);
        return count;
    }
    private int  deal(int[] nums, int target, int currentIndex, List<Integer> currentList, int currentSum) {
        if (currentSum>target||currentIndex>nums.length){
            return 0;
        }
        if (currentSum==target){
            return 1;
        }
        int count=0;
        for (int i = 0; i < nums.length; i++) {
            currentList.add(nums[i]);
            count=count+deal(nums,target,i,currentList,currentSum+nums[i]);
            currentList.remove(currentList.size()-1);
        }
        return count;
    }
    //动态规划
    public int combinationSum(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (num <= i) {
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }
    public static void main(String[] args) {
        CombinationSumIV c=new CombinationSumIV();
        int[] nums=new int[]{1,2,3};
        c.combinationSum4(nums,32);
    }
}
