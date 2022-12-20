package 剑指II;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/9/24 16:08<br/>
 *
 * @author xkunchen<br />
 */

/**
 *✔	[剑指 Offer 10- I]斐波那契数列	36.1%	Easy	0.0%
 * https://leetcode.cn/problems/fei-bo-na-qi-shu-lie-lcof/
 */
public class Offer010I {
    final int MOD = 1000000007;
    public int fib(int n) {
        if (n==0){
            return 0;
        }
        if (n==1){
            return 1;
        }
        int[] arr=new int[n];
        arr[0]=1;
        arr[1]=1;
        for (int i = 2; i < n; i++) {
            arr[i]=(arr[i-2]+arr[i-1]) % MOD;
        }
        return arr[n-1];
    }

    public int fib2(int n) {
        final int MOD = 1000000007;
        if (n < 2) {
            return n;
        }
        int p = 0, q = 0, r = 1;
        for (int i = 2; i <= n; ++i) {
            p = q;
            q = r;
            r = (p + q) % MOD;
        }
        return r;
    }

    public static void main(String[] args) {
        Offer010I i=new Offer010I();
        System.out.println( i.fib(2));
    }
}
