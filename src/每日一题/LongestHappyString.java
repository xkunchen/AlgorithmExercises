package 每日一题;

//如果字符串中不含有任何 'aaa'，'bbb' 或 'ccc' 这样的字符串作为子串，那么该字符串就是一个「快乐字符串」。
// 如果不存在这样的字符串 s ，请返回一个空字符串 ""。
// 示例 1：
// 输入：a = 1, b = 1, c = 7
//输出："ccaccbcc"
//解释："ccbccacc" 也是一种正确答案。

/**
 * https://leetcode-cn.com/problems/longest-happy-string/
 * 最长快乐字符串
 * 贪心算法
 */

import java.util.Arrays;

/**
 * 看答案，知道要用贪心算法，卡在分析这一步，可以记住思想，就是数量多的先排，然后大胆地写算法
 */
public class LongestHappyString {

    public String longestDiverseString(int a, int b, int c) {
        StringBuilder res = new StringBuilder();
        Pair[] arr = {new Pair(a, 'a'), new Pair(b, 'b'), new Pair(c, 'c')};

        while (true) {
            Arrays.sort(arr, (p1, p2) -> p2.freq - p1.freq);
            boolean hasNext = false;
            for (Pair pair : arr) {
                if (pair.freq <= 0) {
                    break;
                }
                int m = res.length();
                if (m >= 2 && res.charAt(m - 2) == pair.ch && res.charAt(m - 1) == pair.ch) {
                    continue;
                }
                hasNext = true;
                res.append(pair.ch);
                pair.freq--;
                break;
            }
            if (!hasNext) {
                break;
            }
        }

        return res.toString();
    }

    class Pair {
        int freq;
        char ch;

        public Pair(int freq, char ch) {
            this.freq = freq;
            this.ch = ch;
        }
    }

    public static void main(String[] args) {
        LongestHappyString longestHappyString=new LongestHappyString();
        longestHappyString.longestDiverseString(2,2,7);
    }

}
