package Program2.Tools;

import Program2.Model.Production;
import Program2.Model.ProductionPart;
import Program2.Model.ProductionUnit;
import Program2.Model.Set;

import java.util.ArrayList;

/**
 * �﷨����������
 */
public class GrammaticalAnalysisTool {
	/**
	 * �õ�ָ�����ֵ�FIRST(��)��
	 *
	 * @param productionArrayList ����ʽ����
	 * @param specificPart        ������FIRST���Ĳ��֣�����
	 * @return �ò��ֵ�FIRST��
	 */
	public static Set getFIRSTSet(ArrayList<Production> productionArrayList, ProductionPart specificPart) throws Exception {
		// �½����ϲ���ʼ��
		Set set = new Set();
		set.setName(specificPart.toString());

		// �� = X��(VN��VT)
		if(specificPart.getUnitSize() == 1) {
			// �õ�X
			ProductionUnit productionUnit = specificPart.getUnit(0);

			// X��VT
			if(productionUnit.isTerminals()) {
				set.addSet(productionUnit.getUnitContent());
			}

			// X��VN
			else {
				// �õ���ΪX�Ĳ���ʽ
				Production productionX = getProductionByContent(productionArrayList, productionUnit.getUnitContent());

				// ��������ʽ���Ӳ���
				for(int i = 0; i < productionX.getRightPartSize(); i++) {
					ProductionPart productionPartX = productionX.getRightPart(i);

					// ��Ӧ����ʽΪX��a...��a��VT
					if(productionPartX.getUnit(0).isTerminals()) {
						set.addSet(productionPartX.getUnit(0).getUnitContent());
					}

					// ��Ӧ����ʽΪX��Y...��Y��VN
					else {
						Set firstSet = getFIRSTSet(productionArrayList, productionPartX);
						set.addSet(firstSet);
					}
				}
			}
		}

		// ����(VN��VT)*����=X1X2...Xn
		else if(specificPart.getUnitSize() > 1) {
			// �� = ��
			if(specificPart.getUnit(0).getUnitContent().equals("��")) {
				set.addSet(specificPart.getUnit(0).getUnitContent());
			}

			// �� != ��
			else {
				// �� != �ţ���FIRST(X1)-{��}����FIRST(��)��
				if(specificPart.getUnit(0).isTerminals()) {
					set.addSet(specificPart.getUnit(0).getUnitContent());
				} else {
					Production productionX1 = getProductionByContent(productionArrayList, specificPart.getUnit(0).getUnitContent());
					assert productionX1 != null;
					Set FIRSTSetX1 = getFIRSTSet(productionArrayList, productionX1.getLeftPart());
					FIRSTSetX1.removeSet("��");
					set.addSet(FIRSTSetX1);

					// ����в���ʽ����X1X2...Xk����FIRST(Xi)-{��}����FIRST(��)
					// ����X1��XK
					for(int j = 0; j < specificPart.getUnitSize(); j++) {
						// �õ�XN
						ProductionUnit productionUnitXN = specificPart.getUnit(j);

						// ����Ϊ�ս�������������
						if(productionUnitXN.isTerminals()) {
							break;
						}

						// �õ�XN�Ĳ���ʽ
						Production productionX = getProductionByContent(productionArrayList, productionUnitXN.getUnitContent());
						assert productionX != null;

						// XN�ܵõ��������XN+1��FIRST������ʱX1-XN�������Ƴ���
						if(productionX.canGetEmpty() && j < specificPart.getUnitSize() - 1) {
							Set FIRSTSetYN1 = getFIRSTSet(productionArrayList, specificPart.getUnit(j + 1));
							FIRSTSetYN1.removeSet("��");
							set.addSet(FIRSTSetYN1);
						}

						// ����XN���ܵõ���������ۼƣ��˳�����
						else {
							break;
						}

						// ��ʱX1-XN�������Ƴ��գ��򽫿ռ��뼯��
						if(j == specificPart.getUnitSize() - 1) {
							set.addSet("��");
						}
					}
				}
			}
		}

		return set;
	}

	/**
	 * �õ�ָ������ʽ��Ԫ��FIRST��
	 *
	 * @param productionArrayList ����ʽ����
	 * @param specificUnit        ָ������ʽ��Ԫ
	 * @return �ò���ʽ��Ԫ��FIRST��
	 */
	private static Set getFIRSTSet(ArrayList<Production> productionArrayList, ProductionUnit specificUnit) throws Exception {
		Set set = new Set();
		if(specificUnit.isTerminals()) {
			set.addSet(specificUnit.getUnitContent());
		} else {
			Production production = getProductionByContent(productionArrayList, specificUnit.getUnitContent());

			for(int i = 0; i < production.getRightPartSize(); i++) {
				ProductionPart productionPart = production.getRightPart(i);
				set.addSet(getFIRSTSet(productionArrayList, productionPart));
			}
		}

		return set;
	}

	/**
	 * �õ�ָ���󲿵Ĳ���ʽ
	 *
	 * @param productionArrayList ����ʽ����
	 * @param specificContent     ָ��������
	 * @return ���������Ĳ���ʽ
	 * @throws Exception �Ҳ������������Ĳ���ʽ
	 */
	private static Production getProductionByContent(ArrayList<Production> productionArrayList, String specificContent) throws Exception {
		for(Production production : productionArrayList) {
			if(production.getLeftPart().getUnitContent().equals(specificContent)) {
				return production;
			}
		}

		throw new Exception("�Ҳ�����Ϊ<" + specificContent + ">�Ĳ���ʽ!");
	}

	/**
	 * �õ�ָ���󲿵�FOLLOW��
	 *
	 * @param productionArrayList ����ʽ����
	 * @param beginningUnit       ��ʼ����
	 * @param specificUnit        ָ���󲿵�Ԫ
	 * @return ָ���󲿵�Ԫ��FOLLOW��
	 */
	public static Set getFOLLOWSet(ArrayList<Production> productionArrayList, ProductionUnit beginningUnit, ProductionUnit specificUnit) throws Exception {
		Set set = new Set();
		set.setName(specificUnit.getUnitContent());

		// �󲿵�ԪΪ��ʼ����
		if(specificUnit.getUnitContent().equals(beginningUnit.getUnitContent())) {
			set.addSet("$");
		}

		// �Ҳ�������������ʽ��Ԫ�����в���ʽ
		ArrayList<Production> productionContainingUnitArrayList = getProductionContainingUnit(productionArrayList, specificUnit);

		// ������Щ����ʽ
		for(Production production : productionContainingUnitArrayList) {
			for(int i = 0; i < production.getRightPartSize(); i++) {
				ProductionPart productionPart = production.getRightPart(i);

				// �ò�����������ָ����Ԫ���л�����һ����
				if(!productionPart.haveUnit(specificUnit.getUnitContent())) {
					continue;
				}

				// �õ����а���ָ����Ԫ�Ĳ���ʽ��Ԫ����
				ArrayList<Integer> unitIndexArrayList = new ArrayList<>();
				for(int j = 0; j < productionPart.getUnitSize(); j++) {
					if(productionPart.getUnit(j).getUnitContent().equals(specificUnit.getUnitContent())) {
						unitIndexArrayList.add(j);
					}
				}

				// ��ָ����Ԫ�������е�Ԫ���뵽һ������ʽ�����в���FIRST��
				ProductionPart productionPartAfterUnit;
				for(int unitIndex : unitIndexArrayList) {
					productionPartAfterUnit = new ProductionPart();
					for(unitIndex = unitIndex + 1; unitIndex < productionPart.getUnitSize(); unitIndex++) {
						productionPartAfterUnit.addUnit(productionPart.getUnit(unitIndex));
					}

					// A����B�£���FIRST(��)�еķǦ�Ԫ������FOLLOW(B)
					Set FIRSTSetAfterUnit = getFIRSTSet(productionArrayList, productionPartAfterUnit);
					FIRSTSetAfterUnit.removeSet("��");
					set.addSet(FIRSTSetAfterUnit);

					// A����B�¶�FIRST(��)���Цţ���FOLLOW(A)��Ԫ������FOLLOW(B)
					if(getFIRSTSet(productionArrayList, productionPartAfterUnit).haveSet("��")) {
						if(!production.getLeftPart().getUnitContent().equals(specificUnit.getUnitContent())) {
							set.addSet(getFOLLOWSet(productionArrayList, beginningUnit, production.getLeftPart()));
						}
					}

					// A����B����FOLLOW(A)��Ԫ������FOLLOW(B)
					if(productionPartAfterUnit.getUnitSize() == 0) {
						if(!production.getLeftPart().getUnitContent().equals(specificUnit.getUnitContent())) {
							set.addSet(getFOLLOWSet(productionArrayList, beginningUnit, production.getLeftPart()));
						}
					}
				}
			}
		}

		return set;
	}

	/**
	 * �õ��Ҳ�������������ʽ��Ԫ�����в���ʽ
	 *
	 * @param productionArrayList ����ʽ����
	 * @param specificUnit        ��������ʽ��Ԫ
	 * @return ���а�����������ʽ��Ԫ�Ĳ���ʽ����
	 */
	private static ArrayList<Production> getProductionContainingUnit(ArrayList<Production> productionArrayList, ProductionUnit specificUnit) {
		ArrayList<Production> container = new ArrayList<>();

		for(Production production : productionArrayList) {
			for(int i = 0; i < production.getRightPartSize(); i++) {
				ProductionPart productionPart = production.getRightPart(i);

				// �жϸò����Ƿ����������Ԫ
				if(productionPart.haveUnit(specificUnit.getUnitContent())) {
					// ���ʽ�Ƿ�����
					if(!container.contains(production)) {
						container.add(production);
					}
				}
			}
		}

		return container;
	}
}