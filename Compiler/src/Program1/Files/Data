package Program1.Files;

/**
 * 用来演示词法分析的模板类
 */
public class Template {
	public static void main(String[] args) {
		double[] array = {1.5, 2.3, 1.8, 3.6, 4.9, 2.6, 3.5, 5.0};

		System.out.print("原数组:");
		displayArray(array);

		bubbleSort(array);

		System.out.print("排序后数组:");
		displayArray(array);
	}

	/**
	 * 冒泡排序
	 *
	 * @param array 待排序的数组
	 */
	private static void bubbleSort(double[] array) {
		// 交换变量temp
		double temp;

		for(int i = 0; i < array.length - 1; i++) {
			// 指示变量
			boolean isSorted = true;

			for(int j = 0; j < array.length - 1 - i; j++) {
				// 从小到大排序
				if(array[j] > array[j + 1]) {
					// 交换两数
					temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
					isSorted = false;
				}
			}

			// 如果一次排序中没有发生交换，就代表数列已经有序
			if(isSorted) {
				return;
			}
		}
	}

	/**
	 * 输出数组
	 *
	 * @param array 待输出的数组
	 */
	private static void displayArray(double[] array) {
		for(double anArray : array) {
			System.out.print(anArray + " ");
		}
		System.out.println();
	}
}