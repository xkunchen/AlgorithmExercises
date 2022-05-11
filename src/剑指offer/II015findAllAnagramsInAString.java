package 剑指offer;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/5/6 11:07<br/>
 *
 * @author xkunchen<br />
 */

import java.util.*;

/**
 *    [剑指 Offer II 015]字符串中的所有变位词
 *    https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/
 */

/**
 * 该题的重点是：如何选择数据结构存储结果满足判断单个字符串的数量相同，看findAnagrams3 回溯
 */
public class II015findAllAnagramsInAString {
    public List<Integer> findAnagramsError(String s, String p) {
        List<Integer> returnList = new ArrayList<>();
        Set<Character> contentMap=new HashSet<>();
        Map<Character,Integer> isExist=new HashMap<>();
        for (int i = 0; i < p.length(); i++) {
            contentMap.add(p.charAt(i));
        }
        char[] chars = s.toCharArray();
        int length = s.length();
        int left=0; int right=0;
        //首先先初始化窗口
        while (right<=length){
            if (isExist.size()==p.length()){
                returnList.add(left);
            }
            if (right>=length){
                break;
            }
            if (!contentMap.contains(chars[right])){
                right++;
                if (right>=length){
                    break;
                }
                left=right;
                isExist.clear();
            }else{
                if (right-left>=p.length()){
                    if (isExist.get(chars[left])>1){
                        isExist.put(chars[left],isExist.get(chars[left])-1);
                    }else {
                        isExist.remove(chars[left]);
                    }
                    left++;
                }
            }
            isExist.put(chars[right],isExist.getOrDefault(chars[right],0)+1);
            right++;
        }
        return returnList;
    }

    public List<Integer> findAnagrams2(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        if (sLen < pLen) {
            return new ArrayList<Integer>();
        }
        List<Integer> ans = new ArrayList<Integer>();
        int[] count = new int[26];
        for (int i = 0; i < pLen; ++i) {
            ++count[s.charAt(i) - 'a'];
            --count[p.charAt(i) - 'a'];
        }
        int differ = 0;
        for (int j = 0; j < 26; ++j) {
            if (count[j] != 0) {
                ++differ;
            }
        }
        if (differ == 0) {
            ans.add(0);
        }
        for (int i = 0; i < sLen - pLen; ++i) {
            if (count[s.charAt(i) - 'a'] == 1) {  // 窗口中字母 s[i] 的数量与字符串 p 中的数量从不同变得相同
                --differ;
            } else if (count[s.charAt(i) - 'a'] == 0) {  // 窗口中字母 s[i] 的数量与字符串 p 中的数量从相同变得不同
                ++differ;
            }
            --count[s.charAt(i) - 'a'];

            if (count[s.charAt(i + pLen) - 'a'] == -1) {  // 窗口中字母 s[i+pLen] 的数量与字符串 p 中的数量从不同变得相同
                --differ;
            } else if (count[s.charAt(i + pLen) - 'a'] == 0) {  // 窗口中字母 s[i+pLen] 的数量与字符串 p 中的数量从相同变得不同
                ++differ;
            }
            ++count[s.charAt(i + pLen) - 'a'];

            if (differ == 0) {
                ans.add(i + 1);
            }
        }

        return ans;
    }

    public List<Integer> findAnagrams(String s, String p) {
        int sLen = s.length(), pLen = p.length();

        if (sLen < pLen) {
            return new ArrayList<Integer>();
        }

        List<Integer> ans = new ArrayList<Integer>();
        int[] sCount = new int[26];
        int[] pCount = new int[26];
        for (int i = 0; i < pLen; ++i) {
            ++sCount[s.charAt(i) - 'a'];
            ++pCount[p.charAt(i) - 'a'];
        }

        if (Arrays.equals(sCount, pCount)) {
            ans.add(0);
        }

        for (int i = 0; i < sLen - pLen; ++i) {
            --sCount[s.charAt(i) - 'a'];
            ++sCount[s.charAt(i + pLen) - 'a'];

            if (Arrays.equals(sCount, pCount)) {
                ans.add(i + 1);
            }
        }

        return ans;
    }

    public List<Integer> findAnagrams3(String s, String p) {
        int[] cnt = new int[128];
        for (char c : p.toCharArray()) cnt[c]++;
        int lo = 0, hi = 0;
        List<Integer> res = new ArrayList<>();
        while (hi < s.length()) {
            if (cnt[s.charAt(hi)] > 0) {
                cnt[s.charAt(hi++)]--;
                if (hi - lo == p.length()){
                    res.add(lo);
                }
            } else {
                cnt[s.charAt(lo++)]++;
            }
        }
        return res;
    }
    public List<Integer> findAnagrams4(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        int n = s.length(), m = p.length();
        if (m > n) return ans;
        int[] count = new int[26];
        int[] target = new int[26];
        int diff = 0;   // sum(count[i]-target[i] )
        for (char c: p.toCharArray()){
            ++target[c-'a'];
            ++diff;
        }
        // 滑动窗口
        for (int i=0; i<n; ++i){
            ++count[s.charAt(i)-'a'];
            diff += count[s.charAt(i)-'a'] <= target[s.charAt(i)-'a'] ? -1 : 1;
            if (i >= m){
                --count[s.charAt(i-m)-'a'];
                diff += count[s.charAt(i-m)-'a'] >= target[s.charAt(i-m)-'a'] ? -1 : 1;
            }
            if (diff == 0) ans.add(i-m+1);
        }
        return ans;
    }

    public static List<Integer> findAnagrams6(String s, String p) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (s.length() < p.length()) return ans;
        int[] counts = new int[26]; // 欠账表：欠的字符 -> 欠的个数
        int all = p.length(); // 总欠账数目
        // 统计欠账，生成欠账表:
        for (char c : p.toCharArray()) counts[c-'a']++;
        // 【滑动窗口】还账：
        int l = 0, r = 0, n = s.length();
        char[] str = s.toCharArray();
        for (; l < n; l++) {
            // 窗口右边界字符进入窗口还账，如果不超额还账，就一直还：
            while (r < n && counts[str[r] - 'a'] > 0) {
                all--;
                counts[str[r++] - 'a']--;
            }
            // 还账结束，看当前窗口内是否还清了所有欠账：
            if (all == 0) ans.add(l);
            // 窗口左边界字符出窗口，重新赊账：
            counts[str[l] - 'a']++;
            all++;
        }
        return ans;
    }

    public List<Integer> findAnagrams7(String s, String p) {
        List<Integer> ans=new ArrayList<Integer>();
        int sLen=s.length();
        int pLen=p.length();

        if(sLen<pLen){
            return ans;
        }
        //建立两个数组存放字符串中字母出现的词频，并以此作为标准比较
        int [] scount=new int[26];
        int [] pcount=new int[26];

        //当滑动窗口的首位在s[0]处时 （相当于放置滑动窗口进入数组）
        for(int i=0;i<pLen;i++){
            ++scount[s.charAt(i)-'a']; //记录s中前pLen个字母的词频
            ++pcount[p.charAt(i)-'a']; //记录要寻找的字符串中每个字母的词频(只用进行一次来确定)
        }

        //判断放置处是否有异位词     (在放置时只需判断一次)
        if(Arrays.equals(scount,pcount)){
            ans.add(0);
        }

        //开始让窗口进行滑动
        for(int i=0;i<sLen-pLen;i++){ //i是滑动前的首位
            --scount[s.charAt(i) -'a'];       //将滑动前首位的词频删去
            ++scount[s.charAt(i+pLen) -'a'];  //增加滑动后最后一位的词频（以此达到滑动的效果）

            //判断滑动后处，是否有异位词
            if(Arrays.equals(scount,pcount)){
                ans.add(i+1);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        II015findAllAnagramsInAString i=new II015findAllAnagramsInAString();
        i.findAnagrams2("eidbaooo","ab");
    }
}
