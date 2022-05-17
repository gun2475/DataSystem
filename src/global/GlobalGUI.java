package global;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import Database.*;

public class GlobalGUI extends JFrame{
    private final static int WINDOW_HEIGHT = 720;
    private final static int WINDOW_WIDTH = 1280;
    private sign_Dialog sign = new sign_Dialog();
    static JPanel panel = new JPanel();

    protected GlobalGUI(String title){
        super();
        createMenu();
        setTitle(title);
        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
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
            }
        });
    }

    public static void main(String args[]){
        new GlobalGUI("temp");
    }
}
