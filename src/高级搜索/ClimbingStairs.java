package 高级搜索;

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
// 👍 1546 👎 0
/**
 * 1   爬楼梯
 *               https://leetcode-cn.com/problems/climbing-stairs/
 *               再次做一遍，高级算法前提，递归进行剪枝
 */

/**
 * 总结，方向不对，要清楚，有个不好的习惯，做题不想，直接开始，所以一开始方向不对就直接over
 */
public class ClimbingStairs {
    /*
    第一个版本：暴力递归，
    根据爬上 n−1 阶楼梯的方法数量。因为再爬1阶就能到第n阶
    加上爬上 n−2 阶楼梯的方法数量，因为再爬2阶就能到第n阶
    但提交超时
     */
    public int climbStairs(int n) {
        //进行递归返回结果
        //结束条件
        if (n==1){
            return 1;
        }else if (n==2){
            return 2;
        }
        //处理逻辑
        return  climbStairs(n-1)+climbStairs(n-2);
    }
    /*
    第二个版本：改善递归，把结果存储起来，减少递归次数.有点动态规划的味道
     */
    public int climbStairs2(int n) {
        int[] arr=new int[n+1];
        return climb(n,arr);
    }
    private static  int count=0;//看遍历多少次
    private int climb(int n, int[] arr) {
        //进行递归返回结果
        //结束条件
        if (n==1){
            return 1;
        }else if (n==2){
            return 2;
        }
        if (arr[n]!=0){
            return arr[n];
        }
        count++;
        //保存结果
        arr[n]=climb(n-1,arr)+climb(n-2,arr);
        //处理逻辑
        return  arr[n];
    }

}
