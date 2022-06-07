package global;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Database.*;
public class GlobalGUI extends JFrame{
    static String user_id;
    final static int WINDOW_HEIGHT = 600;
    final static int WINDOW_WIDTH = 720;
    private sign_Dialog sign = new sign_Dialog();
    static JPanel panel = new GlobalPanel("src/asset/background.png");
    private DB_Connect dbCon = new DB_Connect();
    public GlobalGUI(String title){
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
                (windowSize.height - WINDOW_HEIGHT) / 2); //화면 중앙에 띄우기
        setVisible(true);
    }
    public static String getUser_id(){
        return user_id;
    }
    public static void repaintGUI(){
        panel.repaint();
        panel.revalidate();
        System.out.println("GUI를 다시 그리는 중...");
    }
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
        JLabel loginID_lb = new JLabel("id :");
        JLabel loginPW_lb = new JLabel("pw :");
        JTextField loginID_tf = new JTextField();
        JPasswordField loginPW_tf = new JPasswordField();
        loginID_lb.setSize(100,20);
        loginID_lb.setLocation(this.getWidth() / 2 - 130, this.getHeight() /2 - 25);
        loginPW_lb.setLocation(this.getWidth() / 2 - 130, this.getHeight() / 2);
        loginPW_lb.setSize(100,20);
        loginID_tf.setLocation(this.getWidth() / 2 - 80 , this.getHeight() / 2 - 25);
        loginID_tf.setSize(100,20);
        loginPW_tf.setLocation(this.getWidth() / 2 - 80, this.getHeight() /2);
        loginPW_tf.setSize(100,20);
        JButton login_btn = new JButton("로그인");
        login_btn.setLocation(this.getWidth() / 2 + 30, this.getHeight() / 2);
        login_btn.setSize(100,20);
        login_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean loginFlag = false;
                loginFlag = dbCon.login(loginID_tf.getText(), loginPW_tf.getText());
                if(loginFlag == true) {
                    user_id = loginID_tf.getText();
                    System.out.println("로그인 성공");
                    new main_Dialog(user_id);
                    setVisible(false);
                }
                else JOptionPane.showMessageDialog(null, "ID와 PW를 확인해주세요.", "Invaild user", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(loginID_lb);
        panel.add(loginPW_lb);
        panel.add(loginID_tf);
        panel.add(loginPW_tf);
        panel.add(login_btn);
    }
}