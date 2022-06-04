package Database;
import java.sql.*;
import java.time.LocalDate;
import java.util.Vector;
public class DB_Connect {
    static int cnt = 0;
    private float bmi;
    private double[] fInfo = new double[4];
    private String[] user_info = new String[5];
    private String[] user_bmi = new String[2];
    private String userName = "";
    private Connection connection;
    private ResultSet rs;
    private Statement st;
    private String url = "jdbc:mysql://203.255.57.227:3306/DBID?serverTimezone=UTC";
    private String user = "dbid";
    private String password = "DBID!!!dbpw1";
    private String driverName = "com.mysql.cj.jdbc.Driver";
    LocalDate now = LocalDate.now();

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
        boolean flag1 = false;
        boolean flag2 = false;
        try {
            bmi = (float)(Math.round(weight/(height/100 * height/100) * 100) / 100.0);
            String SQL1 = "INSERT INTO User(id, pw, weight, height, sex, age, bmi) VALUES('" + myId + "','" + myPw + "','" +
                    weight + "','" + height + "','" + sex + "','" + age + "'," + bmi + ");";
            PreparedStatement pstmt = connection.prepareStatement(SQL1);
            int re = pstmt.executeUpdate();
            if (re == 1) {
                flag1 = true;
            }
            String SQL2 = "INSERT INTO Achievement(id, date) VALUES('" + myId + "','" + now + "');";
            pstmt = connection.prepareStatement(SQL2);
            re = pstmt.executeUpdate();
            if (re == 1) {
                flag2 = true;
            }
            if(flag1 && flag2) return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean isAdmin(String id) { // 아이디 중복확인
        try {
            String SQL = "SELECT * FROM User WHERE id = '" + id + "';".toString();
            rs = st.executeQuery(SQL);
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

    public String getUser_name(){
        return userName;
    }
    public String[] getUser_info(String id){
        try {
            String SQL = "SELECT weight, height, sex, age, bmi FROM User WHERE id = '" + id + "';";
            rs = st.executeQuery(SQL);
            while(rs.next()) {
                user_info[0] = rs.getString("weight");
                user_info[1] = rs.getString("height");
                user_info[2] = rs.getString("sex");
                user_info[3] = rs.getString("age");
                user_info[4] = rs.getString("bmi");
            }
        } catch (Exception e) {
            System.out.println("[데이터베이스 검색 오류] : " + e.getMessage());
        }
        return user_info;
    }
    public boolean setUser_info(String id, float weight, float height, String sex, int age){
        bmi = (float)(Math.round(weight/(height/100 * height/100) * 100) / 100.0);
        try {
            String SQL = "UPDATE User SET weight=" + weight + ",height=" +
                    height + ",sex='" + sex + "',age="+ age +",bmi = " + bmi + " WHERE ID='"+id+"'";
            PreparedStatement pstmt = connection.prepareStatement(SQL);
            int re = pstmt.executeUpdate();
            if (re == 1) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("[데이터베이스 검색 오류] : " + e.getMessage());
        }
        return false;
    }

    public Vector<String> get_food()
    {
        Vector<String> vec = new Vector<String>();
        try {
            String SQL = "SELECT * FROM Food;";
            rs = st.executeQuery(SQL);
            while(rs.next()) {
                vec.add(rs.getString("food_name"));
            }
        } catch (Exception e) {
            System.out.println("[데이터베이스 검색 오류] : " + e.getMessage());
        }
        return vec;
    }
    public double[] get_fInfo(String fName){
        try {
            String SQL = "SELECT * FROM Food WHERE food_name = '" + fName + "';";
            rs = st.executeQuery(SQL);
            while(rs.next()) {
                fInfo[0] = rs.getDouble("food_cal"); // 칼로리
                fInfo[1] = rs.getDouble("food_car"); // 탄수화물
                fInfo[2] = rs.getDouble("food_pro"); // 단백질
                fInfo[3] = rs.getDouble("food_fat"); // 지방
            }
        } catch (Exception e) {
            System.out.println("[데이터베이스 검색 오류] : " + e.getMessage());
        }
        return fInfo;
    }

    public float get_weight(String userName){

        return 1;
    }
    public String get_date_bmi(String userName){
        try {
            String SQL = "SELECT date, bmi FROM Food WHERE food_name = '" + fName + "';";
            rs = st.executeQuery(SQL);
            while(rs.next()) {
                fInfo[0] = rs.getDouble("food_cal"); // 칼로리
                fInfo[1] = rs.getDouble("food_car"); // 탄수화물
                fInfo[2] = rs.getDouble("food_pro"); // 단백질
                fInfo[3] = rs.getDouble("food_fat"); // 지방
            }
        } catch (Exception e) {
            System.out.println("[데이터베이스 검색 오류] : " + e.getMessage());
        }

        //user_bmi[0]
        return "";
    }


}