import global.GlobalGUI;
import javax.swing.*;
import java.awt.*;
public class Main extends JFrame{
    private static String id="";
    //private InfoDialog settingScreen;
    private static String title ="database";
    //static JPanel panel;
    private final static int WINDOW_HEIGHT = 720;
    private final static int WINDOW_WIDTH = 1280;
    public Main(String id){
        super("Screen");
        setTitle(title);
        // setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        requestFocusInWindow();
        setVisible(true);
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - WINDOW_WIDTH) / 2,
                (windowSize.height - WINDOW_HEIGHT) / 2);
    }
    public static void main(String[]args){
        new GlobalGUI("database");
        //new Main("");
    }
}