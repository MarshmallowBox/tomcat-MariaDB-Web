package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class userDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public userDAO(){
		try {
			String dbURL="jdbc:mysql://localhost:3306/bbs";
			String dbID = "root";
			String dbPassword = "tjdeh";
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL,dbID, dbPassword);
			System.out.println("DB연결 완료");
		}
		catch(Exception e) {
			e.printStackTrace();
			
		}
	}
	public int login(String userID, String userPassword) {
		String SQL = "SELECT userPassword From USER WHERE userID = ?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if(rs.getString(1).contentEquals(userPassword)) {
					System.out.println("로그인성공");				return 1;//로그인 성공
				}
				else {
					System.out.println("비밀번호 불일치");				return 0;//비밀번호 불일치
				}
			}	
		return -1;//아이디가 없음
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("데이터베이스오류");
		return -2; //데이터베이스 오류
	}
	public int join(User user) {
		String SQL = "INSERT INTO USER VALUES(?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getUserID());
			pstmt.setString(2, user.getUserPassword());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getUserGender());
			pstmt.setString(5, user.getUserEmail());
			return pstmt.executeUpdate();  //실행한 결과 
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
}
