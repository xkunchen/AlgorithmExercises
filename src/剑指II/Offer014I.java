package 剑指II;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/12/17 11:00<br/>
 *
 * @author xkunchen<br />
 */

/**
 *    	[剑指 Offer 14- I]剪绳子	57.5%	Medium	0.0%
 *    https://leetcode-cn.com/problems/integer-break/
 */
public class Offer014I {
    public int cuttingRope(int n) {
        if (n==2){
            return 1;
        }
        if (n==3){
            return 2;
        }
        int[] dp = new int[n + 1];
        dp[1]=1;
        for (int i = 2; i <= n; i++) {
            int curMax = 0;
            for (int j = 1; j < i; j++) {
                curMax = Math.max(curMax, Math.max(i, j * dp[i - j]));
            }
            dp[i] = curMax;
        }
        return dp[n];
    }
    public int cuttingRope2(int n) {
        if (n <= 3) {
            return n - 1;
        }
        int[] dp = new int[n + 1];
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i] = Math.max(Math.max(2 * (i - 2), 2 * dp[i - 2]), Math.max(3 * (i - 3), 3 * dp[i - 3]));
        }
        return dp[n];
    }
    //数学证明
    public int cuttingRope3(int n) {
        if (n <= 3) {
            return n - 1;
        }
        int quotient = n / 3;
        int remainder = n % 3;
        if (remainder == 0) {
            return (int) Math.pow(3, quotient);
        } else if (remainder == 1) {
            return (int) Math.pow(3, quotient - 1) * 4;
        } else {
            return (int) Math.pow(3, quotient) * 2;
        }
    }

    public static void main(String[] args) {
        Offer014I i=new Offer014I();
        i.cuttingRope(2);
    }

}
