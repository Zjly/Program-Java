package Program2.Tools;

import Program2.Model.Production;
import Program2.Model.ProductionPart;
import Program2.Model.ProductionUnit;
import Program2.Model.Set;

import java.util.ArrayList;

/**
 * 语法分析工具类
 */
public class GrammaticalAnalysisTool {
	/**
	 * 得到指定部分的FIRST(α)集
	 *
	 * @param productionArrayList 产生式数组
	 * @param specificPart        待计算FIRST集的部分，即α
	 * @return 该部分的FIRST集
	 */
	public static Set getFIRSTSet(ArrayList<Production> productionArrayList, ProductionPart specificPart) throws Exception {
		// 新建集合并初始化
		Set set = new Set();
		set.setName(specificPart.toString());

		// α = X∈(VN∪VT)
		if(specificPart.getUnitSize() == 1) {
			// 得到X
			ProductionUnit productionUnit = specificPart.getUnit(0);

			// X∈VT
			if(productionUnit.isTerminals()) {
				set.addSet(productionUnit.getUnitContent());
			}

			// X∈VN
			else {
				// 得到左部为X的产生式
				Production productionX = getProductionByContent(productionArrayList, productionUnit.getUnitContent());

				// 遍历产生式的子部分
				for(int i = 0; i < productionX.getRightPartSize(); i++) {
					ProductionPart productionPartX = productionX.getRightPart(i);

					// 相应产生式为X→a...，a∈VT
					if(productionPartX.getUnit(0).isTerminals()) {
						set.addSet(productionPartX.getUnit(0).getUnitContent());
					}

					// 相应产生式为X→Y...，Y∈VN
					else {
						Set firstSet = getFIRSTSet(productionArrayList, productionPartX);
						set.addSet(firstSet);
					}
				}
			}
		}

		// α∈(VN∪VT)*，α=X1X2...Xn
		else if(specificPart.getUnitSize() > 1) {
			// α = ε
			if(specificPart.getUnit(0).getUnitContent().equals("ε")) {
				set.addSet(specificPart.getUnit(0).getUnitContent());
			}

			// α != ε
			else {
				// α != ε，则FIRST(X1)-{ε}属于FIRST(α)；
				if(specificPart.getUnit(0).isTerminals()) {
					set.addSet(specificPart.getUnit(0).getUnitContent());
				} else {
					Production productionX1 = getProductionByContent(productionArrayList, specificPart.getUnit(0).getUnitContent());
					assert productionX1 != null;
					Set FIRSTSetX1 = getFIRSTSet(productionArrayList, productionX1.getLeftPart());
					FIRSTSetX1.removeSet("ε");
					set.addSet(FIRSTSetX1);

					// 如果有产生式α→X1X2...Xk，则FIRST(Xi)-{ε}属于FIRST(α)
					// 遍历X1至XK
					for(int j = 0; j < specificPart.getUnitSize(); j++) {
						// 得到XN
						ProductionUnit productionUnitXN = specificPart.getUnit(j);

						// 若其为终结符号则结束遍历
						if(productionUnitXN.isTerminals()) {
							break;
						}

						// 得到XN的产生式
						Production productionX = getProductionByContent(productionArrayList, productionUnitXN.getUnitContent());
						assert productionX != null;

						// XN能得到空则加入XN+1的FIRST集，此时X1-XN都可以推出空
						if(productionX.canGetEmpty() && j < specificPart.getUnitSize() - 1) {
							Set FIRSTSetYN1 = getFIRSTSet(productionArrayList, specificPart.getUnit(j + 1));
							FIRSTSetYN1.removeSet("ε");
							set.addSet(FIRSTSetYN1);
						}

						// 如若XN不能得到空则结束累计，退出遍历
						else {
							break;
						}

						// 此时X1-XN都可以推出空，则将空加入集合
						if(j == specificPart.getUnitSize() - 1) {
							set.addSet("ε");
						}
					}
				}
			}
		}

		return set;
	}

	/**
	 * 得到指定产生式单元的FIRST集
	 *
	 * @param productionArrayList 产生式数组
	 * @param specificUnit        指定产生式单元
	 * @return 该产生式单元的FIRST集
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
	 * 得到指定左部的产生式
	 *
	 * @param productionArrayList 产生式数组
	 * @param specificContent     指定左部内容
	 * @return 符合条件的产生式
	 * @throws Exception 找不到符合条件的产生式
	 */
	private static Production getProductionByContent(ArrayList<Production> productionArrayList, String specificContent) throws Exception {
		for(Production production : productionArrayList) {
			if(production.getLeftPart().getUnitContent().equals(specificContent)) {
				return production;
			}
		}

		throw new Exception("找不到左部为<" + specificContent + ">的产生式!");
	}

	/**
	 * 得到指定左部的FOLLOW集
	 *
	 * @param productionArrayList 产生式数组
	 * @param beginningUnit       开始符号
	 * @param specificUnit        指定左部单元
	 * @return 指定左部单元的FOLLOW集
	 */
	public static Set getFOLLOWSet(ArrayList<Production> productionArrayList, ProductionUnit beginningUnit, ProductionUnit specificUnit) throws Exception {
		Set set = new Set();
		set.setName(specificUnit.getUnitContent());

		// 左部单元为开始符号
		if(specificUnit.getUnitContent().equals(beginningUnit.getUnitContent())) {
			set.addSet("$");
		}

		// 右部包含给定产生式单元的所有产生式
		ArrayList<Production> productionContainingUnitArrayList = getProductionContainingUnit(productionArrayList, specificUnit);

		// 遍历这些产生式
		for(Production production : productionContainingUnitArrayList) {
			for(int i = 0; i < production.getRightPartSize(); i++) {
				ProductionPart productionPart = production.getRightPart(i);

				// 该部分若不包含指定单元则切换到下一部分
				if(!productionPart.haveUnit(specificUnit.getUnitContent())) {
					continue;
				}

				// 得到所有包含指定单元的产生式单元索引
				ArrayList<Integer> unitIndexArrayList = new ArrayList<>();
				for(int j = 0; j < productionPart.getUnitSize(); j++) {
					if(productionPart.getUnit(j).getUnitContent().equals(specificUnit.getUnitContent())) {
						unitIndexArrayList.add(j);
					}
				}

				// 将指定单元后续所有单元加入到一个产生式部分中并求FIRST集
				ProductionPart productionPartAfterUnit;
				for(int unitIndex : unitIndexArrayList) {
					productionPartAfterUnit = new ProductionPart();
					for(unitIndex = unitIndex + 1; unitIndex < productionPart.getUnitSize(); unitIndex++) {
						productionPartAfterUnit.addUnit(productionPart.getUnit(unitIndex));
					}

					// A→αBβ，则FIRST(β)中的非ε元素属于FOLLOW(B)
					Set FIRSTSetAfterUnit = getFIRSTSet(productionArrayList, productionPartAfterUnit);
					FIRSTSetAfterUnit.removeSet("ε");
					set.addSet(FIRSTSetAfterUnit);

					// A→αBβ而FIRST(β)含有ε，则FOLLOW(A)的元素属于FOLLOW(B)
					if(getFIRSTSet(productionArrayList, productionPartAfterUnit).haveSet("ε")) {
						if(!production.getLeftPart().getUnitContent().equals(specificUnit.getUnitContent())) {
							set.addSet(getFOLLOWSet(productionArrayList, beginningUnit, production.getLeftPart()));
						}
					}

					// A→αB，则FOLLOW(A)的元素属于FOLLOW(B)
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
	 * 得到右部包含给定产生式单元的所有产生式
	 *
	 * @param productionArrayList 产生式数组
	 * @param specificUnit        给定产生式单元
	 * @return 所有包含给定产生式单元的产生式数组
	 */
	private static ArrayList<Production> getProductionContainingUnit(ArrayList<Production> productionArrayList, ProductionUnit specificUnit) {
		ArrayList<Production> container = new ArrayList<>();

		for(Production production : productionArrayList) {
			for(int i = 0; i < production.getRightPartSize(); i++) {
				ProductionPart productionPart = production.getRightPart(i);

				// 判断该部分是否包含给定单元
				if(productionPart.haveUnit(specificUnit.getUnitContent())) {
					// 表达式是否加入过
					if(!container.contains(production)) {
						container.add(production);
					}
				}
			}
		}

		return container;
	}
}