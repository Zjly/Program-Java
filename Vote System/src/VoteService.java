import java.io.*;
import java.util.ArrayList;

/**
 * ͶƱ�����࣬��Ҫ�����ṩͶƱ����������ķ���
 */
class VoteService {
	/**
	 * ��txt�м���ѧ����Ϣ����ʼ��ѧ����ArrayList
	 *
	 * @param students ���ڴ��ѧ����Ϣ
	 */
	static void initStudentList(ArrayList<Student> students) {
		BufferedReader reader;
		String line;
		// ��ȡ�ļ� ��ʼ��ѧ����Ϣ
		try {
			reader = new BufferedReader(new FileReader("files\\students.txt"));
			while((line = reader.readLine()) != null) {
				students.add(initStudent(line));
			}
		} catch(IOException e) {
			e.printStackTrace();
		}

		// ��ȡ�ļ� ��ʼ��ѧ����Ʊ
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
	 * ��ʼ������ѧ������ϸ��Ϣ
	 *
	 * @param line ���ļ��ж�ȡ���У�����ѧ������ϸ��Ϣ
	 * @return ��ʼ����ɵ�һ��ѧ��
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
	 * ��txt�м����û���Ϣ����ʼ��ͶƱ�û���ArrayList
	 *
	 * @param users ���ڴ��ͶƱ�û�
	 */
	static void initUserList(ArrayList<User> users) {
		BufferedReader reader;
		String line;
		// ��ȡ�ļ�
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
	 * ��ʼ�������û�
	 *
	 * @param line ���ļ��ж�ȡ���У������û�����ϸ��Ϣ
	 * @return ��ʼ����ɵ�һ���û�
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
	 * ��¼�ж�
	 *
	 * @param userName ��¼�û���
	 * @param password �û�����
	 * @param users    �����û�
	 * @return ��¼���û�
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
	 * ͶƱ�ж�
	 *
	 * @param user  ��ǰ�û�
	 * @param index ��ǰѧ������
	 */
	static void vote(User user, int index) {
		MainInterface.students.get(index).voteCounts++;
		user.voteCount++;

		// �����û���ͶƱ���
		StringBuilder stringBuilder = new StringBuilder(user.voteResult);
		stringBuilder.setCharAt(index, '1');
		user.voteResult = stringBuilder.toString();
	}

	/**
	 * �õ���ǰ�û���ͶƱ��Ŀ
	 *
	 * @param user ��ǰ�û�
	 * @return ��ͶƱ��Ŀ
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
	 * ��ͶƱ����д���ļ�
	 *
	 * @param students ѧ���б�
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
	 * ���û�����д���ļ�
	 *
	 * @param users �û��б�
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

	/**
	 * ��studentsList1��Ԫ�������studentsList2��
	 *
	 * @param studentsList1 ԭ�б�
	 * @param studentsList2 ���б�
	 */
	static void copyStudentsList(ArrayList<Student> studentsList1, ArrayList<Student> studentsList2) {
		for(Student student : studentsList1) {
			Student newStudent = new Student();
			newStudent.name = student.name;
			newStudent.voteCounts = student.voteCounts;
			studentsList2.add(newStudent);
		}
	}

	/**
	 * ����Ʊ���Ӹߵ�������ѧ��
	 *
	 * @param students ѧ���б�
	 */
	static void sortByVoteCount(ArrayList<Student> students) {
		Student temp = new Student();
		for(int i = 0; i < students.size() - 1; i++) {
			for(int j = i + 1; j < students.size(); j++) {
				if(students.get(i).voteCounts < students.get(j).voteCounts) {
					copyStudent(students.get(i), temp);
					copyStudent(students.get(j), students.get(i));
					copyStudent(temp, students.get(j));
				}
			}
		}
	}

	/**
	 * ��student1���Ƶ�student2��
	 *
	 * @param student1 ԭѧ��
	 * @param student2 ��ѧ��
	 */
	private static void copyStudent(Student student1, Student student2) {
		student2.name = student1.name;
		student2.voteCounts = student1.voteCounts;
	}

	/**
	 * �õ�ͶƱ����
	 *
	 * @param students ѧ��˵���б�
	 * @return ͶƱ����
	 */
	static int getTotalVotes(ArrayList<Student> students) {
		int totalVotes = 0;
		for(Student student : students) {
			totalVotes += student.voteCounts;
		}
		return totalVotes;
	}
}
