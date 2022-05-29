package global;

import javax.swing.*;
import Database.*;

import java.awt.*;

public class main_Dialog extends JDialog {
    static JPanel mainPanel = new JPanel();
    private DB_Connect dbCon = new DB_Connect();
    final static int WINDOW_HEIGHT = 720;
    final static int WINDOW_WIDTH = 1280;
    main_Dialog(){
        dbCon.connect();
        setContentPane(mainPanel);
        mainPanel.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        mainPanel.setVisible(false);
        setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        setVisible(false);
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - WINDOW_WIDTH) / 2,
                (windowSize.height - WINDOW_HEIGHT) / 2);
    }
}
