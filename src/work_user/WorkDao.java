package work_user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import work_admin.WorkVo;

public class WorkDao {

	// 필드
	List<UserVo> klist = new ArrayList<UserVo>();
	Scanner sc = new Scanner(System.in);
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://192.168.0.59:3306/work_db";
	private String id = "work";
	private String pw = "work";

	// 메소드- 일반

	private void getConnection() {
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver); // 위에 생성자로 올려주고 변수명으로 넣어줌

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error: " + e);
		}

	} // getConnection()끝

	private void close() {
		// 5. 자원정리
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}// close()끝

	public UserVo login(String a,String b ) {// for문을 사용하는게 아닌 쿼리를 이용한 대표적인 로그인 방식
		this.getConnection();
		UserVo userVo = null;
		
		int count=-1;
		
		try {
			this.getConnection();
			// 3. SQL문 준비 / 바인딩 / 실행
			String query="";
			query += " select	user_id, ";
			query += "  		pw ";
			query += " from user ";
			query += " where user_id=? and pw=? ";

			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, a);
			pstmt.setString(2, b);
			
			rs = pstmt.executeQuery();
			
			
			while (rs.next()) {

				a = rs.getString("user_id");
				b = rs.getString("user_id");
				userVo = new UserVo(a, b);
				
			}
			

			// 4.결과처리
			System.out.println(userVo);

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();
		return userVo;

	}

	public List<UserVo> klist() {
		List<UserVo> userList = new ArrayList<UserVo>();// 신규 리스트를 만들어줘야 신규 생성시마다 리스트가 갱신된다

		try {
			this.getConnection();
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " select	user_id, ";
			query += "  		pw ";
			query += " from user ";

			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String a = rs.getString("user_id");
				String b = rs.getString("pw");
				UserVo vo = new UserVo(a, b);
				userList.add(vo);
			}

			// 4.결과처리

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();
		return userList;

	}

	public List<UserVo> kList2(String userId, String userPw) {

		// 리스트 준비
		List<UserVo> userList = new ArrayList<UserVo>();

		this.getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			// - sql문 준비
			String query = "";
			query += " select	user_id, ";
			query += "  		pw ";
			query += " from user ";
			query += " where user_id=? and pw=? ";

			// - 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPw);

			// - 실행
			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {
				String id = rs.getString("user_id");
				String pw = rs.getString("pw");

				UserVo userVo = new UserVo(id, pw);

				userList.add(userVo);
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();

		return userList;
	}

	public void userInsert(String id, int department_id, String pw, String user_name, String user_address,
			String user_hp, String user_email, String hire_date) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://192.168.0.59:3306/work_db";
			conn = DriverManager.getConnection(url, "work", "work");

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " insert into user( ";
			query += "		user_id, ";
			query += "		department_id, ";
			query += "		pw, ";
			query += "		user_name, ";
			query += "		user_address, ";
			query += "		user_hp, ";
			query += "		user_email, ";
			query += "		hire_date) ";
			query += "		values (?,?,?,?,?,?,?,?) ";

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, id);
			pstmt.setInt(2, department_id);
			pstmt.setString(3, pw);
			pstmt.setString(4, user_name);
			pstmt.setString(5, user_address);
			pstmt.setString(6, user_hp);
			pstmt.setString(7, user_email);
			pstmt.setString(8, hire_date);

			int count = pstmt.executeUpdate();
			// System.out.println(count+"건 처리되었습니다");
			System.out.println();
			System.out.println("-----------------------------------------");
			System.out.println("   🙂 회원가입 되었습니다. 로그인 해주세요 🙂");
			System.out.println("-----------------------------------------");
			System.out.println();

			// 4.결과처리

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}

		}

	}

	public void userUpdate(String id, String pw, String name, String hp, String address, String email) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://192.168.0.59:3306/work_db";
			conn = DriverManager.getConnection(url, "work", "work");

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " update user ";
			query += "	set	pw=?, ";
			query += "		user_name=?, ";
			query += "		user_hp=?, ";
			query += "		user_address=?, ";
			query += "		user_email=? ";
			query += " where user_id = ? ";

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, pw);
			pstmt.setString(2, name);
			pstmt.setString(3, hp);
			pstmt.setString(4, address);
			pstmt.setString(5, email);
			pstmt.setString(6, id);

			pstmt.executeUpdate();

			// 4.결과처리

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}

		}

	}

	
}