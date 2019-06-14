package Program3.Tools;

import Program2.Model.Node;
import Program3.Model.Quaternion;
import Program3.SemanticAnalysis;

/**
 * �������������
 */
public class SemanticAnalysisTool {
	private static int nextQuad = 0;

	/**
	 * �����﷨��������ǰ�ڵ�ı��ʽ
	 *
	 * @param node ��ǰ�ڵ�
	 */
	public static void productionCreate(Node node) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(node.getContent()).append("��");

		// ����ֱ���ӽڵ㽨������ʽ�Ҳ�
		for(int i = 0; i < node.getChildSize(); i++) {
			stringBuilder.append(node.getChildNode(i).getContent());
		}

		// ��������
		codeGeneration(node, stringBuilder.toString());
	}

	/**
	 * �Ե�ǰ�ڵ�������������������Ԫʽ
	 *
	 * @param node ��ǰ�ڵ�
	 * @param s ��ǰ�ڵ����ʽ
	 */
	private static void codeGeneration(Node node, String s) {
		Quaternion quaternion;

		// ͨ����ͬ�Ĳ���ʽ���в�ͬ�Ĵ���
		switch(s) {
			case "��ʽ����ʶ��":
				node.setData(node.getChildNode(0).getData());
				break;
			case "�����ʽ�˳�����":
				if(node.getChildNode(1).getData().equals("��")) {
					node.setData(node.getChildNode(0).getData());
				} else {
					quaternion = new Quaternion();
					quaternion.setOperator(node.getChildNode(1).getChildNode(0).getContent());
					quaternion.setOperand1(node.getChildNode(0).getData());
					quaternion.setOperand2(node.getChildNode(1).getData());
					quaternion.setResult("T" + ++nextQuad);
					node.setData("T" + nextQuad);
					SemanticAnalysis.quaternions.add(quaternion);
				}
				break;
			case "�˳����֡�*��ʽ�˳�����":
			case "�˳����֡�/��ʽ�˳�����":
				if(node.getChildNode(1).getContent().equals("��")) {
					node.setData("��");
				} else {
					node.setData(node.getChildNode(1).getData());
				}
				break;
			case "�Ӽ����֡�+��Ӽ�����":
			case "�Ӽ����֡�-��Ӽ�����":
				if(node.getChildNode(1).getContent().equals("��")) {
					node.setData("��");
				} else {
					node.setData(node.getChildNode(1).getData());
				}
				break;
			case "���ʽ����Ӽ�����":
				if(node.getChildNode(1).getData().equals("��")) {
					node.setData(node.getChildNode(0).getData());
				} else {
					quaternion = new Quaternion();
					quaternion.setOperator(node.getChildNode(1).getChildNode(0).getContent());
					quaternion.setOperand1(node.getChildNode(0).getData());
					quaternion.setOperand2(node.getChildNode(1).getData());
					quaternion.setResult("T" + ++nextQuad);
					node.setData("T" + nextQuad);
					SemanticAnalysis.quaternions.add(quaternion);
				}
				break;
			case "��ʽ��(���ʽ)":
				node.setData(node.getChildNode(1).getData());
				break;
			case "�Ӽ����֡���":
			case "�˳����֡���":
				node.setData("��");
		}
	}
}
