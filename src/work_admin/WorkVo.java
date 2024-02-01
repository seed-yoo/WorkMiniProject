package work_admin;

public class WorkVo {

	// 필드
	private int work_id;
	private String user_id;
	private String state;
	private String work_date;
	
	private String user_name;
	private String department_name;
	private int cwork;
	private int crest;
	private int csick;
	private int crun;
	
	// 생성자
	public WorkVo() {
		super();
	}

	public WorkVo(int work_id, String user_id, String state, String work_date) {
		super();
		this.work_id = work_id;
		this.user_id = user_id;
		this.state = state;
		this.work_date = work_date;
	}
	
	public WorkVo(String user_id, String state, String work_date) {
		super();
		this.user_id = user_id;
		this.state = state;
		this.work_date = work_date;
	}
	


	public WorkVo(String user_id, String user_name, String department_name,
			int cwork, int crest, int csick, int crun) {
		this.user_id = user_id;
		this.user_name = user_name;
		this.department_name = department_name;
		this.cwork = cwork;
		this.crest = crest;
		this.csick = csick;
		this.crun = crun;
	}

	// 메소드 - getter/setter
	public int getWork_id() {
		return work_id;
	}

	public void setWork_id(int work_id) {
		this.work_id = work_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getWork_date() {
		return work_date;
	}

	public void setWork_date(String work_date) {
		this.work_date = work_date;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	
	public int getCwork() {
		return cwork;
	}

	public void setCwork(int cwork) {
		this.cwork = cwork;
	}

	public int getCrest() {
		return crest;
	}

	public void setCrest(int crest) {
		this.crest = crest;
	}

	public int getCsick() {
		return csick;
	}

	public void setCsick(int csick) {
		this.csick = csick;
	}

	public int getCrun() {
		return crun;
	}

	public void setCrun(int crun) {
		this.crun = crun;
	}



	// 메소드 - 일반
	@Override
	public String toString() {
		return "WorkVo [work_id=" + work_id + ", user_id=" + user_id + ", state=" + state + ", work_date=" + work_date
				+ ", user_name=" + user_name + ", department_name=" + department_name + ", cwork=" + cwork + ", crest="
				+ crest + ", csick=" + csick + ", crun=" + crun + "]";
	}
//	@Override
//	public String toString() {
//		return "WorkVo [work_id=" + work_id + ", user_id=" + user_id + ", state=" + state + ", work_date=" + work_date
//				+ "]";
//	}
	
	
	
	
	
	
	
}
