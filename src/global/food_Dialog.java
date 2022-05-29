package global;
import java.awt.*;
import javax.swing.*;
import Database.DB_Connect;
import java.util.Vector;
public class food_Dialog extends JDialog {
    static JPanel food = new JPanel();
    private DB_Connect dbCon = new DB_Connect();
    private String[] food_list = new String[100];
    final static int WINDOW_HEIGHT = 720;
    final static int WINDOW_WIDTH = 1280;
    Vector<String> vec = new Vector<String>();
    public food_Dialog()
    {
        dbCon.connect();
        setTitle("나만의 식단");
        setContentPane(food);

        food.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        food.setVisible(true);

        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - WINDOW_WIDTH) / 2,
                (windowSize.height - WINDOW_HEIGHT) / 2);
        setLayout(null);
        setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        setVisible(true);

        JComboBox<String> food_list = new JComboBox<String>();
        food_list.setBounds(50,50,100,20);
        food_list.setVisible(true);
        vec = dbCon.get_food();
        for(int i=0; i<vec.size(); i++)
        {
            System.out.println(vec.get(i));
            food_list.addItem(vec.get(i));
        }
        food.add(food_list);

    }
}





