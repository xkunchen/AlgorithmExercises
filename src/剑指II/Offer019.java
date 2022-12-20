package 剑指II;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/12/20 15:32<br/>
 *
 * @author xkunchen<br />
 */

import 动态规划.RegularExpressionMatching;

/**
 * https://leetcode-cn.com/problems/regular-expression-matching/
 *    	正则表达式匹配
 */
// 输入:
//"aaab" p = "a*b"
//输出: true
public class Offer019 {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        //1 普通字符，如果当前最后一个字符相等，对比s和p上一个字符串
        //2 .字符，当成第一种情况的特殊处理，无论如何相等
        //3 *字符，
        //  3.1 p先消掉2个对比一下
        //  3.2 是否对比s当前与p上一个字符是否相等，如果相等，再对比s上一个字符串 与p当前字符串
        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    //这一步不能放里面的原因是当i为0的时候，如果有*的话为匹配成功这种情况
                    //"aab","c*a*ab"
                    //这一步是举例 c*a* 与 aa 对比aa与c*
                    f[i][j] = f[i][j - 2];
                    if (matches(s, p, i, j - 1)) {
                        //这一步是举例 c*a* 与 aa， 对比a与c*a*
                        f[i][j] = f[i][j] || f[i - 1][j];
                    }
                } else {
                    if (matches(s, p, i, j)) {
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }
        return f[m][n];
    }

    public boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }
    public static void main(String[] args) {
        Offer019 s=new Offer019();
        s.isMatch("aa","c*a*");
    }
}
