package 剑指offer;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/5/9 14:12<br/>
 *
 * @author xkunchen<br />
 */


/**
 * https://leetcode-cn.com/problems/valid-palindrome-ii/
 *   [剑指 Offer II 019]最多删除一个字符得到回文
 */
public class II019validPalindromeII {
    public boolean validPalindrome(String s) {
        Boolean isremove=false;
        return dealData(s,isremove);
    }

    private boolean dealData(String s, Boolean isremove) {
        int n = s.length();
        int left = 0, right = n - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                if (isremove){
                    return false;
                }else{
                    return dealData(s.substring(left+1,right+1),true)||dealData(s.substring(left,right),true);
                }
            }
            ++left;
            --right;
        }
        return true;
    }

    public boolean validPalindrome2(String s) {
        int low = 0, high = s.length() - 1;
        while (low < high) {
            char c1 = s.charAt(low), c2 = s.charAt(high);
            if (c1 == c2) {
                ++low;
                --high;
            } else {
                return validPalindrome(s, low, high - 1) || validPalindrome(s, low + 1, high);
            }
        }
        return true;
    }

    public boolean validPalindrome(String s, int low, int high) {
        for (int i = low, j = high; i < j; ++i, --j) {
            char c1 = s.charAt(i), c2 = s.charAt(j);
            if (c1 != c2) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        II019validPalindromeII i=new II019validPalindromeII();
        i.validPalindrome("amanaplanacanalpansama");
    }
}
