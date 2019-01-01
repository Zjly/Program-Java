import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * �����ʾ����
 */
class ChartDialog extends Dialog {
	private ArrayList<Student> students;

	/**
	 * ���캯��
	 *
	 * @param owner    ������
	 * @param students ѧ���б�
	 */
	ChartDialog(Frame owner, ArrayList<Student> students) {
		super(owner, true);
		this.students = students;
		setUp();
		addWindowListener(new WindowCloser());
		pack();
		setVisible(true);
	}

	/**
	 * �ؼ��ڷ�����
	 */
	private void setUp() {
		CategoryDataset dataset = getDataSet();
		JFreeChart chart = ChartFactory.createBarChart3D(
				"ͶƱͳ��", // ͼ�����
				"ѧ��", // Ŀ¼�����ʾ��ǩ
				"��Ʊ��", // ��ֵ�����ʾ��ǩ
				dataset, // ���ݼ�
				PlotOrientation.VERTICAL, // ͼ����ˮƽ����ֱ
				false, // �Ƿ���ʾͼ��
				false, // �Ƿ����ɹ���
				false // �Ƿ�����URL����
		);

		CategoryPlot plot = chart.getCategoryPlot(); // ��ȡͼ���������
		CategoryAxis domainAxis = plot.getDomainAxis(); // ˮƽ�ײ��б�
		domainAxis.setLabelFont(new Font("����", Font.BOLD, 14)); // ˮƽ�ײ�����
		domainAxis.setTickLabelFont(new Font("����", Font.BOLD, 12)); // ��ֱ����
		ValueAxis rangeAxis = plot.getRangeAxis(); // ��ȡ��״
		rangeAxis.setLabelFont(new Font("����", Font.BOLD, 15));
		chart.getTitle().setFont(new Font("����", Font.BOLD, 20)); // ���ñ�������

		ChartPanel chartPanel = new ChartPanel(chart, true);
		add(chartPanel);
	}

	/**
	 * ��ȡ���ݼ�
	 *
	 * @return ���ݼ�
	 */
	private CategoryDataset getDataSet() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		// �������
		for(Student student : students) {
			dataset.setValue(student.voteCounts, "", student.name);
		}
		return dataset;
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