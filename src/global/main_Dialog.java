package global;

import javax.swing.*;
import Database.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class main_Dialog extends JDialog {

    private JPanel mainPanel = new GlobalPanel("src/asset/sign.png");
    private DB_Connect dbCon = new DB_Connect();
    final static int WINDOW_HEIGHT = 720;
    final static int WINDOW_WIDTH = 1280;
    static JLabel username = new JLabel();

    public main_Dialog(String id){
        String name = id;
        dbCon.connect();
        setContentPane(mainPanel);
        mainPanel.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        setVisible(true);
        mainPanel.setVisible(true);
        mainPanel.setLayout(null);
        setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - WINDOW_WIDTH) / 2,
                (windowSize.height - WINDOW_HEIGHT) / 2);
        username.setText(name+"님 반갑습니다.");
        Font f = new Font("돋움체",Font.CENTER_BASELINE,20);
        username.setForeground(new Color(178,102,255));
        username.setFont(f);
        username.setBounds(10,10,400,20);
        username.setVisible(true);
        mainPanel.add(username);
        ImageIcon profileChange_png = new ImageIcon("src/asset/profileChange.png"); //이미지파일 크기 조정하는 하드코딩
        Image img = profileChange_png.getImage();
        Image changeImg = img.getScaledInstance(100,100,Image.SCALE_SMOOTH);
        ImageIcon change_png = new ImageIcon(changeImg);
        JButton profileChange = new JButton(change_png);

        profileChange.setVisible(true);
        profileChange.setBounds(1150,10,100,100);

        profileChange.setBorderPainted(false); //버튼 투명하게 만들기
        profileChange.setContentAreaFilled(false);
        mainPanel.add(profileChange);
        ImageIcon button_1_png = new ImageIcon("src/asset/button_1.png");
        Image img_1 = button_1_png.getImage();
        Image changeImg_1 = img_1.getScaledInstance(300,100,Image.SCALE_SMOOTH);
        ImageIcon change_png_1 = new ImageIcon(changeImg_1);
        JButton food = new JButton(change_png_1);
        food.setBounds(100,200,300,100);
        food.setVisible(true);
        food.setBorderPainted(false); //버튼 투명하게 만들기
        food.setContentAreaFilled(false);
        mainPanel.add(food);

        profileChange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UserInfo_Dialog(id);
            }
        });
        food.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new food_Dialog(name);
            }
        });

        ImageIcon button_2_png = new ImageIcon("src/asset/button_2.png");
        Image img_2 = button_2_png.getImage();
        Image changeImg_2 = img_2.getScaledInstance(300,100,Image.SCALE_SMOOTH);
        ImageIcon change_png_2 = new ImageIcon(changeImg_2);
        JButton graph = new JButton(change_png_2);

        graph.setBounds(100,500,300,100);
        graph.setVisible(true);
        graph.setBorderPainted(false); //버튼 투명하게 만들기
        graph.setContentAreaFilled(false);
        mainPanel.add(graph);
        graph.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { new graph_dialog(id);}
        });
    }
}
