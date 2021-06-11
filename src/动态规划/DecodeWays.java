package 动态规划;
//一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：
//'A' -> 1
//'B' -> 2
//...
//'Z' -> 26
// 要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，"11106" 可以映射为：
// "AAJF" ，将消息分组为 (1 1 10 6)
// "KJF" ，将消息分组为 (11 10 6)
// 注意，消息不能分组为 (1 11 06) ，因为 "06" 不能映射为 "F" ，这是由于 "6" 和 "06" 在映射中并不等价。
// 给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。
// 题目数据保证答案肯定是一个 32 位 的整数。
// 示例 1：
//输入：s = "12"
//输出：2
//解释：它可以解码为 "AB"（1 2）或者 "L"（12）。
// 示例 2：
//输入：s = "226"
//输出：3
//解释：它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
// 示例 3：
//输入：s = "0"
//输出：0
//解释：没有字符映射到以 0 开头的数字。
//含有 0 的有效映射是 'J' -> "10" 和 'T'-> "20" 。
//由于没有字符，因此没有有效的方法对此进行解码，因为所有数字都需要映射。
// 示例 4：
//输入：s = "06"
//输出：0
//解释："06" 不能映射到 "F" ，因为字符串含有前导 0（"6" 和 "06" 在映射中并不等价）。
// 提示：
// 1 <= s.length <= 100
// s 只包含数字，并且可能包含前导零。
// Related Topics 字符串 动态规划
// 👍 861 👎 0
/**
 * @Author: xkunchen
 * @Description:
 * @Data: 2021/6/4
 **/

/**
 *1. https://leetcode-cn.com/problems/decode-ways/
 *        解码方法
 *        动态规划
 */

import sun.security.util.Length;

/**
 * 思路：单个不能为0，两个不能大于26
 * f(n)= f(n-1)+arr[n-1]*10+arr[n]<26&&arr[n-1]!=0?0:1
 */
public class DecodeWays {
    public int numDecodings(String s) {
        if (s.length()==0||s.substring(0,1).equals("0")) return 0;
        if (s.length()==1) return 1;
        char[] charArray = s.toCharArray();
        int[] arrs=new int[charArray.length];
        arrs[0]=1;
        for (int i = 1; i < charArray.length; i++) {
            char currentString = charArray[i];
            char preString = charArray[i-1];
            int num = (charArray[i - 1] - '0') * 10 + (charArray[i] - '0');
            int icre=0;
            if ((currentString==(preString)&&currentString=='0')||(num>27&&currentString=='0')){
                return 0;
            }
            if (currentString=='0'){
                arrs[i]= i>2?arrs[i-2]:1;
                continue;
            }else  if(num<27&&preString !='0'&&currentString!='0'){
                icre=1;
            }
            int t=i>2?arrs[i-2]:1;
            arrs[i]= arrs[i-1]+t*icre;
        }
        return arrs[s.length()-1];
    }
    public int numDecodings2(String s) {
        int n = s.length();
        int[] f = new int[n + 1];
        f[0] = 1;
        for (int i = 1; i <= n; ++i) {
            if (s.charAt(i - 1) != '0') {
                f[i] += f[i - 1];
            }
            if (i > 1 && s.charAt(i - 2) != '0' && ((s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0') <= 26)) {
                f[i] += f[i - 2];
            }
        }
        return f[n];
    }
    public int numDecodings3(String s) {
        int n = s.length();
        // a = f[i-2], b = f[i-1], c=f[i]
        int a = 0, b = 1, c = 0;
        for (int i = 1; i <= n; ++i) {
            c = 0;
            if (s.charAt(i - 1) != '0') {
                c += b;
            }
            if (i > 1 && s.charAt(i - 2) != '0' && ((s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0') <= 26)) {
                c += a;
            }
            a = b;
            b = c;
        }
        return c;
    }

    public static void main(String[] args) {
        DecodeWays d=new DecodeWays();
        d.numDecodings("12");
    }
}

