package global;

import javax.swing.*;
import Database.*;
public class main_Dialog extends JFrame {
    static JPanel mainPanel = new JPanel();
    private DB_Connect dbCon = new DB_Connect();
    main_Dialog(){
        dbCon.connect();
        setContentPane(mainPanel);
        mainPanel.setSize(1280,720);
        mainPanel.setVisible(false);
    }
}
