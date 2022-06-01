package 剑指offer;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/5/30 18:29<br/>
 *
 * @author xkunchen<br />
 */

import java.util.List;

/**
 * https://leetcode-cn.com/problems/triangle/
 *    	[剑指 Offer II 100]三角形中最小路径之和
 */
public class II100triangle {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }
}
