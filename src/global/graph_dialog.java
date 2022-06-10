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
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setVisible(true);
    }

    public JFreeChart getChart(){
        int cnt1 = 0;
        int cnt2 = 0;
        dbCon.connect();
        data1 = dbCon.get_date_bmi(user_id);
        DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();
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
        final LineAndShapeRenderer renderer1 = new LineAndShapeRenderer();
        final LineAndShapeRenderer renderer2 = new LineAndShapeRenderer();
        final LineAndShapeRenderer renderer3 = new LineAndShapeRenderer();
        //////////////////////////////////공통옵션//////////////////////////////////////////
        final CategoryItemLabelGenerator generator = new StandardCategoryItemLabelGenerator(); // 안해도됨
        final ItemLabelPosition p_center = new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER); //안해도됨
        final ItemLabelPosition p_below = new ItemLabelPosition( ItemLabelAnchor.OUTSIDE6, TextAnchor.TOP_LEFT); // 안해도됨
        Font f = new Font("Gulim", Font.BOLD, 14);
        Font axisF = new Font("Gulim", Font.PLAIN, 14);
        //////////////////////////////////공통옵션//////////////////////////////////////////
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
        //////////////////////////////////////////////////////////////////////////////////
        renderer2.setBaseItemLabelGenerator(generator);
        renderer2.setBaseItemLabelsVisible(true);
        renderer2.setBaseShapesVisible(true);
        renderer2.setDrawOutlines(true);
        renderer2.setUseFillPaint(true);
        renderer2.setBaseFillPaint(Color.WHITE);
        renderer2.setBaseItemLabelFont(f);
        renderer2.setBasePositiveItemLabelPosition(p_below);
        renderer2.setSeriesPaint(0,new Color(0,191,255));
        renderer2.setSeriesStroke(0,new BasicStroke(2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 3.0f));
        ///////////////////////////////////////////////////////////////////////////////
        renderer3.setBaseItemLabelGenerator(generator);
        renderer3.setBaseItemLabelsVisible(true);
        renderer3.setBaseShapesVisible(true);
        renderer3.setDrawOutlines(true);
        renderer3.setUseFillPaint(true);
        renderer3.setBaseFillPaint(Color.WHITE);
        renderer3.setBaseItemLabelFont(f);
        renderer3.setBasePositiveItemLabelPosition(p_below);
        renderer3.setSeriesPaint(0,new Color(60,179,113));
        renderer3.setSeriesStroke(0,new BasicStroke(2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 3.0f));
        ///////////////////////////////////////////////////////////////////////////////
        final CategoryPlot plot = new CategoryPlot(); // plot 생성 및 데이터 적재
        plot.setDataset(1, dataset1);
        plot.setRenderer(1, renderer1);
        plot.setDataset(2, dataset2);
        plot.setRenderer(2, renderer2);
        plot.setDataset(3, dataset3);
        plot.setRenderer(3, renderer3);
        ///////////////////////////////////plot 기본 설정 여기까지
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