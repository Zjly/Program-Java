package Program2.Model;

import java.util.ArrayList;

/**
 * ����ʽ��
 */
public class Production {
	/**
	 * ����ʽ��
	 */
	private ProductionUnit leftPart;

	/**
	 * ����ʽ�Ҳ�
	 * ����<Ԥ�������>��import <����>|package <����>���Ҳ��ͷ�Ϊimport <����>��package <����>�Ӳ���
	 */
	private ArrayList<ProductionPart> rightParts;

	/**
	 * ���캯��
	 * ��ʼ������ʽ�Ҳ�����
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
	 * ��Ӳ���ʽ�Ҳ�����
	 *
	 * @param productionPart ����ӵĲ���ʽ�Ҳ�����
	 */
	public void addRightPart(ProductionPart productionPart) {
		rightParts.add(productionPart);
	}

	/**
	 * �õ�ָ���������Ĳ���ʽ�Ҳ�����
	 *
	 * @param index Ҫ��ȡ�Ĳ���ʽ�Ҳ����ֵ�����
	 * @return ���������Ĳ���ʽ�Ҳ�����
	 */
	public ProductionPart getRightPart(int index) {
		return rightParts.get(index);
	}

	/**
	 * ��ȡ����ʽ�Ҳ���������
	 *
	 * @return ����ʽ�Ҳ�����
	 */
	public int getRightPartSize() {
		return rightParts.size();
	}

	/**
	 * �ò���ʽ�ܷ��Ƴ���
	 *
	 * @return �����Ƴ��շ���true���޷��Ƴ��շ���false
	 */
	public boolean canGetEmpty() {
		for(ProductionPart rightPart : rightParts) {
			if(rightPart.getUnit(0).getUnitContent().equals("��")) {
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

		return leftPart + "��" + stringBuilder.toString();
	}
}
