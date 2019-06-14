package Program2.Model;

import java.util.HashMap;

/**
 * ���ñ���
 */
public class CallTable {
	/**
	 * ����һ�ξ�ʹ��callTable[a, b] = true
	 */
	private boolean[][] callTable;
	private HashMap<String, Integer> hashMap = new HashMap<>();

	public CallTable(Set nonTerminalSymbolSet) {
		int size = nonTerminalSymbolSet.getSize();
		callTable = new boolean[size][size];

		for(int i = 0; i < nonTerminalSymbolSet.getSize(); i++) {
			hashMap.put(nonTerminalSymbolSet.getSet(i), i);
		}
	}

	/**
	 * ����һ��
	 *
	 * @param father ���ò���
	 * @param child �����ò���
	 */
	public void call(String father, String child) {
		int row = hashMap.get(father);
		int col = hashMap.get(child);

		callTable[row][col] = true;
	}

	/**
	 * �Ƿ��໥����
	 *
	 * @param a ����a
	 * @param b ����b
	 * @return �Ƿ��໥����
	 */
	public boolean isCallEach(String a, String b) {
		int row = hashMap.get(a);
		int col = hashMap.get(b);

		return callTable[row][col] && callTable[col][row];
	}
}
