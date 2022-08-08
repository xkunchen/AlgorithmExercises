package 剑指offer突击;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/5/6 11:08<br/>
 *
 * @author xkunchen<br />
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 *    [剑指 Offer II 016]不含重复字符的最长子字符串
 *    https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 */
public class II016longestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        int maxLen=0;
        char[] chars = s.toCharArray();
        int length = s.length();
        Set<Character> isExist=new HashSet<>();
        int left=0; int right=0;
        while (right<length){
            if (isExist.contains(chars[right])){
                while (chars[left]!=chars[right]){
                    isExist.remove(chars[left]);
                    left++;
                }
                left++;
            }
            isExist.add(chars[right]);
            maxLen=Math.max(maxLen,right-left+1);
            ++right;
        }
        return maxLen;
    }

    public int lengthOfLongestSubstring3(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int max = 0, start = 0;
        for (int end = 0; end < s.length(); end++) {
            char ch = s.charAt(end);
            if (map.containsKey(ch)){
                start = Math.max(map.get(ch)+1,start);
            }
            max = Math.max(max,end - start + 1);
            map.put(ch,end);
        }
        return max;
    }

    public int lengthOfLongestSubstring2(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        char[] charArray = s.toCharArray();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(charArray[i - 1]);
            }
            while (rk + 1 < n && !occ.contains(charArray[rk + 1])) {
                // 不断地移动右指针
                occ.add(charArray[rk + 1]);
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        II016longestSubstringWithoutRepeatingCharacters i=new II016longestSubstringWithoutRepeatingCharacters();
        i.lengthOfLongestSubstring3("abcdefdefsd");
    }
}
