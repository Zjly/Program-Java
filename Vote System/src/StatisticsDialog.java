import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Vector;

/**
 * 统计信息对话框，用于显示学生票数的统计信息
 */
class StatisticsDialog extends Dialog {
	private ArrayList<Student> students = new ArrayList<>();
	private DefaultTableModel model = new DefaultTableModel(); // 默认表控制模型
	private JTable table = new JTable(model); // 表格对象
	private JScrollPane scrollPane = new JScrollPane(table); // 创建显示表格的滚动面板
	private Vector<Vector<String>> data = new Vector<>(); // 数据行向量集
	private Vector<String> columnName = new Vector<>(); // 列名向量
	private JLabel totalVotes = new JLabel(); // 投票总数

	/**
	 * 构造函数
	 *
	 * @param owner    父窗口
	 * @param title    标题
	 * @param students 学生列表
	 */
	StatisticsDialog(Frame owner, String title, ArrayList<Student> students) {
		super(owner, title, true);
		VoteService.copyStudentsList(students, this.students);
		setUp();
		addWindowListener(new WindowCloser());
		pack();
		setVisible(true);
	}

	/**
	 * 控件设置
	 */
	private void setUp() {
		setLayout(new BorderLayout());
		setTable();
		updateData();
		setText();
	}

	/**
	 * 表格设置
	 */
	private void setTable() {
		// 列名设置
		columnName.add("姓名");
		columnName.add("得票数");
		model.setDataVector(data, columnName); // 设置模型中的元素，它会自动显示在列表中

		// 设置表格格式
		table.setFont(new Font("", Font.PLAIN, 20));
		table.setRowHeight(30);
		table.getColumnModel().getColumn(0).setMaxWidth(200);
		table.getColumnModel().getColumn(1).setMaxWidth(200);

		add("North", scrollPane);
	}

	/**
	 * 设置显示文字
	 */
	private void setText() {
		totalVotes.setText("总投票数: " + VoteService.getTotalVotes(students));
		totalVotes.setFont(new Font("", Font.PLAIN, 20));
		add("South", totalVotes);
	}

	/**
	 * 更新数据
	 */
	private void updateData() {
		VoteService.sortByVoteCount(students); // 对学生得票数进行排序
		data.clear();
		for(Student student : students) {
			Vector<String> row = new Vector<>();
			row.add(student.name);
			row.add(String.valueOf(student.voteCounts));
			data.add(row);
		}
		table.updateUI();
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
