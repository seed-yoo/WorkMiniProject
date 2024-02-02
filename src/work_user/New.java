package work_user;

import java.util.Scanner;

public class New {
	
	public static void main(String[] args) {
		
		WorkDao dda = new WorkDao();
		
		Scanner sc=new Scanner(System.in);
		
		String a=sc.nextLine();
		
		String b=sc.nextLine();
		
		WorkDao wd=new WorkDao();
		
		dda.login(a,b);
		
	}
}
