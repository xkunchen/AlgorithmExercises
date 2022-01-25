package 动态规划;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/1/22 11:28<br/>
 *
 * @author xkunchen<br />
 */

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/integer-replacement/
 * 整数替换
 */
public class IntegerReplacement {

    //公式：f(n)=n%2==0?f(n/2)+1:min(f(n+1)+1,f(n-1)+1)
    public int integerReplacement(int n) {
        if (n<=1){
            return 0;
        }
        int[] arr=new int[n+2];
        for (int i = 2; i < arr.length; i++) {
            boolean isDouble=i%2==0;
            arr[i]=isDouble?arr[i/2]+1:arr[i-1]+1;
            arr[i-1]=isDouble&&i!=2?Math.min(arr[i-2]+1,arr[i]+1):arr[i-1];
        }
        return arr[n];
    }

    //递归
    public int integerReplacement2(int n) {
        if (n == 1) {
            return 0;
        }
        if (n % 2 == 0) {
            return 1 + integerReplacement2(n / 2);
        }
        return 2 + Math.min(integerReplacement2(n / 2), integerReplacement2(n / 2 + 1));
    }

    Map<Integer, Integer> memo = new HashMap<Integer, Integer>();

    public int integerReplacement3(int n) {
        if (n == 1) {
            return 0;
        }
        if (!memo.containsKey(n)) {
            if (n % 2 == 0) {
                memo.put(n, 1 + integerReplacement3(n / 2));
            } else {
                memo.put(n, 2 + Math.min(integerReplacement3(n / 2), integerReplacement3(n / 2 + 1)));
            }
            System.out.println(memo.get(n));
        }
        return memo.get(n);
    }

    public static void main(String[] args) {
        IntegerReplacement i=new IntegerReplacement();
        System.out.println(i.integerReplacement(100000000));
    }
}
