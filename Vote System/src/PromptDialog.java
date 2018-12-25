import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * ��ʾ�� ������ʾһЩ��Ϣ ��һ�����ֺ�һ��ȷ�ϰ�ť����
 */
class PromptDialog extends Dialog implements ActionListener {
	private JButton button = new JButton("ȷ��");

	/**
	 * @param parent  ������
	 * @param title   ����
	 * @param context ��ʾ����
	 */
	PromptDialog(Frame parent, String title, String context) {
		super(parent, title, true);
		setUp(context);
		addWindowListener(new WindowCloser());
		pack();
		setVisible(true);
	}

	/**
	 * �ؼ��ڷ�����
	 *
	 * @param context ��ʾ����
	 */
	private void setUp(String context) {
		setLayout(new GridLayout(2, 1));

		// ��������
		JPanel panelLabel = new JPanel();
		panelLabel.setSize(300, 200);
		JLabel label = new JLabel(context, JLabel.CENTER);
		label.setFont(new Font("", Font.BOLD, 15));
		panelLabel.add(label);
		add(panelLabel);

		// ��ť����
		JPanel panelButton = new JPanel();
		panelButton.setSize(300, 100);
		panelButton.add(button);
		button.setFocusPainted(false);
		button.addActionListener(this);
		add(panelButton);
	}

	/**
	 * ������Ӧ�¼�����
	 *
	 * @param e �������¼�
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button) {
			dispose();
		}
	}

	/**
	 * �رմ���
	 */
	class WindowCloser extends WindowAdapter {
		public void windowClosing(WindowEvent we) {
			dispose();
		}
	}
}