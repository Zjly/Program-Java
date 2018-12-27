import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 登录界面
 */
class LoginDialog extends Dialog implements ActionListener {
	// 登录控件
	private JTextField textUserName = new JTextField(10);
	private JPasswordField textPassword = new JPasswordField(10);
	private JButton buttonLogin = new JButton("登录");

	// 面板控件
	private JPanel panelInputArea = new JPanel();
	private JPanel panelButton = new JPanel();

	// 父窗口
	private Frame owner;

	/**
	 * 构造函数
	 *
	 * @param owner 父窗口
	 * @param title 标题
	 */
	LoginDialog(Frame owner, String title) {
		super(owner, title, true);
		this.owner = owner;
		setUp();
		pack();
		setVisible(true);
	}

	/**
	 * 控件摆放设置
	 */
	private void setUp() {
		setInputArea();
		setButton();

		setLayout(new BorderLayout());
		add("North", panelInputArea);
		add("South", panelButton);

		addWindowListener(new WindowCloser()); // 关闭按钮监听
	}

	/**
	 * 登陆输入区设置
	 */
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

	/**
	 * 按钮设置
	 */
	private void setButton() {
		panelButton.add(buttonLogin);
		buttonLogin.addActionListener(this);
	}

	/**
	 * 按键响应事件处理
	 *
	 * @param e 触发的事件
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// 登录
		if(e.getSource() == buttonLogin) {
			User user = VoteService.login(textUserName.getText(), String.valueOf(textPassword.getPassword()), MainInterface.users);
			if(user != null) {
				MainInterface.currentUser = user;
				MainInterface.voteCount = VoteService.getVotes(user);
				dispose();
			} else {
				PromptDialog promptDialog = new PromptDialog(owner, "登录失败", "用户名或密码错误"); // 密码错误提示框
			}
		}
	}

	/**
	 * 关闭窗口
	 */
	class WindowCloser extends WindowAdapter {
		public void windowClosing(WindowEvent we) {
			dispose(); // 点击关闭按钮后释放当前窗口
		}
	}
}
