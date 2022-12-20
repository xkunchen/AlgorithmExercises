package 剑指II;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/12/17 11:00<br/>
 *
 * @author xkunchen<br />
 */

import java.math.BigInteger;
import java.util.Arrays;

/**
 *    	[剑指 Offer 14- I]剪绳子	57.5%	Medium	0.0%
 *    https://leetcode-cn.com/problems/integer-break/
 */
public class Offer014II {
    public int cuttingRope(int n) {
        if(n <= 3) return n - 1;
        int b = n % 3, p = 1000000007;
        long rem = 1, x = 3;
        for(int a = n / 3 - 1; a > 0; a /= 2) {
            if(a % 2 == 1) rem = (rem * x) % p;
            x = (x * x) % p;
        }
        if(b == 0) return (int)(rem * 3 % p);
        if(b == 1) return (int)(rem * 4 % p);
        return (int)(rem * 6 % p);
    }

    public static void main(String[] args) {
        Offer014II i=new Offer014II();
        i.cuttingRope(120);
    }

}
