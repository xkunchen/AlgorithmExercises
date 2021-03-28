package 字符串;
//给定一个字符串 S，返回 “反转后的” 字符串，其中不是字母的字符都保留在原地，而所有字母的位置发生反转。
// 示例 1：
// 输入："ab-cd"
//输出："dc-ba"
// 示例 2：
// 输入："a-bC-dEf-ghIj"
//输出："j-Ih-gfE-dCba"
// 示例 3：
// 输入："Test1ng-Leet=code-Q!"
//输出："Qedo1ct-eeLg=ntse-T!"
// 提示：
// S.length <= 100
// 33 <= S[i].ASCIIcode <= 122
// S 中不包含 \ or "
// Related Topics 字符串
// 👍 77 👎 0
/**
 * https://leetcode-cn.com/problems/reverse-only-letters/
 * 仅仅反转字母
 */
public class ReverseOnlyLetters {
    public String reverseOnlyLetters(String S) {
        if (S.equals("")){
            return "";
        }
        char[] chars = S.toCharArray();
        int start=0;
        int end=S.length()-1;
        char temp=chars[start];
        while (start<end){
            while (start<end&&(chars[start]>'z'||(chars[start]<'a'&&chars[start]>'Z')||chars[start]<'A'))start++;
            while (start<end&&(chars[end]>'z'||(chars[end]<'a'&&chars[end]>'Z')||chars[end]<'A'))end--;
            if (start>=end) break;
            temp=chars[start];
            chars[start]=chars[end];
            chars[end]=temp;
            start++;
            end--;
        }
        return String.valueOf(chars);
    }

    public static void main(String[] args) {
        ReverseOnlyLetters r=new ReverseOnlyLetters();
        System.out.println(r.reverseOnlyLetters("7_28]"));;
    }
}
