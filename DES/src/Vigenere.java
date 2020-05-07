public class Vigenere {
	public static void main(String[] args) {
		String plainText = "intrusion detection".replace(" ", "");
		String key = "menu";
		StringBuilder cipherText = new StringBuilder();

		for(int i = 0; i < plainText.length(); i++) {
			char cKey = key.charAt(i % key.length());
			int iKey = cKey - 97;

			char cPlainText = plainText.charAt(i);
			int iPlainText = cPlainText - 97;

			int index = (iPlainText + iKey) % 26 + 97;
			char c = (char)index;
			cipherText.append(c);
		}

		System.out.println(cipherText.toString());
	}
}
