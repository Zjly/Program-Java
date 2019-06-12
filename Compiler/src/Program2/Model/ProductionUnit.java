package Program2.Model;

public class ProductionUnit {
	/**
	 * 单元内容
	 */
	private String unitContent;

	/**
	 * 是否是终结符号
	 */
	private boolean isTerminals;

	public String getUnitContent() {
		return unitContent;
	}

	public void setUnitContent(String unitContent) {
		this.unitContent = unitContent;
	}

	public boolean isTerminals() {
		return isTerminals;
	}

	public void setTerminals(boolean terminals) {
		isTerminals = terminals;
	}

	@Override
	public String toString() {
		if(!isTerminals) {
		    return  "<" + unitContent + ">";
		}
		return unitContent;
	}
}
