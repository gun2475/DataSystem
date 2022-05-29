package global;
import java.awt.*;
import javax.swing.*;

public class food_Dialog extends JFrame {
    public food_Dialog()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(null);
        setVisible(true);
    }
    public static void main(Sring [] args)
    {
        food_Dialog = new food_Dialog();
    }

}


