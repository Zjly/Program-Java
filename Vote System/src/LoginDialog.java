import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * ��¼����
 */
class LoginDialog extends Dialog implements ActionListener {
	// ��¼�ؼ�
	private JTextField textUserName = new JTextField(10);
	private JPasswordField textPassword = new JPasswordField(10);
	private JButton buttonLogin = new JButton("��¼");

	// ���ؼ�
	private JPanel panelInputArea = new JPanel();
	private JPanel panelButton = new JPanel();

	// ������
	private Frame owner;

	/**
	 * ���캯��
	 *
	 * @param owner ������
	 * @param title ����
	 */
	LoginDialog(Frame owner, String title) {
		super(owner, title, true);
		this.owner = owner;
		setUp();
		pack();
		setVisible(true);
	}

	/**
	 * �ؼ��ڷ�����
	 */
	private void setUp() {
		setInputArea();
		setButton();

		setLayout(new BorderLayout());
		add("North", panelInputArea);
		add("South", panelButton);

		addWindowListener(new WindowCloser()); // �رհ�ť����
	}

	/**
	 * ��½����������
	 */
	private void setInputArea() {
		// �û�����������
		JPanel panelUserName = new JPanel();
		JLabel labelUserName = new JLabel("�û���");
		panelUserName.setLayout(new FlowLayout());
		panelUserName.add(labelUserName);
		panelUserName.add(textUserName);

		// ������������
		JPanel panelPassword = new JPanel();
		JLabel labelPassword = new JLabel("����");
		panelPassword.setLayout(new FlowLayout());
		panelPassword.add(labelPassword);
		panelPassword.add(textPassword);

		panelInputArea.setLayout(new GridLayout(2, 1));
		panelInputArea.add(panelUserName);
		panelInputArea.add(panelPassword);
	}

	/**
	 * ��ť����
	 */
	private void setButton() {
		panelButton.add(buttonLogin);
		buttonLogin.addActionListener(this);
	}

	/**
	 * ������Ӧ�¼�����
	 *
	 * @param e �������¼�
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// ��¼
		if(e.getSource() == buttonLogin) {
			User user = VoteService.login(textUserName.getText(), String.valueOf(textPassword.getPassword()), MainInterface.users);
			if(user != null) {
				MainInterface.currentUser = user;
				MainInterface.voteCount = VoteService.getVotes(user);
				dispose();
			} else {
				PromptDialog promptDialog = new PromptDialog(owner, "��¼ʧ��", "�û������������"); // ���������ʾ��
			}
		}
	}

	/**
	 * �رմ���
	 */
	class WindowCloser extends WindowAdapter {
		public void windowClosing(WindowEvent we) {
			dispose(); // ����رհ�ť���ͷŵ�ǰ����
		}
	}
}
