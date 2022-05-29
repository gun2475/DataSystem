package global;

import javax.swing.*;
import Database.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class main_Dialog extends JDialog {
    private JPanel mainPanel = new JPanel();
    private DB_Connect dbCon = new DB_Connect();
    final static int WINDOW_HEIGHT = 720;
    final static int WINDOW_WIDTH = 1280;
    static JLabel username = new JLabel();
    main_Dialog(String id){
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
        username.setText(name+"님 환영합니다.");
        username.setBounds(10,10,150,15);
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
        profileChange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new setUserInfo(id);
            }
        });
    }
}
