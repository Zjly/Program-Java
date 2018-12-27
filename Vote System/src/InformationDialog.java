import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 学生详细信息
 */
class InformationDialog extends Dialog {
	private Student student; // 当前学生

	/**
	 * @param owner   主窗口
	 * @param student 当前学生
	 */
	InformationDialog(Frame owner, Student student) {
		super(owner, "详细信息", true);
		this.student = student;
		setUp();
		pack();
		setResizable(false);
		setVisible(true);
	}

	/**
	 * 控件摆放设置
	 */
	private void setUp() {
		// 面板设置
		JPanel panelPhoto = new JPanel();
		JPanel panelName = new JPanel();
		JPanel panelIntroduce = new JPanel();

		// 学生详细信息
		ImageIcon image = new ImageIcon(student.imagePath);
		JLabel labelImage = new JLabel(image);
		JLabel labelName = new JLabel(student.name);
		JTextArea textIntroduce = new JTextArea("       " + student.detailedInformation, 5, 25);
		textIntroduce.setLineWrap(true);

		labelName.setFont(new Font("", Font.BOLD, 20));
		textIntroduce.setFont(new Font("", Font.PLAIN, 15));
		textIntroduce.setEditable(false);

		// 摆放设置
		panelPhoto.add(labelImage);
		panelName.add(labelName);
		panelIntroduce.add(new JScrollPane(textIntroduce));

		setLayout(new BorderLayout());
		add("North", panelPhoto);
		add("Center", panelName);
		add("South", panelIntroduce);

		addWindowListener(new WindowCloser());
	}

	/**
	 * 关闭窗口
	 */
	class WindowCloser extends WindowAdapter {
		public void windowClosing(WindowEvent we) {
			dispose(); // 点击关闭时释放当前窗口
		}
	}
}
