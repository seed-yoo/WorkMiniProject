package work_user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HistoryDao {

	// 필드
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://192.168.0.59:3306/work_db";
	private String id = "work";
	private String pw = "work";

	// 생성자

	// 메소드 -gs

	// 메소드 -일반
	public void getConnection() {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);
			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

	}

	public void close() {
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

	// work리스트
	public List<WorkVo> workList(String ID) {
		// 리스트 만들고
		// db에서 데이터 가져오고
		// 리스트에 추가하기
		// 리스트 주소 전달하기

		// 리스트 준비
		List<WorkVo> workList = new ArrayList<WorkVo>();

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2. Connection 얻어오기
			String url = "jdbc:mysql://192.168.0.59:3306/work_db";
			conn = DriverManager.getConnection(url, "work", "work");
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += "select user_id, ";
			query += "       state, ";
			query += "       date_format(work_date, '%Y-%m-%d') work_date ";
			query += " from work ";
			query += " where user_id=? ";

			// -바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, ID);

			// -실행
			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {
				String id = rs.getString("user_id");
				String state = rs.getString("state");
				String work_date = rs.getString("work_date");

				WorkVo workVo = new WorkVo(id, state, work_date);

				workList.add(workVo);
			}

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();
		return workList;
	}

	// work수정
	public int workUpdate(String userId, String workDate, String workState) {
		int count = -1;

		this.getConnection();

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2. Connection 얻어오기
			String url = "jdbc:mysql://192.168.0.59:3306/work_db";
			conn = DriverManager.getConnection(url, "work", "work");
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " update work set state=? where work_date=? and user_id=? ";
			// - 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, workState);
			pstmt.setString(2, workDate);
			pstmt.setString(3, userId);

			// - 실행
			count = pstmt.executeUpdate();
			if (count == 0) {
				throw new SQLException("해당 날짜의 데이터가 존재하지 않습니다.");
			} else {
				// 4.결과처리
				System.out.println("------------------------------------");
				System.out.println("\t    🙂 수정되었습니다 🙂");
				System.out.println("------------------------------------");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("해당 날짜의 데이터가 존재하지 않습니다.");
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
		return count;
	}

	// 작가수정2 -- 주소만 받는 경우(이걸 더 많이 씀)
	public int workUpdate(WorkVo Vo) {
		int count = -1;

		this.getConnection();

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2. Connection 얻어오기
			String url = "jdbc:mysql://192.168.0.59:3306/work_db";
			conn = DriverManager.getConnection(url, "work", "work");
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " update author set state=? where work_date=? ";
			// - 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, Vo.getUserId());
			pstmt.setString(2, Vo.getWorkState());
			pstmt.setString(3, Vo.getWorkDate());

			// - 실행
			count = pstmt.executeUpdate();
			// 4.결과처리
			System.out.println(count + "건 수정 되었습니다.");

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
		return count;
	}

	// 작가등록2
	// dao.workInset("아이디", "날짜,"근태상태");
	public int workInsert(WorkVo Vo) {

		int count = -1;

		// book_db 데이터베이스에 접속
		// book/book
		// author 테이블에 작가를 insert
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2. Connection 얻어오기
			String url = "jdbc:mysql://192.168.0.59:3306/work_db";

			conn = DriverManager.getConnection(url, "work", "work");
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " insert into work ";
			query += " values(null, ?, ?, ? ) ";
			// - 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, Vo.getUserId());
			pstmt.setString(2, Vo.getWorkState());
			pstmt.setString(3, Vo.getWorkDate());
			
			// - 실행
			count = pstmt.executeUpdate();
			// 4.결과처리
			System.out.println(count + "건 등록 되었습니다.");

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
		return count;
	}
	
	//부서리스트 출력
	public List<DepartmentVo> departmentList() {
			List<DepartmentVo> departmentList = new ArrayList<DepartmentVo>();
			
			try {
				// 1. JDBC 드라이버 (Oracle) 로딩
				Class.forName("com.mysql.cj.jdbc.Driver");
				// 2. Connection 얻어오기
				String url = "jdbc:mysql://192.168.0.59:3306/work_db";
				conn = DriverManager.getConnection(url, "work", "work");
				// 3. SQL문 준비 / 바인딩 / 실행
				String query = "";
				query += "select department_id, ";
				query += "       department_name ";
				query += " from department ";

				// -바인딩
				pstmt = conn.prepareStatement(query);

				// -실행
				rs = pstmt.executeQuery();

				// 4.결과처리
				while (rs.next()) {
					int department_id = rs.getInt("department_id");
					String department_name = rs.getString("department_name");

					//WorkVo workVo = new WorkVo(id, state, work_date);
					DepartmentVo departmentVo = new DepartmentVo(department_id, department_name);
					
					departmentList.add(departmentVo);
					//workList.add(workVo);
				}

			} catch (ClassNotFoundException e) {
				System.out.println("error: 드라이버 로딩 실패 - " + e);
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}

			this.close();
			
			return departmentList;
	}
}
