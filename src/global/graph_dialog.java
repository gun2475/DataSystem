package global;
import javax.swing.*;
import java.awt.*;
import javax.swing.*;
import Database.DB_Connect;
import org.jfree.chart.*;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.*;

public class graph_dialog {
    final static int WINDOW_HEIGHT = 600;
    final static int WINDOW_WIDTH = 840;
    String[] my_bmi = new String[2];
    JFreeChart chart = getChart();
    ChartFrame frame = new ChartFrame("Bar chart", chart);
    DB_Connect dbCon = new DB_Connect();
    graph_dialog(String id){
        dbCon.connect();
        System.out.println("굿1");
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setVisible(true);
        my_bmi = dbCon.get_date_bmi(id);
        System.out.println("굿2");
        System.out.println(my_bmi[0]);
        System.out.println(my_bmi[1]);
    }
    public JFreeChart getChart(){
        DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();
        dataset1.addValue(9.0, "T1", "1월");
        //dataset1.addValue(my_bmiF, "T1", my_bmi[0]);
        dataset1.addValue(7.0, "T1", "2월");
        dataset1.addValue(2.0, "T1", "3월");
        dataset1.addValue(6.0, "T1", "4월");
        dataset1.addValue(6.0, "T1", "5월");
        dataset1.addValue(9.0, "T1", "6월");
        dataset1.addValue(5.0, "T1", "7월");
        dataset1.addValue(4.0, "T1", "8월");
        dataset1.addValue(8.0, "T1", "9월");
        dataset1.addValue(8.0, "T1", "10월");
        dataset1.addValue(8.0, "T1", "11월");
        dataset1.addValue(8.0, "T1", "12월");
        final LineAndShapeRenderer renderer1 = new LineAndShapeRenderer();
        final CategoryItemLabelGenerator generator = new StandardCategoryItemLabelGenerator();
        final ItemLabelPosition p_center = new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER);
        final ItemLabelPosition p_below = new ItemLabelPosition( ItemLabelAnchor.OUTSIDE6, TextAnchor.TOP_LEFT);
        Font f = new Font("Gulim", Font.BOLD, 14);
        Font axisF = new Font("Gulim", Font.PLAIN, 14);
        renderer1.setBaseItemLabelGenerator(generator);
        renderer1.setBaseItemLabelsVisible(true);
        renderer1.setBaseShapesVisible(true);
        renderer1.setDrawOutlines(true);
        renderer1.setUseFillPaint(true);
        renderer1.setBaseFillPaint(Color.WHITE);
        renderer1.setBaseItemLabelFont(f);
        renderer1.setBasePositiveItemLabelPosition(p_below);
        renderer1.setSeriesPaint(0,new Color(219,121,22));
        renderer1.setSeriesStroke(0,new BasicStroke(2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 3.0f));

        final CategoryPlot plot = new CategoryPlot();
        plot.setDataset(dataset1);
        plot.setDataset(1, dataset1);
        plot.setRenderer(1, renderer1);

        plot.setOrientation(PlotOrientation.VERTICAL); // 그래프 표시 방향
        plot.setRangeGridlinesVisible(true); // X축 가이드 라인 표시여부
        plot.setDomainGridlinesVisible(true);

        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
        plot.setDomainAxis(new CategoryAxis()); // X축 종류 설정
        plot.getDomainAxis().setTickLabelFont(axisF); // X축 눈금라벨 폰트 조정
        plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.STANDARD); // 카테고리 라벨 위치 조정

        plot.setRangeAxis(new NumberAxis()); // Y축 종류 설정
        plot.getRangeAxis().setRange(0,50);
        plot.getRangeAxis().setTickLabelFont(axisF); // Y축 눈금라벨 폰트 조정

        // 세팅된 plot을 바탕으로 chart 생성
        final JFreeChart chart = new JFreeChart(plot);
        // chart.setTitle("Overlaid Bar Chart"); // 차트 타이틀
        // TextTitle copyright = new TextTitle("JFreeChart WaferMapPlot", new Font("SansSerif", Font.PLAIN, 9));
        // copyright.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        // chart.addSubtitle(copyright); // 차트 서브 타이틀
        return chart;
    }
}
