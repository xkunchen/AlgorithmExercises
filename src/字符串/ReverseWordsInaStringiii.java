package 字符串;
//给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
// 示例：
// 输入："Let's take LeetCode contest"
//输出："s'teL ekat edoCteeL tsetnoc"
// 提示：
// 在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。
// Related Topics 字符串
// 👍 280 👎 0
/**
 * https://leetcode-cn.com/problems/reverse-words-in-a-string-iii/
 * 反转字符串中的单词 III
 */
//翻转加空格
public class ReverseWordsInaStringiii {
    public String reverseWords(String s) {
        StringBuffer ret = new StringBuffer();
        int length = s.length();
        int i = 0;
        while (i < length) {
            int start = i;
            while (i < length && s.charAt(i) != ' ') {
                i++;
            }
            for (int p = start; p < i; p++) {
                ret.append(s.charAt(start + i - 1 - p));
            }
            while (i < length && s.charAt(i) == ' ') {
                i++;
                ret.append(' ');
            }
        }
        return ret.toString();
    }

    public static void main(String[] args) {
        ReverseWordsInaStringiii r=new ReverseWordsInaStringiii();
        r.reverseWords("Let's take LeetCode contest");
    }
}
