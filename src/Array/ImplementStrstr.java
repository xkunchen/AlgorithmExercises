package Array;
//实现 strStr() 函数。
// 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如
//果不存在，则返回 -1。
// 示例 1:
// 输入: haystack = "hello", needle = "ll"
//输出: 2
// 示例 2:
// 输入: haystack = "aaaaa", needle = "bba"
//输出: -1
// 说明:
// 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
// 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
// Related Topics 双指针 字符串
// 👍 542 👎 0
//8月26日看答案解题
public class ImplementStrstr {
    //第一次提交，暴力解法，全部遍历一遍，一个一个匹配
    //看别人的代码，kmp
    public int strStr(String haystack, String needle) {
        KMP kmp = new KMP("aaab");
        int pos1 = kmp.search("aaacaaab");
        return pos1;
    }
    //kmp评论里的写法
    public int strStr2(String haystack, String needle) {
        if(needle.equals("")) {
            return 0;
        }
        if(haystack.equals("")) {
            return -1;
        }

        // 构造KMP中的dp矩阵
        int m = needle.length();
        // 各个状态(行)遇到下一个字符(列)跳转到哪个状态
        int[][] dp = new int[m][256];
        // 影子状态
        int X = 0;
        dp[0][needle.charAt(0)] = 1;
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < 256; j++) {
                //假设下个字符不匹配，此时要回去看影子状态，从而得知跳转到哪个状态
                dp[i][j] = dp[X][j];
            }
            // 只有pat上i的字符匹配，跳转到下个状态
            dp[i][needle.charAt(i)] = i + 1;
            // 更新影子状态
            X = dp[X][needle.charAt(i)];
        }

        // 构造dp完成后，开始search
        // 初始状态为0
        int s = 0;
        for (int i = 0; i < haystack.length(); i++) {
            s = dp[s][haystack.charAt(i)];
            if (s == m) {
                return i - m + 1;
            }
        }

        // 匹配失败，返回-1
        return -1;
    }

    public static void main(String[] args) {
        ImplementStrstr i=new ImplementStrstr();
        System.out.println( i.strStr("hello","ll"));
    }
}
class KMP {
    private int[][] dp;
    private String pat;
    public KMP(String pat) {
        this.pat = pat;
        int M = pat.length();
        // dp[状态][字符] = 下个状态
        dp = new int[M][256];
        // base case
        char c2 = pat.charAt(0);
        dp[0][c2] = 1;
        // 影子状态 X 初始为 0
        int X = 0;
        // 构建状态转移图（稍改的更紧凑了）
        for (int j = 1; j < M; j++) {
            for (int c = 0; c < 256; c++) {
                char c1 = pat.charAt(j);
                dp[j][c] = dp[X][c];
                dp[j][c1] = j + 1;
                // 更新影子状态
                X = dp[X][pat.charAt(j)];
            }
        }
    }
    public int search(String txt){

        int M = pat.length();
        int N = txt.length();
        // pat 的初始态为 0
        int j = 0;
        for (int i = 0; i < N; i++) {
            // 计算 pat 的下一个状态
            char c = txt.charAt(i);
            j = dp[j][c];
            // 到达终止态，返回结果
            if (j == M) return i - M + 1;
        }
        // 没到达终止态，匹配失败
        return -1;
    }
}