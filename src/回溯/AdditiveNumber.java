package 回溯;

/**
 * @Author: xkunchen
 * @Description:
 * @Data: 2021/6/16
 **/

/**
 * https://leetcode-cn.com/problems/additive-number/
 *         累加数
 *         回溯算法
 */
public class AdditiveNumber {
    //第一遍看答案解答
    public boolean isAdditiveNumber(String num) {
        int n = num.length();
        char[] chs = num.toCharArray();
        long v1 = 0;
        for (int i = 0; i < n - 2; i++) {
            if (i == 1 && chs[0] == '0') {
                return false;
            }
            v1 = v1 * 10 + (chs[i] - '0');
            long v2 = 0;
            for (int j = i + 1; j < n - 1; j++) {
                v2 = v2 * 10 + chs[j] - '0';
                if (j > i + 1 && chs[i + 1] == '0') {
                    break;
                }
                if (check(num, j + 1, v2, v1 + v2)) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean check(String num, int start, long pre, long cur) {
        for (int i = start; i < num.length(); ) {
            String scur = String.valueOf(cur);
            if (!num.startsWith(scur, i)) {
                return false;
            }
            i += scur.length();
            long t = pre + cur;
            pre = cur;
            cur = t;
        }
        return true;
    }

    public static void main(String[] args) {
        AdditiveNumber a=new AdditiveNumber();
        a.isAdditiveNumber("1100101102");
    }
}
