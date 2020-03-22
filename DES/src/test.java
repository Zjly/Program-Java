public class test {
	public static void main(String[] args) {
		for(int a = 0; a < 100; a++) {
			for(int b = 0; b < 100; b++) {
				for(int c = 0; c < 100; c++) {
					for(int d = 0; d < 100; d++) {
						if((4 * a + 7 * c) % 31 == 1) {
							if((5 * a + 11 * c) % 31 == 0) {
								if((5 * b + 11 * d) % 31 == 1) {
									if((4 * b + 7 * d) % 31 == 0) {
										System.out.println(a + " " + b + " " + c + " " + d);
										return;
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
