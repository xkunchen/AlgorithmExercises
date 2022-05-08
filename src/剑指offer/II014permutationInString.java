package 剑指offer;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/5/6 11:05<br/>
 *
 * @author xkunchen<br />
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *    [剑指 Offer II 014]字符串中的变位词
 *    https://leetcode-cn.com/problems/permutation-in-string/
 */
public class II014permutationInString {
    public boolean checkInclusion(String s1, String s2) {
        int[] cnt = new int[128];
        for (char c : s1.toCharArray()) cnt[c]++;
        int lo = 0, hi = 0;
        while (hi < s2.length()) {
            if (cnt[s2.charAt(hi)] > 0) {
                cnt[s2.charAt(hi++)]--;
                if (hi - lo == s1.length()) {
                    return true;
                }
            } else {
                cnt[s2.charAt(lo++)]++;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        II014permutationInString i=new II014permutationInString();
        i.checkInclusion("eidbaooo","ab");
    }
}
