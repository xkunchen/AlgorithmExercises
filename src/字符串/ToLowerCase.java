package 字符串;
//实现函数 ToLowerCase()，该函数接收一个字符串参数 str，并将该字符串中的大写字母转换成小写字母，之后返回新的字符串。
// 示例 1：
//输入: "Hello"
//输出: "hello"
// 示例 2：
//输入: "here"
//输出: "here"
// 示例 3：
//输入: "LOVELY"
//输出: "lovely"
// Related Topics 字符串
// 👍 145 👎 0
//转换成小写字母
public class ToLowerCase {
    public String toLowerCase(String str) {
        if (str.length()==0) return "";
        char[] chars = str.toCharArray();
        int length='A'-'a';
        for (int i = 0; i < chars.length; i++) {
            if ('A'<=chars[i]&&chars[i]<='Z'){
                chars[i]=(char) (chars[i]-length);
            }
        }
        return chars.toString();
    }
}
