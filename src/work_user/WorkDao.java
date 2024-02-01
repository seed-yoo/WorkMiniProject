package work_user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WorkDao {

	// 필드
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

	// 직원리스트(근태현황)
	public List<WorkVo> eplyList() {
		this.getConnection();

		List<WorkVo> eplyList = new ArrayList<WorkVo>();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// -sql문 준비
			String query = "";
			query += " select 	u.user_id ";
			query += " 			,user_name ";
			query += "			,department_name ";
			query += "			,count(case when state = '근무' then 1 end) as '근무' ";
			query += "			,count(case when state = '휴가' then 1 end) as '휴가' ";
			query += "			,count(case when state = '병가' then 1 end) as '병가' ";
			query += "			,count(case when state = '무단결근' then 1 end) as '무단결근' ";
			query += " from user u left join work w ";
			query += " 				on u.user_id = w.user_id ";
			query += " 				left join department d ";
			query += " 				on u.department_id = d.department_id ";
			query += " group by u.user_id; ";

			// -바인딩
			pstmt = conn.prepareStatement(query);

			// -실행
			rs = pstmt.executeQuery();

			// 결과처리
			while (rs.next()) {
				String id = rs.getString("user_id");
				String name = rs.getString("user_name");
				String dptmtName = rs.getString("department_name");
				int cWork = rs.getInt("근무");
				int cRest = rs.getInt("휴가");
				int cSick = rs.getInt("병가");
				int cRun = rs.getInt("무단결근");

				WorkVo workVo = new WorkVo(id, name, dptmtName, cWork, cRest, cSick, cRun);
				eplyList.add(workVo);

			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		this.close();
		return eplyList;

	}

	// 근태현황 수정
	// work리스트
	public List<WorkVo> workList(String userId) {

		// 리스트 준비
		List<WorkVo> workList = new ArrayList<WorkVo>();

		this.getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			// - sql문 준비
			String query = "";
			query += "select user_id, ";
			query += "       state, ";
			query += "       date_format(work_date, '%Y-%m-%d') work_date ";
			query += " from work ";
			query += " where user_id=? ";

			// - 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);

			// - 실행
			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {
				String id = rs.getString("user_id");
				String state = rs.getString("state");
				String work_date = rs.getString("work_date");

				WorkVo workVo = new WorkVo(id, state, work_date);

				workList.add(workVo);
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();

		return workList;
	}

	// work수정
	public int workUpdate(String userId, String workDate, String workState) {
		int count = -1;

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
			// 4.결과처리
			//System.out.println(count + "건 수정 되었습니다.");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		this.close();
		return count;
	}

	// 부서 리스트
	public List<DepartmentVo> departmentList() {

		// 리스트 준비
		List<DepartmentVo> departmentList = new ArrayList<DepartmentVo>();

		this.getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			// - sql문 준비
			String query = "";
			query += " select department_id, ";
			query += "        user_id, ";
			query += "        department_name ";
			query += " from department ";

			// - 바인딩
			pstmt = conn.prepareStatement(query);

			// - 실행
			rs = pstmt.executeQuery();

			// 검색결과에서 데이터 꺼내기
			while (rs.next()) {

				int department_id = rs.getInt("department_id");
				String user_id = rs.getString("user_id");
				String department_name = rs.getString("department_name");

				// Vo묶기
				DepartmentVo departmentVo = new DepartmentVo(department_id, user_id, department_name);

				// 리스트에 추가
				departmentList.add(departmentVo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();

		return departmentList;
	}

	// 부서 등록
	public int departmentInsert(DepartmentVo departmentVo) {

		int count = -1;

		this.getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			// - sql문 준비
			String query = "";
			query += " insert into department ";
			query += " value (?, ?, ?) ";

			// - 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, departmentVo.getDepartment_id());
			pstmt.setString(2, departmentVo.getUser_id());
			pstmt.setString(3, departmentVo.getDepartment_name());

			// - 실행
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 등록 되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();

		return count;
	}

	// 부서 수정
	public int departmentUpdate(DepartmentVo departmentVo) {

		int count = -1;

		this.getConnection();
		// 0. import java.sql.*;

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			// - sql문 준비

			String query = "";
			query += " update department ";
			query += " set user_id = ?, ";
			query += " 	   department_name = ? ";
			query += " where department_id = ? ";
			// - 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, departmentVo.getUser_id());
			pstmt.setString(2, departmentVo.getDepartment_name());
			pstmt.setInt(3, departmentVo.getDepartment_id());

			// - 실행
			// count = pstmt.executeUpdate();

			// 4.결과처리
			// System.out.println(count + "건 수정 되었습니다.");
			// System.out.println("수정완료");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		this.close();
		return count;

	}

	// 부서 삭제
	public int departmentDelete(DepartmentVo departmentVo) {

		int count = -1;

		this.getConnection();
		// 0. import java.sql.*;

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			// - sql문 준비
			String query = "";
			query += " delete from department ";
			query += " where department_id = ? ";
			// - 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, departmentVo.getDepartment_id());

			// - 실행
			count = pstmt.executeUpdate();

			// 4.결과처리
			// System.out.println(count + "건 수정 되었습니다.");
			System.out.println("삭제완료");

		} catch (SQLException e) {
			System.out.println("error: " + e);
		} catch (NullPointerException e) {
			System.out.println("부서번호를 확인해주세요");
		}
		this.close();
		return count;

	}

	/*
	 * public int departmentFinsert(int n) {
	 * 
	 * int count = -1;
	 * 
	 * this.getConnection(); // 0. import java.sql.*;
	 * 
	 * try {
	 * 
	 * // 3. SQL문 준비 / 바인딩 / 실행 // - sql문 준비
	 * 
	 * String query = ""; query += " set foreign_key_checks = 0 ";
	 * 
	 * // - 바인딩 pstmt = conn.prepareStatement(query); //pstmt.setInt(1, n);
	 * 
	 * 
	 * // - 실행 count = pstmt.executeUpdate();
	 * 
	 * // 4.결과처리 // System.out.println(count + "건 수정 되었습니다."); //
	 * System.out.println("수정완료");
	 * 
	 * } catch (SQLException e) { System.out.println("error:" + e); } this.close();
	 * return count;
	 * 
	 * }
	 * 
	 */
}