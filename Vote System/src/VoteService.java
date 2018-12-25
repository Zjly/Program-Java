import java.io.*;
import java.util.ArrayList;

/**
 * 投票方法类，主要用于提供投票操作中所需的方法
 */
class VoteService {
	/**
	 * 从txt中加载学生信息，初始化学生的ArrayList
	 *
	 * @param students 用于存放学生信息
	 */
	static void initStudentList(ArrayList<Student> students) {
		BufferedReader reader;
		String line;
		// 读取文件 初始化学生信息
		try {
			reader = new BufferedReader(new FileReader("files\\students.txt"));
			while((line = reader.readLine()) != null) {
				students.add(initStudent(line));
			}
		} catch(IOException e) {
			e.printStackTrace();
		}

		// 读取文件 初始化学生得票
		int index = 0;
		try {
			reader = new BufferedReader(new FileReader("files\\studentVotes.txt"));
			while((line = reader.readLine()) != null) {
				students.get(index).voteCounts = Integer.parseInt(line);
				index++;
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 初始化单个学生的详细信息
	 *
	 * @param line 从文件中读取的行，存有学生的详细信息
	 * @return 初始化完成的一个学生
	 */
	private static Student initStudent(String line) {
		Student student = new Student();
		String[] strings = line.split(" {4}");
		student.name = strings[0];
		student.imagePath = strings[1];
		student.briefIntroduction = strings[2];
		student.detailedInformation = strings[3];
		return student;
	}

	/**
	 * 从txt中加载用户信息，初始化投票用户的ArrayList
	 *
	 * @param users 用于存放投票用户
	 */
	static void initUserList(ArrayList<User> users) {
		BufferedReader reader;
		String line;
		// 读取文件
		try {
			reader = new BufferedReader(new FileReader("files\\users.txt"));
			while((line = reader.readLine()) != null) {
				users.add(initUser(line));
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 初始化单个用户
	 *
	 * @param line 从文件中读取的行，存有用户的详细信息
	 * @return 初始化完成的一个用户
	 */
	private static User initUser(String line) {
		User user = new User();
		String[] strings = line.split(" {4}");
		user.userName = strings[0];
		user.password = strings[1];
		user.voteCount = Integer.parseInt(strings[2]);
		user.voteResult = strings[3];
		return user;
	}

	/**
	 * 登录判断
	 *
	 * @param userName 登录用户名
	 * @param password 用户密码
	 * @param users    已有用户
	 * @return 登录的用户
	 */
	static User login(String userName, String password, ArrayList<User> users) {
		for(User user : users) {
			if(userName.equals(user.userName) && password.equals(user.password)) {
				return user;
			}
		}
		return null;
	}

	/**
	 * 投票判断
	 *
	 * @param user  当前用户
	 * @param index 当前学生索引
	 */
	static void vote(User user, int index) {
		MainInterface.students.get(index).voteCounts++;
		user.voteCount++;

		// 更新用户中投票结果
		StringBuilder stringBuilder = new StringBuilder(user.voteResult);
		stringBuilder.setCharAt(index, '1');
		user.voteResult = stringBuilder.toString();
	}

	/**
	 * 得到当前用户已投票数目
	 *
	 * @param user 当前用户
	 * @return 已投票数目
	 */
	static int getVotes(User user) {
		int count = 0;
		for(int i = 0; i < 7; i++) {
			if(user.voteResult.charAt(i) == '1') {
				count++;
			}
		}
		return count;
	}

	/**
	 * 将投票数据写回文件
	 *
	 * @param students 学生列表
	 */
	static void writeStudentsBack(ArrayList<Student> students) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("files\\studentVotes.txt"));
			for(Student student : students) {
				writer.write(student.voteCounts + "\n");
			}
			writer.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将用户数据写回文件
	 *
	 * @param users 用户列表
	 */
	static void writeUsersBack(ArrayList<User> users) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("files\\users.txt"));
			for(User user : users) {
				String line = user.userName + "    " + user.password + "    " + user.voteCount + "    " + user.voteResult + "\n";
				writer.write(line);
			}
			writer.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
