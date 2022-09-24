package 树的前中后序遍历;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/8/17 14:53<br/>
 *
 * @author xkunchen<br />
 */
public class BinaryTreePostorderTraversal {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        postorder(root, res);
        return res;
    }

    public void postorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        postorder(root.left, res);
        postorder(root.right, res);
        res.add(root.val);
    }
}
