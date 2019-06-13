package Program2.Model;

import java.util.ArrayList;

/**
 * 集合类
 */
public class Set {
	/**
	 * 集合名称
	 * 表示这是谁的集合，例如FIRST(X)的name属性为X
	 */
	private String name;

	/**
	 * 集合内容
	 */
	private ArrayList<String> sets;

	public Set() {
		sets = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 添加集合项
	 *
	 * @param set 待添加的集合项
	 * @return 是否添加了元素
	 */
	public boolean addSet(String set) {
		if(!sets.contains(set)) {
			sets.add(set);
			return true;
		}

		return false;
	}

	/**
	 * 向集合中添加另一个集合
	 *
	 * @param sets 待添加的另一个集合
	 * @return 是否添加了元素
	 */
	public boolean addSet(Set sets) {
		boolean flag = false;
		for(int i = 0; i < sets.getSize(); i++) {
			if(!this.sets.contains(sets.getSet(i))) {
				this.sets.add(sets.getSet(i));
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * 根据内容删去集合中的一个元素
	 *
	 * @param content 待删除的集合元素的内容
	 */
	public void removeSet(String content) {
		sets.remove(content);
	}

	/**
	 * 得到指定索引处的集合项
	 *
	 * @param index 索引
	 * @return 指定索引处的集合项
	 */
	public String getSet(int index) {
		return sets.get(index);
	}

	/**
	 * 判断集合中是否有指定集合项
	 *
	 * @param content 指定的内容
	 * @return 是否有指定集合项
	 */
	public boolean haveSet(String content) {
		return sets.contains(content);
	}

	/**
	 * 得到指定字符串的索引
	 *
	 * @param content 指定字符串
	 * @return 索引
	 */
	public int getIndex(String content) {
		for(int i = 0; i < sets.size(); i++) {
			if(sets.get(i).equals(content)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 得到集合的容量
	 *
	 * @return 集合的容量
	 */
	public int getSize() {
		return sets.size();
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		for(int i = 0; i < sets.size(); i++) {
			stringBuilder.append(sets.get(i));
			if(i != sets.size() - 1) {
				stringBuilder.append(", ");
			}
		}
		return "集合(" + name + ") = {" + stringBuilder.toString() + "}";
	}
}
