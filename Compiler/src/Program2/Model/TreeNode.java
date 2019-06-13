package Program2.Model;

import java.util.ArrayList;

public class TreeNode {
	private String content;

	private ArrayList<TreeNode> childNodes = new ArrayList<>();

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 添加子节点
	 *
	 * @param treeNode 子节点
	 */
	private void addChild(TreeNode treeNode) {
		childNodes.add(treeNode);
	}

	/**
	 * 得到子节点
	 *
	 * @param index 子节点索引
	 * @return 子节点
	 */
	private TreeNode getNode(int index) {
		return childNodes.get(index);
	}
}
