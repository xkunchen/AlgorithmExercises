package 动态规划;

/**
 * @Author: xkunchen
 * @Description:
 * @Data: 2021/6/2
 **/
//给你一个字符串 s，找到 s 中最长的回文子串。
// 示例 1：
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
// 示例 2：
//输入：s = "cbbd"
//输出："bb"
// 示例 3：
//输入：s = "a"
//输出："a"
// 示例 4：
//输入：s = "ac"
//输出："a"
// 提示：
// 1 <= s.length <= 1000
// s 仅由数字和英文字母（大写和/或小写）组成
// Related Topics 字符串 动态规划
// 👍 3694 👎 0

import java.util.ArrayList;
import java.util.List;

/**
 *  https://leetcode-cn.com/problems/longest-palindromic-substring/
 *        最长回文子串
 *        动态规划
 *        第一遍
 */
//自己思路：第一遍看题解自己写，公式是第i个到第j个如果为ture 那么第i-1到第j+1 进行一个判断，如果是，那么就是回文串。
// 公式： f(i)(j)=f(i+1)(j-1)&& s.substring(i)==s.substring(j)
// 理解错了思路，应该这样理解，假设
// 第一次为2个长度的字符串，（0,2）（1,3）（2,4）...进行判断；
// 第二次为3个长度的字符串，（0,3）（1,4）（2,5）...进行判断；
// 第二次为4个长度的字符串，（0,4）（1,5）（2,6）...进行判断,并获取（1,3）（2,4）（3,5）的值进行判断，就用到了公式，其实公式没理解错，就是不知道怎么用；
public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        int[][] arr=new int[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < s.length(); j++) {
                if (i==j){
                    arr[i][j]=1;
                }
            }
        }
        return  null;
    }
    //动态规划
    public String longestPalindrome2(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        int maxLen = 1;
        int begin = 0;
        // dp[i][j] 表示 s[i..j] 是否是回文串
        boolean[][] dp = new boolean[len][len];
        // 初始化：所有长度为 1 的子串都是回文串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        char[] charArray = s.toCharArray();
        // 递推开始
        // 先枚举子串长度
        for (int L = 2; L <= len; L++) {
            // 枚举左边界，左边界的上限设置可以宽松一些
            for (int i = 0; i < len; i++) {
                // 由 L 和 i 可以确定右边界，即 j - i + 1 = L 得
                int j = L + i - 1;
                // 如果右边界越界，就可以退出当前循环
                if (j >= len) {
                    break;
                }

                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                // 只要 dp[i][L] == true 成立，就表示子串 s[i..L] 是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }
    //中心扩展法
    public String longestPalindrome3(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    public int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }
        return right - left - 1;
    }
    //Manacher 算法
    public String longestPalindrome4(String s) {
        int start = 0, end = -1;
        StringBuffer t = new StringBuffer("#");
        for (int i = 0; i < s.length(); ++i) {
            t.append(s.charAt(i));
            t.append('#');
        }
        t.append('#');
        s = t.toString();

        List<Integer> arm_len = new ArrayList<Integer>();
        int right = -1, j = -1;
        for (int i = 0; i < s.length(); ++i) {
            int cur_arm_len;
            if (right >= i) {
                int i_sym = j * 2 - i;
                int min_arm_len = Math.min(arm_len.get(i_sym), right - i);
                cur_arm_len = expand(s, i - min_arm_len, i + min_arm_len);
            } else {
                cur_arm_len = expand(s, i, i);
            }
            arm_len.add(cur_arm_len);
            if (i + cur_arm_len > right) {
                j = i;
                right = i + cur_arm_len;
            }
            if (cur_arm_len * 2 + 1 > end - start) {
                start = i - cur_arm_len;
                end = i + cur_arm_len;
            }
        }

        StringBuffer ans = new StringBuffer();
        for (int i = start; i <= end; ++i) {
            if (s.charAt(i) != '#') {
                ans.append(s.charAt(i));
            }
        }
        return ans.toString();
    }

    public int expand(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }
        return (right - left - 2) / 2;
    }
    public static void main(String[] args) {
        LongestPalindromicSubstring l=new LongestPalindromicSubstring();
        l.longestPalindrome3("abcddcbaacca");
    }
}
