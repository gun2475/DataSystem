package global;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;

public class made_dialog extends JDialog{
    private JPanel madePanel = new GlobalPanel("src/asset/sign.png");
    final static int WINDOW_HEIGHT = 720;
    final static int WINDOW_WIDTH = 500;
    JLabel welcome = new JLabel("만든 사람들");
    made_dialog(String id){
        setTitle("만든 사람들");
        setContentPane(madePanel);
        madePanel.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        setVisible(true);
        madePanel.setVisible(true);
        madePanel.setLayout(null);
        setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - WINDOW_WIDTH) / 2,
                (windowSize.height - WINDOW_HEIGHT) / 2);

        Font f = new Font("궁서체",Font.PLAIN,60);
        welcome.setForeground(new Color(100,100,250));
        welcome.setFont(f);
        welcome.setBounds(75,30,400,80);
        welcome.setVisible(true);
        madePanel.add(welcome);
        ImageIcon profileChange_png = new ImageIcon("src/asset/jaemin.png"); //이미지파일 크기 조정하는 하드코딩
        Image img = profileChange_png.getImage();
        Image changeImg = img.getScaledInstance(100,130,Image.SCALE_SMOOTH);
        ImageIcon change_png = new ImageIcon(changeImg);
        JLabel jaemin_Image = new JLabel(change_png);
        jaemin_Image.setVisible(true);
        jaemin_Image.setBounds(90,130,100,130);
        madePanel.add(jaemin_Image);
        JLabel jaemin_name = new JLabel("이름 : 권재민");
        JLabel jaemin_id = new JLabel("학번 : 2221008");
        JLabel jaemin_work = new JLabel("맡은일 : UI/Logic 담당");
        jaemin_name.setVisible(true);
        jaemin_id.setVisible(true);
        jaemin_work.setVisible(true);
        jaemin_name.setBounds(90,260,100,25);
        jaemin_id.setBounds(90,275,100,25);
        jaemin_work.setBounds(90,290,200,25);
        madePanel.add(jaemin_name);
        madePanel.add(jaemin_id);
        madePanel.add(jaemin_work);

        ImageIcon profileChange_png_1 = new ImageIcon("src/asset/jaemin.png"); //이미지파일 크기 조정하는 하드코딩
        Image img_1 = profileChange_png_1.getImage();
        Image changeImg_1 = img_1.getScaledInstance(100,130,Image.SCALE_SMOOTH);
        ImageIcon change_png_1 = new ImageIcon(changeImg_1);
        JLabel hyunsu_Image = new JLabel(change_png_1);
        hyunsu_Image.setVisible(true);
        hyunsu_Image.setBounds(290,130,100,130);
        madePanel.add(hyunsu_Image);
        JLabel hyunsu_name = new JLabel("이름 : 이현수");
        JLabel hyunsu_id = new JLabel("학번 : 2221072");
        JLabel hyunsu_work = new JLabel("맡은일 : DB/Logic 담당");
        hyunsu_name.setVisible(true);
        hyunsu_id.setVisible(true);
        hyunsu_work.setVisible(true);
        hyunsu_name.setBounds(290,260,100,25);
        hyunsu_id.setBounds(290,275,100,25);
        hyunsu_work.setBounds(290,290,200,25);
        madePanel.add(hyunsu_name);
        madePanel.add(hyunsu_id);
        madePanel.add(hyunsu_work);

        ImageIcon profileChange_png_2 = new ImageIcon("src/asset/daehui.png"); //이미지파일 크기 조정하는 하드코딩
        Image img_2 = profileChange_png_2.getImage();
        Image changeImg_2 = img_2.getScaledInstance(100,130,Image.SCALE_SMOOTH);
        ImageIcon change_png_2 = new ImageIcon(changeImg_2);
        JLabel daehee_Image = new JLabel(change_png_2);
        daehee_Image.setVisible(true);
        daehee_Image.setBounds(90,310,100,130);
        madePanel.add(daehee_Image);
        JLabel daehee_name = new JLabel("이름 : 정대희");
        JLabel daehee_id = new JLabel("학번 : 2221081");
        JLabel daehee_work = new JLabel("맡은일 : Calculation/Logic 담당");
        daehee_name.setVisible(true);
        daehee_id.setVisible(true);
        daehee_work.setVisible(true);
        daehee_name.setBounds(90,440,100,25);
        daehee_id.setBounds(90,455,100,25);
        daehee_work.setBounds(90,470,200,25);
        madePanel.add(daehee_name);
        madePanel.add(daehee_id);
        madePanel.add(daehee_work);

        ImageIcon profileChange_png_3 = new ImageIcon("src/asset/jaemin.png"); //이미지파일 크기 조정하는 하드코딩
        Image img_3 = profileChange_png_3.getImage();
        Image changeImg_3 = img_3.getScaledInstance(100,130,Image.SCALE_SMOOTH);
        ImageIcon change_png_3 = new ImageIcon(changeImg_3);
        JLabel youngwoo_Image = new JLabel(change_png_3);
        youngwoo_Image.setVisible(true);
        youngwoo_Image.setBounds(290,310,100,130);
        madePanel.add(youngwoo_Image);
        JLabel youngwoo_name = new JLabel("이름 : 전영우");
        JLabel youngwoo_id = new JLabel("학번 : 22210??");
        JLabel youngwoo_work = new JLabel("맡은일 : ?? 담당");
        youngwoo_name.setVisible(true);
        youngwoo_id.setVisible(true);
        youngwoo_work.setVisible(true);
        youngwoo_name.setBounds(290,440,100,25);
        youngwoo_id.setBounds(290,455,100,25);
        youngwoo_work.setBounds(290,470,200,25);
        madePanel.add(youngwoo_name);
        madePanel.add(youngwoo_id);
        madePanel.add(youngwoo_work);

        ImageIcon profileChange_png_4 = new ImageIcon("src/asset/jaemin.png"); //이미지파일 크기 조정하는 하드코딩
        Image img_4 = profileChange_png_4.getImage();
        Image changeImg_4 = img_4.getScaledInstance(100,130,Image.SCALE_SMOOTH);
        ImageIcon change_png_4 = new ImageIcon(changeImg_4);
        JLabel jongsik_Image = new JLabel(change_png_4);
        jongsik_Image.setVisible(true);
        jongsik_Image.setBounds(90,490,100,130);
        madePanel.add(jongsik_Image);
        JLabel jongsik_name = new JLabel("이름 : 윤종식");
        JLabel jongsik_id = new JLabel("학번 : 22210??");
        JLabel jongsik_work = new JLabel("맡은일 : ?? 담당");
        jongsik_name.setVisible(true);
        jongsik_id.setVisible(true);
        jongsik_work.setVisible(true);
        jongsik_name.setBounds(90,620,100,25);
        jongsik_id.setBounds(90,635,100,25);
        jongsik_work.setBounds(90,650,200,25);
        madePanel.add(jongsik_name);
        madePanel.add(jongsik_id);
        madePanel.add(jongsik_work);

        ImageIcon profileChange_png_5 = new ImageIcon("src/asset/jaemin.png"); //이미지파일 크기 조정하는 하드코딩
        Image img_5 = profileChange_png_4.getImage();
        Image changeImg_5 = img_5.getScaledInstance(100,130,Image.SCALE_SMOOTH);
        ImageIcon change_png_5 = new ImageIcon(changeImg_5);
        JLabel chanmin_Image = new JLabel(change_png_5);
        chanmin_Image.setVisible(true);
        chanmin_Image.setBounds(290,490,100,130);
        madePanel.add(chanmin_Image);
        JLabel chanmin_name = new JLabel("이름 : 김찬민");
        JLabel chanmin_id = new JLabel("학번 : 22200??");
        JLabel chanmin_work = new JLabel("맡은일 : ?? 담당");
        chanmin_name.setVisible(true);
        chanmin_id.setVisible(true);
        chanmin_work.setVisible(true);
        chanmin_name.setBounds(290,620,100,25);
        chanmin_id.setBounds(290,635,100,25);
        chanmin_work.setBounds(290,650,200,25);
        madePanel.add(chanmin_name);
        madePanel.add(chanmin_id);
        madePanel.add(chanmin_work);


    }
}