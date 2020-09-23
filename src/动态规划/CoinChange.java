package 动态规划;
//给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回
// -1。
// 示例 1:
// 输入: coins = [1, 2, 5], amount = 11
//输出: 3
//解释: 11 = 5 + 5 + 1
// 示例 2:
// 输入: coins = [2], amount = 3
//输出: -1
// 说明:
//你可以认为每种硬币的数量是无限的。
// Related Topics 动态规划
// 👍 807 👎 0

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 零钱兑换
 *         动态规划，贪心
 *         https://leetcode-cn.com/problems/coin-change/description/
 */
public class CoinChange {
    //暴力解法，算出所有结果，并进行选择最少的，想象一棵树，加到11
    //贪心算法
    public int coinChange(int[] coins, int amount) {
        int count=0;
        int countData=0;
        Arrays.sort(coins);
        if (amount==0){
            return 0;
        }
        while(countData<amount){
            for (int i = coins.length-1; i >=0 ; i--) {
                if (countData+coins[i]<=amount&&countData+coins[i]>0){
                    countData+=coins[i];
                    count++;
                    if (countData==amount){
                        return count;
                    }
                    break;
                }
                if (i==0){
                    countData+=coins[i];
                    count++;
                    if (countData==amount){
                        return count;
                    }
                }
            }
        }
        return -1;
    }

    //动态规划自上而下 ，看答案，存储结果是每次到数字有几次
    public int coinChange2(int[] coins, int amount) {
        if (amount < 1) return 0;
        return coinChange(coins, amount, new int[amount]);
    }

    private int coinChange(int[] coins, int rem, int[] count) {
        if (rem < 0) return -1;
        if (rem == 0) return 0;
        if (count[rem - 1] != 0) return count[rem - 1];
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = coinChange(coins, rem - coin, count);
            if (res >= 0 && res < min)
                min = 1 + res;
        }
        count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[rem - 1];
    }
    //动态规划自下而上 ，看答案，存储结果是每次到数字有几次
    public int coinChange3(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
    public static void main(String[] args) {
        CoinChange c=new CoinChange();
        c.coinChange2( new int[]{1, 2, 5},11);
    }
}
