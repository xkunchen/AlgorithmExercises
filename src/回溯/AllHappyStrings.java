package 回溯;
//一个 「开心字符串」定义为：
// 仅包含小写字母 ['a', 'b', 'c'].
// 对所有在 1 到 s.length - 1 之间的 i ，满足 s[i] != s[i + 1] （字符串的下标从 1 开始）。
// 比方说，字符串 "abc"，"ac"，"b" 和 "abcbabcbcb" 都是开心字符串，但是 "aa"，"baa" 和 "ababbc" 都不是开心字
//符串。
// 给你两个整数 n 和 k ，你需要将长度为 n 的所有开心字符串按字典序排序。
// 请你返回排序后的第 k 个开心字符串，如果长度为 n 的开心字符串少于 k 个，那么请你返回 空字符串 。
// 示例 1：
// 输入：n = 1, k = 3
//输出："c"
//解释：列表 ["a", "b", "c"] 包含了所有长度为 1 的开心字符串。按照字典序排序后第三个字符串为 "c" 。
// 示例 2：
// 输入：n = 1, k = 4
//输出：""
//解释：长度为 1 的开心字符串只有 3 个。
// 示例 3：
// 输入：n = 3, k = 9
//输出："cab"
//解释：长度为 3 的开心字符串总共有 12 个 ["aba", "abc", "aca", "acb", "bab", "bac", "bca", "bcb
//", "cab", "cac", "cba", "cbc"] 。第 9 个字符串为 "cab"
// 示例 4：
// 输入：n = 2, k = 7
//输出：""
// 示例 5：
// 输入：n = 10, k = 100
//输出："abacbabacb"
// 提示：
// 1 <= n <= 10
// 1 <= k <= 100
// Related Topics 回溯算法
// 👍 18 👎 0

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 长度为 n 的开心字符串中字典序第 k 小的字符串
 *            https://leetcode-cn.com/problems/the-k-th-lexicographical-string-of-all-happy-strings-of-length-n/
 *             回溯
 */
public class AllHappyStrings {
    String[] arr={"a","b","c"};
    public String getHappyString(int n, int k) {
        if (n==0){
            return "";
        }
        List<String> result=new ArrayList<>();
        jumpAndDeal(n,result,"");
        if (result.size()>=k){
            return result.get(k-1);
        }
        return "";
    }

    private void jumpAndDeal(int n, List<String> result, String s) {
        if(s.length()>1&&s.substring(s.length()-1).equals(s.substring(s.length()-2,s.length()-1))){
            return;
        }
        if (s.length()==n){
            result.add(s);
        }else {
            for (int i = 0; i < 3; i++) {
                s+=arr[i];
                jumpAndDeal(n,result,s);
                if(s.length()>1){
                    s=s.substring(0,s.length()-1);
                }else{
                    s="";
                }
            }
        }
    }

    public static void main(String[] args) {
        AllHappyStrings s =new AllHappyStrings();
        s.getHappyString(1,4);
    }
}
