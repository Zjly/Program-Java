package Program1.Model;

/**
 * ���ű�
 * �洢��ʶ�������ֺ����ʶ���йص���Ϣ
 */
public class SymbolTable {
	/**
	 * ��ʶ������
	 */
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return content;
	}
}
