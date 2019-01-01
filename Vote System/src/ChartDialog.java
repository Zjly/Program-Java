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
 * 表格显示窗口
 */
class ChartDialog extends Dialog {
	private ArrayList<Student> students;

	/**
	 * 构造函数
	 *
	 * @param owner    父窗口
	 * @param students 学生列表
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
	 * 控件摆放设置
	 */
	private void setUp() {
		CategoryDataset dataset = getDataSet();
		JFreeChart chart = ChartFactory.createBarChart3D(
				"投票统计", // 图表标题
				"学生", // 目录轴的显示标签
				"得票数", // 数值轴的显示标签
				dataset, // 数据集
				PlotOrientation.VERTICAL, // 图表方向：水平、垂直
				false, // 是否显示图例
				false, // 是否生成工具
				false // 是否生成URL链接
		);

		CategoryPlot plot = chart.getCategoryPlot(); // 获取图表区域对象
		CategoryAxis domainAxis = plot.getDomainAxis(); // 水平底部列表
		domainAxis.setLabelFont(new Font("黑体", Font.BOLD, 14)); // 水平底部标题
		domainAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 12)); // 垂直标题
		ValueAxis rangeAxis = plot.getRangeAxis(); // 获取柱状
		rangeAxis.setLabelFont(new Font("黑体", Font.BOLD, 15));
		chart.getTitle().setFont(new Font("宋体", Font.BOLD, 20)); // 设置标题字体

		ChartPanel chartPanel = new ChartPanel(chart, true);
		add(chartPanel);
	}

	/**
	 * 获取数据集
	 *
	 * @return 数据集
	 */
	private CategoryDataset getDataSet() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		// 添加数据
		for(Student student : students) {
			dataset.setValue(student.voteCounts, "", student.name);
		}
		return dataset;
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