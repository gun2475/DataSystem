package global;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Database.*;

public class GlobalGUI extends JFrame{
    final static int WINDOW_HEIGHT = 720;
    final static int WINDOW_WIDTH = 1280;
    private sign_Dialog sign = new sign_Dialog();
    private main_Dialog my_main = new main_Dialog();
    static JPanel panel = new JPanel();

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
        login();
        requestFocusInWindow();


        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - WINDOW_WIDTH) / 2,
                (windowSize.height - WINDOW_HEIGHT) / 2); //화면 중앙에 띄우기
        /*try {
            //Font f = Font.createFont(Font.TRUETYPE_FONT, new File(FONT_PATH));
            //GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //ge.registerFont(f);
        } catch (//IOException | FontFormatException e) {
            //e.printStackTrace();
        }
        //setDefaultFont();*/
        setVisible(true);
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
        JLabel loginID_lb = new JLabel("id :");
        JLabel loginPW_lb = new JLabel("pw :");
        JTextField loginID_tf = new JTextField();
        JTextField loginPW_tf = new JTextField();

        loginID_lb.setSize(100,20);
        loginID_lb.setLocation(this.getWidth() / 2 - 150, this.getHeight() /2 - 25);

        loginPW_lb.setLocation(this.getWidth() / 2 - 150, this.getHeight() / 2);
        loginPW_lb.setSize(100,20);

        loginID_tf.setLocation(this.getWidth() / 2 - 100 , this.getHeight() / 2 - 25);
        loginID_tf.setSize(100,20);

        loginPW_tf.setLocation(this.getWidth() / 2 - 100, this.getHeight() /2);
        loginPW_tf.setSize(100,20);

        JButton login_btn = new JButton("로그인");
        login_btn.setLocation(this.getWidth() / 2 + 20, this.getHeight() / 2);
        login_btn.setSize(100,20);
        login_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean loginFlag = false;
                loginFlag = dbCon.login(loginID_tf.getText(), loginPW_tf.getText());
                if(loginFlag == true) System.out.println("로그인 성공");
                else System.out.println("로그인 실패");
                my_main.setVisible(true);
                panel.add(my_main.mainPanel);
            }
        });
        panel.add(loginID_lb);
        panel.add(loginPW_lb);
        panel.add(loginID_tf);
        panel.add(loginPW_tf);
        panel.add(login_btn);
    }
}
