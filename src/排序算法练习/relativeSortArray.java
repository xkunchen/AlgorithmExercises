package 排序算法练习;
//给你两个数组，arr1 和 arr2，
// arr2 中的元素各不相同
// arr2 中的每个元素都出现在 arr1 中
// 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末
//尾。
// 示例：
//输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
//输出：[2,2,2,1,4,3,3,9,6,7,19]
// 提示：
// 1 <= arr1.length, arr2.length <= 1000
// 0 <= arr1[i], arr2[i] <= 1000
// arr2 中的元素 arr2[i] 各不相同
// arr2 中的每个元素 arr2[i] 都出现在 arr1 中
// Related Topics 排序 数组
// 👍 168 👎 0
/**
 * @Author: xkunchen
 * @Description:
 * @Data: 2021/4/26
 **/

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * https://leetcode-cn.com/problems/relative-sort-array/
 *  数组的相对排序
 *  一开始用hashMap，因为hashMap是无序的，所以后面不在arr2的值如果需要排序的话要不使用额外的存储空间，
 *  要不使用计数排序用数组存储
 *  自定义排序也很牛逼，可以参考一下
 */
public class relativeSortArray {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int upper = 0;
        for (int x : arr1) {
            upper = Math.max(upper, x);
        }
        int[] frequency = new int[upper + 1];
        for (int x : arr1) {
            ++frequency[x];
        }
        int[] ans = new int[arr1.length];
        int index = 0;
        for (int x : arr2) {
            for (int i = 0; i < frequency[x]; ++i) {
                ans[index++] = x;
            }
            frequency[x] = 0;
        }
        for (int x = 0; x <= upper; ++x) {
            for (int i = 0; i < frequency[x]; ++i) {
                ans[index++] = x;
            }
        }
        return ans;
    }
    public int[] relativeSortArray1(int[] arr1, int[] arr2) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        for(int num : arr1) list.add(num);
        for(int i = 0; i < arr2.length; i++) map.put(arr2[i], i);
        Collections.sort(list, (x, y) -> {
            if(map.containsKey(x) || map.containsKey(y)) return map.getOrDefault(x, 1001) - map.getOrDefault(y, 1001);
            return x - y;
        });
        for(int i = 0; i < arr1.length; i++) arr1[i] = list.get(i);
        return arr1;
    }
    public int[] relativeSortArray2(int[] arr1, int[] arr2) {
        Map<Integer,Integer> numPostions = IntStream.range(0,arr2.length).boxed().collect(Collectors.toMap(i->arr2[i], i->i));
        return  Arrays.stream(arr1).boxed().sorted((o1, o2) -> {
            int a1 = numPostions.getOrDefault(o1, 1001);
            int a2 = numPostions.getOrDefault(o2, 1001);
            if (a1 != 1001 || a2 != 1001) {
                return a1 - a2;
            }
            return o1 - o2;
        }).mapToInt(o->o).toArray();
    }
    public static void main(String[] args) {
        relativeSortArray r=new relativeSortArray();
        int[] arr1={2,3,1,3,2,4,6,7,9,2,19};
        int[] arr2={2,1,4,3,9,6};
        r.relativeSortArray1(arr1,arr2);
    }
}
