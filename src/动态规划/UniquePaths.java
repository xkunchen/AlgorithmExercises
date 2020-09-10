package 动态规划;
//一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
// 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
// 问总共有多少条不同的路径？
// 例如，上图是一个7 x 3 的网格。有多少可能的路径？
// 示例 1:
// 输入: m = 3, n = 2
//输出: 3
//解释:
//从左上角开始，总共有 3 条路径可以到达右下角。
//1. 向右 -> 向右 -> 向下
//2. 向右 -> 向下 -> 向右
//3. 向下 -> 向右 -> 向右
// 示例 2:
// 输入: m = 7, n = 3
//输出: 28
// 提示：
// 1 <= m, n <= 100
// 题目数据保证答案小于等于 2 * 10 ^ 9
// Related Topics 数组 动态规划
// 👍 667 👎 0
/**
 * 不同路径
 *  https://leetcode-cn.com/problems/unique-paths/
  */

public class UniquePaths {
    //思路，从后往前推，用一个二维数组记录
    //f[n][n]=f[n][n-1]+f[n-1][n]
    //动态规划
    public int uniquePaths(int m, int n) {
        int[][] arr=new int[n][m];
        for (int i = 0; i < m; i++) arr[0][i]=1;
        for (int i = 0; i < n; i++) arr[i][0]=1;
        //从后面向前遍历，套入公式
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                arr[i][j]=arr[i][j-1]+arr[i-1][j];
            }
        }
        return arr[n-1][m-1];
    }
    //其他答案
    public int uniquePaths2(int m, int n) {
        //只跟第几行第几列有关，从m+n-2步中抽出m-1步
        long ans=1;
        for(int i=0;i<Math.min(m-1,n-1);i++){
            ans*=m+n-2-i;
            ans/=i+1;
        }
        return (int)ans;
    }
    public static void main(String[] args) {
        UniquePaths u=new UniquePaths();
        System.out.println( u.uniquePaths2(7,3));
    }
}
