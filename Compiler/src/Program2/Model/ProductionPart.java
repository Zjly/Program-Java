package Program2.Model;

import java.util.ArrayList;

/**
 * ����ʽ�Ҳ��Ӳ���
 */
public class ProductionPart {
	/**
	 * ����ʽ��Ԫ���飬ÿ��������������ʽ�Ҳ���һ����
	 * ����<����>��<Ԥ�������><��>���Ҳ�����ͷָ�ΪԤ������������������ʽ��Ԫ
	 */
	private ArrayList<ProductionUnit> units;

	/**
	 * ���캯��
	 * ��ʼ����Ԫ����
	 */
	public ProductionPart() {
		units = new ArrayList<>();
	}

	/**
	 * ��Ӳ���ʽ�µ�Ԫ
	 *
	 * @param productionUnit ����ӵĲ���ʽ��Ԫ
	 */
	public void addUnit(ProductionUnit productionUnit) {
		units.add(productionUnit);
	}

	/**
	 * �õ�ָ���������Ĳ���ʽ��Ԫ
	 *
	 * @param index Ҫ��ȡ�Ĳ���ʽ��Ԫ����
	 * @return ���������Ĳ���ʽ��Ԫ
	 */
	public ProductionUnit getUnit(int index) {
		return units.get(index);
	}

	/**
	 * �õ���Ԫ����Ĵ�С
	 *
	 * @return ��Ԫ����Ĵ�С
	 */
	public int getUnitSize() {
		return units.size();
	}

	/**
	 * ����ʽ�Ƿ����ĳ������Ϊcontent�ĵ�Ԫ
	 *
	 * @param content ��Ԫ����
	 * @return �Ƿ�����õ�Ԫ
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
