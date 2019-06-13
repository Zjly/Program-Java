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
	 * ����ӽڵ�
	 *
	 * @param treeNode �ӽڵ�
	 */
	private void addChild(TreeNode treeNode) {
		childNodes.add(treeNode);
	}

	/**
	 * �õ��ӽڵ�
	 *
	 * @param index �ӽڵ�����
	 * @return �ӽڵ�
	 */
	private TreeNode getNode(int index) {
		return childNodes.get(index);
	}
}
