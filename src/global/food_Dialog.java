package global;
import java.awt.*;
import javax.swing.*;
import Database.DB_Connect;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.Vector;
public class food_Dialog extends JDialog {
    LocalDate now = LocalDate.now();
    private static JPanel food = new GlobalPanel("src/asset/sign.png");
    private final DB_Connect dbCon = new DB_Connect();
    private double[] myfInfo = new double[4];
    private String[] User_if = new String[4];
    private JButton fInfo_add = new JButton("식단 추가하기");
    private JButton User_kcal = new JButton("칼로리 계산하기");
    private JButton fInfo_dlete = new JButton("제거하기");
    private JButton fInfo_clear = new JButton("초기화하기");

    private JButton food_br = new JButton("아침");
    private JButton food_lun = new JButton("점심");
    private JButton food_din = new JButton("저녁");
    
    private JLabel noticela = new JLabel("음식을 선택해주세요");
    private JLabel calla = new JLabel("칼로리 : ");
    private JLabel carla = new JLabel("탄수화물 : ");
    private JLabel prola = new JLabel("단백질 : ");
    private JLabel fatla = new JLabel("지방 : ");

    private JLabel sumcalla = new JLabel("칼로리 : ");
    private JLabel sumcarla = new JLabel("탄수화물 : ");
    private JLabel sumprola = new JLabel("단백질 : ");
    private JLabel sumfatla = new JLabel("지방 : ");

    private JLabel diet_sumcalla = new JLabel("칼로리 : ");
    private JLabel diet_sumcarla = new JLabel("탄수화물 : ");
    private JLabel diet_sumprola = new JLabel("단백질 : ");
    private JLabel diet_sumfatla = new JLabel("지방 : ");

    private int diet_scalla =0;
    private float diet_scarla =0;
    private float diet_sprola =0;
    private float diet_sfatla =0;
    private float sumcal =0;
    private float sumcar =0;
    private float sumpro =0;
    private float sumfat =0;

    private float br_cal[] = new float[4];
    private float lun_cal[] = new float[4];
    private float din_cal[] = new float[4];

    private JLabel base_metabolic = new JLabel("기초 대사량 : ");
    private JLabel maintenance_cal = new JLabel("유지 칼로리 : ");
    private JLabel target_upcal = new JLabel("목표 증량 칼로리 : ");
    private JLabel target_downcal = new JLabel("목표 감량 칼로리 : ");

    private JLabel carbohydrate_g = new JLabel("탄수화물 :");
    private JLabel protein_g = new JLabel("단백질 : ");
    private JLabel Fat_g = new JLabel("지방 : ");

    private float base =0;
    private float maintenance =0;
    private float targetup =0;
    private float targetdown =0;

    private float carbohydrate =0;
    private float protein =0;
    private float Fat =0;
    private String fname = "";
    final static int WINDOW_HEIGHT = 720;
    final static int WINDOW_WIDTH = 900;
    Vector<String> vec = new Vector<String>();
    Vector<String> food_vec = new Vector<String>();

    private JTextArea JTA_br = new JTextArea();
    private JTextArea JTA_lun = new JTextArea();
    private JTextArea JTA_din = new JTextArea();

    JRadioButton diet_sel1= new JRadioButton("");
    JRadioButton diet_sel2 = new JRadioButton("");
    ButtonGroup diet_sel = new ButtonGroup();
    private JButton eatfood_update = new JButton("적용하기");
    public food_Dialog(String id) {
        setTitle("식단 관리");
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

        ////////////////////////////////////////////////////////////////////////콤보박스, 해당 음식 영양소 가져오기
        JComboBox<String> food_list = new JComboBox<String>();
        food_list.setBounds(50, 50, 120, 20);
        food_list.setVisible(true);
        vec = dbCon.get_food();
        for (int i = 0; i < vec.size(); i++) {
            food_list.addItem(vec.get(i));
        }
        ////////////////////////////////////////////////////////////////////////영양소 정보 나타내는 메서드
        food_list.addActionListener(new ActionListener() {
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
        food.add(food_list);
        ////////////////////////////////////////////////////////////////////////라벨 설정
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

        ////////////////////////////////////////////////////////////////////////식단 추가하기
        JComboBox<String> food_addlist = new JComboBox<String>();
        food_addlist.setBounds(400, 50, 120, 20);
        food_addlist.setVisible(true);
        food.add(food_list);
        ////////////////////////////////////////////////////////////////////////
        fInfo_add.setBounds(270, 50, 120, 20);
        fInfo_add.setVisible(true);

        fInfo_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sumcal += myfInfo[0];
                sumcalla.setText("칼로리 : " + sumcal + "Kcal");

                BigDecimal car = new BigDecimal(String.valueOf(myfInfo[0]));
                sumcar += (float)(Math.round(myfInfo[1] *100)) / 100.0;
                sumcarla.setText("탄수화물 : " + sumcar + "g");

                BigDecimal pro = new BigDecimal(String.valueOf(myfInfo[1]));
                sumpro += (float)(Math.round(myfInfo[2] *100)) / 100.0;
                sumprola.setText("단백질 : " + sumpro + "g");

                BigDecimal fat = new BigDecimal(String.valueOf(myfInfo[2]));
                sumfat += (float)(Math.round(myfInfo[3]*100))/100.0;
                sumfatla.setText("지방 : " + sumfat + "g");

                fname = food_list.getSelectedItem().toString();
                food_vec.add(fname);
                food_addlist.addItem(fname);
            }
        });
        add(fInfo_add);
        add(food_addlist);
        ////////////////////////////////////////////////////////////////////////식단 빼기
        fInfo_dlete.setBounds(270, 100, 120, 20);
        fInfo_dlete.setVisible(true);
        fInfo_dlete.addActionListener(new ActionListener() {
            int tempCnt = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                if(food_addlist.getItemCount() != 0) {
                    String temp_foodName = food_addlist.getSelectedItem().toString();
                    myfInfo = dbCon.get_fInfo(temp_foodName);
                    food_addlist.removeItemAt(food_addlist.getSelectedIndex());
                    tempCnt = 0;
                }
                else if(food_addlist.getItemCount() == 0) {
                    JOptionPane.showMessageDialog(null, "식단이 없습니다.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    tempCnt = 1;
                }
                if(tempCnt == 0){
                    sumcal -= myfInfo[0];
                    sumcalla.setText("칼로리 : " + sumcal + "Kcal");

                    sumcar -= Math.round(myfInfo[1] *100) / 100.0;
                    sumcarla.setText("탄수화물 : " + sumcar + "g");

                    sumpro -= Math.round(myfInfo[2] *100) / 100.0;
                    sumprola.setText("단백질 : " + sumpro + "g");

                    sumfat -= Math.round(myfInfo[3] *100) / 100.0;
                    sumfatla.setText("지방 : " + sumfat + "g");
                }
            }
        });
        add(fInfo_dlete);
        ////////////////////////////////////////////////////////////////////////식단 초기화하기
        fInfo_clear.setBounds(270, 150, 120, 20);
        fInfo_clear.setVisible(true);
        fInfo_clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sumcal = 0;
                sumcalla.setText("칼로리 : " + sumcal + "Kcal");
                sumcar = 0;
                sumcarla.setText("탄수화물 : " + sumcar + "g");
                sumpro = 0;
                sumprola.setText("단백질 : " + sumpro + "g");
                sumfat = 0;
                sumfatla.setText("지방 : " + sumfat + "g");

                diet_scalla = 0;
                diet_scarla = 0;
                diet_sprola = 0;
                diet_sfatla = 0;

                diet_sumcalla.setText("칼로리 : " + diet_scalla + "Kcal");
                diet_sumcarla.setText("탄수화물 : " + diet_scarla + "g");
                diet_sumprola.setText("단백질 : " + diet_sprola + "g");
                diet_sumfatla.setText("지방 : " + diet_sfatla + "g");
                JTA_br.setText("");
                JTA_lun.setText("");
                JTA_din.setText("");
                food_vec.clear();
                food_addlist.removeAllItems();
                food_addlist.revalidate();

            }
        });
        add(fInfo_clear);
        ////////////////////////////////////////////////////////////////////////
        sumcalla.setBounds(400, 100, 300, 35);
        sumcalla.setVisible(true);
        sumcalla.setFont(font);
        add(sumcalla);

        sumcarla.setBounds(400, 150, 300, 35);
        sumcarla.setVisible(true);
        sumcarla.setFont(font);
        add(sumcarla);

        sumprola.setBounds(400, 200, 300, 35);
        sumprola.setVisible(true);
        sumprola.setFont(font);
        add(sumprola);

        sumfatla.setBounds(400, 250, 300, 35);
        sumfatla.setVisible(true);
        sumfatla.setFont(font);
        add(sumfatla);
///////////////////////////////////////////////////////////////////////////////칼로리 계산하기
        User_if = dbCon.getUser_info(id);
        User_kcal.setBounds(50, 320, 200, 20);
        User_kcal.setVisible(true);//계산하기 함수
        User_kcal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float wei = Float.parseFloat(User_if[0]);
                float hei = Float.parseFloat(User_if[1]);
                float age = Float.parseFloat(User_if[3]);
                if(User_if[2].equals("남성"))
                {
                    base = 66 + ((float)13.7 * wei) + (5 * hei) - ((float)6.8 * age);
                    base_metabolic.setText("기초 대사량 : " + (int)base + "Kcal");

                    maintenance = base * (float) 1.55;
                    maintenance_cal.setText("유지 칼로리 : " + (int)maintenance + "Kcal");

                    targetup = maintenance + (maintenance*(float)0.2);
                    target_upcal.setText("목표 증량 칼로리 : " + (int)targetup + "Kcal");

                    targetdown = maintenance - (maintenance*(float)0.2);
                    target_downcal.setText("목표 감량 칼로리 : " + (int)targetdown + "Kcal");

                    if(diet_sel1.isSelected())
                    {
                        carbohydrate = (targetdown*(float)0.5)/4;
                        protein = (targetdown*(float)0.3)/4;
                        Fat = (targetdown*(float)0.2)/9;
                    }
                    else {
                        carbohydrate = (targetup*(float)0.5)/4;
                        protein = (targetup*(float)0.3)/4;
                        Fat = (targetup*(float)0.2)/9;
                    }

                    carbohydrate_g.setText("탄수화물 : " + (int)carbohydrate + "g");
                    protein_g.setText("단백질 : " + (int)protein + "g");
                    Fat_g.setText("지방 : " + (int)Fat + "g");

                }
                else {
                    base =665 + ((float)19.6 * wei) + ((float)1.7 * hei) - ((float)4.7 * age);;
                    base_metabolic.setText("기초 대사량 : " + (int)base + "Kcal");

                    maintenance = base * (float) 1.55;
                    maintenance_cal.setText("유지 칼로리 : " + (int)maintenance + "Kcal");

                    targetup = maintenance + (maintenance*(float)0.2);
                    target_upcal.setText("목표 증량 칼로리 : " + (int)targetup + "Kcal");

                    targetdown =maintenance - (maintenance*(float)0.2);
                    target_downcal.setText("목표 감량 칼로리 : " + (int)targetdown + "Kcal");

                    if(diet_sel1.isSelected())
                    {
                        carbohydrate = (targetdown*(float)0.5)/4;
                        protein = (targetdown*(float)0.3)/4;
                        Fat = (targetdown*(float)0.2)/9;
                    }
                    else {
                        carbohydrate = (targetup*(float)0.5)/4;
                        protein = (targetup*(float)0.3)/4;
                        Fat = (targetup*(float)0.2)/9;
                    }
                    carbohydrate_g.setText("탄수화물 : " + (int)carbohydrate + "g");
                    protein_g.setText("단백질 : " + (int)protein + "g");
                    Fat_g.setText("지방 : " + (int)Fat + "g");
                }
            }
        });
        add(User_kcal);
//////////////////////////////////////////////////////////////////칼로리 계산 관련 라벨 추가
        base_metabolic.setBounds(50, 350, 450, 35);
        base_metabolic.setVisible(true);
        base_metabolic.setFont(font);
        add(base_metabolic);

        maintenance_cal.setBounds(50, 385, 450, 35);
        maintenance_cal.setVisible(true);
        maintenance_cal.setFont(font);
        add(maintenance_cal);

        target_downcal.setBounds(50, 420, 450, 35);
        target_downcal.setVisible(true);
        target_downcal.setFont(font);
        add(target_downcal);

        target_upcal.setBounds(50, 455, 450, 35);
        target_upcal.setVisible(true);
        target_upcal.setFont(font);
        add(target_upcal);

        carbohydrate_g.setBounds(50, 490, 450, 35);
        carbohydrate_g.setVisible(true);
        carbohydrate_g.setFont(font);
        add(carbohydrate_g);

        protein_g.setBounds(50, 525, 450, 35);
        protein_g.setVisible(true);
        protein_g.setFont(font);
        add(protein_g);

        Fat_g.setBounds(50, 560, 450, 35);
        Fat_g.setVisible(true);
        Fat_g.setFont(font);
        add(Fat_g);
////////////////////////////////////////////////////////////저장된 식단을 각 텍스트 에어리어에 저장(아침)
        food_br.setBounds(720, 180, 100, 20);
        food_br.setVisible(true);
        food_br.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTA_br.setText("");
                for (String str : food_vec) {
                    JTA_br.append(str + "\n");
                }
                br_cal[0] = sumcal;
                br_cal[1] = sumcar;
                br_cal[2] = sumpro;
                br_cal[3] = sumfat;
                diet_scalla = (int)(br_cal[0]+lun_cal[0]+ din_cal[0]);
                diet_scarla = br_cal[1]+lun_cal[1]+ din_cal[1];
                diet_sprola = br_cal[2]+lun_cal[2]+ din_cal[2];
                diet_sfatla = br_cal[3]+lun_cal[3]+ din_cal[3];
                diet_sumcalla.setText("칼로리 : " + diet_scalla + "Kcal");
                diet_sumcarla.setText("탄수화물 : " + diet_scarla + "g");
                diet_sumprola.setText("단백질 : " + diet_sprola + "g");
                diet_sumfatla.setText("지방 : " + diet_sfatla + "g");
                sumcal = 0;
                sumcalla.setText("칼로리 : " + sumcal + "Kcal");

                sumcar = 0;
                sumcarla.setText("탄수화물 : " + sumcar + "g");

                sumpro = 0;
                sumprola.setText("단백질 : " + sumpro + "g");

                sumfat = 0;
                sumfatla.setText("지방 : " + sumfat + "g");

                food_vec.clear();
                food_addlist.removeAllItems();
                food_addlist.revalidate();

            }
        });
        add(food_br);
////////////////////////////////////////////////////////////저장된 식단을 각 텍스트 에어리어에 저장(점심)
        food_lun.setBounds(720, 330, 100, 20);
        food_lun.setVisible(true);
        food_lun.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTA_lun.setText("");
                for (String str : food_vec) {
                    JTA_lun.append(str + "\n");
                }
                lun_cal[0] = sumcal;
                lun_cal[1] = sumcar;
                lun_cal[2] = sumpro;
                lun_cal[3] = sumfat;
                diet_scalla = (int)(br_cal[0]+lun_cal[0]+ din_cal[0]);
                diet_scarla = br_cal[1]+lun_cal[1]+ din_cal[1];
                diet_sprola = br_cal[2]+lun_cal[2]+ din_cal[2];
                diet_sfatla = br_cal[3]+lun_cal[3]+ din_cal[3];
                diet_sumcalla.setText("칼로리 : " + diet_scalla + "Kcal");
                diet_sumcarla.setText("탄수화물 : " + diet_scarla + "g");
                diet_sumprola.setText("단백질 : " + diet_sprola + "g");
                diet_sumfatla.setText("지방 : " + diet_sfatla + "g");
                sumcal = 0;
                sumcalla.setText("칼로리 : " + sumcal + "Kcal");

                sumcar = 0;
                sumcarla.setText("탄수화물 : " + sumcar + "g");

                sumpro = 0;
                sumprola.setText("단백질 : " + sumpro + "g");

                sumfat = 0;
                sumfatla.setText("지방 : " + sumfat + "g");
                food_vec.clear();
                food_addlist.removeAllItems();
                food_addlist.revalidate();
            }
        });
        add(food_lun);
////////////////////////////////////////////////////////////저장된 식단을 각 텍스트 에어리어에 저장(저녁)
        food_din.setBounds(720, 480, 100, 20);
        food_din.setVisible(true);
        food_din.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTA_din.setText("");
                for (String str : food_vec) {
                    JTA_din.append(str + "\n");
                }
                din_cal[0] = sumcal;
                din_cal[1] = sumcar;
                din_cal[2] = sumpro;
                din_cal[3] = sumfat;
                diet_scalla = (int)(br_cal[0]+lun_cal[0]+ din_cal[0]);
                diet_scarla = br_cal[1]+lun_cal[1]+ din_cal[1];
                diet_sprola = br_cal[2]+lun_cal[2]+ din_cal[2];
                diet_sfatla = br_cal[3]+lun_cal[3]+ din_cal[3];
                diet_sumcalla.setText("칼로리 : " + diet_scalla + "Kcal");
                diet_sumcarla.setText("탄수화물 : " + diet_scarla + "g");
                diet_sumprola.setText("단백질 : " + diet_sprola + "g");
                diet_sumfatla.setText("지방 : " + diet_sfatla + "g");
                sumcal = 0;
                sumcalla.setText("칼로리 : " + sumcal + "Kcal");

                sumcar = 0;
                sumcarla.setText("탄수화물 : " + sumcar + "g");

                sumpro = 0;
                sumprola.setText("단백질 : " + sumpro + "g");

                sumfat = 0;
                sumfatla.setText("지방 : " + sumfat + "g");
                food_vec.clear();
                food_addlist.removeAllItems();
                food_addlist.revalidate();
            }
        });
        add(food_din);
////////////////////////////////////////////////////////////텍스트 에어리어 추가

        JScrollPane scrollPane_br = new JScrollPane(JTA_br, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane_br.setBounds(620, 50, 200, 130);
        add(scrollPane_br);
        scrollPane_br.setVisible(true);


        JScrollPane scrollPane_lun = new JScrollPane(JTA_lun, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane_lun.setBounds(620, 200, 200, 130);
        add(scrollPane_lun);
        scrollPane_lun.setVisible(true);


        JScrollPane scrollPane_din = new JScrollPane(JTA_din, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane_din.setBounds(620, 350, 200, 130);
        add(scrollPane_din);
        scrollPane_din.setVisible(true);

        ///////////////////////////////////////////////////////각 식사 마다의 칼로리 및 탄단지양 표시
        diet_sumcalla.setBounds(620, 500, 200, 30);
        diet_sumcalla.setVisible(true);
        diet_sumcalla.setFont(font);
        add(diet_sumcalla);
        diet_sumcarla.setBounds(620, 530, 200, 30);
        diet_sumcarla.setVisible(true);
        diet_sumcarla.setFont(font);
        add(diet_sumcarla);
        diet_sumprola.setBounds(620, 560, 200, 30);
        diet_sumprola.setVisible(true);
        diet_sumprola.setFont(font);
        add(diet_sumprola);
        diet_sumfatla.setBounds(620, 590, 200, 30);
        diet_sumfatla.setVisible(true);
        diet_sumfatla.setFont(font);
        add(diet_sumfatla);
////////////////////////////////////////////////////////////////// 라디오 버튼으로 증량 감량 정하기

        diet_sel1.setSelected(true);
        diet_sel.add(diet_sel1);
        diet_sel.add(diet_sel2);

        diet_sel1.setBounds(0, 412, 50, 50);
        diet_sel1.setVisible(true);
        diet_sel1.setFont(font);
        diet_sel1.setBackground(new Color(178, 195, 207));
        add(diet_sel1);

        diet_sel2.setBounds(0, 447, 50, 50);
        diet_sel2.setVisible(true);
        diet_sel2.setBackground(new Color(178, 195, 207));
        diet_sel2.setFont(font);
        add(diet_sel2);
//////////////////////////////////////////////////////////////////////////적용하기
        eatfood_update.setBounds(620, 620, 200, 30);
        eatfood_update.setVisible(true);
        eatfood_update.setFont(font);
        eatfood_update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dbCon.set_myeatCal(id, now.toString(), (int)diet_scalla);

                for(int i=0; i<4; i++)
                {
                    br_cal[i] =0;
                    lun_cal[i] =0;
                    din_cal[i] =0;
                }
                diet_scalla = 0;
                diet_scarla = 0;
                diet_sprola = 0;
                diet_sfatla = 0;
                diet_sumcalla.setText("칼로리 : " + diet_scalla + "Kcal");
                diet_sumcarla.setText("탄수화물 : " + diet_scarla + "g");
                diet_sumprola.setText("단백질 : " + diet_sprola + "g");
                diet_sumfatla.setText("지방 : " + diet_sfatla + "g");
                JTA_br.setText("");
                JTA_lun.setText("");
                JTA_din.setText("");
            }
        });
        add(eatfood_update);
    }
    ////////////////////////////////////////////////////////////GUI 다시 그리기
    public static void repaintGUI() {
        food.repaint();
        food.revalidate();

        System.out.println("GUI를 다시 그리는 중...");
    }
}