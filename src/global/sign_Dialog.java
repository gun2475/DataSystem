package global;
import Database.DB_Connect;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
public class sign_Dialog extends JDialog {
    private JPanel signUpPanel = new global_Panel("sign.png");
    private JOptionPane MessageBox = new JOptionPane();
    private JLabel idlabel = new JLabel("아이디");
    private JLabel pwlabel = new JLabel("비밀번호");
    private JLabel pwChecklabel = new JLabel("비밀번호 확인");

    private JLabel weightlabel = new JLabel("몸무게");
    private JLabel heightlabel = new JLabel("키");
    private JLabel agelabel = new JLabel("나이");
    private JLabel sexlabel = new JLabel("성별");
    private JTextField idText = new JTextField(15);
    private JPasswordField pwText = new JPasswordField(15);
    private JPasswordField pwCheckText = new JPasswordField(15);
    private JTextField weightText = new JTextField(15);
    private JTextField heightText = new JTextField(15);
    private JTextField ageText = new JTextField(15);
    private JTextField sexText = new JTextField(15);
    private JButton signUpbtn = new JButton("회원가입");
    private JButton overlapBtn = new JButton("중복확인");
    private boolean check = false;
    public DB_Connect dbCon = new DB_Connect();


    public sign_Dialog() {
        dbCon.connect();
        signUpPanel.setLayout(null);
        signUpPanel.setSize(500,1000);
        setTitle("회원가입");
        setContentPane(signUpPanel);
        idlabel.setBounds(50, 20, 100, 20);
        signUpPanel.add(idlabel);
        idText.setBounds(50, 40, 100, 20);
        signUpPanel.add(idText);
        overlapBtn.setBounds(180, 40, 100, 20);
        overlapBtn.setContentAreaFilled(false);
        overlapBtn.setFocusPainted(false);
        overlapBtn.setForeground(Color.black);
        overlapBtn.setFont(new Font("나눔고딕",Font.BOLD,15));


        //                  /*예외 처리*/                  //
        signUpPanel.repaint();
        signUpPanel.revalidate();
        overlapBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {overlapBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));}

            @Override
            public void mouseExited(MouseEvent e) {
                overlapBtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (!(idText.getText().length() >= 4)) {
                    MessageBox.showMessageDialog(null, "4글자 이상으로 작성해주세요!");
                } else if (dbCon.isAdmin(idText.getText())) {
                    MessageBox.showMessageDialog(null, "중복된 아이디입니다. 다시 입력해주세요");
                    idText.setText("");
                } else {
                    MessageBox.showMessageDialog(null, "사용 가능한 아이디입니다.");
                    check = true;
                }
            }
        });
        signUpPanel.add(overlapBtn);

        pwlabel.setBounds(50, 90, 100, 20);
        signUpPanel.add(pwlabel);

        pwText.setBounds(50, 110, 100, 20);
        signUpPanel.add(pwText);

        pwChecklabel.setBounds(180, 90, 100, 20);
        signUpPanel.add(pwChecklabel);

        pwCheckText.setBounds(180, 110, 100, 20);
        signUpPanel.add(pwCheckText);

        weightlabel.setBounds(50, 140, 100, 20);
        signUpPanel.add(weightlabel);

        weightText.setBounds(50, 160, 100, 20);
        signUpPanel.add(weightText);

        heightlabel.setBounds(180, 140, 100, 20);
        signUpPanel.add(heightlabel);

        heightText.setBounds(180, 160, 100, 20);
        signUpPanel.add(heightText);

        agelabel.setBounds(50, 190, 100, 20);
        signUpPanel.add(agelabel);

        ageText.setBounds(50, 210, 100, 20);
        signUpPanel.add(ageText);

        sexlabel.setBounds(180, 190, 100, 20);
        signUpPanel.add(sexlabel);

        sexText.setBounds(180, 210, 100, 20);
        signUpPanel.add(sexText);

        signUpbtn.setBounds(120, 250, 100, 20);
        signUpbtn.setContentAreaFilled(false);
        signUpbtn.setFocusPainted(false);
        //                  /*회원가입*/                  //
        signUpbtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                signUpbtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                signUpbtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
 
            @Override
            public void mousePressed(MouseEvent e) {
                String pass = new String(pwText.getPassword());
                String passcheck = new String(pwCheckText.getPassword());
                float weight_c = Float.parseFloat(weightText.getText());
                float height_c = Float.parseFloat(heightText.getText());
                int age_c = Integer.parseInt(ageText.getText());
                String sex_c = new String(sexText.getText());
                if (sex_c.equals("남성")  || sex_c.equals("여성")){
                    }
                else{
                    MessageBox.showMessageDialog(null, "성별 : '남성' 혹은 '여성'으로 입력해주세요.");
                }
                if (idlabel == null || pass == null || passcheck == null || weight_c == 0 || height_c == 0 || age_c == 0 || sex_c == null) {
                    MessageBox.showMessageDialog(null, "빈칸을 입력해주세요.");
                } else if (!check) {
                    MessageBox.showMessageDialog(null, "아이디를 중복확인해 주세요.");
                } else if (!(pass.length() >= 4)) {
                    MessageBox.showMessageDialog(null, "비밀번호를 4글자 이상으로 작성해주세요.");
                } else if (!pass.equals(passcheck)) {
                    MessageBox.showMessageDialog(null, "비밀번호를 다시 확인해주세요.");
                }
                else {
                    if (dbCon.Enrollment(idText.getText(), pass, weight_c, height_c, sex_c, age_c)) {
                        MessageBox.showMessageDialog(null, "회원가입이 완료되었습니다.");
                        setVisible(false);
                    } else {
                        MessageBox.showMessageDialog(null, "회원가입에 실패하였습니다. 다시 시도해주세요.");
                    }

                }
            }
        });
        signUpPanel.add(signUpbtn);

        setSize(350, 250);
        setLocationRelativeTo(null);
    }
}