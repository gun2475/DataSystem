package Database;
import java.sql.*;
import java.time.LocalDate;
import java.util.Vector;
public class DB_Connect {
    static int cnt = 0;
    private float bmi;
    private double[] fInfo = new double[4];
    private String[] user_info = new String[5];
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
    ////////////////////////////////////회원가입
    public boolean Enrollment(String myId, String myPw, float weight, float height, String sex, int age) {
        boolean flag1 = false;
        boolean flag2 = false;
        float targetUp_cal;
        float targetDown_cal;

        if(sex.equals("남성")){
            targetUp_cal = ((66 + ((float)13.7 * weight) + (5 * height) - ((float)6.8 * age)) * (float) 1.55) +
                    (((66 + ((float)13.7 * weight) + (5 * height) - ((float)6.8 * age)) * (float) 1.55)*(float)0.2);
            targetDown_cal = ((66 + ((float)13.7 * weight) + (5 * height) - ((float)6.8 * age)) * (float) 1.55) -
                    (((66 + ((float)13.7 * weight) + (5 * height) - ((float)6.8 * age)) * (float) 1.55)*(float)0.2);
        }
        else{
            targetUp_cal = ((665 + ((float)19.6 * weight) + ((float)1.7 * height) - ((float)4.7 * age)) * (float) 1.55) +
                    (((665 + ((float)19.6 * weight) + ((float)1.7 * height) - ((float)4.7 * age)) * (float) 1.55)*(float)0.2);
            targetDown_cal =((665 + ((float)19.6 * weight) + ((float)1.7 * height) - ((float)4.7 * age)) * (float) 1.55) -
                    (((665 + ((float)19.6 * weight) + ((float)1.7 * height) - ((float)4.7 * age)) * (float) 1.55)*(float)0.2);
        }
        try {
            bmi = (float)(Math.round(weight/(height/100 * height/100) * 100) / 100.0);
            String SQL1 = "INSERT INTO User(id, pw, weight, height, sex, age) VALUES('" + myId + "','" + myPw + "','" +
                    weight + "','" + height + "','" + sex + "','" + age + "');";
            PreparedStatement pstmt = connection.prepareStatement(SQL1);
            int re = pstmt.executeUpdate();
            if (re == 1) {
                flag1 = true;
            }
            String SQL2 = "INSERT INTO Date_bmi2(id, date, bmi, targetUp_cal, targetDown_cal) " +
                    "VALUES('" + myId + "','" + now + "','" + bmi + "','" + targetUp_cal + "','" + targetDown_cal + "');";
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
    ////////////////////////////////////아이디 중복확인
    public boolean isAdmin(String id) {
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
    //////////////////////////////////// 로그인
    public boolean login(String id, String pw) {
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
    //////////////////////////////////// 유저 정보 가져오기
    public String[] getUser_info(String id){
        try {
            String SQL = "SELECT weight, height, sex, age, bmi FROM User U join Date_bmi2 D WHERE U.id = '" + id + "' and D.id = '" + id + "';";
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
    ////////////////////////////////////유저정보 수정하기
    public boolean setUser_info(String id, float weight, float height, String sex, int age){
        boolean flag1 = false;
        boolean flag2 = false;
        boolean flag3 = false;
        float targetUp_cal;
        float targetDown_cal;
        if(sex.equals("남성")){
            targetUp_cal = ((66 + ((float)13.7 * weight) + (5 * height) - ((float)6.8 * age)) * (float) 1.55) +
                    (((66 + ((float)13.7 * weight) + (5 * height) - ((float)6.8 * age)) * (float) 1.55)*(float)0.2);
            targetDown_cal = ((66 + ((float)13.7 * weight) + (5 * height) - ((float)6.8 * age)) * (float) 1.55) -
                    (((66 + ((float)13.7 * weight) + (5 * height) - ((float)6.8 * age)) * (float) 1.55)*(float)0.2);
        }
        else{
            targetUp_cal = ((665 + ((float)19.6 * weight) + ((float)1.7 * height) - ((float)4.7 * age)) * (float) 1.55) +
                    (((665 + ((float)19.6 * weight) + ((float)1.7 * height) - ((float)4.7 * age)) * (float) 1.55)*(float)0.2);
            targetDown_cal =((665 + ((float)19.6 * weight) + ((float)1.7 * height) - ((float)4.7 * age)) * (float) 1.55) -
                    (((665 + ((float)19.6 * weight) + ((float)1.7 * height) - ((float)4.7 * age)) * (float) 1.55)*(float)0.2);
        }
        bmi = (float)(Math.round(weight/(height/100 * height/100) * 100) / 100.0);
        try {
            String SQL1 = "UPDATE User SET weight=" + weight + ",height=" +
                    height + ",sex='" + sex + "',age="+ age + " WHERE id='"+id+"'";
            PreparedStatement pstmt = connection.prepareStatement(SQL1);
            int re = pstmt.executeUpdate();
            if (re == 1) {
                flag1 = true;
            }
            String SQL2 = "select date FROM Date_bmi2 where id = '" + id + "' and date = '" + now + "';";
            rs = st.executeQuery(SQL2);
            if(rs.next()){
                if(rs.getString("date").equals(now.toString())){
                    flag3 = true;
                }
            }
            if(flag3 == true){
                String SQL4 = "UPDATE Date_bmi2 SET bmi = '" + bmi + "', targetUp_cal = '" + targetUp_cal + "', " +
                        "targetDown_cal = '" + targetDown_cal + "' WHERE id='" +id+ "' and date = '" + now + "'";
                pstmt = connection.prepareStatement(SQL4);
                re = pstmt.executeUpdate();
                if (re == 1) {
                    flag2 = true;
                }
            }
            else{
                String SQL3 = "INSERT INTO Date_bmi2 (id, date, bmi, targetUp_cal, targetDown_cal) " +
                        "VALUES('" + id + "','" + now + "','" + bmi + "','" + targetUp_cal + "','" + targetDown_cal + "');";
                pstmt = connection.prepareStatement(SQL3);
                re = pstmt.executeUpdate();
                if (re == 1) {
                    flag2 = true;
                }
            }
        } catch (Exception e) {
            System.out.println("[데이터베이스 검색 오류] : " + e.getMessage());
        }
        if(flag1 == true && flag2 == true) return true;
        else return false;
    }
    ////////////////////////////////////음식 가져오기
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
    //////////////////////////////// 음식 정보 가져오기
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
    //////////////////////////////////// 날짜 bmi 가져오기 - Date_bmi join User 테이블에서
    public Vector<Double> get_date_bmi(String userName){
        Vector<Double> data = new Vector<>();
        try {
            String SQL1 = "SELECT date, bmi FROM Date_bmi2 D join User U WHERE U.id = '" + userName + "' and D.id = '" + userName + "';";
            rs = st.executeQuery(SQL1);
            while(rs.next()) {
                LocalDate date = rs.getTimestamp("date").toLocalDateTime().toLocalDate();
                double month = date.getMonthValue();
                double day = date.getDayOfMonth();
                data.add(month);
                data.add(day);
                data.add(rs.getDouble("bmi"));
            }
        }  catch (Exception e) {
            System.out.println("[데이터베이스 검색 오류] : " + e.getMessage());
        }
        return data;
    }
    //////////////////////////////////// 내가 먹은 칼로리 넣기 - bmi테이블로
    public boolean set_myeatCal(String userName, String date, int eatcal)
    {
        boolean flag = false;
        try
        {
            PreparedStatement pstmt;
            int re;
            String SQL1 = "select date FROM Date_bmi2 where id = '" + userName + "' and date = '" + now + "';";
            rs = st.executeQuery(SQL1);
            if(rs.next()){
                if(rs.getString("date").equals(now.toString())){
                    flag = true;
                }
            }
            if(flag == true){
                String SQL2 = "UPDATE Date_bmi2 SET my_cal = '" + eatcal + "' WHERE id='" + userName + "' and date = '" + now + "'";
                pstmt = connection.prepareStatement(SQL2);
                re = pstmt.executeUpdate();
                if (re == 1) {
                    flag = true;
                }
            }
            else{
                String SQL3 = "INSERT INTO Date_bmi2(mycal) VALUES('" + eatcal + "') WHERE id = '" + userName + "', date = '" + date + "';";
                pstmt = connection.prepareStatement(SQL3);
                re = pstmt.executeUpdate();
                if (re == 1) {
                    flag = true;
                }
            }
            if(flag == true) return true;
            else return false;
        }catch (Exception e)
        {
            System.out.println("[데이터베이스 검색 오류] : " + e.getMessage());
        }
        return false;
    }
    //////////////////////////////////// 값 가져오기, bmi테이블에서
    public Vector<String> get_rate(String id){
        Vector<String> data = new Vector<>();
        try {
            String SQL = "SELECT date, my_cal, targetUp_cal, targetDown_cal FROM Date_bmi2 WHERE id = '" + id + "';";
            rs = st.executeQuery(SQL);
            while(rs.next()) {
                LocalDate date = rs.getTimestamp("date").toLocalDateTime().toLocalDate();
                double my_eat = rs.getInt("my_cal");
                double targetUp = rs.getInt("targetUp_cal");
                double targetDown = rs.getInt("targetDown_cal");
                String up_rate = Double.toString(Math.round(my_eat / targetUp * 100));
                String down_rate = Double.toString(Math.round(my_eat / targetDown * 100));
                String md = Integer.toString(date.getMonthValue()) + "월" +
                                Integer.toString(date.getDayOfMonth()) + "일";
                data.add(up_rate);
                data.add(down_rate);
                data.add(md);
            }
        } catch (Exception e) {
            System.out.println("[데이터베이스 검색 오류] : " + e.getMessage());
        }
        return data;
    }
}