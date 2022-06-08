package 剑指offer;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/6/2 16:03<br/>
 *
 * @author xkunchen<br />
 */

/**
 *   	[剑指 Offer II 092]翻转字符
 *   https://leetcode-cn.com/problems/flip-string-to-monotone-increasing/
 */

/**
 * 前缀和的应用，该题目可以转换成：选定字符串的一个索引，左边为0，右边为1
 * 所以可以用前缀树，左边0的个数，右边1的个数进行判断
 */
public class II092flipStringToMonotoneIncreasing {
    public int minFlipsMonoIncr(String S) {
        int N = S.length();
        int[] P = new int[N + 1];
        for (int i = 0; i < N; ++i){
            P[i+1] = P[i] + (S.charAt(i) == '1' ? 1 : 0);
        }
        int ans = Integer.MAX_VALUE;
        for (int j = 0; j <= N; ++j) {
            ans = Math.min(ans, P[j] + N-j-(P[N]-P[j]));
        }

        return ans;
    }

    public int minFlipsMonoIncr2(String s) {
        int dp = 0, cnt = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '1') {
                ++cnt;
            } else {
                dp = Math.min(dp + 1, cnt);
            }
        }
        return dp;
    }

    //dp[i][0]表示前i个元素，最后一个元素为0的最小翻转次数；
    //dp[i][1]表示前i个元素，最后一个元素为1的最小翻转次数
    public int minFlipsMonoIncr3(String s) {
        int dp[][]=new int[s.length()][2];
        //初始化
        dp[0][0]=s.charAt(0)=='0'?0:1;
        dp[0][1]=s.charAt(0)=='1'?0:1;
        //状态转移
        for (int i = 1; i <s.length() ; i++) {
            dp[i][0]=dp[i-1][0]+(s.charAt(i)=='0'?0:1);
            dp[i][1]=Math.min(dp[i-1][0],dp[i-1][1])+(s.charAt(i)=='1'?0:1);
        }
        return Math.min(dp[s.length()-1][0],dp[s.length()-1][1]);
    }

    public static void main(String[] args) {
        II092flipStringToMonotoneIncreasing i=new II092flipStringToMonotoneIncreasing();
        System.out.println( i.minFlipsMonoIncr2("00011000"));
    }
}
