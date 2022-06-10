package global;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Database.*;
public class GlobalGUI extends JFrame{
    static String user_id;
    final static int WINDOW_HEIGHT = 800;
    final static int WINDOW_WIDTH = 600;
    private sign_Dialog sign = new sign_Dialog();
    static JPanel panel = new GlobalPanel("src/asset/background.jpg");
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
        JTextField loginID_tf = new JTextField();
        JPasswordField loginPW_tf = new JPasswordField();
        loginID_tf.setBounds(200,545,100,22);
        loginPW_tf.setBounds(200,600,100,22);

        ImageIcon profileChange_jpg = new ImageIcon("src/asset/login.png"); //이미지파일 크기 조정하는 하드코딩
        Image img = profileChange_jpg.getImage();
        Image changeImg = img.getScaledInstance(100,22,Image.SCALE_SMOOTH);
        ImageIcon change_png = new ImageIcon(changeImg);
        JButton login_btn = new JButton(change_png);
        login_btn.setVisible(true);
        login_btn.setBounds(320,600,100,22);
        login_btn.setBorderPainted(false); //버튼 투명하게 만들기
        login_btn.setContentAreaFilled(false);
        add(login_btn);
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
        panel.add(loginID_tf);
        panel.add(loginPW_tf);
        panel.add(login_btn);
    }
}