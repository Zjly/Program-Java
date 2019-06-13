package Program2.Model;

import java.util.ArrayList;

public class Node {
	/**
	 * �ڵ�����
	 */
	private String content;

	/**
	 * ���ڵ�
	 */
	private Node parentNode;

	/**
	 * �ӽڵ�����
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
	 * ����ӽڵ�
	 *
	 * @param node �ӽڵ�
	 */
	public void addChild(Node node) {
		childNodes.add(node);
	}

	/**
	 * �õ��ӽڵ�
	 *
	 * @param index �ӽڵ�����
	 * @return �ӽڵ�
	 */
	public Node getNode(int index) {
		return childNodes.get(index);
	}

	/**
	 * ���ӽڵ����
	 *
	 * @return �ӽڵ����
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
