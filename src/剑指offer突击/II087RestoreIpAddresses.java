package 剑指offer突击;

import java.util.ArrayList;
import java.util.List;

//给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能从 s 获得的 有效 IP 地址 。你可以按任何顺序返回答案。
// 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
// 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312"
//和 "192.168@1.1" 是 无效 IP 地址。
// 示例 1：
//输入：s = "25525511135"
//输出：["255.255.11.135","255.255.111.35"]
// 示例 2：
//输入：s = "0000"
//输出：["0.0.0.0"]
// 示例 3：
//输入：s = "1111"
//输出：["1.1.1.1"]
// 示例 4：
//输入：s = "010010"
//输出：["0.10.0.10","0.100.1.0"]
// 示例 5：
//输入：s = "10203040"
//输出：["10.20.30.40","102.0.30.40","10.203.0.40"]
// 提示：
// 0 <= s.length <= 3000
// s 仅由数字组成
// 注意：本题与主站 93 题相同：https://leetcode-cn.com/problems/restore-ip-addresses/
// Related Topics 字符串 回溯 👍 23 👎 0

/**
 * 回溯算法
 * 分析：每个整数位于 0 到 255 之间组成，且不能含有前导 0
 */

/**
 * https://leetcode-cn.com/problems/restore-ip-addresses/
 *    [剑指 Offer II 087]复原 IP
 */
public class II087RestoreIpAddresses {
    public List<String> restoreIpAddresses(String s) {
        List<String> result=new ArrayList<>();
        if (s.length()>12){
            return result;
        }
        dealData(s,result,0,"",0);
        return result;
    }

    private void dealData(String s, List<String> result, int strIndex, String currentStr,int currentIndex) {
        if (strIndex>4){
            return;
        }
        if (currentIndex==s.length()&&strIndex==4){
            result.add(currentStr.substring(0,currentStr.length()-1));
            return;
        }
        String currentStrSub ="";
        for (int i = currentIndex; i < s.length(); i++) {
            if (strIndex==3){
                i=s.length()-1;
            }
            currentStrSub=s.substring(currentIndex, i + 1);
            if (Integer.parseInt(currentStrSub)>255||(currentStrSub.length()>1&&currentStrSub.substring(0,1).equals("0"))){
                break;
            }
            dealData(s,result,strIndex+1,currentStr+currentStrSub+".",i+1);
        }
    }
    static final int SEG_COUNT = 4;
    List<String> ans = new ArrayList<String>();
    int[] segments = new int[SEG_COUNT];

    public List<String> restoreIpAddresses2(String s) {
        segments = new int[SEG_COUNT];
        dfs(s, 0, 0);
        return ans;
    }

    public void dfs(String s, int segId, int segStart) {
        // 如果找到了 4 段 IP 地址并且遍历完了字符串，那么就是一种答案
        if (segId == SEG_COUNT) {
            if (segStart == s.length()) {
                StringBuffer ipAddr = new StringBuffer();
                for (int i = 0; i < SEG_COUNT; ++i) {
                    ipAddr.append(segments[i]);
                    if (i != SEG_COUNT - 1) {
                        ipAddr.append('.');
                    }
                }
                ans.add(ipAddr.toString());
            }
            return;
        }

        // 如果还没有找到 4 段 IP 地址就已经遍历完了字符串，那么提前回溯
        if (segStart == s.length()) {
            return;
        }

        // 由于不能有前导零，如果当前数字为 0，那么这一段 IP 地址只能为 0
        if (s.charAt(segStart) == '0') {
            segments[segId] = 0;
            dfs(s, segId + 1, segStart + 1);
        }

        // 一般情况，枚举每一种可能性并递归
        int addr = 0;
        for (int segEnd = segStart; segEnd < s.length(); ++segEnd) {
            addr = addr * 10 + (s.charAt(segEnd) - '0');
            if (addr > 0 && addr <= 0xFF) {
                segments[segId] = addr;
                dfs(s, segId + 1, segEnd + 1);
            } else {
                break;
            }
        }
    }
//["255.255.11.135","255.255.111.35"]
    public static void main(String[] args) {
        II087RestoreIpAddresses i=new II087RestoreIpAddresses();
        i.restoreIpAddresses("0279245587303");
    }
}
