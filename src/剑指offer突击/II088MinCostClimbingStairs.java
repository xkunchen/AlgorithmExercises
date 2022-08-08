package 剑指offer突击;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/4/29 16:09<br/>
 *
 * @author xkunchen<br />
 */

/**
 * ✔[剑指 Offer II 088]爬楼梯的最少成本
 * https://leetcode-cn.com/problems/min-cost-climbing-stairs/
 */
public class II088MinCostClimbingStairs {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[n];
    }
    public int minCostClimbingStairs2(int[] cost) {
        int n = cost.length;
        int prev = 0, curr = 0;
        for (int i = 2; i <= n; i++) {
            int next = Math.min(curr + cost[i - 1], prev + cost[i - 2]);
            prev = curr;
            curr = next;
        }
        return curr;
    }

    public static void main(String[] args) {
        II088MinCostClimbingStairs ii=new II088MinCostClimbingStairs();
        ii.minCostClimbingStairs(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1});
    }
}
