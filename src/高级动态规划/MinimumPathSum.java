package 高级动态规划;

/**
 * @Author: xkunchen
 * @Description:
 * @Data: 2021/5/14
 **/
//给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
// 说明：每次只能向下或者向右移动一步。
// 示例 1：
//输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
//输出：7
//解释：因为路径 1→3→1→1→1 的总和最小。
// 示例 2：
//输入：grid = [[1,2,3],[4,5,6]]
//输出：12
// 提示：
// m == grid.length
// n == grid[i].length
// 1 <= m, n <= 200
// 0 <= grid[i][j] <= 100
// Related Topics 数组 动态规划
// 👍 875 👎 0

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/minimum-path-sum/
 * 最小路径和
 */
public class MinimumPathSum {
    //自上往下
    public int minPathSum(int[][] grid) {
        //新建一个数组存储grid每个值的最小值
        //公式 arr[i][j]=min(arr[i-1][j],arr[i][j-1])+grid[i][j]
        if (grid.length<2){
            int sum=0;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    sum+=grid[i][j];
                }
            }
            return sum;
        }
        int[][] arr= new int[grid.length][grid[0].length];
        arr[0][0]=grid[0][0];
        for (int i = 1; i < grid.length; i++) {
            arr[i][0]=grid[i][0]+arr[i-1][0];
        }
        for (int j = 1; j < grid[0].length; j++) {
            arr[0][j]=grid[0][j]+arr[0][j-1];
        }
        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                arr[i][j]=Math.min(arr[i-1][j],arr[i][j-1])+grid[i][j];
            }
        }
        return arr[arr.length-1][arr[0].length-1];
    }
    //简化写法，用一个数组
    public int minPathSum2(int[][] grid) {
        int [] dp = new int[grid[0].length];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0] = 0;

        for(int i = 0; i < grid.length; i++){
            dp[0]=dp[0]+grid[i][0];
            for(int j = 1; j < grid[i].length; j++){
                dp[j]=Math.min(dp[j-1],dp[j])+grid[i][j];
            }
        }
        return dp[grid[0].length-1];
    }
    public static void main(String[] args) {
        int[][] arr= new int[][]{{1,3,1},{1,5,1},{4,2,1}};
        MinimumPathSum m=new MinimumPathSum();
        m.minPathSum(arr);
    }
}
