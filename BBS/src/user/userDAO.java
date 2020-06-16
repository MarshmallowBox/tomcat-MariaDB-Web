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
			System.out.println("DB���� �Ϸ�");
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
					System.out.println("�α��μ���");				return 1;//�α��� ����
				}
				else {
					System.out.println("��й�ȣ ����ġ");				return 0;//��й�ȣ ����ġ
				}
			}	
		return -1;//���̵� ����
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("�����ͺ��̽�����");
		return -2; //�����ͺ��̽� ����
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
			return pstmt.executeUpdate();  //������ ��� 
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
}
