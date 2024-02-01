package work_user;

public class UserVo {
	
	// 필드
	private String user_id;
	private int department_id;
	private String pw;
	private String user_name;
	private String address;
	private String user_hp;
	private String user_email;
	private String hire_date;
	
	// 생성자
	public UserVo() {
		super();
	}
	
	

	public UserVo(String user_id, String pw) {		//생성자 추가
		super();
		this.user_id = user_id;
		this.pw = pw;
	}



	public UserVo(String user_id, int department_id, String pw, String user_name, String address, String user_hp,
			String user_email, String hire_date) {
		super();
		this.user_id = user_id;
		this.department_id = department_id;
		this.pw = pw;
		this.user_name = user_name;
		this.address = address;
		this.user_hp = user_hp;
		this.user_email = user_email;
		this.hire_date = hire_date;
	}

	// 메소드 - getter/setter
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUser_hp() {
		return user_hp;
	}

	public void setUser_hp(String user_hp) {
		this.user_hp = user_hp;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getHire_date() {
		return hire_date;
	}

	public void setHire_date(String hire_date) {
		this.hire_date = hire_date;
	}

	// 메소드 - 일반
	@Override
	public String toString() {
		return "UserVo [user_id=" + user_id + ", department_id=" + department_id + ", pw=" + pw + ", user_name="
				+ user_name + ", address=" + address + ", user_hp=" + user_hp + ", user_email=" + user_email
				+ ", hire_date=" + hire_date + "]";
	}
	
	
	
	
}