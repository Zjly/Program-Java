package Program2.Model;

import java.util.ArrayList;

/**
 * 产生式类
 */
public class Production {
	/**
	 * 产生式左部
	 */
	private ProductionUnit leftPart;

	/**
	 * 产生式右部
	 * 形如<预处理语句>→import <包名>|package <包名>的右部就分为import <包名>和package <包名>子部分
	 */
	private ArrayList<ProductionPart> rightParts;

	/**
	 * 构造函数
	 * 初始化产生式右部数组
	 */
	public Production() {
		rightParts = new ArrayList<>();
	}

	public ProductionUnit getLeftPart() {
		return leftPart;
	}

	public void setLeftPart(ProductionUnit leftPart) {
		this.leftPart = leftPart;
	}

	/**
	 * 添加产生式右部部分
	 *
	 * @param productionPart 待添加的产生式右部部分
	 */
	public void addRightPart(ProductionPart productionPart) {
		rightParts.add(productionPart);
	}

	/**
	 * 得到指定索引处的产生式右部部分
	 *
	 * @param index 要获取的产生式右部部分的索引
	 * @return 该索引处的产生式右部部分
	 */
	public ProductionPart getRightPart(int index) {
		return rightParts.get(index);
	}

	/**
	 * 获取产生式右部部分数量
	 *
	 * @return 产生式右部数量
	 */
	public int getRightPartSize() {
		return rightParts.size();
	}

	/**
	 * 该产生式能否推出空
	 *
	 * @return 可以推出空返回true，无法推出空返回false
	 */
	public boolean canGetEmpty() {
		for(ProductionPart rightPart : rightParts) {
			if(rightPart.getUnit(0).getUnitContent().equals("ε")) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		for(int i = 0; i < rightParts.size(); i++) {
			stringBuilder.append(rightParts.get(i));
			if(i != rightParts.size() - 1) {
			    stringBuilder.append("|");
			}
		}

		return leftPart + "→" + stringBuilder.toString();
	}
}
