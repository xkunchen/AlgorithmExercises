package 动态规划;

/**
 * @Author: xkunchen
 * @Description:
 * @Data: 2021/6/5
 **/

/**
 * https://leetcode-cn.com/problems/maximal-square/
 *         最大正方形
 *         动态规划
 *         第一遍
 */
public class MaximalSquare {
    public int maximalSquare(char[][] matrix) {
        int maxSide = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return maxSide;
        }
        int rows = matrix.length, columns = matrix[0].length;
        int[][] dp = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }
        int maxSquare = maxSide * maxSide;
        return maxSquare;
    }

    public static void main(String[] args) {
        MaximalSquare m=new MaximalSquare();
        char[][] arrs=new char[][]{{'1','0','1','0','0'}
                ,{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
        m.maximalSquare(arrs);
    }
}
