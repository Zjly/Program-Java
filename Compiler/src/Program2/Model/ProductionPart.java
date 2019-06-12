package Program2.Model;

import java.util.ArrayList;

/**
 * 产生式右部子部分
 */
public class ProductionPart {
	/**
	 * 产生式单元数组，每个数组项代表产生式右部的一部分
	 * 形如<程序>→<预处理语句><类>的右部数组就分割为预处理语句和类两个产生式单元
	 */
	private ArrayList<ProductionUnit> units;

	/**
	 * 构造函数
	 * 初始化单元数组
	 */
	public ProductionPart() {
		units = new ArrayList<>();
	}

	/**
	 * 添加产生式新单元
	 *
	 * @param productionUnit 待添加的产生式单元
	 */
	public void addUnit(ProductionUnit productionUnit) {
		units.add(productionUnit);
	}

	/**
	 * 得到指定索引处的产生式单元
	 *
	 * @param index 要获取的产生式单元索引
	 * @return 该索引处的产生式单元
	 */
	public ProductionUnit getUnit(int index) {
		return units.get(index);
	}

	/**
	 * 得到单元数组的大小
	 *
	 * @return 单元数组的大小
	 */
	public int getUnitSize() {
		return units.size();
	}

	/**
	 * 产生式是否包含某个内容为content的单元
	 *
	 * @param content 单元内容
	 * @return 是否包含该单元
	 */
	public boolean haveUnit(String content) {
		for(ProductionUnit productionUnit : units) {
			if(productionUnit.getUnitContent().equals(content)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		for(ProductionUnit unit : units) {
			stringBuilder.append(unit);
		}
		return stringBuilder.toString();
	}
}
