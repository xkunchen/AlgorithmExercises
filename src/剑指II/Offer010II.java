package 剑指II;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/9/24 16:28<br/>
 *
 * @author xkunchen<br />
 */

/**
 * https://leetcode-cn.com/problems/climbing-stairs/
 *    	[剑指 Offer 10- II]青蛙跳台阶问题	45.6%	Easy	0.0%
 */
public class Offer010II {
    final int MOD = 1000000007;
    public int numWays(int n) {
        if (n==0){
            return 1;
        }
        if (n==1){
            return 1;
        }
        int[] arr=new int[n+1];
        arr[0]=1;
        arr[1]=1;
        for (int i = 2; i <= n; i++) {
            arr[i]=(arr[i-2]+arr[i-1]) % MOD;
        }
        return arr[n];
    }
}
