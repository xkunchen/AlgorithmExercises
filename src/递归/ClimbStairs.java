package 递归;
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
 * 8月24日第一次练习，接下来练习优化的动态规划和公式解法
 */
public class ClimbStairs {
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
     /*
    第三个版本：动态规划，用数组存储起来
     */
    public int climbStairs3(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
    /*
    第四个版本：动态规划，优化
     */
    public int climbStairs4(int n) {
        int p = 0, q = 0, r = 1;
        for (int i = 1; i <= n; ++i) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }
    /*
    其他解法1：矩阵快速幂，需要理解
     */
    public int OtherStairsOther(int n) {
        int[][] q = {{1, 1}, {1, 0}};
        int[][] res = pow(q, n);
        return res[0][0];
    }
    public int[][] pow(int[][] a, int n) {
        int[][] ret = {{1, 0}, {0, 1}};
        while (n > 0) {
            if ((n & 1) == 1) {
                ret = multiply(ret, a);
            }
            n >>= 1;
            a = multiply(a, a);
        }
        return ret;
    }
    public int[][] multiply(int[][] a, int[][] b) {
        int[][] c = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                c[i][j] = a[i][0] * b[0][j] + a[i][1] * b[1][j];
            }
        }
        return c;
    }
    /*
    其他解法2，根据斐波那契数列，天才，根据公式执行的时间复杂度为O(1)
     */
    public int climbStairsOther(int n) {
        double sqrt_5 = Math.sqrt(5);
        double fib_n = Math.pow((1 + sqrt_5) / 2, n + 1) - Math.pow((1 - sqrt_5) / 2,n + 1);
        return (int)(fib_n / sqrt_5);
    }

    public static void main(String[] args) {
        ClimbStairs s=new ClimbStairs();
        System.out.println(s.climbStairs2(45));
        System.out.println(count);
    }
}
