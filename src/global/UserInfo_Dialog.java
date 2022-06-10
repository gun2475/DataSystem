package global;

import Database.DB_Connect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;import java.time.ZoneId;
public class UserInfo_Dialog extends JDialog {
    private String[] my_info = new String[5];
    private JLabel weightlabel = new JLabel("몸무게: ");
    private JLabel weightShow = new JLabel("");
    private JLabel heightlabel = new JLabel("키: ");
    private JLabel heightShow = new JLabel("");
    private JLabel agelabel = new JLabel("나이: ");
    private JLabel ageShow = new JLabel("");
    private JLabel sexlabel = new JLabel("성별: ");
    private JLabel sexShow = new JLabel("");

    private JLabel bmilabel = new JLabel("BMI: ");
    private JLabel target_cal = new JLabel("target_cal: ");

    private JLabel bmiShow = new JLabel("");

    private JTextField weightText = new JTextField(15);
    private JTextField heightText = new JTextField(15);
    private JTextField ageText = new JTextField(15);
    private JTextField sexText = new JTextField(15);
    private JPanel InfoPanel = new GlobalPanel("src/asset/sign.png");
    private DB_Connect dbCon = new DB_Connect();
    final static int Info_HEIGHT = 450;
    final static int Info_WIDTH = 360;
    LocalDate now = LocalDate.now();
    static JLabel username = new JLabel();
    UserInfo_Dialog(String id) {
        setTitle("유저정보");
        String name = id;
        dbCon.connect();
        setContentPane(InfoPanel);
        InfoPanel.setSize(Info_WIDTH, Info_HEIGHT);
        setVisible(true);
        InfoPanel.setVisible(true);
        InfoPanel.setLayout(null);
        setSize(Info_WIDTH, Info_HEIGHT);
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - Info_WIDTH) / 2,
                (windowSize.height - Info_HEIGHT) / 2);

        ImageIcon exit_png = new ImageIcon("src/asset/exit.png");
        ImageIcon rectify_png = new ImageIcon("src/asset/rectify.png");

        JButton exit = new JButton(exit_png);
        JButton rectify = new JButton(rectify_png);

        username.setForeground(new Color(178,102,255));

        username.setText(name + "님 정보입니다.");
        username.setBounds(10, 10, 150, 20);
        username.setVisible(true);
        InfoPanel.add(username);

        exit.setBounds(80, 280, 50, 50);
        exit.setVisible(true);
        exit.setBorderPainted(false); //버튼 투명하게 만들기
        exit.setContentAreaFilled(false);
        InfoPanel.add(exit);

        rectify.setBounds(200, 280, 50, 50);
        rectify.setVisible(true);
        rectify.setBorderPainted(false); //버튼 투명하게 만들기
        rectify.setContentAreaFilled(false);
        InfoPanel.add(rectify);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        rectify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float weight_f = Float.parseFloat(weightText.getText());
                float height_f = Float.parseFloat(heightText.getText());
                String sex_s = sexText.getText();
                int age_i = Integer.parseInt(ageText.getText());
                dbCon.setUser_info(name, weight_f, height_f, sex_s, age_i);
                if(weightShow.getText().equals(weightText.getText()) && heightShow.getText().equals(heightText.getText())&&
                        sexShow.getText().equals(sexText.getText())&&ageShow.getText().equals(ageText.getText())) {
                    JOptionPane.showMessageDialog(null, "변경 사항이 없습니다!", "notice", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(null, "수정 되었습니다! 창을 다시 띄어주세요!",
                            "notice", JOptionPane.INFORMATION_MESSAGE); setVisible(false);
                }
            }
        });
        /////////////////////////////////////////////////////////////
        weightlabel.setBounds(50, 100, 100, 20);
        InfoPanel.add(weightlabel);
        weightText.setBounds(50, 120, 100, 20);
        InfoPanel.add(weightText);
        weightShow.setBounds(95,100,100,20);
        InfoPanel.add(weightShow);
        /////////////////////////////////////////////////////////////
        heightlabel.setBounds(180, 100, 100, 20);
        InfoPanel.add(heightlabel);
        heightText.setBounds(180, 120, 100, 20);
        InfoPanel.add(heightText);
        heightShow.setBounds(200,100,100,20);
        InfoPanel.add(heightShow);
        /////////////////////////////////////////////////////////////
        agelabel.setBounds(50, 190, 100, 20);
        InfoPanel.add(agelabel);
        ageText.setBounds(50, 210, 100, 20);
        InfoPanel.add(ageText);
        ageShow.setBounds(80,190,100,20);
        InfoPanel.add(ageShow);
        /////////////////////////////////////////////////////////////
        sexlabel.setBounds(180, 190, 100, 20);
        InfoPanel.add(sexlabel);
        sexText.setBounds(180, 210, 100, 20);
        InfoPanel.add(sexText);
        sexShow.setBounds(210,190,100,20);
        InfoPanel.add(sexShow);
        /////////////////////////////////////////////////////////////
        bmilabel.setBounds(50,240,100,20);
        InfoPanel.add(bmilabel);
        bmiShow.setBounds(75,250,100,20);
        InfoPanel.add(bmiShow);

        target_cal.setBounds(180,250,100,20);
        InfoPanel.add(target_cal);
        /////////////////////////////////////////////////////////////
        my_info = dbCon.getUser_info(name);
        weightText.setText(my_info[0]);
        heightText.setText(my_info[1]);
        sexText.setText(my_info[2]);
        ageText.setText(my_info[3]);
        weightShow.setText(my_info[0]);
        heightShow.setText(my_info[1]);
        sexShow.setText(my_info[2]);
        ageShow.setText(my_info[3]);
        bmiShow.setText(my_info[4]);
    }
}
