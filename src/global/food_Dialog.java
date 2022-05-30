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
    String User_if[] = new String[4];
    private JButton fInfo_import = new JButton("불러오기");

    private JButton fInfo_add = new JButton("식단 추가하기");
    private JButton User_kcal = new JButton("칼로리 계산하기");
    private JButton fInfo_dlete = new JButton("제거하기");
    private JButton fInfo_clear = new JButton("초기화하기");
    private JLabel noticela = new JLabel("음식 선택 후 불러오기를 눌러주세요");
    private JLabel calla = new JLabel("칼로리 : ");
    private JLabel carla = new JLabel("탄수화물 : ");
    private JLabel prola = new JLabel("단백질 : ");
    private JLabel fatla = new JLabel("지방 : ");

    private JLabel sumcalla = new JLabel("칼로리 : ");
    private JLabel sumcarla = new JLabel("탄수화물 : ");
    private JLabel sumprola = new JLabel("단백질 : ");
    private JLabel sumfatla = new JLabel("지방 : ");
    private float sumcal =0;
    private float sumcar =0;
    private float sumpro =0;
    private float sumfat =0;

    private JLabel base_metabolic = new JLabel("기초 대사량 : ");
    private JLabel maintenance_cal = new JLabel("유지 대사량 : ");
    private JLabel target_cal = new JLabel("목표 칼로리 : ");

    private float base =0;
    private float maintenance =0;
    private float target =0;

    private String fname = "";
    private int count = 0;
    final static int WINDOW_HEIGHT = 720;
    final static int WINDOW_WIDTH = 1280;
    Vector<String> vec = new Vector<String>();
    Vector<String> food_vec = new Vector<String>();
    public food_Dialog(String id) {
        Font font = new Font("Serif", Font.BOLD, 25);

        dbCon.connect();
        setTitle("나만의 식단");
        setContentPane(food);

        food.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        food.setVisible(true);

        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - WINDOW_WIDTH) / 2,
                (windowSize.height - WINDOW_HEIGHT) / 2);
        setLayout(null);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setVisible(true);

        noticela.setBounds(50, 70, 210, 20);
        noticela.setVisible(true);
        add(noticela);

        JComboBox<String> food_list = new JComboBox<String>();
        food_list.setBounds(50, 50, 120, 20);
        food_list.setVisible(true);
        vec = dbCon.get_food();
        for (int i = 0; i < vec.size(); i++) {
            food_list.addItem(vec.get(i));
        }
        food.add(food_list);


        calla.setBounds(50, 100, 300, 35);
        calla.setVisible(true);
        calla.setFont(font);
        add(calla);

        carla.setBounds(50, 150, 300, 35);
        carla.setVisible(true);
        carla.setFont(font);
        add(carla);

        prola.setBounds(50, 200, 300, 35);
        prola.setVisible(true);
        prola.setFont(font);
        add(prola);

        fatla.setBounds(50, 250, 300, 35);
        fatla.setVisible(true);
        fatla.setFont(font);
        add(fatla);


        fInfo_import.setBounds(170, 50, 100, 20);
        fInfo_import.setVisible(true);
        fInfo_import.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myfInfo = dbCon.get_fInfo(food_list.getSelectedItem().toString());
                calla.setText("칼로리 : " + myfInfo[0] + "Kcal");
                carla.setText("탄수화물 : " + myfInfo[1] + "g");
                prola.setText("단백질 : " + myfInfo[2] + "g");
                fatla.setText("지방 : " + myfInfo[3] + "g");
                fname = food_list.getSelectedItem().toString();
            }
        });
        add(fInfo_import);

        fInfo_add.setBounds(370, 50, 100, 20);
        fInfo_add.setVisible(true);

        JComboBox<String> food_addlist = new JComboBox<String>();
        food_addlist.setBounds(500, 50, 120, 20);
        food_addlist.setVisible(true);
        food.add(food_list);
        fInfo_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sumcal += myfInfo[0];
                sumcalla.setText("칼로리 : " + sumcal + "Kcal");

                sumcar += myfInfo[1];
                sumcarla.setText("탄수화물 : " + sumcar + "Kcal");

                sumpro += myfInfo[2];
                sumprola.setText("단백질 : " + sumpro + "Kcal");

                sumfat += myfInfo[3];
                sumfatla.setText("지방 : " + sumfat + "Kcal");
                fname = food_list.getSelectedItem().toString();
                food_addlist.addItem(fname);
            }
        });
        add(fInfo_add);
        add(food_addlist);

        fInfo_dlete.setBounds(370, 100, 100, 20);
        fInfo_dlete.setVisible(true);
        fInfo_dlete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i=0; i< food_addlist.getItemCount(); i++)
                {
                    if(fname.equals(food_addlist.getItemAt(i)))
                    {
                        sumcal -= myfInfo[0];
                        sumcalla.setText("칼로리 : " + sumcal + "Kcal");

                        sumcar -= myfInfo[1];
                        sumcarla.setText("탄수화물 : " + sumcar + "Kcal");

                        sumpro -= myfInfo[2];
                        sumprola.setText("단백질 : " + sumpro + "Kcal");

                        sumfat -= myfInfo[3];
                        sumfatla.setText("지방 : " + sumfat + "Kcal");
                        food_addlist.removeItem(fname);
                    }
                }

            }
        });
        add(fInfo_dlete);

        fInfo_clear.setBounds(370, 150, 100, 20);
        fInfo_clear.setVisible(true);
        fInfo_clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sumcal = 0;
                sumcalla.setText("칼로리 : " + sumcal + "Kcal");

                sumcar = 0;
                sumcarla.setText("탄수화물 : " + sumcar + "Kcal");

                sumpro = 0;
                sumprola.setText("단백질 : " + sumpro + "Kcal");

                sumfat = 0;
                sumfatla.setText("지방 : " + sumfat + "Kcal");
                food_addlist.removeAllItems();
            }
        });
        add(fInfo_clear);

        sumcalla.setBounds(500, 100, 300, 35);
        sumcalla.setVisible(true);
        sumcalla.setFont(font);
        add(sumcalla);

        sumcarla.setBounds(500, 150, 300, 35);
        sumcarla.setVisible(true);
        sumcarla.setFont(font);
        add(sumcarla);

        sumprola.setBounds(500, 200, 300, 35);
        sumprola.setVisible(true);
        sumprola.setFont(font);
        add(sumprola);

        sumfatla.setBounds(500, 250, 300, 35);
        sumfatla.setVisible(true);
        sumfatla.setFont(font);
        add(sumfatla);

        User_if = dbCon.getUser_info(id);

        User_kcal.setBounds(100, 500, 100, 20);
        User_kcal.setVisible(true);//계산하기 함수
        User_kcal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               /* user_info[0] = rs.getString("weight");
                user_info[1] = rs.getString("height");
                user_info[2] = rs.getString("sex");
                user_info[3] = rs.getString("age");*/
                float wei = Float.parseFloat(User_if[0]);
                float hei = Float.parseFloat(User_if[1]);
                float age = Float.parseFloat(User_if[3]);
                if(User_if[2].equals("남성"))
                {
                    base =0;
                    base_metabolic.setText("기초 대사량 : " + base + "Kcal");

                    maintenance = 0;
                    maintenance_cal.setText("유지 칼로리 : " + maintenance + "Kcal");

                    target = 0;
                    target_cal.setText("목표 칼로리 : " + target + "Kcal");
                }
                else {
                    base =0;
                    base_metabolic.setText("기초 대사량 : " + base + "Kcal");

                    maintenance = 0;
                    maintenance_cal.setText("유지 칼로리 : " + maintenance + "Kcal");

                    target = 0;
                    target_cal.setText("목표 칼로리 : " + target + "Kcal");
                }
            }
        });
        add(User_kcal);

        base_metabolic.setBounds(50, 300, 300, 35);
        base_metabolic.setVisible(true);
        base_metabolic.setFont(font);
        add(base_metabolic);

        maintenance_cal.setBounds(50, 400, 300, 35);
        maintenance_cal.setVisible(true);
        maintenance_cal.setFont(font);
        add(maintenance_cal);

        target_cal.setBounds(50, 500, 300, 35);
        target_cal.setVisible(true);
        target_cal.setFont(font);
        add(target_cal);
    }

}





