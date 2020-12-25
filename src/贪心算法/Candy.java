package 贪心算法;
//老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。
// 你需要按照以下要求，帮助老师给这些孩子分发糖果：
// 每个孩子至少分配到 1 个糖果。
// 评分更高的孩子必须比他两侧的邻位孩子获得更多的糖果。
// 那么这样下来，老师至少需要准备多少颗糖果呢？
// 示例 1：
//输入：[1,0,2]
//输出：5
//解释：你可以分别给这三个孩子分发 2、1、2 颗糖果。
// 示例 2：
//输入：[1,2,2]
//输出：4
//解释：你可以分别给这三个孩子分发 1、2、1 颗糖果。
//     第三个孩子只得到 1 颗糖果，这已满足上述两个条件。
// Related Topics 贪心算法
// 👍 428 👎 0
public class Candy {
    /**
     * 分发糖果
     *          https://leetcode-cn.com/problems/candy/
     *          贪心算法
     */
    public int candy(int[] ratings) {
        int count=0;
        if (ratings.length==1){
            return 1;
        }
        if (ratings.length==0){
            return 0;
        }
        //记录从前往后分发糖果的记录
        int[] arr=new int[ratings.length];
        //遍历数组，从前往后遍历，如果比前一个大的话，增加一颗糖果
        arr[0]=1;
        for (int i = 1; i < ratings.length; i++) {
            arr[i]=ratings[i]>ratings[i-1]?arr[i-1]+1:1;
        }
        count+=arr[ratings.length-1];
        for (int i = ratings.length-2; i >0 ; i--) {
           arr[i]=Math.max(arr[i],ratings[i]>ratings[i+1]?arr[i+1]+1:1);
           count+=arr[i];
        }
        count+=Math.max(arr[0],ratings[0]>ratings[1]?arr[1]+1:1);
        return count;
    }

    public static void main(String[] args) {
        Candy c=new Candy();
        c.candy(new int[]{1,0,2});
    }
}
