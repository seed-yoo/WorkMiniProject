package work_user;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserApp {

	public static void main(String[] args) {

		String iD;
		String pW;
		String namE;
		String hP;
		String addresS;
		String emaiL;

		WorkDao dda = new WorkDao();
		HistoryDao historyDao = new HistoryDao();

		Scanner sc = new Scanner(System.in);

		String ID; // 로그인 했을때의 아이디 기록용

		System.out.println();
		System.out.println("\t ꕤ 회원님 환영합니다 ꕤ");
		System.out.println();

		while (true) {
			try {
				System.out.println("============== 목록 ===============");
				System.out.println("1. 로그인");
				System.out.println("2. 회원가입");
				System.out.println("3. 종료");
				System.out.println("==================================");
				System.out.print(">>");
				int a = sc.nextInt();
				if (a == 1) {
					while (true) {
						int rou; // 로그아웃시 메인화면으로 이동용
						rou = 0;
						if (rou == 1) {
							break;
						}
						while(true) {
							int ii;
							ii=0;
						
						System.out.println();
						System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
						System.out.println("               로그인  ");
						System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
						System.out.println("* 돌아가시려면 아이디에 '/q'를 입력해주세요");
						System.out.println();

						int p;
						p = 0;
						System.out.print("아이디:");
						String A = sc.next();
						ID = A;
						if (A.equals("/q")) { // 아이디에서 바로 탈출
							System.out.println();
							System.out.println("\t▷ 초기화면으로 돌아갑니다 ◁");
							System.out.println();
							p = 1;
							break;
						}
						
						System.out.print("비밀번호:");
						String B = sc.next();
						List<UserVo> authorList = dda.klist();
						for (UserVo authorVo : authorList) {
							String id = authorVo.getUser_id();
							String pw = authorVo.getPw();
							if ((A.equals(id) && B.equals(pw))) {
								System.out.println();
								System.out.println("-----------------------------------------");
								System.out.println("\t    🙂 로그인 되었습니다 🙂");
								System.out.println("-----------------------------------------");
								System.out.println();
								ii=1;
							}
						}if(ii==1) {
							break;
						}else {
							System.out.println("없는 아이디입니다");
						}
						}
								while (true) {
									try {
										System.out.println("=================  메뉴  =================");
										System.out.println("1. 나의 정보 수정");
										System.out.println("2. 나의 근태 히스토리");
										System.out.println("3. 나의 근태 히스토리 관리");
										System.out.println("4. 로그아웃");
										System.out.println("=========================================");
										System.out.print(">>");
										int le = sc.nextInt();
										if (le == 1) {

//											String iD;
//											String pW;
//											String namE;
//											String hP;
//											String addresS;
//											String emaiL;

											System.out.println();
											System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
											System.out.println("             나의 정보 수정");
											System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");

											
											
											System.out.print("▪ 비밀번호(20자 이하):");
											sc.nextLine();
											String aw=sc.nextLine();
											pW = aw;

											System.out.print("▪ 이름:");
											String ae = sc.nextLine();
											namE = ae;

											System.out.print("▪ 휴대폰 번호:");
											String at = sc.nextLine();
											hP = at;
											
											System.out.print("▪ 주소(20자 이하):");
											String ar = sc.nextLine();
											addresS = ar;

											System.out.print("▪ 이메일:");
											String ay = sc.nextLine();
											emaiL = ay;

											dda.userUpdate(ID, pW, namE, hP, addresS, emaiL); // 부서는 바꿀수 없다 외부키 지정

										} else if (le == 2) {
											// 근태 히스토리 창 출력
											System.out.println();
											System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
											System.out.println("             나의 근태 히스토리");
											System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");

											List<WorkVo> workList = historyDao.workList(ID);
											System.out.println("-----------+-------------+");
											System.out.println("날짜" + "\t   |" + "근무상태" + "\t |");
											System.out.println("-----------+-------------+");
											for (WorkVo vo : workList) {
												if ((vo.getWorkState()).equals("무단결근")) {
													System.out.println(
															vo.getWorkDate() + " |" + vo.getWorkState() + "\t |");
												} else {
													System.out.println(
															vo.getWorkDate() + " |" + vo.getWorkState() + "\t\t |");
												}
												System.out.println("-----------+-------------+");
											}
											System.out.println();

										} else if (le == 3) {
											// 근태 히스토리 관리창 출력
											System.out.println();
											System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
											System.out.println("           나의 근태 히스토리 관리");
											System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
											while (true) {
												System.out.println("============  메 뉴  ===========");
												System.out.println("1.등록");
												System.out.println("2.수정");
												System.out.println("3.나가기");
												System.out.println("===============================");

												System.out.print("원하는 메뉴번호를 입력해주세요 >>");
												int n = sc.nextInt();

												if (n == 1) {
													// 등록
													System.out.println();
													System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
													System.out.println("      나의 근태 히스토리 관리 ▶ 등록");
													System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");

													System.out.println("등록하고 싶은 날짜를 입력해주세요(xxxx-xx-xx).");
													System.out.print(">");
													String work_date = sc.next();

													while (true) {
														System.out.print(">근무상태:");
														String work_state = sc.next();
														if (work_state.equals("근무") || work_state.equals("휴가")
																|| work_state.equals("병가")
																|| work_state.equals("무단결근")) {
															System.out.println(
																	"날짜 : " + work_date + ", 근무상태: " + work_state);
															WorkVo workVo = new WorkVo(ID, work_state, work_date); // 나중에
															// 바꾸기
															historyDao.workInsert(workVo);
															System.out.println();
															System.out.println("🙂 등록되었습니다 🙂");
															System.out.println();
															break;
														} else {
															System.out.println();
															System.out.println("☹️ 다시 입력해주세요");
															System.out.println();
														}
													}

												} else if (n == 2) {
													// 수정
													System.out.println();
													System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
													System.out.println("      나의 근태 히스토리 관리 ▶ 수정");
													System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
													System.out.println("수정하고 싶은 날짜를 입력해주세요(xxxx-xx-xx)");
													System.out.print(">");
													String work_date = sc.next();

													while (true) {
														System.out.print(">근무상태:");
														String work_state = sc.next();
														if (work_state.equals("근무") || work_state.equals("휴가")
																|| work_state.equals("병가")
																|| work_state.equals("무단결근")) {
															System.out.println(
																	"날짜 : " + work_date + ", 근무상태: " + work_state);
															historyDao.workUpdate(ID, work_date, work_state);
															break;
														} else {
															System.out.println();
															System.out.println("☹️ 다시 입력해주세요");
															System.out.println();
														}
													}

												} else if (n == 3) {
													System.out.println();
													System.out.println("\t▷ 히스토리 관리를 종료합니다 ◁");
													System.out.println();
													break;
												} else {
													System.out.println();
													System.out.println("☹️ 다시 입력해주세요");
													System.out.println();
												}

											}

										} else if (le == 4) {
											System.out.println("-----------------------------------------");
											System.out.println("          🙂 로그아웃 되었습니다 🙂");
											System.out.println("-----------------------------------------");
											rou = 1; // 로그아웃시 메인화면으로 이동용
											break;
										} else {
											System.out.println();
											System.out.println("☹️ 다시 입력해주세요");
											System.out.println();
										}
									} catch (InputMismatchException e) {
										System.out.println();
										System.out.println("☹️ 다시 입력해주세요");
										System.out.println();
										sc = new Scanner(System.in);
									}
								}
							

					
						if (rou == 0) {
							System.out.println();
							System.out.println("☹️ 없는 아이디입니다");
							System.out.println();
						} else {
							break;
						}
					}
				} else if (a == 2) {
					while (true) {
						String kq = null; // user_id
						int kw = 0; // department_id
						String ke; // pw
						String kr; // name
						String kt; // address
						String ky; // hp
						String ku; // email
						String ki; // hire
						int kk;// 특정구문으로 탈출하기
						kk = 0;

						while (true) {
							System.out.println();
							System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
							System.out.println("               회원가입  ");
							System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
							System.out.println("* 돌아가시려면 아이디에 '/q'를 입력해주세요");
							System.out.println();
							System.out.print("▪ 아이디(20자 내외):");
							int zx;
							zx = 1;
							String kid = sc.next();
							if (kid.equals("/q")) {// 특정구문으로 탈출하기
								kk = 1;
							}

							List<UserVo> authorList = dda.klist();
							for (UserVo authorVo : authorList) {
								String id = authorVo.getUser_id();
								if (kid.equals(id)) {
									zx = 0;
								}
							}

							if ((zx == 1 && kk == 0)) {// 특정구문으로 탈출하기
								System.out.println(" ⤷ 사용할 수 있는 아이디입니다");
								kq = kid;
								break;
							} else if (kk == 1) {// 특정구문으로 탈출하기
								break;
							} else {
								System.out.println(" ⤷ 사용할 수 없는 아이디입니다");
							}

						}
						if (kk == 1) {// 특정구문으로 탈출하기
							System.out.println();
							System.out.println("\t▷ 초기화면으로 돌아갑니다 ◁");
							System.out.println();
							break;
						}

						System.out.println();
						System.out.println("    ▫ 부서아이디 선택 ▫");
						List<DepartmentVo> departmentList = historyDao.departmentList();
						System.out.println("---------+---------------+");
						System.out.println("부서아이디" + "\t |" + "부서이름" + "\t |");
						System.out.println("---------+---------------+");

						for (DepartmentVo dVo : departmentList) {
							System.out.print(dVo.getDepartment_id() + "." + "\t |");
							System.out.println(dVo.getDepartment_name() + "\t\t |");
							System.out.println("---------+---------------+");
						}

						while (true) {
							try {
								System.out.print("▪ 부서아이디:");
								int aq = sc.nextInt();
								int mmmm = 0;

								for (DepartmentVo dVo : departmentList) {
									int di = dVo.getDepartment_id();
									if (aq == di) {
										mmmm = 1;
									}
								}
								if (mmmm == 1) {
									kw = aq;
									break;
								}

								System.out.println();
								System.out.println("☹️ 없는 부서번호입니다");
								System.out.println();
							} catch (InputMismatchException e) {
								System.out.println();
								System.out.println("☹️ 잘못된 입력입니다 ");
								System.out.println();
								sc = new Scanner(System.in);
							}

						}

						if (kk == 1) {// 특정구문으로 탈출하기
							System.out.println();
							System.out.println("\t▷ 초기화면으로 돌아갑니다 ◁");
							System.out.println();
							break;
						}

						System.out.print("▪ 비밀번호(20자 이하):");
						String aw = sc.next();
						if (aw.equals("/q")) {// 특정구문으로 탈출하기
							System.out.println();
							System.out.println("\t▷ 초기화면으로 돌아갑니다 ◁");
							System.out.println();
							kk = 1;
						}
						if (kk == 1) {
							break;
						}
						ke = aw;

						System.out.print("▪ 이름:");
						String ae = sc.next();
						sc.nextLine();
						if (ae.equals("/q")) {// 특정구문으로 탈출하기
							System.out.println();
							System.out.println("\t▷ 초기화면으로 돌아갑니다 ◁");
							System.out.println();
							kk = 1;
						}
						if (kk == 1) {
							break;
						}
						kr = ae;

						System.out.print("▪ 주소(20자 이하):");
						String ar = sc.nextLine();
						if (ar.equals("/q")) {// 특정구문으로 탈출하기
							System.out.println();
							System.out.println("\t▷ 초기화면으로 돌아갑니다 ◁");
							System.out.println();
							kk = 1;
						}
						if (kk == 1) {
							break;
						}
						kt = ar;

						System.out.print("▪ 휴대폰 번호:");
						String at = sc.nextLine();
						if (at.equals("/q")) {// 특정구문으로 탈출하기
							System.out.println();
							System.out.println("\t▷ 초기화면으로 돌아갑니다 ◁");
							System.out.println();
							kk = 1;
						}
						if (kk == 1) {
							break;
						}
						ky = at;
						System.out.print("▪ 이메일:");
						String ay = sc.nextLine();
						if (ay.equals("/q")) {// 특정구문으로 탈출하기
							kk = 1;
						}
						if (kk == 1) {
							System.out.println();
							System.out.println("\t▷ 초기화면으로 돌아갑니다 ◁");
							System.out.println();
							break;
						}
						ku = ay;
						System.out.print("▪ 입사일(YYYY-MM-DD):");
						String au = sc.nextLine();
						if (au.equals("/q")) {// 특정구문으로 탈출하기
							kk = 1;
						}
						if (kk == 1) {
							System.out.println();
							System.out.println("\t▷ 초기화면으로 돌아갑니다 ◁");
							System.out.println();
							break;
						}
						ki = au;

						dda.userInsert(kq, kw, ke, kr, kt, ky, ku, ki);
						break;
					}
				}

				else if (a == 3) {
					System.out.println();
					System.out.println("\t▶ 종료되었습니다 ◀");
					System.out.println();
					break;

				} else {
					System.out.println();
					System.out.println("☹️ 잘못된 입력입니다 ");
					System.out.println();
				}

			} catch (InputMismatchException e) {
				System.out.println();
				System.out.println("☹️ 잘못된 입력입니다 ");
				System.out.println();
				sc = new Scanner(System.in);
			}
		}
	}
}
