package global;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Database.*;
public class sign_Page extends JFrame{
    static String user_id;
    final static int WINDOW_HEIGHT = 800;
    final static int WINDOW_WIDTH = 600;
    private sign_Dialog sign = new sign_Dialog();
    static JPanel panel = new global_Panel("background.jpg");
    private db_Function dbCon = new db_Function();
    public sign_Page(String title){
        super();
        dbCon.connect();
        createMenu();
        setTitle(title);
        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        repaintGUI();
        login();
        requestFocusInWindow();
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - WINDOW_WIDTH) / 2,
                (windowSize.height - WINDOW_HEIGHT) / 2);
        setVisible(true);
    }
    public static String getUser_id(){
        return user_id;
    }
    public static void repaintGUI(){
        panel.repaint();
        panel.revalidate();
    }
    //                  /*메뉴바 생성*/                  //
    public void createMenu(){
        JMenuBar mb = new JMenuBar();
        JMenu accountMenu = new JMenu("계정 관리");
        JMenuItem signItem = new JMenuItem("계정 생성");

        mb.setVisible(true);
        mb.add(accountMenu);
        accountMenu.add(signItem);
        setJMenuBar(mb);
        signItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sign.setVisible(true);
                sign.setSize(350,400);
            }
        });
    }
    public void login(){
        setTitle("로그인창");
        JTextField loginID_tf = new JTextField();
        JPasswordField loginPW_tf = new JPasswordField();
        loginID_tf.setBounds(200,545,100,22);
        loginPW_tf.setBounds(200,600,100,22);

        Image img = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("login.png"));
        Image changeImg = img.getScaledInstance(100,22,Image.SCALE_SMOOTH);
        ImageIcon change_png = new ImageIcon(changeImg);
        JButton login_btn = new JButton(change_png);
        login_btn.setVisible(true);
        login_btn.setBounds(320,600,100,22);
        login_btn.setBorderPainted(false);
        login_btn.setContentAreaFilled(false);
        add(login_btn);
        //                  /*로그인*/                  //
        login_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean loginFlag = false;
                loginFlag = dbCon.login(loginID_tf.getText(), loginPW_tf.getText());
                if(loginFlag == true) {
                    user_id = loginID_tf.getText();
                    System.out.println("로그인 성공");
                    new menu_Page(user_id);
                    setVisible(false);
                }
                else JOptionPane.showMessageDialog(null, "ID와 PW를 확인해주세요.", "Invaild user", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(loginID_tf);
        panel.add(loginPW_tf);
        panel.add(login_btn);
    }
}