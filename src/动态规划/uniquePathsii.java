package 动态规划;
//不同路径 II
//  https://leetcode-cn.com/problems/unique-paths-ii/
//一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
// 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
// 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
// 网格中的障碍物和空位置分别用 1 和 0 来表示。
// 说明：m 和 n 的值均不超过 100。
// 示例 1:
// 输入:
//[
//  [0,0,0],
//  [0,1,0],
//  [0,0,0]
//]
//输出: 2
//解释:
//3x3 网格的正中间有一个障碍物。
//从左上角到右下角一共有 2 条不同的路径：
//1. 向右 -> 向右 -> 向下 -> 向下
//2. 向下 -> 向下 -> 向右 -> 向右
// Related Topics 数组 动态规划
// 👍 404 👎 0
public class uniquePathsii {
    //9月9日第一次解答
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int n=obstacleGrid.length;
        int m=obstacleGrid[0].length;
        int[][] arr=new int[n][m];
        for (int i = 0; i < m; i++){
            if (obstacleGrid[0][i]!=1){
                arr[0][i]=1;
            }else{
                break;
            }
        }
        for (int i = 0; i < n; i++){
            if (obstacleGrid[i][0]!=1){
                arr[i][0]=1;
            }else{
                break;
            }
        }
        //从后面向前遍历，套入公式
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++)  if (obstacleGrid[i][j]!=1)arr[i][j]=arr[i][j-1]+arr[i-1][j];
        }
        return arr[n-1][m-1];
    }
    //优化动态规划
    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        int n = obstacleGrid.length, m = obstacleGrid[0].length;
        int[] f = new int[m];

        f[0] = obstacleGrid[0][0] == 0 ? 1 : 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (obstacleGrid[i][j] == 1) {
                    f[j] = 0;
                    continue;
                }
                if (j - 1 >= 0 && obstacleGrid[i][j - 1] == 0) {
                    f[j] += f[j - 1];
                }
            }
        }

        return f[m - 1];
    }
    public static void main(String[] args) {
        int[][] obstacleGrid={{0,0,0},{0,1,0},{0,0,0}};
        uniquePathsii u=new uniquePathsii();
        System.out.println(u.uniquePathsWithObstacles(obstacleGrid));
    }
}
