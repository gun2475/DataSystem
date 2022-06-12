package global;
import javax.swing.*;
import Database.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class menu_Page extends JFrame {
    private JPanel mainPanel = new global_Panel("menu.jpg");
    private db_Function dbCon = new db_Function();
    final static int WINDOW_HEIGHT = 700;
    final static int WINDOW_WIDTH = 500;
    static JLabel username = new JLabel();
    public menu_Page(String id){
        setTitle("메인");
        String name = id;
        dbCon.connect();
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainPanel.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        setVisible(true);
        mainPanel.setVisible(true);
        mainPanel.setLayout(null);
        setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - WINDOW_WIDTH) / 2, (windowSize.height - WINDOW_HEIGHT) / 2);
        username.setText(name+"님");
        Font f = new Font("Gothic",Font.CENTER_BASELINE,20);
        username.setForeground(Color.GRAY);
        username.setFont(f);
        username.setBounds(150,18,400,20);
        username.setVisible(true);
        mainPanel.add(username);

        Image img1 = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("set.jpg"));
        Image changeImg = img1.getScaledInstance(70,70,Image.SCALE_SMOOTH);
        ImageIcon change_png = new ImageIcon(changeImg);
        JButton profileChange = new JButton(change_png);
        profileChange.setVisible(true);
        profileChange.setBounds(320,10,70,70);
        profileChange.setBorderPainted(false);
        profileChange.setContentAreaFilled(false);
        mainPanel.add(profileChange);
        profileChange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new userInfo_Dialog(id);
            }
        });

        Image img2 = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("exit.jpg"));
        Image exitImg = img2.getScaledInstance(70,70,Image.SCALE_SMOOTH);
        ImageIcon exit_jpg = new ImageIcon(exitImg);
        JButton exit = new JButton(exit_jpg);
        exit.setVisible(true);
        exit.setBounds(400,10,70,70);
        exit.setBorderPainted(false);
        exit.setContentAreaFilled(false);
        mainPanel.add(exit);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    dbCon.close_DB();
                    System.exit(0);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        Image img3 = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("food_menu.jpg"));
        Image changeImg_1 = img3.getScaledInstance(262,213,Image.SCALE_SMOOTH);
        ImageIcon change_png_1 = new ImageIcon(changeImg_1);
        JButton food = new JButton(change_png_1);
        food.setBounds(119,80,262,213);
        food.setVisible(true);
        food.setBorderPainted(false);
        food.setContentAreaFilled(false);
        mainPanel.add(food);
        food.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new food_Dialog(name);
            }
        });

        Image img4 = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("graph.jpg"));
        Image changeImg_2 = img4.getScaledInstance(267,226,Image.SCALE_SMOOTH);
        ImageIcon change_png_2 = new ImageIcon(changeImg_2);
        JButton graph = new JButton(change_png_2);
        graph.setBounds(117,280,267,226);
        graph.setVisible(true);
        graph.setBorderPainted(false);
        graph.setContentAreaFilled(false);
        mainPanel.add(graph);
        graph.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { new graph_Dialog(id);}
        });

        Image img5 = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("made.jpg"));
        Image changeImg_3 = img5.getScaledInstance(172,182,Image.SCALE_SMOOTH);
        ImageIcon change_png_3 = new ImageIcon(changeImg_3);
        JButton made = new JButton(change_png_3);
        made.setBounds(164,490,172,182);
        made.setVisible(true);
        made.setBorderPainted(false);
        made.setContentAreaFilled(false);
        mainPanel.add(made);
        made.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new made_Dialog(id);
            }
        });
    }
}