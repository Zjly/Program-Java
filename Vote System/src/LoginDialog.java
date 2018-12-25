import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class LoginDialog extends Dialog implements ActionListener {
    // 登录控件
    private JTextField textUserName = new JTextField(10);
    private JPasswordField textPassword = new JPasswordField(10);
    private JButton buttonLogin = new JButton("登录");

    // 面板控件
    private JPanel panelInputArea = new JPanel();
    private JPanel panelButton = new JPanel();

    LoginDialog(Frame owner, String title) {
        super(owner, title, true);
        setUp();
        pack();
        setVisible(true);
    }

    private void setUp() {
        setInputArea();
        setButton();

        setLayout(new BorderLayout());
        add("North", panelInputArea);
        add("South", panelButton);

        addWindowListener(new WindowCloser()); // 关闭按钮监听
    }

    // 登陆输入区设置
    private void setInputArea() {
        // 用户名区域设置
        JPanel panelUserName = new JPanel();
        JLabel labelUserName = new JLabel("用户名");
        panelUserName.setLayout(new FlowLayout());
        panelUserName.add(labelUserName);
        panelUserName.add(textUserName);

        // 密码区域设置
        JPanel panelPassword = new JPanel();
        JLabel labelPassword = new JLabel("密码");
        panelPassword.setLayout(new FlowLayout());
        panelPassword.add(labelPassword);
        panelPassword.add(textPassword);

        panelInputArea.setLayout(new GridLayout(2, 1));
        panelInputArea.add(panelUserName);
        panelInputArea.add(panelPassword);
    }

    // 按钮设置
    private void setButton() {
        panelButton.add(buttonLogin);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 登录
        if(e.getSource() == buttonLogin) {
            User user = VoteService.login(textUserName.getText(), String.valueOf(textPassword.getPassword()), MainInterface.users);
            if(MainInterface.currentUser != null) {
                MainInterface.currentUser = user;
                MainInterface.voteCount = VoteService.getVotes(user);
                dispose();
            }
        }
    }

    class WindowCloser extends WindowAdapter {
        // 关闭按钮
        public void windowClosing(WindowEvent we) {
            dispose();
        }
    }
}
