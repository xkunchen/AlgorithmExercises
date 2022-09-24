package 剑指II;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/8/10 11:37<br/>
 *
 * @author xkunchen<br />
 */

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *    	[剑指 Offer 03]数组中重复的数字	67.9%	Easy	0.0%
 */
public class Offer003 {
    public int findRepeatNumber(int[] nums) {
        Set<Integer> containSet=new HashSet<>();
        for (int num:nums){
            if (containSet.contains(num)){
                return num;
            }
            containSet.add(num);
        }
        return -1;
    }
}
