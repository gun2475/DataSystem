import global.GlobalGui;
import javax.swing.*;
public class Main extends JFrame{
    private static String id="";
    private static String title ="database";
    private final static int WINDOW_HEIGHT = 720;
    private final static int WINDOW_WIDTH = 1280;
    public static void main(String[]args){
        new GlobalGui("database");
    }
}