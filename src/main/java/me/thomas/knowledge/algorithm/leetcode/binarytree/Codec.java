package me.thomas.knowledge.algorithm.leetcode.binarytree;

import me.thomas.knowledge.algorithm.leetcode.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * 297. 二叉树的序列化与反序列化 Hard
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 * <p>
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 * <p>
 * 提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution297.java, v 0.1 2022/7/10 19:27 xinsheng2.zhao Exp $
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder stringBuilder = new StringBuilder();
        serialize(root, stringBuilder);
        return stringBuilder.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        LinkedList<String> linkedList = Arrays.stream(data.split(",")).collect(Collectors.toCollection(LinkedList::new));
        return deserialize(linkedList);
    }

    /**
     * 递归前序遍历序列化
     */
    private void serialize(TreeNode node, StringBuilder stringBuilder) {
        if (node == null) {
            stringBuilder.append("#");
            stringBuilder.append(",");
            return;
        }
        stringBuilder.append(node.val);
        stringBuilder.append(",");
        serialize(node.left, stringBuilder);
        serialize(node.right, stringBuilder);
    }

    /**
     * 递归反序列化二叉树
     */
    private TreeNode deserialize(LinkedList<String> linkedList) {
        if (linkedList.isEmpty()) {
            return null;
        }
        String nodeVal = linkedList.pollFirst();
        if ("#".equals(nodeVal)) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(nodeVal));
        root.left = deserialize(linkedList);
        root.right = deserialize(linkedList);
        return root;
    }

    public static void main(String[] args) {
        Codec codec = new Codec();
        System.out.println(codec.serialize(codec.deserialize("1,2,#,4,#,#,3,#,#,")));
    }

}
