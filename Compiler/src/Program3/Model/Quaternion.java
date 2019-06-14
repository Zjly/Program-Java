package Program3.Model;

/**
 * ��Ԫ��
 */
public class Quaternion {
	/**
	 * �����
	 */
	private String operator;

	/**
	 * ������1
	 */
	private String operand1;

	/**
	 * ������2
	 */
	private String operand2;

	/**
	 * ���
	 */
	private String result;

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getOperand1() {
		return operand1;
	}

	public void setOperand1(String operand1) {
		this.operand1 = operand1;
	}

	public String getOperand2() {
		return operand2;
	}

	public void setOperand2(String operand2) {
		this.operand2 = operand2;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "(" + operator + ", " + operand1 + ", " + operand2 + ", " + result + ")";
	}
}
