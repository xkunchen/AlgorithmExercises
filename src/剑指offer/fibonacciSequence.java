package 剑指offer;

/**
 * @Author: xkunchen
 * @Description:
 * @Data: 2021/7/29
 **/

/**
 * 剑指 Offer 10- I. 斐波那契数列
 * https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof/
 */
public class fibonacciSequence {
    public int fib(int n) {
        if (n==0) return 0;
        if (n==1) return 1;
        int arr[]=new int[n+1];
        arr[0]=0;
        arr[1]=1;
        for (int i = 2; i <= n; i++) {
            arr[i]=(arr[i-1]+arr[i-2])% 1000000007;
        }
        return arr[n];
    }

    public static void main(String[] args) {
        fibonacciSequence f=new fibonacciSequence();
        f.fib( 45);
    }
}
