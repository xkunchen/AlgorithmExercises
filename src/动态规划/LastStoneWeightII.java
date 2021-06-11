package 动态规划;

/**
 * @Author: xkunchen
 * @Description:
 * @Data: 2021/6/8
 **/

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/last-stone-weight-ii/
 *         最后一块石头的重量 II
 *         动态规划
 */

public class LastStoneWeightII {
    /**
     * 思想：可以转换为求数组中最靠近平均数的分组
     * 第一遍看答案思考，学到一种思想，应用于背包问题，数量不限，大小不限，找出组成最接近于固定数的
     * 集合中哪几个数
     */
    public int lastStoneWeightII(int[] stones) {
        Arrays.sort(stones);
        int sum=0;
        for (int i = 0; i < stones.length; i++) {
            sum+=stones[i];
        }
        for (int i = 0; i < stones.length; i++) {
//            stones[i];
        }
        return 0;
    }
    public int lastStoneWeightII2(int[] stones) {
        int sum = 0;
        for (int weight : stones) {
            sum += weight;
        }
        int n = stones.length, m = sum / 2;
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j <= m; ++j) {
                if (j < stones[i]) {
                    dp[i + 1][j] = dp[i][j];
                } else {
                    dp[i + 1][j] = dp[i][j] || dp[i][j - stones[i]];
                }
            }
        }
        for (int j = m;; --j) {
            if (dp[n][j]) {
                return sum - 2 * j;
            }
        }
    }

    public static void main(String[] args) {
        LastStoneWeightII l=new LastStoneWeightII();
        l.lastStoneWeightII2(new int[]{31,26,33,21,40});
    }
}
