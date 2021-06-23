package 回溯;

/**
 * @Author: xkunchen
 * @Description:
 * @Data: 2021/6/23
 **/

import java.util.ArrayList;
import java.util.List;

/**
 * 判断一个数字是否可以表示成三的幂的和
 * https://leetcode-cn.com/problems/check-if-number-is-a-sum-of-powers-of-three/
 * 回溯
 */
public class CheckIfNumberIsASumOfPowersOfThree {
    //数学法
    /**
     * 转换为三进制数计算每一位， 如果出现某一位为2则不满足题意。
     *     例如 12 的三进制数为
     *      12 -> 110 (0 * 3^0 + 1 * 3^1 + 1 * 3^2)(0∗30+1∗31+1∗32) true
     */

    public boolean checkPowersOfThree(int n) {
        while(n!=0) {
            if (n % 3 == 2) return false;
            n = (int) Math.floor(n / 3);
        }
        return true;
        //return n<2 || (n%3 != 2 && checkPowersOfThree(n/3)); //一行代码
    }
    public boolean checkPowersOfThree4(int n) {
        int MaxData=0;
        int[] list=new int[n+1];
        while ( Math.pow(3,MaxData) <= n ) {
            list[MaxData]=(int) Math.pow(3,MaxData);
            MaxData++;
        }
        for (int i = 0; i < n; i++) {

        }
        boolean isUsed[]=new boolean[MaxData];
        return deal(list,n,0,isUsed);
    }
    //1+3+9+27+81+...+3^(n-1)<3^n 成立，才可以使得以下程序
    public boolean checkPowersOfThree3(int n) {
        int MaxData=0;
        int[] list=new int[n+1];
        while ( Math.pow(3,MaxData) <= n ) {
            list[MaxData]=(int) Math.pow(3,MaxData);
            MaxData++;
        }
        for (int i = MaxData -1; i >= 0; i--) {
            if (n >= list[i]) n -= list[i];
        }
        return n == 0;
    }
    //回溯超时
    public boolean checkPowersOfThree2(int n) {
        int MaxData=0;
       int[] list=new int[n+1];
        while ( Math.pow(3,MaxData) <= n ) {
            list[MaxData]=(int) Math.pow(3,MaxData);
            MaxData++;
        }
        boolean isUsed[]=new boolean[MaxData];
        return deal(list,n,0,isUsed);
    }

    private boolean deal( int[] list, int n,int currentVal,boolean isUsed[]) {
        if (n==currentVal) return  true;
        if (currentVal>n) return false;
        for (int i = 0; i < isUsed.length; i++) {
            if (isUsed[i]) continue;
            isUsed[i]=true;
            if (deal(list,n,currentVal+list[i],isUsed)) return true;
            isUsed[i]=false;
        }
        return false;
    }

    public static void main(String[] args) {
        CheckIfNumberIsASumOfPowersOfThree c=new CheckIfNumberIsASumOfPowersOfThree();
        c.checkPowersOfThree3(10);
    }
}
