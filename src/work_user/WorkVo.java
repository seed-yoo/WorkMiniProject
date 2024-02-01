package work_user;

public class WorkVo {
	//필드
	private String userId; //유저 아이디
	private String workDate; //근무일
	private String workState; //근태상태(근무, 휴가, 병가, 무단결석)
	//생성자
	public WorkVo() {
		
	}
	
	public WorkVo(String userId,String workState,  String workDate) {
		this.userId  = userId;
		this.workState = workState;
		this.workDate = workDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getWorkDate() {
		return workDate;
	}

	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}

	public String getWorkState() {
		return workState;
	}

	public void setWorkState(String workState) {
		this.workState = workState;
	}

	@Override
	public String toString() {
		return "WorkVo [userId=" + userId + ", workDate=" + workDate + ", workState=" + workState + "]";
	}
	
	
	
}
