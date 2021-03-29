package 字符串;
//给定字符串J 代表石头中宝石的类型，和字符串 S代表你拥有的石头。 S 中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。
// J 中的字母不重复，J 和 S中的所有字符都是字母。字母区分大小写，因此"a"和"A"是不同类型的石头。
// 示例 1:
// 输入: J = "aA", S = "aAAbbbb"
//输出: 3
// 示例 2:
// 输入: J = "z", S = "ZZ"
//输出: 0
// 注意:
// S 和 J 最多含有50个字母。
// J 中的字符不重复。
// Related Topics 哈希表
// 👍 634 👎 0

import java.util.HashMap;

/**
 * https://leetcode-cn.com/problems/jewels-and-stones/
 * 宝石与石头
 */
public class JewelsAndStones {
    public int numJewelsInStones(String jewels, String stones) {
        char[] jChars = jewels.toCharArray();
        char[] sChars = stones.toCharArray();
        HashMap<Object, Object> hash = new HashMap<>();
        for (int i = 0; i < jChars.length; i++) {
            hash.put(jChars[i],null);
        }
        int returnData=0;
        for (int i = 0; i < sChars.length; i++) {
            if (hash.containsKey(sChars[i])){
                returnData++;
            }
        }
        return  returnData;
    }
}
