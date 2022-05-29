package global;

import Database.DB_Connect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class setUserInfo extends JDialog {
    private JPanel InfoPanel = new JPanel();
    private DB_Connect dbCon = new DB_Connect();
    final static int Info_HEIGHT = 620;
    final static int Info_WIDTH = 500;
    static JLabel username = new JLabel();
    setUserInfo(String id){
        setTitle("수정하세요");
        String name = id;
        dbCon.connect();
        setContentPane(InfoPanel);
        InfoPanel.setSize(Info_WIDTH,Info_HEIGHT);
        setVisible(true);
        InfoPanel.setVisible(true);
        InfoPanel.setLayout(null);
        setSize(Info_WIDTH,Info_HEIGHT);
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - Info_WIDTH) / 2,
                (windowSize.height - Info_HEIGHT) / 2);
        username.setText(name+"님 정보입니다.");
        username.setBounds(10,10,150,15);
        username.setVisible(true);
        InfoPanel.add(username);
        ImageIcon exit_png = new ImageIcon("src/asset/exit.png");
        ImageIcon rectify_png = new ImageIcon("src/asset/rectify.png");
        JButton exit = new JButton(exit_png);
        JButton rectify = new JButton(rectify_png);
        exit.setBounds(200,450,50,50);
        exit.setVisible(true);
        exit.setBorderPainted(false); //버튼 투명하게 만들기
        exit.setContentAreaFilled(false);
        InfoPanel.add(exit);

        rectify.setBounds(255,450,50,50);
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

            }
        });


    }
}
