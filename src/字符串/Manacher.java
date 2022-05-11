package 字符串;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/5/9 17:18<br/>
 *
 * @author xkunchen<br />
 */
public class Manacher {
    /**
     * 对输入字符串预处理，加上辅助字符#
     * @param str
     * @return
     */
    private static char[] preprocess(String str) {
        char[] strArr = new char[str.length()*2+1];
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = (i&1) == 0 ? '#' : str.charAt(i/2);
        }
        return strArr;
    }
//[0, 1, 2, 1, 2, 3, 2, 1, 2, 3, 4, 9, 4, 3, 2, 1, 2, 3, 2, 1]
    /**
     * 寻找一个字符串中最长的回文子串，返回这个回文字符串的长度
     * @param str
     * @return
     */
    public static int maxLcpsLength(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] strArr = preprocess(str);
        int[] rArr = new int[strArr.length]; // 回文半径数组
        int R = -1; // 回文右边界
        int C = -1; // 回文中心
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < strArr.length; i++) {
            // 确定i位置回文半径的初始值
            // 当i>=R时，最短的回文半径是1，也就是字符本身；反之，最短的回文半径可能是i对应的i'的回文半径或者i到R的距离
            rArr[i] = R > i ? Math.min(rArr[2*C-i], R-i) : 1;
            // 尝试向外扩展
            while (i + rArr[i] < strArr.length && i - rArr[i] >= 0) {
                if (strArr[i+rArr[i]] == strArr[i-rArr[i]]) {
                    rArr[i]++;
                } else {
                    break;
                }
            }
            if (i + rArr[i] > R) {
                R = i+rArr[i];
                C = i;
            }
            // 更新最大回文半径的值
            max = Math.max(max, rArr[i]);
        }
        // 这里为什么返回值是max-1，因为预处理之后的字符串和原字符串不同，
        // 所以这里得到的最大回文半径其实是原字符串的最大回文子串长度加1
        return max - 1;
    }

    public static void main(String[] args) {
        String str1 = "daaccccaa";
        System.out.println(maxLcpsLength(str1));
    }
}

