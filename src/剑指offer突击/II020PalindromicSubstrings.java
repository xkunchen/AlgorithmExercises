package 剑指offer突击;


/**
 *      https://leetcode-cn.com/problems/palindromic-substrings/
 * ✔	[剑指 Offer II 020]回文子字符串的个数
 */
// 示例 1：
//输入：s = "abc"
//输出：3
//解释：三个回文子串: "a", "b", "c"
// 示例 2：
//输入：s = "aaa"
//输出：6
//解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
public class II020PalindromicSubstrings {
    public int countSubstrings(String s) {
        boolean[][] arr=new boolean[s.length()][s.length()];
        char[] chars = s.toCharArray();
        //初始化当长度为1时，每个字符串都是回文串
        int sum=0;
        for (int i = 0; i < s.length(); i++) {
            arr[i][i]=true;
            sum++;
        }
        for (int i = 1; i < s.length(); i++) {
            for (int j = 0; j < s.length()-i; j++) {
                if (chars[j]==chars[j+i]&&(i==1||arr[j+1][j+i-1])){
                    arr[j][j+i]=true;
                    sum++;
                }
            }
        }
        return sum;
    }

    //中心拓展
    public int countSubstrings2(String s) {
        int n = s.length(), ans = 0;
        for (int i = 0; i < 2 * n - 1; ++i) {
            int l = i / 2, r = i / 2 + i % 2;
            System.out.println(l+","+r);
            while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
                --l;
                ++r;
                System.out.println(l+","+r);
                ++ans;
            }
        }
        return ans;
    }

    //Manacher 算法
    //$#d#a#a#c#c#c#c#a#a#!
    //[0, 1, 2, 1, 2, 3, 2, 1, 2, 3, 4, 9, 4, 3, 2, 1, 2, 3, 2, 1]
    public int countSubstrings3(String s) {
        int n = s.length();
        StringBuffer t = new StringBuffer("$#");
        for (int i = 0; i < n; ++i) {
            t.append(s.charAt(i));
            t.append('#');
        }
        n = t.length();
        t.append('!');

        int[] f = new int[n];
        int C = 0, R = 0, ans = 0;
        for (int i = 1; i < n; ++i) {
            System.out.println("该字符串："+t.charAt(i));
            // 初始化 f[i]
            f[i] = i <= R ? Math.min(R - i + 1, f[2 * C - i]) : 1;
            if ( i <= R ){
                System.out.println("判断："+(R - i + 1)+",f索引："+(2 * C - i)+"，值："+f[2 * C - i]);
            }else {
                System.out.println("默认1");
            }
            // 中心拓展
            while (t.charAt(i + f[i]) == t.charAt(i - f[i])) {
                ++f[i];
            }
            // 动态维护 iMax 和 rMax
            if (i + f[i] - 1 > R) {
                C = i;
                R = i + f[i] - 1;
            }
            System.out.println(C+","+R);
            System.out.println(t.substring(2*C-R,R+1));
            // 统计答案, 当前贡献为 (f[i] - 1) / 2 上取整
            ans += f[i] / 2;
        }

        return ans;
    }


    public static void main(String[] args) {
        II020PalindromicSubstrings i=new II020PalindromicSubstrings();
        i.countSubstrings3("daaccccaa");
    }
}
