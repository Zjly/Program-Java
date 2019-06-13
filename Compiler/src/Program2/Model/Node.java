package Program2.Model;

import java.util.ArrayList;

public class Node {
	/**
	 * 节点内容
	 */
	private String content;

	/**
	 * 父节点
	 */
	private Node parentNode;

	/**
	 * 子节点数组
	 */
	private ArrayList<Node> childNodes = new ArrayList<>();

	public Node(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 添加子节点
	 *
	 * @param node 子节点
	 */
	public void addChild(Node node) {
		childNodes.add(node);
	}

	/**
	 * 得到子节点
	 *
	 * @param index 子节点索引
	 * @return 子节点
	 */
	public Node getNode(int index) {
		return childNodes.get(index);
	}

	/**
	 * 求子节点个数
	 *
	 * @return 子节点个数
	 */
	public int getSize() {
		return childNodes.size();
	}

	public Node getParentNode() {
		return parentNode;
	}

	public void setParentNode(Node parentNode) {
		this.parentNode = parentNode;
	}
}
