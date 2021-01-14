package 分治;
//实现 pow(x, n) ，即计算 x 的 n 次幂函数。
// 示例 1:
// 输入: 2.00000, 10
//输出: 1024.00000
// 示例 2:
// 输入: 2.10000, 3
//输出: 9.26100
// 示例 3:
// 输入: 2.00000, -2
//输出: 0.25000
//解释: 2-2 = 1/22 = 1/4 = 0.25
// 说明:
// -100.0 < x < 100.0
// n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。
// Related Topics 数学 二分查找
// 👍 567 👎 0

/**
 * Pow(x, n)   x的n次幂
 *                https://leetcode-cn.com/problems/powx-n/
 *                分治
 *                第一遍
 */

/**
 * 思考：两种算法：核心思想都是：奇数->7=1+2+4 偶数->8=(2+2+2+2)*1
 */
public class powxN {
    //快速幂 + 迭代
    double quickMul(double x, long N) {
        double ans = 1.0;
        // 贡献的初始值为 x
        double x_contribute = x;
        // 在对 N 进行二进制拆分的同时计算答案
        //等于1+2+4+8+...
        while (N > 0) {
            if (N % 2 == 1) {
                // 如果 N 二进制表示的最低位为 1，那么需要计入贡献
                ans *= x_contribute;
            }
            // 将贡献不断地平方
            x_contribute *= x_contribute;
            // 舍弃 N 二进制表示的最低位，这样我们每次只要判断最低位即可
            N /= 2;
        }
        return ans;
    }

    public double myPow(double x, int n) {
        long N = n;
        return N >= 0 ? quickMul(x, N) : 1.0 / quickMul(x, -N);
    }
    //快速幂 + 递归
    public double quickMul2(double x, long N) {
        if (N == 0) {
            return 1.0;
        }
        double y = quickMul2(x, N / 2);
        return N % 2 == 0 ? y * y : y * y * x;
    }

    public double myPow2(double x, int n) {
        long N = n;
        return N >= 0 ? quickMul2(x, N) : 1.0 / quickMul2(x, -N);
    }
    public static void main(String[] args) {
        powxN n=new powxN();
        n.myPow(2.10000,8);
    }

}
