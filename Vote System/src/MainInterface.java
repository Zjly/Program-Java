import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class MainInterface extends JFrame implements ActionListener {
    static ArrayList<Student> students = new ArrayList<>(); // ѧ����
    private static int index = 0; // ��ǰѧ��
    static ArrayList<User> users = new ArrayList<>(); // �û���
    static User currentUser = null; // ��ǰ�û�
    static int voteCount = 0;

    // ���
    private JPanel panelUser = new JPanel();
    private JPanel panelPhoto = new JPanel();
    private JPanel panelInformation = new JPanel();
    private JPanel panelButton = new JPanel();

    // �˵�Ԫ��
    private JMenuBar menuBar = new JMenuBar();

    // �û��˵�
    private JMenu user = new JMenu("�û�");
    private JMenuItem login = new JMenuItem("��¼");
    private JMenuItem logoff = new JMenuItem("ע��");

    // ͳ�Ʋ˵�
    private JMenu statistics = new JMenu("ͳ��");
    private JMenuItem pollStatistics = new JMenuItem("��Ʊͳ��");
    private JMenuItem detailedInformation = new JMenuItem("��ϸ��Ϣ");

    // ���ܰ�ť
    private JButton buttonPrevious = new JButton();
    private JButton buttonNext = new JButton();
    private JButton buttonVote = new JButton("ͶƱ");
    private JButton buttonInformation = new JButton("��ϸ��Ϣ");

    // ͼƬ
    private JLabel labelImage = new JLabel();

    // ������Ϣ
    private JLabel labelName = new JLabel();
    private JLabel labelIntroduction = new JLabel();
    private JLabel labelVoteCounts = new JLabel();
    private JLabel labelUserName = new JLabel();
    private JLabel labelVoteTimes = new JLabel();

    MainInterface() {
        super("����ѧ��ͶƱ");
        setUp();
        pack();
        setResizable(false); // �̶����ڴ�С
        setVisible(true);
    }

    // �ؼ�����
    private void setUp() {
        setMenu();
        setPanel();
        VoteService.initStudentList(students); // ��ʼ��ѧ���б�
        VoteService.initUserList(users); // ��ʼ���û��б�
        refreshSelectStudent(); // ���õ�һ����ʾ��ѧ��
        addWindowListener(new WindowCloser()); // �رհ�ť����
    }

    // �˵�����
    private void setMenu() {
        menuBar.add(user);
        menuBar.add(statistics);

        // �û��˵����
        user.add(login);
        user.add(logoff);

        // ͳ�Ʋ˵����
        statistics.add(pollStatistics);
        statistics.add(detailedInformation);

        // ������ť
        login.addActionListener(this);
        logoff.addActionListener(this);
        pollStatistics.addActionListener(this);
        detailedInformation.addActionListener(this);

        setJMenuBar(menuBar);
    }

    // �������
    private void setPanel() {
        setPanelUser();
        setPanelPhoto();
        setPanelInformation();
        setPanelButton();

        // ��������
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add("Center", panelPhoto);
        panel.add("South", panelInformation);
        setLayout(new BorderLayout());
        this.add("North", panelUser);
        this.add("Center", panel);
        this.add("South", panelButton);
    }

    // �û���������
    private void setPanelUser() {
        panelUser.setLayout(new FlowLayout());
        panelUser.add(labelUserName);
        panelUser.add(labelVoteTimes);
        refreshUser();
    }

    // ��Ƭ��������
    private void setPanelPhoto() {
        // ��ҳ��ť����(��ť��С��ӦͼƬ��С)
        ImageIcon left = new ImageIcon("files\\image\\left.png");
        ImageIcon right = new ImageIcon("files\\image\\right.png");
        // ���ð�ť��ͼƬ
        buttonPrevious.setIcon(left);
        buttonNext.setIcon(right);
        buttonPrevious.setPreferredSize(new Dimension(left.getIconWidth(), left.getIconHeight()));
        buttonNext.setPreferredSize(new Dimension(right.getIconWidth(), left.getIconHeight()));

        // ��������
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setHgap(50); // ����������
        panelPhoto.setLayout(flowLayout);
        panelPhoto.add(buttonPrevious);
        panelPhoto.add(labelImage);
        panelPhoto.add(buttonNext);

        // ��Ӽ���
        buttonPrevious.addActionListener(this);
        buttonNext.addActionListener(this);
    }

    // �������
    private void setPanelInformation() {
        JPanel panelName = new JPanel();
        JPanel panelIntroduction = new JPanel();
        JPanel panelVoteCounts = new JPanel();
        panelName.add(labelName);
        panelIntroduction.add(labelIntroduction);
        panelVoteCounts.add(labelVoteCounts);

        // ��������
        labelName.setFont(new Font("", Font.BOLD, 25));
        labelIntroduction.setFont(new Font("", Font.PLAIN, 15));
        labelVoteCounts.setFont(new Font("", Font.BOLD, 18));

        // ��������
        panelInformation.setLayout(new GridLayout(3, 1));
        panelInformation.add(panelName);
        panelInformation.add(panelIntroduction);
        panelInformation.add(panelVoteCounts);
    }

    // ��ť����
    private void setPanelButton() {
        // ȡ������ѡ��
        buttonVote.setFocusPainted(false);
        buttonInformation.setFocusPainted(false);

        // ��������
        panelButton.setLayout(new FlowLayout());
        panelButton.add(buttonVote);
        panelButton.add(buttonInformation);

        // ��Ӽ���
        buttonVote.addActionListener(this);
        buttonInformation.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // ǰһ��ѧ��
        if(e.getSource() == buttonPrevious) {
            if(index != 0) {
                index--;
                refreshSelectStudent();
            }
        }
        // ��һ��ѧ��
        else if(e.getSource() == buttonNext) {
            if(index != students.size() - 1) {
                index++;
                refreshSelectStudent();
            }
        }
        // ͶƱ
        else if(e.getSource() == buttonVote) {
            if(voteCount < 3) {
                VoteService.vote(currentUser, index);
                refreshSelectStudent();
                refreshUser();
            }
        }
        // ��ϸ��Ϣ
        else if(e.getSource() == buttonInformation) {
            InformationDialog informationDialog = new InformationDialog(this, students.get(index));
        }
        // ��¼
        else if(e.getSource() == login) {
            LoginDialog loginDialog = new LoginDialog(this, "��¼");
        }
    }

    // ���µ�ǰ��ʾ��ѧ��
    private void refreshSelectStudent() {
        labelImage.setIcon(new ImageIcon(students.get(index).imagePath));
        labelName.setText(students.get(index).name);
        labelVoteCounts.setText(students.get(index).voteCounts + "Ʊ");
        labelIntroduction.setText(students.get(index).briefIntroduction);
    }

    // ���µ�ǰ�û�
    private void refreshUser() {
        labelUserName.setText("�û���: " + currentUser);
        labelVoteTimes.setText("ͶƱ����: " + voteCount + " / 3");
    }

    class WindowCloser extends WindowAdapter {
        // �رհ�ť
        public void windowClosing(WindowEvent we) {
            System.exit(0);
        }
    }
}
