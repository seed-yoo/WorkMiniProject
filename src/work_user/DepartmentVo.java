package work_user;

public class DepartmentVo {

	// 필드
		private int department_id;
		private String user_id;
		private String department_name;
		
		// 생성자
		public DepartmentVo() {
			super();
		}

		public DepartmentVo(int department_id, String department_name) {
			super();
			this.department_id = department_id;
			this.department_name = department_name;
		}
		
		// 메소드 - getter/setter
		public int getDepartment_id() {
			return department_id;
		}

		public void setDepartment_id(int department_id) {
			this.department_id = department_id;
		}

		public String getUser_id() {
			return user_id;
		}

		public void setUser_id(String user_id) {
			this.user_id = user_id;
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
			return "DepartmentVo [department_id=" + department_id + ", user_id=" + user_id + ", department_name="
					+ department_name + "]";
		}

}