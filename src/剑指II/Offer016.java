package 剑指II;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/12/20 11:08<br/>
 *
 * @author xkunchen<br />
 */

import 分治.powxN;

/**
 * https://leetcode-cn.com/problems/powx-n/
 *    	[剑指 Offer 16]数值的整数次方
 */
public class Offer016 {
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
        Offer016 n=new Offer016();
        n.myPow2(2.10000,15);
    }
}
