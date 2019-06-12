package Program1.Model;

/**
 * 单词串
 * 形式为(单词类别编码, 单词自身值)的二元组
 */
public class WordString {
	/**
	 * 单词类别编码
	 * 采用一符一码的形式储存
	 */
	private int categoryCode;

	/**
	 * 单词自身值
	 * 采用符号表的方式储存
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
