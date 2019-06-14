package Program3.Tools;

import Program3.Model.Quaternion;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * �ļ�����������
 */
public class FileOperationTool {
	/**
	 * ����Ԫʽд���ļ�
	 *
	 * @param filepath �ļ�·��
	 * @param quaternions ��Ԫʽ
	 */
	public static void writeQuaternionToFile(String filepath, ArrayList<Quaternion> quaternions) {
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filepath));

			for(Quaternion quaternion : quaternions) {
				bufferedWriter.write(quaternion + "\n");
			}

			bufferedWriter.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
