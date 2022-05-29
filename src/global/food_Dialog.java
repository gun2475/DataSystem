package global;
import java.awt.*;
import javax.swing.*;

public class food_Dialog extends JFrame {

    public food_Dialog()
    {
        setTitle("나만의 식단");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JFrame frame = new JFrame();
        frame.setLayout(null);


        Scrollbar scr1 = new Scrollbar();
        scr1.setBounds(500, 400, 30, 200);

        frame.add(scr1);

        frame.setSize(1280,720);
        frame.setVisible(true);
    }
    public static void main(String [] args)
    {
        new food_Dialog();
    }

}


