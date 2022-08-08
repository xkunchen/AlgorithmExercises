package 剑指offer突击;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/5/12 10:03<br/>
 *
 * @author xkunchen<br />
 */

/**
 * https://leetcode-cn.com/problems/target-sum/
 *    	[剑指 Offer II 102]加减的目标值
 */
//输入：nums = [1,1,1,1,1], target = 3
//输出：5
//解释：一共有 5 种方法让最终目标和为 3 。
//-1 + 1 + 1 + 1 + 1 = 3
//+1 - 1 + 1 + 1 + 1 = 3
//+1 + 1 - 1 + 1 + 1 = 3
//+1 + 1 + 1 - 1 + 1 = 3
//+1 + 1 + 1 + 1 - 1 = 3
// 示例 2：
//输入：nums = [1], target = 1
//输出：1
public class II102targetSum {
    public int findTargetSumWays(int[] nums, int target) {
        int sum=0;
        return sum+=dfs(nums,target,0,0);
    }

    private int dfs(int[] nums, int target ,int creentSum,int index) {
        if (creentSum==target&&index==nums.length){
             return 1;
        }
        if (index>= nums.length) {
            return 0;
        }
        int sum=0;
        sum+=dfs(nums,target,creentSum+nums[index],index+1)+dfs(nums,target,creentSum-nums[index],index+1);
        return sum;
    }

    int count = 0;

    public int findTargetSumWays2(int[] nums, int target) {
        backtrack(nums, target, 0, 0);
        return count;
    }

    public void backtrack(int[] nums, int target, int index, int sum) {
        if (index == nums.length) {
            if (sum == target) {
                count++;
            }
        } else {
            backtrack(nums, target, index + 1, sum + nums[index]);
            backtrack(nums, target, index + 1, sum - nums[index]);
        }
    }

    //消消乐思想，sum-target 多余的值消掉
    public int findTargetSumWays3(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int diff = sum - target;
        if (diff < 0 || diff % 2 != 0) {
            return 0;
        }
        int n = nums.length, neg = diff / 2;
        int[][] dp = new int[n + 1][neg + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            int num = nums[i - 1];
            for (int j = 0; j <= neg; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= num) {
                    dp[i][j] += dp[i - 1][j - num];
                }
            }
        }
        return dp[n][neg];
    }

    public static void main(String[] args) {
        II102targetSum i=new II102targetSum();
        System.out.println(i.findTargetSumWays3(new int[]{1,2,3,4,1,2},7));
    }
}
