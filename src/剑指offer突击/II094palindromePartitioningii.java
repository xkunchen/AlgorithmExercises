package 剑指offer突击;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/5/24 11:40<br/>
 *
 * @author xkunchen<br />
 */

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/palindrome-partitioning-ii/
 *    	[剑指 Offer II 094]最少回文分割
 */
public class II094palindromePartitioningii {
    public int minCut(String s) {
        int n = s.length();
        boolean[][] g = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(g[i], true);
        }

        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                g[i][j] = s.charAt(i) == s.charAt(j) && g[i + 1][j - 1];
            }
        }

        int[] f = new int[n];
        Arrays.fill(f, Integer.MAX_VALUE);
        for (int i = 0; i < n; ++i) {
            if (g[0][i]) {
                f[i] = 0;
            } else {
                for (int j = 0; j < i; ++j) {
                    if (g[j + 1][i]) {
                        f[i] = Math.min(f[i], f[j] + 1);
                    }
                }
            }
        }

        return f[n - 1];
    }

    public static void main(String[] args) {
        II094palindromePartitioningii i=new II094palindromePartitioningii();
        i.minCut("aaabaa");
    }

}
