package 树的前中后序遍历;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/8/17 11:13<br/>
 *
 * @author xkunchen<br />
 */

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/binary-tree-preorder-traversal/
 *    	[144]二叉树的前序遍历	71.2%	Easy	0.0%
 */
class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;
  TreeNode() {}
  TreeNode(int val) { this.val = val; }
  TreeNode(int val, TreeNode left, TreeNode right) {
   this.val = val;
   this.left = left;
   this.right = right;
  }
}
public class BinaryTreePreorderTraversal {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        preorder(root, res);
        return res;
    }

    public void preorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        preorder(root.left, res);
        preorder(root.right, res);
    }
}
