package work_admin;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class WorkApp {

	public static void department_admin(DepartmentVo departmentVo, Scanner sc) {
		int num;		
		while (true) {
			System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
			System.out.println("              부서 관리  ");
			System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
			System.out.println();
			System.out.println("============== 목록 ===============");
			System.out.println("1. 등록");
			System.out.println("2. 수정");
			System.out.println("3. 삭제");
			System.out.println("4. 돌아가기");
			System.out.println("==================================");
			System.out.print(">>");

			try {
				num = sc.nextInt();

				System.out.println();

				if (num == 1) {
					System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
					System.out.println("           부서 관리 ▶ 등록 ");
					System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
					System.out.println();
					System.out.print("▪ 부서번호를 입력해주세요 >>");
					int dno = sc.nextInt();
					sc.nextLine();
					System.out.print("▪ 유저아이디를 입력해주세요 >>");
					String uid = sc.nextLine();
					System.out.print("▪ 부서이름을 입력해주세요 >>");
					String dname = sc.nextLine();

					departmentVo = new DepartmentVo(dno, uid, dname);
					WorkDao workDao = new WorkDao();
					workDao.departmentInsert(departmentVo);

				} else if (num == 2) {
					System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
					System.out.println("           부서 관리 ▶ 수정 ");
					System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
					System.out.println();
					System.out.print("▪ 수정 할 부서번호를 입력해주세요 >>");
					int dno = sc.nextInt();
					sc.nextLine();
					System.out.print("▪ 수정 할 유저아이디를 입력해주세요 >>");
					String uid = sc.nextLine();
					System.out.print("▪ 수정 할 부서이름을 입력해주세요 >>");
					String dname = sc.nextLine();

					departmentVo = new DepartmentVo(dno, uid, dname);
					WorkDao workDao = new WorkDao();
					workDao.departmentUpdate(departmentVo);

				} else if (num == 3) {
					System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
					System.out.println("           부서 관리 ▶ 삭제 ");
					System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
					System.out.println();
					System.out.print("▪ 삭제 할 부서번호를 입력해주세요 >>");
					int dno = sc.nextInt();
					sc.nextLine();
					String uid = null;
					String dname = null;

					departmentVo = new DepartmentVo(dno, uid, dname);
					WorkDao workDao = new WorkDao();
					workDao.departmentUpdate(departmentVo);

				} else if (num == 4) {
//					System.out.println("나가기");
					System.out.println();
					System.out.println("\t▷ 초기화면으로 돌아갑니다 ◁");
					System.out.println();
					break;
				} else {
					System.out.println();
					System.out.println("☹️ 다시 입력해주세요");
					System.out.println();
				}
			} catch (InputMismatchException e) {
				System.out.println();
				System.out.println(" \t⫸ 숫자로 입력해주세요 ⫷ ");
				System.out.println();
				sc = new Scanner(System.in);
			}
			
			
		}
	}

	public static void main(String[] args) {
		WorkDao workDao = new WorkDao();
		DepartmentVo departmentVo = new DepartmentVo();
		Scanner sc = new Scanner(System.in);
		System.out.println();
		System.out.println("\t ꕤ 관리자님 환영합니다 ꕤ");
		System.out.println();

		while (true) { // 종료 설정 없음
			try {
				System.out.println("============== 전체메뉴 =============");
				System.out.println("1. 직원 리스트");
				System.out.println("2. 직원 근태수정");
				System.out.println("3. 부서 리스트");
				System.out.println("4. 부서 관리");
				System.out.println("5. 종료");
				System.out.println("===================================");
				System.out.print(">>");
				int a = sc.nextInt();
				System.out.println();
				
				if (a == 1) {
					// 직원 리스트 출력
					System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
					System.out.println("                        직원 리스트 ");
					System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
					List<WorkVo> eplyList = workDao.eplyList();
					System.out.println("---------+-------+-------+-------+-------+-------+-------+");
					System.out.println("직원아이디" + "\t |" + "직원이름" + "\t |" + "부서이름" + "\t |" + "근무" + "\t |" + "휴가" + "\t |"
							+ "병가" + "\t |" + "무단결근" + "\t |");
					System.out.println("---------+-------+-------+-------+-------+-------+-------|");
	//				System.out.println("=========+=======+=======+=======+=======+=======+=======|");
					for (WorkVo vo : eplyList) {
						System.out.println(vo.getUser_id() + "\t |" + vo.getUser_name() + "\t |" + vo.getDepartment_name()
								+ "\t |" + vo.getCwork() + "\t |" + vo.getCrest() + "\t |" + vo.getCsick() + "\t |"
								+ vo.getCrun() + "\t |");
	//					System.out.println("---------|-------|-------|-------|-------|-------|-------|");
						System.out.println("---------+-------+-------+-------+-------+-------+-------|");
					}
					System.out.println();
	
				} else if (a == 2) {
	
					// 근태현황 수정
					System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
					System.out.println("           직원 근태수정 ");
					System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
					System.out.println();
					// 근태 히스토리 출력
	//		                System.out.println("근태 히스토리 수정");
	
					System.out.println("▪ 수정하고 싶은 직원의 아이디를 입력해주세요");
					System.out.print(">");
					String s = sc.next();
					sc.nextLine();
					
					
					List<WorkVo> workList = workDao.workList(s);
					System.out.println("---------+---------------+-------+");
					System.out.println("직원아이디" + "\t |" + "날짜" + "\t\t |" + "근무상태" + "\t |");
					System.out.println("---------+---------------+-------+");
					for (WorkVo vo : workList) {
						//System.out.println(vo.getUser_id() + "," + vo.getWork_date() + "," + vo.getState());
						System.out.println(vo.getUser_id() + "\t |" + vo.getWork_date() + "\t |" + vo.getState()
						+ "\t |");
						System.out.println("---------+---------------+-------+");
					}
					
					System.out.println("▪ 수정하고 싶은 날짜를 입력해주세요(YYYY-MM-DD)");
					System.out.print(">");
					String work_date = sc.nextLine();
	
					while (true) {
						System.out.println("▪ 근무상태를 입력해주세요(근무/휴무/병가/무단결근)");
						System.out.print(">");
						String work_state = sc.nextLine();
						if (work_state.equals("근무") || work_state.equals("휴가") || work_state.equals("병가")
								|| work_state.equals("무단결근")) {
							System.out.println("날짜 : " + work_date + ", 근무상태: " + work_state);
							workDao.workUpdate(s , work_date, work_state);
							System.out.println("------------------------------------");
							System.out.println("\t    🙂 수정되었습니다 🙂");
							System.out.println("------------------------------------");
							break;
						} else {
							System.out.println();
							System.out.println("☹️ 잘못입력하셨습니다 다시 돌아갑니다");
							System.out.println();
							break;
						}
					}
	
				} else if (a == 3) {
					// 부서리스트 출력
					System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■");
					System.out.println("         부서 리스트 ");
					System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■");
					List<DepartmentVo> departmentList = workDao.departmentList();
					System.out.println("---------+-------+-------+");
					System.out.println("부서번호" + "\t |" + "아이디" + "\t |" + "부서이름" + "\t |");
					System.out.println("---------+-------+-------|");
	
					for (DepartmentVo vo : departmentList) {
	
						System.out.println(vo.getDepartment_id() + "\t |" + vo.getUser_id() + "\t |"
								+ vo.getDepartment_name() + "\t |");
						System.out.println("---------+-------+-------|");
	
					}
					System.out.println();
					
				} else if (a == 4) {
					// 부서 관리
					department_admin(departmentVo, sc);
				} else if (a == 5) {
					System.out.println();
					System.out.println("\t▶ 종료되었습니다 ◀");
					System.out.println();
					break;
				} else {
					System.out.println();
					System.out.println("☹️ 다시 입력해주세요");
					System.out.println();
				}
			} catch (InputMismatchException e) {
				sc = new Scanner(System.in);
				System.out.println();
				System.out.println(" \t⫸ 숫자로 입력해주세요 ⫷ ");
				System.out.println();
			}
			
		}
		sc.close();

	}

}
