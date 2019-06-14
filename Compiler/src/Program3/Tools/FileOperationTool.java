package Program3.Tools;

import Program3.Model.Quaternion;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 文件操作工具类
 */
public class FileOperationTool {
	/**
	 * 将四元式写入文件
	 *
	 * @param filepath 文件路径
	 * @param quaternions 四元式
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
