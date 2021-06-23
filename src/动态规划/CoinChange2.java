package 动态规划;

/**
 * @Author: xkunchen
 * @Description:
 * @Data: 2021/6/10
 **/

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/coin-change-2/
 *         零钱兑换 II
 *         动态规划
 */

/**
 * 动态方程：
 * 自己理解的动态方程：f(5)=f(5-coins[0])+f(5-coins[1])+f(5-coins[2])+...
 * 正解的动态方程：f(1)~f(n)+=f(n-coins[0]) 遍历coins数组
 * 和自己理解的差别是，每次都只能从coins[i]去加，而coins[i] 之前的不会再参与，
 * 所以这类题的总结就是自己理解的动态方程存在集合的重复性，如 f（3）=f(3-2)+f(3-1)=3
 * f(1) 只有1；f(2)有 1+1，2两种情况
 * f(3) 有 1+1+1，1+2两种情况
 * 所以两个结果不同，造成的原因是1+2与2+1这种情况重了，这是个集合问题
 * 所以给我一个解决这类题思路就是每次固定一个集合的元素去进行计算
 */
public class CoinChange2 {
    //自己写的
    public int mychange(int amount, int[] coins) {
        int arr[]=new int[amount+1];
        arr[0]=1;
        for (int coin:coins){
            for (int i = 1; i <= amount; i++) {
                if (coin>i){
                    continue;
                }
                arr[i]+=arr[i-coin];
            }
        }
        return arr[amount];
    }
    //正解
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];//包含本身，可以无限，所以动态方程存储的还是本维数组
            }
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        CoinChange2 c=new CoinChange2();
        c.mychange(5,new int[]{1, 2, 5});
    }
}
