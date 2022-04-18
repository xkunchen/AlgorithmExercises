package 剑指offer;


/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/4/7 17:58<br/>
 *
 * @author xkunchen<br />
 */

/**
 * https://leetcode-cn.com/problems/counting-bits/
 *    [剑指 Offer II 003]前 n 个数字二进制中 1 的个数
 */
public class II003CountingBits {
    public int[] countBits(int n) {
        int[] bits = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            bits[i] = countOnes(i);
        }
        return bits;
    }

    public int countOnes(int x) {
        int ones = 0;
        while (x > 0) {
            x &= (x - 1);
            ones++;
        }
        return ones;
    }

    //最高有效位
    public int[] countBits2(int n) {
        int[] bits = new int[n + 1];
        int highBit = 0;
        for (int i = 1; i <= n; i++) {
            if ((i & (i - 1)) == 0) {
                highBit = i;
            }
            bits[i] = bits[i - highBit] + 1;
        }
        return bits;
    }

    //最低有效位
    public int[] countBits3(int n) {
        int[] bits = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            bits[i] = bits[i >> 1] + (i & 1);
        }
        return bits;
    }
    //i & (i - 1) (i & (i - 1)) == 0 ? 0:
    //最低设置位
    public int[] countBits4(int n) {
        int[] bits = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            bits[i] = bits[i & (i - 1)] + 1;
        }
        return bits;
    }


    public static void main(String[] args) {
        II003CountingBits bit=new II003CountingBits();
        bit.countBits4(7);
    }

}

