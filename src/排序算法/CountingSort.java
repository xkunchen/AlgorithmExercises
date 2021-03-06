package 排序算法;

/**
 * @Author: xkunchen
 * @Description:
 * @Data: 2021/4/13
 **/

import java.util.Arrays;

/**
 * 计数排序
 */
public class CountingSort {
    public int[] sort(int[] sourceArray)  {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        int maxValue = getMaxValue(arr);
        return countingSort(arr, maxValue);
    }
    private int[] countingSort(int[] arr, int maxValue) {
        int bucketLen = maxValue + 1;
        int[] bucket = new int[bucketLen];
        for (int value : arr) {
            bucket[value]++;
        }
        int sortedIndex = 0;
        for (int j = 0; j < bucketLen; j++) {
            while (bucket[j] > 0) {
                arr[sortedIndex++] = j;
                bucket[j]--;
            }
        }
        return arr;
    }
    private int getMaxValue(int[] arr) {
        int maxValue = arr[0];
        for (int value : arr) {
            if (maxValue < value) {
                maxValue = value;
            }
        }
        return maxValue;
    }
    public static void main(String[] args) {
        CountingSort q=new CountingSort();
        int[] arr=new int[]{10,9,8,6,2,4,5,8,11,3,5};
        q.sort(arr);
    }
}
