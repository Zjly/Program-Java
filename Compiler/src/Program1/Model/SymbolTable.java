package Program1.Model;

/**
 * 符号表
 * 存储标识符的名字和与标识符有关的信息
 */
public class SymbolTable {
	/**
	 * 标识符名字
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
