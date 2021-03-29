package 字符串;

import java.util.HashMap;
import java.util.Map;

/**
 * 字符串中的第一个唯一字符
 * https://leetcode-cn.com/problems/first-unique-character-in-a-string/
 */
//给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
// 示例：
// s = "leetcode"
//返回 0
//s = "loveleetcode"
//返回 2
// 提示：你可以假定该字符串只包含小写字母。
// Related Topics 哈希表 字符串
// 👍 364 👎 0

/**
 * 总结：用到hash表进行统计
 */
public class FirstUniqueCharacterInString {
    public int firstUniqChar(String s) {
        Map<Character,Integer> map= new HashMap();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (map.get(chars[i])!=null){
                map.put(chars[i],map.get(chars[i])+1);
            }else {
                map.put(chars[i],1);
            }
        }
        int count=0;
        for (Map.Entry<Character,Integer> e:map.entrySet()){
            if (e.getValue()==1){
                count++;
            }
        }
        return count;
    }
}
