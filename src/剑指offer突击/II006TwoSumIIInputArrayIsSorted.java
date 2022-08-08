package 剑指offer突击;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/4/22 10:30<br/>
 *
 * @author xkunchen<br />
 */

/**
 * https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/
 *    [剑指 Offer II 006]排序数组中两个数字之和
 */
public class II006TwoSumIIInputArrayIsSorted {
    public int[] twoSum(int[] numbers, int target) {
        int left=0;
        int right=numbers.length-1;
        int sum=0;
        while (left<right){
            sum=numbers[left]+numbers[right];
            if (sum>target){
                right--;
            }else if (sum<target){
                left++;
            }else {
                break;
            }
        }
        return new int[]{left,right};
    }

    public static void main(String[] args) {
        II006TwoSumIIInputArrayIsSorted i=new II006TwoSumIIInputArrayIsSorted();
        i.twoSum(new int[]{1,2,4,6,10},8);
    }
}
