package global;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class GlobalGUI extends JFrame{
    private final static int WINDOW_HEIGHT = 720;
    private final static int WINDOW_WIDTH = 1280;
    static JPanel panel;

    protected GlobalGUI(String title){
        super();
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

}
