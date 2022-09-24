package 树的前中后序遍历;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/8/16 17:45<br/>
 *
 * @author xkunchen<br />
 */

import java.util.Stack;

/**
 *    	[剑指 Offer 33]二叉搜索树的后序遍历序列	56.4%	Medium	0.0%
 *    https://leetcode.cn/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/solution/mian-shi-ti-33-er-cha-sou-suo-shu-de-hou-xu-bian-6/
 */
public class Offer033 {
    public boolean verifyPostorder(int[] postorder) {
        return recur(postorder, 0, postorder.length - 1);
    }
    boolean recur(int[] postorder, int i, int j) {
        if(i >= j) {
            return true;
        }
        int p = i;
        while(postorder[p] < postorder[j]){
            p++;
        }
        int m = p;
        while(postorder[p] > postorder[j]){
            p++;
        }
        return p == j && recur(postorder, i, m - 1) && recur(postorder, m, j - 1);
    }


    public boolean verifyPostorder2(int[] postorder) {
        Stack<Integer> stack = new Stack<>();
        int root = Integer.MAX_VALUE;
        for(int i = postorder.length - 1; i >= 0; i--) {
            if(postorder[i] > root){
                return false;
            }
            while(!stack.isEmpty() && stack.peek() > postorder[i]){
                root = stack.pop();
            }
            stack.add(postorder[i]);
        }
        return true;
    }

    public static void main(String[] args) {
        Offer033 o=new Offer033();
        o.verifyPostorder2(new int[]{1,6,3,2,5});
    }

}
