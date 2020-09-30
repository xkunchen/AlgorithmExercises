package 二分查找;
//实现 int sqrt(int x) 函数。
// 计算并返回 x 的平方根，其中 x 是非负整数。
// 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
// 示例 1:
// 输入: 4
//输出: 2
// 示例 2:
// 输入: 8
//输出: 2
//说明: 8 的平方根是 2.82842...,
//     由于返回类型是整数，小数部分将被舍去。
// Related Topics 数学 二分查找
// 👍 517 👎 0

/**
 * x 的平方根
 *         https://leetcode-cn.com/problems/sqrtx/
 *         二分查找
 */
public class Sqrtx {
    public int mySqrt(int x) {
        int left = 0, right = x, mid;
        while (left <= right) {
            mid = (right - left) / 2 + left;
            if(mid*mid==x){
                return mid;
            }else if((long)mid*(long)mid<x){
                if(((long)mid+1)*((long)mid+1)>x){
                    return mid;
                }
                left = mid + 1;
            }else{
                if(((long)mid-1)*((long)mid-1)<x){
                    return mid-1;
                }
                right = mid - 1;
            }
        }
        return -1;
    }
    public int mySqrt2(int x) {
        if (x == 0) {
            return 0;
        }
        int ans = (int) Math.exp(0.5 * Math.log(x));
        return (long) (ans + 1) * (ans + 1) <= x ? ans + 1 : ans;
    }
    public int mySqrt3(int x) {
        int l = 0, r = x, ans = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if ((long) mid * mid <= x) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        Sqrtx s=new Sqrtx();
        s.mySqrt(2147395599);
    }
}
