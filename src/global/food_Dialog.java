package global;
import java.awt.*;
import javax.swing.*;
import Database.DB_Connect;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
public class food_Dialog extends JDialog {
    static JPanel food = new JPanel();
    private DB_Connect dbCon = new DB_Connect();
    float[] myfInfo = new float[4];
    private JButton fInfo_import = new JButton("불러오기");

    private JLabel noticela = new JLabel("음식 선택 후 불러오기를 눌러주세요");
    private JLabel calla = new JLabel("칼로리 : ");
    private JLabel carla = new JLabel("탄수화물 : ");
    private JLabel prola = new JLabel("단백질 : ");
    private JLabel fatla = new JLabel("지방 : ");
    final static int WINDOW_HEIGHT = 720;
    final static int WINDOW_WIDTH = 1280;
    Vector<String> vec = new Vector<String>();
    public food_Dialog()
    {
        Font font = new Font("Serif", Font.BOLD, 25);

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

        noticela.setBounds(50,70,210,20);
        noticela.setVisible(true);
        add(noticela);

        JComboBox<String> food_list = new JComboBox<String>();
        food_list.setBounds(50,50,120,20);
        food_list.setVisible(true);
        vec = dbCon.get_food();
        for(int i=0; i<vec.size(); i++)
        {
            food_list.addItem(vec.get(i));
        }
        food.add(food_list);


        calla.setBounds(50,100,300,35);
        calla.setVisible(true);
        calla.setFont(font);
        add(calla);
        carla.setBounds(50,150,300,35);
        carla.setVisible(true);
        carla.setFont(font);
        add(carla);
        prola.setBounds(50,200,300,35);
        prola.setVisible(true);
        prola.setFont(font);
        add(prola);
        fatla.setBounds(50,250,300,35);
        fatla.setVisible(true);
        fatla.setFont(font);
        add(fatla);


        fInfo_import.setBounds(170,50,100,20);
        fInfo_import.setVisible(true);
        fInfo_import.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myfInfo = dbCon.get_fInfo(food_list.getSelectedItem().toString());
                calla.setText("칼로리 : " + myfInfo[0] + "Kcal");
                carla.setText("탄수화물 : " + myfInfo[1] +"g");
                prola.setText("단백질 : " + myfInfo[2]+"g");
                fatla.setText("지방 : " + myfInfo[3]+"g");
            }
        });
        add(fInfo_import);
    }
}





