import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 提示框 用来显示一些信息 由一段文字和一个确认按钮构成
 */
class PromptDialog extends Dialog implements ActionListener {
	private JButton button = new JButton("确定");

	/**
	 * @param parent  父窗口
	 * @param title   标题
	 * @param context 提示内容
	 */
	PromptDialog(Frame parent, String title, String context) {
		super(parent, title, true);
		setUp(context);
		addWindowListener(new WindowCloser());
		pack();
		setVisible(true);
	}

	/**
	 * 控件摆放设置
	 *
	 * @param context 提示内容
	 */
	private void setUp(String context) {
		setLayout(new GridLayout(2, 1));

		// 文字区域
		JPanel panelLabel = new JPanel();
		panelLabel.setSize(300, 200);
		JLabel label = new JLabel(context, JLabel.CENTER);
		label.setFont(new Font("", Font.BOLD, 15));
		panelLabel.add(label);
		add(panelLabel);

		// 按钮区域
		JPanel panelButton = new JPanel();
		panelButton.setSize(300, 100);
		panelButton.add(button);
		button.setFocusPainted(false);
		button.addActionListener(this);
		add(panelButton);
	}

	/**
	 * 按键响应事件处理
	 *
	 * @param e 触发的事件
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button) {
			dispose();
		}
	}

	/**
	 * 关闭窗口
	 */
	class WindowCloser extends WindowAdapter {
		public void windowClosing(WindowEvent we) {
			dispose();
		}
	}
}