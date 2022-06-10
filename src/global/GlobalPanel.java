package global;
import javax.swing.*;
import java.awt.*;
public class GlobalPanel extends JPanel {
    private final Image img;
    public GlobalPanel(String BackgroundImgURL) {
        img = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource(BackgroundImgURL));
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
    }
}