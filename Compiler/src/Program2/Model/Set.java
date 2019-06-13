package Program2.Model;

import java.util.ArrayList;

/**
 * ������
 */
public class Set {
	/**
	 * ��������
	 * ��ʾ����˭�ļ��ϣ�����FIRST(X)��name����ΪX
	 */
	private String name;

	/**
	 * ��������
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
	 * ��Ӽ�����
	 *
	 * @param set ����ӵļ�����
	 * @return �Ƿ������Ԫ��
	 */
	public boolean addSet(String set) {
		if(!sets.contains(set)) {
			sets.add(set);
			return true;
		}

		return false;
	}

	/**
	 * �򼯺��������һ������
	 *
	 * @param sets ����ӵ���һ������
	 * @return �Ƿ������Ԫ��
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
	 * ��������ɾȥ�����е�һ��Ԫ��
	 *
	 * @param content ��ɾ���ļ���Ԫ�ص�����
	 */
	public void removeSet(String content) {
		sets.remove(content);
	}

	/**
	 * �õ�ָ���������ļ�����
	 *
	 * @param index ����
	 * @return ָ���������ļ�����
	 */
	public String getSet(int index) {
		return sets.get(index);
	}

	/**
	 * �жϼ������Ƿ���ָ��������
	 *
	 * @param content ָ��������
	 * @return �Ƿ���ָ��������
	 */
	public boolean haveSet(String content) {
		return sets.contains(content);
	}

	/**
	 * �õ�ָ���ַ���������
	 *
	 * @param content ָ���ַ���
	 * @return ����
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
	 * �õ����ϵ�����
	 *
	 * @return ���ϵ�����
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
		return "����(" + name + ") = {" + stringBuilder.toString() + "}";
	}
}
