import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class MainInterface extends JFrame implements ActionListener {
    static ArrayList<Student> students = new ArrayList<>(); // 学生表
    private static int index = 0; // 当前学生
    static ArrayList<User> users = new ArrayList<>(); // 用户表
    static User currentUser = null; // 当前用户
    static int voteCount = 0;

    // 面板
    private JPanel panelUser = new JPanel();
    private JPanel panelPhoto = new JPanel();
    private JPanel panelInformation = new JPanel();
    private JPanel panelButton = new JPanel();

    // 菜单元素
    private JMenuBar menuBar = new JMenuBar();

    // 用户菜单
    private JMenu user = new JMenu("用户");
    private JMenuItem login = new JMenuItem("登录");
    private JMenuItem logoff = new JMenuItem("注销");

    // 统计菜单
    private JMenu statistics = new JMenu("统计");
    private JMenuItem pollStatistics = new JMenuItem("得票统计");
    private JMenuItem detailedInformation = new JMenuItem("详细信息");

    // 功能按钮
    private JButton buttonPrevious = new JButton();
    private JButton buttonNext = new JButton();
    private JButton buttonVote = new JButton("投票");
    private JButton buttonInformation = new JButton("详细信息");

    // 图片
    private JLabel labelImage = new JLabel();

    // 文字信息
    private JLabel labelName = new JLabel();
    private JLabel labelIntroduction = new JLabel();
    private JLabel labelVoteCounts = new JLabel();
    private JLabel labelUserName = new JLabel();
    private JLabel labelVoteTimes = new JLabel();

    MainInterface() {
        super("三好学生投票");
        setUp();
        pack();
        setResizable(false); // 固定窗口大小
        setVisible(true);
    }

    // 控件设置
    private void setUp() {
        setMenu();
        setPanel();
        VoteService.initStudentList(students); // 初始化学生列表
        VoteService.initUserList(users); // 初始化用户列表
        refreshSelectStudent(); // 设置第一个显示的学生
        addWindowListener(new WindowCloser()); // 关闭按钮监听
    }

    // 菜单设置
    private void setMenu() {
        menuBar.add(user);
        menuBar.add(statistics);

        // 用户菜单添加
        user.add(login);
        user.add(logoff);

        // 统计菜单添加
        statistics.add(pollStatistics);
        statistics.add(detailedInformation);

        // 监听按钮
        login.addActionListener(this);
        logoff.addActionListener(this);
        pollStatistics.addActionListener(this);
        detailedInformation.addActionListener(this);

        setJMenuBar(menuBar);
    }

    // 面板设置
    private void setPanel() {
        setPanelUser();
        setPanelPhoto();
        setPanelInformation();
        setPanelButton();

        // 布局设置
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add("Center", panelPhoto);
        panel.add("South", panelInformation);
        setLayout(new BorderLayout());
        this.add("North", panelUser);
        this.add("Center", panel);
        this.add("South", panelButton);
    }

    // 用户区域设置
    private void setPanelUser() {
        panelUser.setLayout(new FlowLayout());
        panelUser.add(labelUserName);
        panelUser.add(labelVoteTimes);
        refreshUser();
    }

    // 照片区域设置
    private void setPanelPhoto() {
        // 翻页按钮设置(按钮大小适应图片大小)
        ImageIcon left = new ImageIcon("files\\image\\left.png");
        ImageIcon right = new ImageIcon("files\\image\\right.png");
        // 设置按钮内图片
        buttonPrevious.setIcon(left);
        buttonNext.setIcon(right);
        buttonPrevious.setPreferredSize(new Dimension(left.getIconWidth(), left.getIconHeight()));
        buttonNext.setPreferredSize(new Dimension(right.getIconWidth(), left.getIconHeight()));

        // 布局设置
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setHgap(50); // 设置组件间距
        panelPhoto.setLayout(flowLayout);
        panelPhoto.add(buttonPrevious);
        panelPhoto.add(labelImage);
        panelPhoto.add(buttonNext);

        // 添加监听
        buttonPrevious.addActionListener(this);
        buttonNext.addActionListener(this);
    }

    // 简介设置
    private void setPanelInformation() {
        JPanel panelName = new JPanel();
        JPanel panelIntroduction = new JPanel();
        JPanel panelVoteCounts = new JPanel();
        panelName.add(labelName);
        panelIntroduction.add(labelIntroduction);
        panelVoteCounts.add(labelVoteCounts);

        // 字体设置
        labelName.setFont(new Font("", Font.BOLD, 25));
        labelIntroduction.setFont(new Font("", Font.PLAIN, 15));
        labelVoteCounts.setFont(new Font("", Font.BOLD, 18));

        // 布局设置
        panelInformation.setLayout(new GridLayout(3, 1));
        panelInformation.add(panelName);
        panelInformation.add(panelIntroduction);
        panelInformation.add(panelVoteCounts);
    }

    // 按钮设置
    private void setPanelButton() {
        // 取消焦点选中
        buttonVote.setFocusPainted(false);
        buttonInformation.setFocusPainted(false);

        // 布局设置
        panelButton.setLayout(new FlowLayout());
        panelButton.add(buttonVote);
        panelButton.add(buttonInformation);

        // 添加监听
        buttonVote.addActionListener(this);
        buttonInformation.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 前一个学生
        if(e.getSource() == buttonPrevious) {
            if(index != 0) {
                index--;
                refreshSelectStudent();
            }
        }
        // 后一个学生
        else if(e.getSource() == buttonNext) {
            if(index != students.size() - 1) {
                index++;
                refreshSelectStudent();
            }
        }
        // 投票
        else if(e.getSource() == buttonVote) {
            if(voteCount < 3) {
                VoteService.vote(currentUser, index);
                refreshSelectStudent();
                refreshUser();
            }
        }
        // 详细信息
        else if(e.getSource() == buttonInformation) {
            InformationDialog informationDialog = new InformationDialog(this, students.get(index));
        }
        // 登录
        else if(e.getSource() == login) {
            LoginDialog loginDialog = new LoginDialog(this, "登录");
        }
    }

    // 更新当前显示的学生
    private void refreshSelectStudent() {
        labelImage.setIcon(new ImageIcon(students.get(index).imagePath));
        labelName.setText(students.get(index).name);
        labelVoteCounts.setText(students.get(index).voteCounts + "票");
        labelIntroduction.setText(students.get(index).briefIntroduction);
    }

    // 更新当前用户
    private void refreshUser() {
        labelUserName.setText("用户名: " + currentUser);
        labelVoteTimes.setText("投票次数: " + voteCount + " / 3");
    }

    class WindowCloser extends WindowAdapter {
        // 关闭按钮
        public void windowClosing(WindowEvent we) {
            System.exit(0);
        }
    }
}
