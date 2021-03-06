package Program3.Tools;

import Program2.Model.Node;
import Program3.Model.Quaternion;
import Program3.SemanticAnalysis;

/**
 * 语义分析工具类
 */
public class SemanticAnalysisTool {
	private static int nextQuad = 0;

	/**
	 * 根据语法树创建当前节点的表达式
	 *
	 * @param node 当前节点
	 */
	public static void productionCreate(Node node) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(node.getContent()).append("→");

		// 遍历直接子节点建立产生式右部
		for(int i = 0; i < node.getChildSize(); i++) {
			stringBuilder.append(node.getChildNode(i).getContent());
		}

		// 代码生成
		codeGeneration(node, stringBuilder.toString());
	}

	/**
	 * 对当前节点进行语义分析并生成四元式
	 *
	 * @param node 当前节点
	 * @param s    当前节点产生式
	 */
	private static void codeGeneration(Node node, String s) {
		Quaternion quaternion;

		// 通过不同的产生式进行不同的处理
		switch(s) {
			case "因式→(表达式)":
				// 因式节点得到表达式节点的值
				node.setData(node.getChildNode(1).getData());
				break;
			case "因式→标识符":
				// 因式节点得到标识符节点的值
				node.setData(node.getChildNode(0).getData());
				break;
			case "加减部分→ε":
			case "乘除部分→ε":
				// 节点值设为空
				node.setData("ε");
				break;
			case "表达式→项加减部分":
				// 如果加减部分为空，表达式值为项的值
				if(node.getChildNode(1).getData().equals("ε")) {
					node.setData(node.getChildNode(0).getData());
				}
				// 不为空则生成四元式
				else {
					quaternion = new Quaternion();
					quaternion.setOperator(node.getChildNode(1).getChildNode(0).getContent());
					quaternion.setOperand1(node.getChildNode(0).getData());
					quaternion.setOperand2(node.getChildNode(1).getData());
					quaternion.setResult("T" + ++nextQuad);
					node.setData("T" + nextQuad);
					SemanticAnalysis.quaternions.add(quaternion);
				}
				break;
			case "加减部分→+项加减部分":
			case "加减部分→-项加减部分":
				// 如果加减部分为空，表达式值为项的值
				if(node.getChildNode(2).getData().equals("ε")) {
					node.setData(node.getChildNode(1).getData());

				}
				// 不为空则采用右结合的方式生成四元式(连续加或连续减)
				else {
					quaternion = new Quaternion();
					quaternion.setOperator(node.getChildNode(2).getChildNode(0).getContent());
					quaternion.setOperand1(node.getChildNode(1).getData());
					quaternion.setOperand2(node.getChildNode(2).getData());
					quaternion.setResult("T" + ++nextQuad);
					node.setData("T" + nextQuad);
					SemanticAnalysis.quaternions.add(quaternion);
				}
				break;
			case "项→因式乘除部分":
				if(node.getChildNode(1).getData().equals("ε")) {
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
			case "乘除部分→*因式乘除部分":
			case "乘除部分→/因式乘除部分":
				// 如果乘除部分为空，表达式值为项的值
				if(node.getChildNode(2).getData().equals("ε")) {
					node.setData(node.getChildNode(1).getData());

				}
				// 不为空则采用右结合的方式生成四元式(连续乘或连续除)
				else {
					quaternion = new Quaternion();
					quaternion.setOperator(node.getChildNode(2).getChildNode(0).getContent());
					quaternion.setOperand1(node.getChildNode(1).getData());
					quaternion.setOperand2(node.getChildNode(2).getData());
					quaternion.setResult("T" + ++nextQuad);
					node.setData("T" + nextQuad);
					SemanticAnalysis.quaternions.add(quaternion);
				}
				break;
		}
	}
}
