package 剑指II;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/12/17 18:43<br/>
 *
 * @author xkunchen<br />
 */

/**
 *    	[剑指 Offer 15]二进制中1的个数	75.4%	Easy	0.0%
 *https://leetcode-cn.com/problems/number-of-1-bits/
 */
public class Offer015 {
    public int hammingWeight(int n) {
        int ret = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & (1 << i)) != 0) {
                ret++;
            }
        }
        return ret;
    }

    public int hammingWeight2(int n) {
        int ret = 0;
        while (n != 0) {
            n &= n - 1;
            ret++;
        }
        return ret;
    }

    public static void main(String[] args) {
        Offer015 f=new Offer015();
        f.hammingWeight2(31);
    }
}
