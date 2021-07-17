package 滑动窗口;

/**
 * @Author: xkunchen
 * @Description:
 * @Data: 2021/7/16
 **/

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 * 无重复字符的最长子串
 */
public class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        if (s.equals("")){
            return 0;
        }
        if (s.trim().equals("")){
            return 1;
        }
        int left =0;
        int maxData=0;
        HashMap<Character,Integer> map=new HashMap<>();
        char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (map.get(charArray[i])==null) map.put(charArray[i],0);
            if (map.get(charArray[i])!=0){
                while (left<i) {
                    if (charArray[i]==charArray[left]){
                        left++;
                        break;
                    }
                    map.put(charArray[left],map.get(charArray[left])-1);
                    left++;
                }
            }else {
                map.put(charArray[i],map.get(charArray[i])+1);
                maxData=Math.max(maxData,i-left+1);
            }

        }
        return maxData;
    }
    public int lengthOfLongestSubstring2(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }


    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters l=new LongestSubstringWithoutRepeatingCharacters();
        l.lengthOfLongestSubstring2("abcabcbb");
    }
}
