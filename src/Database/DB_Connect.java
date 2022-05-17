package Database;
import java.sql.*;

public class DB_Connect {
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
            System.out.println("[Connection suceesful!]");

        } catch (ClassNotFoundException e) {
            System.out.println("[로드 오류]\n" + e.getStackTrace());
        } catch (SQLException e) {
            System.out.println("[연결 오류]\n" + e.getStackTrace());
        }
    }

    public boolean Enrollment(String myId, String myPw) { // 회원가입
        try {
            String SQL1 = "INSERT INTO tempTable(user_id, user_pw) VALUES('" + myId + "','" + myPw + "');";
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

    public boolean isAdmin(String Id) { // 아이디 중복확인
        try {
            String SQL1 = "SELECT * FROM tempTable WHERE user_id = '" + Id + "';".toString();
            rs = st.executeQuery(SQL1);
            if (rs.next()) {
                if (rs.getString("user_id").equals(Id)) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("데이터베이스 검색 오류 : " + e.getMessage());
        }
        return false;
    }
}