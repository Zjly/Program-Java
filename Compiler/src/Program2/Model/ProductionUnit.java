package Program2.Model;

public class ProductionUnit {
	/**
	 * ��Ԫ����
	 */
	private String unitContent;

	/**
	 * �Ƿ����ս����
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
