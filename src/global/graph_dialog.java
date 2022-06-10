package global;
import java.awt.*;
import java.util.Vector;
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
    Vector<Double> data1 = new Vector<>();
    Vector<String> data2 = new Vector<>();
    String user_id;
    DB_Connect dbCon = new DB_Connect();
    JFreeChart chart;
    graph_dialog(String id){
        this.user_id = id;
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        chart = getChart();
        ChartFrame frame = new ChartFrame("Bmi Graph",chart);
        frame.setLocation((windowSize.width - WINDOW_WIDTH) / 2, (windowSize.height - WINDOW_HEIGHT) / 2);
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setVisible(true);
    }
    public JFreeChart getChart(){
        int cnt1 = 0;
        int cnt2 = 0;
        dbCon.connect();
        data1 = dbCon.get_date_bmi(user_id);
        DefaultCategoryDataset dataset1 = new DefaultCategoryDataset(); // bmi
        for(int i = 0; i < data1.size() / 3; i++){
            int month_i = data1.get(0 + cnt1 * 3).intValue();
            int day_i = data1.get(1 + cnt1 * 3).intValue();
            String month = Integer.toString(month_i) + "월";
            String day = Integer.toString(day_i) + "일";
            dataset1.addValue(data1.get(2 + cnt1 * 3),"bmi",month+day);
            cnt1++;
        }
        data2 = dbCon.get_rate(user_id);
        DefaultCategoryDataset dataset2 = new DefaultCategoryDataset(); // targetDown
        DefaultCategoryDataset dataset3 = new DefaultCategoryDataset(); // targetUp
        for(int i = 0; i < data2.size() / 3; i++){
            String date = data2.get(2 + cnt2 * 3);
            double down_rate = Double.parseDouble(data2.get(1 + cnt2 * 3));
            dataset2.addValue(down_rate,"감량 목표 칼로리(%)",date);
            double up_rate = Double.parseDouble(data2.get(0 + cnt2 * 3));
            dataset3.addValue(up_rate,"증량 목표 칼로리(%)",date);
            cnt2++;
        }
        //여기서부터 63~114줄 반복문으로 해줘
        final LineAndShapeRenderer[] renderer = new LineAndShapeRenderer[3];
        //////////////////////////////////공통옵션//////////////////////////////////////////
        final CategoryItemLabelGenerator generator = new StandardCategoryItemLabelGenerator();
        final ItemLabelPosition p_below = new ItemLabelPosition( ItemLabelAnchor.OUTSIDE6, TextAnchor.TOP_LEFT);
        Font f = new Font("Gulim", Font.BOLD, 14);
        Font axisF = new Font("Gulim", Font.PLAIN, 14);
        //////////////////////////////////공통옵션//////////////////////////////////////////
        for(int i=0;i<3;i++){
            renderer[i] = new LineAndShapeRenderer();
            renderer[i].setBaseItemLabelGenerator(generator);
            renderer[i].setBaseItemLabelsVisible(true);
            renderer[i].setBaseShapesVisible(true);
            renderer[i].setDrawOutlines(true);
            renderer[i].setUseFillPaint(true);
            renderer[i].setBaseFillPaint(Color.WHITE);
            renderer[i].setBaseItemLabelFont(f);
            renderer[i].setBasePositiveItemLabelPosition(p_below);
            renderer[i].setSeriesStroke(0,new BasicStroke(2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 3.0f));
        }

        renderer[0].setSeriesPaint(0,new Color(219,121,22));
        renderer[1].setSeriesPaint(0,new Color(0,191,255));
        renderer[2].setSeriesPaint(0,new Color(60,179,113));

        final CategoryPlot plot = new CategoryPlot(); // plot 생성 및 데이터 적재
        plot.setDataset(1, dataset1);
        plot.setRenderer(1,renderer[0]);
        plot.setDataset(2, dataset2);
        plot.setRenderer(2,renderer[1]);
        plot.setDataset(3, dataset3);
        plot.setRenderer(3,renderer[2]);

        ///////////////////////////////////plot 기본 설정
        plot.setOrientation(PlotOrientation.VERTICAL); // 그래프 표시 방향
        plot.setRangeGridlinesVisible(true); // X축 가이드 라인 표시여부
        plot.setDomainGridlinesVisible(true); // Y축 가이드 라인 표시여부
        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD); // 랜더링 순서 정의
        plot.setDomainAxis(new CategoryAxis()); // X축 종류 설정
        plot.getDomainAxis().setTickLabelFont(axisF); // X축 눈금라벨 폰트 조정
        plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.STANDARD); // 카테고리 라벨 위치 조정
        plot.setRangeAxis(new NumberAxis()); // Y축 종류 설정
        plot.getRangeAxis().setAutoRange(true);
        plot.getRangeAxis().setTickLabelFont(axisF); // Y축 눈금라벨 폰트 조정
        // 세팅된 plot을 바탕으로 chart 생성
        final JFreeChart chart = new JFreeChart(plot);
        return chart;
    }
}