package Program1.Model;

/**
 * ���ʴ�
 * ��ʽΪ(����������, ��������ֵ)�Ķ�Ԫ��
 */
public class WordString {
	/**
	 * ����������
	 * ����һ��һ�����ʽ����
	 */
	private int categoryCode;

	/**
	 * ��������ֵ
	 * ���÷��ű�ķ�ʽ����
	 */
	private SymbolTable symbolTable;

	public int getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(int categoryCode) {
		this.categoryCode = categoryCode;
	}

	public SymbolTable getSymbolTable() {
		return symbolTable;
	}

	public void setSymbolTable(SymbolTable symbolTable) {
		this.symbolTable = symbolTable;
	}

	@Override
	public String toString() {
		return "[" + categoryCode + ", " + symbolTable + "]";
	}
}
