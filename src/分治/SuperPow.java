package 分治;

/**
 * @Author: xkunchen
 * @Description:
 * @Data: 2021/7/1
 **/

import java.util.Stack;
/**
 *https://leetcode-cn.com/problems/super-pow/
 * 超级次方
 */

/**
 * (a * b) % k = (a % k)(b % k) % k
 */
//总结：拆解，寻找子问题
public class SuperPow {
    int base = 1337;
    public int superPow(int a, int[] b) {
        Stack<Integer> stack = new Stack<>();
        int n = b.length;
        for(int i = 0;i < n;i++){
            stack.push(b[i]);
        }
        return hpPow(a,stack);
    }
    //递归实现
    public int hpPow(int a,Stack<Integer> stack){
        if(stack.isEmpty()){
            return 1;
        }
        int last = stack.pop();
        int part1 = mypow2(a,last);
        int part2 = mypow2(hpPow(a,stack),10);
        //(a * b) % k = (a % k)(b % k) % k
        return (part1 * part2) % base;
    }
    //取模防止溢出
    public int myPow(int a,int k){
        // a % k=a % k % k...;
        //(a * b) % k = (a % k)(b % k) % k
        //(a^2)%k =(a*a)%k=(a%k)(a%k)%k
        //(a^3)%k =(a*a^2)%k=(a%k)((a%k)(a%k)%k)%k
        //(a^4)%k =(a%k)((a%k)(a^2%k)%k)% k
        //(a^n)%k =(((a%k)%k)(a%k)%k)...
        int res = 1;
        a %= base;
        for(int i = 0;i < k;i++){
            res *= a;
            res %= base;
        }
        return res;
    }
    //快速求密
    int mypow2(int a, int k) {
        if (k == 0) return 1;
        a %= base;
        // a % k=a % k % k...;
        //(a * b) % k = (a % k)(b % k) % k
        //(a^2)%k =(a*a)%k=(a%k)(a%k)%k
        //(a^3)%k =(a*a^2)%k=(a%k)(a^2%k)%k
        //(a^4)%k =((a%k)(a%k)%k)*((a%k)(a%k)%k)%k
        if (k % 2 == 1) {
            // k 是奇数
            //(a * b) % k = (a % k)(b % k) % k
            return (a * mypow2(a, k - 1)) % base;
        } else {
            // k 是偶数
            int sub = mypow2(a, k / 2);
            return (sub * sub) % base;
        }
    }

    public static void main(String[] args) {
        SuperPow s=new SuperPow();
        s.superPow(2147483647,new int[]{2,0,0});
    }
}
