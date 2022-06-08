package global;

import javax.swing.*;
import java.awt.*;

public class made_dialog extends JDialog{
    private JPanel madePanel = new GlobalPanel("src/asset/sign.png");

    final static int WINDOW_HEIGHT = 720;
    final static int WINDOW_WIDTH = 500;

    made_dialog(String id){
        setContentPane(madePanel);
        madePanel.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        setVisible(true);
        madePanel.setVisible(true);
        madePanel.setLayout(null);
        setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - WINDOW_WIDTH) / 2,
                (windowSize.height - WINDOW_HEIGHT) / 2);
    }
}
