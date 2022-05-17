package Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
class DB_Connect {
    public static void main(String[] args) {
        Connection connection;
        ResultSet rs; // 가져올 데이터
        Statement st; // 상태
        String url = "jdbc:mysql://203.255.57.227:3306/DBID?serverTimezone=UTC";
        String user = "dbid";
        String password = "DBID!!!dbpw1";
        String driverName = "com.mysql.cj.jdbc.Driver";
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
}