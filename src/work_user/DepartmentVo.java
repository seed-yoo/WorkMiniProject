package work_user;

public class DepartmentVo {

	// 필드
		private int department_id;
		private String department_name;
		private String team_leader;
		
		// 생성자
		public DepartmentVo() {
			super();
		}

		public DepartmentVo(int department_id, String department_name) {
			super();
			this.department_id = department_id;
			this.department_name = department_name;
		}

		public DepartmentVo(int department_id, String department_name, String team_leader) {
			super();
			this.department_id = department_id;
			this.department_name = department_name;
			this.team_leader = team_leader;
		}
		
		// 메소드 - getter/setter
		public int getDepartment_id() {
			return department_id;
		}

		public void setDepartment_id(int department_id) {
			this.department_id = department_id;
		}

		public String getTeam_leader() {
			return team_leader;
		}

		public void setTeam_leader(String team_leader) {
			this.team_leader = team_leader;
		}

		public String getDepartment_name() {
			return department_name;
		}

		public void setDepartment_name(String department_name) {
			this.department_name = department_name;
		}

		// 메소드 - 일반
		@Override
		public String toString() {
			return "DepartmentVo [department_id=" + department_id + ", team_leader=" + team_leader + ", department_name="
					+ department_name + "]";
		}

}
