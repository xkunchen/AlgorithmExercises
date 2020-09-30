package 二分查找;
//给定一个正整数 num，编写一个函数，如果 num 是一个完全平方数，则返回 True，否则返回 False。
// 说明：不要使用任何内置的库函数，如 sqrt。
// 示例 1：
// 输入：16
//输出：True
// 示例 2：
// 输入：14
//输出：False
// Related Topics 数学 二分查找
// 👍 169 👎 0
/**
 * 有效的完全平方数
 *             https://leetcode-cn.com/problems/valid-perfect-square/
 *             二分查找
 */
public class ValidPerfectSquare {
    public boolean isPerfectSquare(int num) {
        int left = 0, right = num, mid;
        while (left <= right) {
            mid = (right - left) / 2 + left;
            if(mid*mid==num){
                return true;
            }else if((long)mid*(long)mid<num){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return false;
    }
}
