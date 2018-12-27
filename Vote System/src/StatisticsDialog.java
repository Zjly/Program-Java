import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Vector;

/**
 * ͳ����Ϣ�Ի���������ʾѧ��Ʊ����ͳ����Ϣ
 */
class StatisticsDialog extends Dialog {
	private ArrayList<Student> students = new ArrayList<>();
	private DefaultTableModel model = new DefaultTableModel(); // Ĭ�ϱ����ģ��
	private JTable table = new JTable(model); // ������
	private JScrollPane scrollPane = new JScrollPane(table); // ������ʾ���Ĺ������
	private Vector<Vector<String>> data = new Vector<>(); // ������������
	private Vector<String> columnName = new Vector<>(); // ��������
	private JLabel totalVotes = new JLabel(); // ͶƱ����

	/**
	 * ���캯��
	 *
	 * @param owner    ������
	 * @param title    ����
	 * @param students ѧ���б�
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
	 * �ؼ�����
	 */
	private void setUp() {
		setLayout(new BorderLayout());
		setTable();
		updateData();
		setText();
	}

	/**
	 * �������
	 */
	private void setTable() {
		// ��������
		columnName.add("����");
		columnName.add("��Ʊ��");
		model.setDataVector(data, columnName); // ����ģ���е�Ԫ�أ������Զ���ʾ���б���

		// ���ñ���ʽ
		table.setFont(new Font("", Font.PLAIN, 20));
		table.setRowHeight(30);
		table.getColumnModel().getColumn(0).setMaxWidth(200);
		table.getColumnModel().getColumn(1).setMaxWidth(200);

		add("North", scrollPane);
	}

	/**
	 * ������ʾ����
	 */
	private void setText() {
		totalVotes.setText("��ͶƱ��: " + VoteService.getTotalVotes(students));
		totalVotes.setFont(new Font("", Font.PLAIN, 20));
		add("South", totalVotes);
	}

	/**
	 * ��������
	 */
	private void updateData() {
		VoteService.sortByVoteCount(students); // ��ѧ����Ʊ����������
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
	 * �رմ���
	 */
	class WindowCloser extends WindowAdapter {
		public void windowClosing(WindowEvent we) {
			dispose();
		}
	}
}
