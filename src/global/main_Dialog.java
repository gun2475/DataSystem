package global;

import javax.swing.*;
import Database.*;

import java.awt.*;

public class main_Dialog extends JDialog {
    static JPanel mainPanel = new JPanel();
    private DB_Connect dbCon = new DB_Connect();
    final static int WINDOW_HEIGHT = 720;
    final static int WINDOW_WIDTH = 1280;
    static JLabel username = new JLabel(GlobalGUI.getUser_id());
    main_Dialog(){
        String name = GlobalGUI.getUser_id();
        dbCon.connect();
        setContentPane(mainPanel);
        mainPanel.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        mainPanel.setVisible(false);
        setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        setVisible(false);
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - WINDOW_WIDTH) / 2,
                (windowSize.height - WINDOW_HEIGHT) / 2);

        mainPanel.add(username);
    }
}
