package 高级动态规划;

/**
 * @Author: xkunchen
 * @Description:
 * @Data: 2021/5/6
 **/
//假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
// 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
// 注意：给定 n 是一个正整数。
// 示例 1：
// 输入： 2
//输出： 2
//解释： 有两种方法可以爬到楼顶。
//1.  1 阶 + 1 阶
//2.  2 阶
// 示例 2：
// 输入： 3
//输出： 3
//解释： 有三种方法可以爬到楼顶。
//1.  1 阶 + 1 阶 + 1 阶
//2.  1 阶 + 2 阶
//3.  2 阶 + 1 阶
// Related Topics 动态规划
// 👍 1198 👎 0
/**
 * https://leetcode-cn.com/problems/climbing-stairs/
 * 爬楼梯，第二次做了
 */

/**
 * 思路：递推公式-f(n)=f(n-1)+f(n-2)
 */
public class ClimbingStairs {
    public int climbStairs(int n) {
        //创建一个数组存储结果
        if (n==1){
            return 1;
        }
        int[] result=new int[n];
        result[0]=1;
        result[1]=2;
        for (int i = 2; i < n; i++) {
            result[i]=result[i-1]+result[i-2];
        }
        return result[n-1];
    }

    public static void main(String[] args) {
        ClimbingStairs c=new ClimbingStairs();
        c.climbStairs(2);
    }
}
