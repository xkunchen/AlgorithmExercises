package 剑指offer突击;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/4/13 17:32<br/>
 *
 * @author xkunchen<br />
 */
//给定两个 01 字符串 a 和 b ，请计算它们的和，并以二进制字符串的形式输出。
// 输入为 非空 字符串且只包含数字 1 和 0。
// 示例 1:
//输入: a = "11", b = "10"
//输出: "101"
// 示例 2:
//输入: a = "1010", b = "1011"
//输出: "10101"
// 提示：
// 每个字符串仅由字符 '0' 或 '1' 组成。
// 1 <= a.length, b.length <= 10^4
// 字符串如果不是 "0" ，就都不含前导零。
// 注意：本题与主站 67 题相同：https://leetcode-cn.com/problems/add-binary/
// Related Topics 位运算 数学 字符串 模拟 👍 29 👎 0


/**
 * https://leetcode-cn.com/problems/add-binary/
 * 二进制求和
 * [剑指 Offer II 002]二进制加法
 */
public class II002AddBinary {
    public String addBinary(String a, String b) {
        char[] aCharArray = a.toCharArray();
        char[] bCharArray = b.toCharArray();
        StringBuilder builder=new StringBuilder();
        char[] maxCharArray=a.length()>b.length()?aCharArray:bCharArray;
        char[] minCharArray=a.length()<=b.length()?aCharArray:bCharArray;
        int i = 0;
        int isScale=0;
        for (; i <maxCharArray.length; i++) {
            int addChar=(i < minCharArray.length?Integer.parseInt(minCharArray[minCharArray.length-1-i]+""):0)+isScale+maxCharArray[maxCharArray.length-1-i];
            if (addChar>'1'){
                builder.append((char)( addChar-2));
                isScale=1;
            }else {
                builder.append((char)( addChar));
                isScale=0;
            }
        }
        if (isScale>0){
            builder.append("1");
        }
        return builder.reverse().toString();
    }

    public String addBinary2(String a, String b) {
        return Integer.toBinaryString(
                Integer.parseInt(a, 2) + Integer.parseInt(b, 2)
        );
    }
    public String addBinary3(String a, String b) {
        StringBuffer ans = new StringBuffer();
        int n = Math.max(a.length(), b.length()), carry = 0;
        for (int i = 0; i < n; ++i) {
            carry += i < a.length() ? (a.charAt(a.length() - 1 - i) - '0') : 0;
            carry += i < b.length() ? (b.charAt(b.length() - 1 - i) - '0') : 0;
            ans.append((char) (carry % 2 + '0'));
            carry /= 2;
        }
        if (carry > 0) {
            ans.append('1');
        }
        ans.reverse();
        return ans.toString();
    }

    public static void main(String[] args) {
        II002AddBinary addBinary=new II002AddBinary();
        System.out.println(addBinary.addBinary3("1111","1111"));

    }
}
