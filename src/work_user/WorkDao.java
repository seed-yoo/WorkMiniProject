package work_user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WorkDao {
	
	List<UserVo> klist=new ArrayList<UserVo>();
	Scanner sc=new Scanner(System.in);
	
	public List<UserVo> klist() {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");


			String url = "jdbc:mysql://192.168.0.59:3306/work_db";
			conn = DriverManager.getConnection(url, "work", "work");


		    // 3. SQL문 준비 / 바인딩 / 실행
			String query="";
			query+=" select	user_id, ";
			query+="  		pw ";
			query+=" from user ";
					
			pstmt=conn.prepareStatement(query);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				String a=rs.getString("user_id");
				String b=rs.getString("pw");
				UserVo vo=new UserVo(a,b);
				klist.add(vo);
				}
			
			
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
		return klist;

		
	}
	
	public void userInsert(String id, int department_id, String pw, String user_name, String user_address, String user_hp, String user_email, String hire_date) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");


			String url = "jdbc:mysql://192.168.0.59:3306/work_db";
			conn = DriverManager.getConnection(url, "work", "work");


		    // 3. SQL문 준비 / 바인딩 / 실행
			String query="";
			query+=" insert into user( ";
			query+="		user_id, ";
			query+="		department_id, ";
			query+="		pw, ";
			query+="		user_name, ";
			query+="		user_address, ";
		    query+="		user_hp, ";
		    query+="		user_email, ";
		    query+="		hire_date) ";
		    query+="		values (?,?,?,?,?,?,?,?) ";
		    
		    pstmt=conn.prepareStatement(query);
		    
		    pstmt.setString(1, id);
		    pstmt.setInt(2, department_id);
		    pstmt.setString(3, pw);
		    pstmt.setString(4, user_name);
		    pstmt.setString(5, user_address);
		    pstmt.setString(6, user_hp);
		    pstmt.setString(7, user_email);
		    pstmt.setString(8, hire_date);
			
		    int count=pstmt.executeUpdate();
		    //System.out.println(count+"건 처리되었습니다");
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
	
	public void userUpdate(String id, String newID, String pw, String name, String hp, String address, String email) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");


			String url = "jdbc:mysql://192.168.0.59:3306/work_db";
			conn = DriverManager.getConnection(url, "work", "work");


		    // 3. SQL문 준비 / 바인딩 / 실행
			String query="";
			query+=" update user ";
			query+=" set	user_id = ?, ";
			query+="		pw = ?, ";
			query+="		user_name = ?, ";
			query+="		user_hp = ?, ";
			query+="		user_address = ?, ";
			query+="		user_email = ? ";
			query+=" where user_id=? ";
			
			pstmt=conn.prepareStatement(query);
			
			pstmt.setString(1, newID);
			pstmt.setString(2, pw);
			pstmt.setString(3, name);
			pstmt.setString(4, hp);
			pstmt.setString(5, address);
			pstmt.setString(6, email);
			pstmt.setString(7, id);
			
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
	
public void userUpdate(String id, String pw, String name, String hp, String address, String email) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");


			String url = "jdbc:mysql://192.168.0.59:3306/work_db";
			conn = DriverManager.getConnection(url, "work", "work");


		    // 3. SQL문 준비 / 바인딩 / 실행
			String query="";
			query+=" update user ";
			query+=" set	pw = ?, ";
			query+="		user_name = ?, ";
			query+="		user_hp = ?, ";
			query+="		user_address = ?, ";
			query+="		user_email = ? ";
			query+=" where user_id=? ";
			
			pstmt=conn.prepareStatement(query);
			
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