import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class VoteService {
    // 从txt中加载学生信息，初始化系统
    static void initStudentList(ArrayList<Student> students) {
        BufferedReader reader;
        String line;
        // 读取文件
        try {
            reader = new BufferedReader(new FileReader("files\\students.txt"));
            while((line = reader.readLine()) != null) {
                students.add(initStudent(line));
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    // 初始化单个学生
    private static Student initStudent(String line) {
        Student student = new Student();
        String[] strings = line.split(" {4}");
        student.name = strings[0];
        student.imagePath = strings[1];
        student.voteCounts = Integer.parseInt(strings[2]);
        student.briefIntroduction = strings[3];
        student.detailedInformation = strings[4];
        return student;
    }

    // 从txt中加载用户信息，初始化系统
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

    // 初始化单个用户
    private static User initUser(String line) {
        User user = new User();
        String[] strings = line.split(" {4}");
        user.userName = strings[0];
        user.password = strings[1];
        user.voteCount = Integer.parseInt(strings[2]);
        user.voteResult = strings[3];
        return user;
    }

    // 登录判断
    static User login(String userName, String password, ArrayList<User> users) {
        for(User user : users) {
            if(userName.equals(user.userName) && password.equals(user.password)) {
                return user;
            }
        }
        return null;
    }

    // 投票
    static void vote(User user, int index) {
        if(user.voteResult.indexOf(index) == '0') {
            MainInterface.students.get(index).voteCounts++;

            // 更新用户中投票结果
            StringBuilder stringBuilder = new StringBuilder(user.voteResult);
            stringBuilder.setCharAt(index, '1');
            user.voteResult = stringBuilder.toString();
        }
    }

    // 得到当前用户已投票数目
    static int getVotes(User user) {
        int count = 0;
        for(int i = 0; i < 7; i++) {
            if(user.voteResult.indexOf(i) == '1') {
                count++;
            }
        }
        return count;
    }
}
