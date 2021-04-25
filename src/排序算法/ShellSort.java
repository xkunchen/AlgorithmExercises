package 排序算法;

import sun.applet.Main;

import java.util.Arrays;

/**
 * @Author: xkunchen
 * @Description:
 * @Data: 2021/4/12
 **/

/**
 * 希尔排序
 */
public class ShellSort {
    public int[] sort(int[] sourceArray)  {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        int gap = 1;
        while (gap < arr.length/3) {
            gap = gap * 3 + 1;
        }
        while (gap > 0) {
            for (int i = gap; i < arr.length; i++) {
                int tmp = arr[i];
                int j = i - gap;
                //希尔排序对比插入排序最重要的一步
                while (j >= 0 && arr[j] > tmp) {
                    arr[j + gap] = arr[j];
                    j -= gap;
                }
                arr[j + gap] = tmp;
            }
            gap = (int) Math.floor(gap / 3);
        }
        return arr;
    }

    public static void main(String[] args)   {
        ShellSort s=new ShellSort();
        int[] arr=new int[]{5,1,2,6,8,4,3,6,5,3,2,10,11,9};
        s.sort(arr);
    }
}
