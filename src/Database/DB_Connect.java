package Database;
import java.sql.*;
import java.util.Vector;
public class DB_Connect {
    static int cnt = 0;
    public String food_name[] = new String[100];
    private String userName = "";
    private Connection connection;
    private ResultSet rs;
    private Statement st;
    private String url = "jdbc:mysql://203.255.57.227:3306/DBID?serverTimezone=UTC";
    private String user = "dbid";
    private String password = "DBID!!!dbpw1";
    private String driverName = "com.mysql.cj.jdbc.Driver";
    public void connect(){
        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(url, user, password);
            st = connection.createStatement();
            if(cnt == 0) {
                System.out.println("[Connection successful!]");
                cnt++;
            }


        } catch (ClassNotFoundException e) {
            System.out.println("[로드 오류]\n" + e.getStackTrace());
        } catch (SQLException e) {
            System.out.println("[연결 오류]\n" + e.getStackTrace());
        }
    }

    public boolean Enrollment(String myId, String myPw, float weight, float height, String sex, int age) { // 회원가입
        try {
            String SQL1 = "INSERT INTO User(id, pw, weight, height, sex, age) VALUES('" + myId + "','" + myPw + "','" + weight + "','" + height + "','" + sex + "','" + age + "');";
            PreparedStatement pstmt = connection.prepareStatement(SQL1);
            int re = pstmt.executeUpdate();
            if (re == 1) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean isAdmin(String id) { // 아이디 중복확인
        try {
            String SQL1 = "SELECT * FROM User WHERE id = '" + id + "';".toString();
            rs = st.executeQuery(SQL1);
            if (rs.next()) {
                if (rs.getString("id").equals(id)) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("[데이터베이스 검색 오류] : " + e.getMessage());
        }
        return false;
    }


    public boolean login(String id, String pw) { // 로그인
        boolean idFlag = false;
        boolean pwFlag = false;
        try {
            String SQL1 = "SELECT id FROM User WHERE id = '" + id + "';".toString();
            rs = st.executeQuery(SQL1);
            if (rs.next()) {
                if (rs.getString("id").equals(id)) {
                    idFlag = true;
                }
            }
            String SQL2 = "SELECT pw FROM User WHERE pw = '" + pw + "';".toString();
            rs = st.executeQuery(SQL2);
            if (rs.next()) {
                if (rs.getString("pw").equals(pw)) {
                    pwFlag = true;
                }
            }
        } catch (Exception e) {
            System.out.println("[데이터베이스 검색 오류] : " + e.getMessage());
        }
        if(idFlag == true && pwFlag == true) {
            userName = id;
            return true;
        }
        else return false;
    }

    public String getUserName(){
        return userName;
    }

    public Vector<String> getfoodname()
    {
        Vector<String> vec = new Vector<String>();
        try {
            String SQL1 = "SELECT * FROM Food WHERE food_name;";
            rs = st.executeQuery(SQL1);
            while(rs.next()) {
                vec.add(rs.getString("food_name"));
                System.out.println(rs.getString("food_name"));
            }
        } catch (Exception e) {
            System.out.println("[데이터베이스 검색 오류] : " + e.getMessage());
        }
        for(int i=0; i<vec.size(); i++)
        {
            System.out.println(vec.get(i));
        }
        return vec;

    }
}