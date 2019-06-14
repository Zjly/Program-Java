package Program2.Model;

import java.util.HashMap;

/**
 * 调用表类
 */
public class CallTable {
	/**
	 * 调用一次就使得callTable[a, b] = true
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
	 * 调用一次
	 *
	 * @param father 调用部分
	 * @param child 被调用部分
	 */
	public void call(String father, String child) {
		int row = hashMap.get(father);
		int col = hashMap.get(child);

		callTable[row][col] = true;
	}

	/**
	 * 是否相互调用
	 *
	 * @param a 程序a
	 * @param b 程序b
	 * @return 是否相互调用
	 */
	public boolean isCallEach(String a, String b) {
		int row = hashMap.get(a);
		int col = hashMap.get(b);

		return callTable[row][col] && callTable[col][row];
	}
}
