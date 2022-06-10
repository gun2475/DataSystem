package global;
import javax.swing.*;
import java.awt.*;
import java.util.Vector;
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
    Vector<Double> data2 = new Vector<>();
    String user_id;
    DB_Connect dbCon = new DB_Connect();
    JFreeChart chart;

    graph_dialog(String id){
        this.user_id = id;
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        chart = getChart();
        ChartFrame frame2 = new ChartFrame("Bmi Graph",chart);
        frame2.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame2.setVisible(true);
    }

    public JFreeChart getChart(){
        int cnt = 0;
        dbCon.connect();
        data2 = dbCon.get_date_bmi(user_id);

        DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();
        for(int i = 0; i < data2.size() / 3; i++){
            int month_i = data2.get(0 + cnt * 3).intValue();
            int day_i = data2.get(1 + cnt * 3).intValue();
            String month = Integer.toString(month_i) + "월 ";
            String day = Integer.toString(day_i) + "일";
            dataset1.addValue(data2.get(2 + cnt * 3),"bmi",month+day);
            cnt++;
        }

        DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
        dataset2.addValue(98.7, "목표칼로리 대비 달성률(%)", "6월8일");
        dataset2.addValue(100.7, "목표칼로리 대비 달성률(%)", "6월9일");
        dataset2.addValue(102.7, "목표칼로리 대비 달성률(%)", "6월10일");
        dataset2.addValue(103.7, "목표칼로리 대비 달성률(%)", "6월11일");
        dataset2.addValue(90.7, "목표칼로리 대비 달성률(%)", "6월12일");
        dataset2.addValue(87.7, "목표칼로리 대비 달성률(%)", "6월13일");



        final LineAndShapeRenderer renderer1 = new LineAndShapeRenderer();
        final LineAndShapeRenderer renderer2 = new LineAndShapeRenderer();
        //////////////////////////////////공통옵션//////////////////////////////////////////
        final CategoryItemLabelGenerator generator = new StandardCategoryItemLabelGenerator();
        final ItemLabelPosition p_center = new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER);
        final ItemLabelPosition p_below = new ItemLabelPosition( ItemLabelAnchor.OUTSIDE6, TextAnchor.TOP_LEFT);
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
        final CategoryPlot plot = new CategoryPlot(); // plot 생성
        plot.setDataset(1, dataset1);
        plot.setRenderer(1, renderer1);
        /////////////////////////////////////////plot에 데이터1 적재
        plot.setDataset(2, dataset2);
        plot.setRenderer(2, renderer2);

        ///////////////////////////////////plot 기본 설정
        plot.setOrientation(PlotOrientation.VERTICAL); // 그래프 표시 방향
        plot.setRangeGridlinesVisible(true); // X축 가이드 라인 표시여부
        plot.setDomainGridlinesVisible(true); // Y축 가이드 라인 표시여부

        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD); // 랜더링 순서 정의
        plot.setDomainAxis(new CategoryAxis()); // X축 종류 설정
        plot.getDomainAxis().setTickLabelFont(axisF); // X축 눈금라벨 폰트 조정
        plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.STANDARD); // 카테고리 라벨 위치 조정

        plot.setRangeAxis(new NumberAxis()); // Y축 종류 설정
        plot.getRangeAxis().setRange(10,150);
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
