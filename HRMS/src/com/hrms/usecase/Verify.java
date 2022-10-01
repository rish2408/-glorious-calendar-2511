package com.hrms.usecase;

import java.util.Scanner;

public class Verify {

public static String passwordCredentials(String s) {
		
		String  message = null;
		
		if(s.length() < 4) {
			message = "Password Length Must Be Greater Or Equal To 4";
		}else if(s.contains("@") || s.contains("#") || s.contains("$") || s.contains("%") || s.contains("&")) {
			
			message = "Strong Password";
			
		}else {
			message = "Password Must Contain At Least One Character From -->  @,#,$,%,& ";
		}
		
		
		return message;
		
	}
	
	public static String checkPassword() {
		
	
		
		boolean flag1 = true;
		Scanner sc = new Scanner(System.in);
		String password=null;
		while(flag1) {
			System.out.println("Give Employee Default Password");
			
			String pass = sc.nextLine();
			password = pass;
			String res = Verify.passwordCredentials(pass);
			if(res.equals("Strong Password")) {
				flag1 = false;
			}else {
			System.out.println(res);
			System.out.println("==============================");
			}
		}
		
		return password;
		
	}
	
	public static String emailCredentials(String e) {
		
		String message = "Strong Email";
		
		if(e.length() < 11) {
			message = "Length Of Email Must Be Greater Than 10 Including ->  @gmail.com";
		}else if(e.contains("!") || e.contains("#") || e.contains("$") || e.contains("%") || e.contains("&")) {
			
			if(e.contains("@gmail.com")) {
				
				message = "Strong Email";
			}else {
				message = "Email Must Contain ->  @gmail.com";
			}
			
		}else {
			message = "Email Must Contain At Least One Character From -->  !,#,$,%,&";
		}
		
		
		return message;
	}
	
	public static String checkEmail() {
		
		boolean flag2 = true;
		Scanner sc = new Scanner(System.in);
		String email=null;
		while(flag2) {
			System.out.println("Enter Employee Email");
			String e = sc.nextLine();
			email = e;
			String res = Verify.emailCredentials(e);
			if(res.equals("Strong Email")) {
				flag2 = false;
			}else {
			System.out.println(res);
			System.out.println("=====================");
			}
		}
		return email;
		
	}
	
	public static String checkSalary() {
		
		Scanner s = new Scanner(System.in);
		boolean flag3 = true;
		String salary=null;
		while(flag3) {
		System.out.println("Enter Employee Salary");
		String sal = s.nextLine();
			try {
				Double x = Double.parseDouble(sal);
				salary = x+"";
				flag3 = false;
				
				}catch(Exception e) {
					System.out.println("please enter number only");
					System.out.println("========================");
				}
		}
		
		return salary;
	}
	
	public static String checkDeptid() {
		
		Scanner s = new Scanner(System.in);
		boolean flag = true;
		String deptid=null;
		while(flag) {
		System.out.println("Enter department ID");
		String did = s.nextLine();
			try {
				int x = Integer.parseInt(did);
				deptid = x+"";
				flag = false;
				}catch(Exception e) {
					System.out.println("please enter number only");
					System.out.println("========================");
				}
		}
		
		return deptid;
	}
	
	public static String checkEmpId() {
		
		Scanner s = new Scanner(System.in);
		boolean flag = true;
		String deptid=null;
		while(flag) {
		System.out.println("Enter Employee ID");
		String did = s.nextLine();
			try {
				int x = Integer.parseInt(did);
				deptid = x+"";
				flag = false;
				}catch(Exception e) {
					System.out.println("please enter number only");
					System.out.println("=========================");
				}
		}
		
		return deptid;
	}

	public static String checkLeaveDur() {
		
		Scanner s = new Scanner(System.in);
		boolean flag = true;
		String deptid=null;
		while(flag) {
		System.out.println("Enter Leave duration in days");
		String did = s.nextLine();
			try {
				int x = Integer.parseInt(did);
				deptid = x+"";
				flag = false;
				}catch(Exception e) {
					System.out.println("please enter number only");
					System.out.println("=========================");
				}
		}
		
		return deptid;
	}
	
	public static String checkChoicesForEmp() {
		
		Scanner s = new Scanner(System.in);
		boolean flag = true;
		String deptid=null;
		while(flag) {
		System.out.println("Enter Valid Number to Run The Application\n Press 1 to View Your Profile \n Press 2 to Update Your Profile \n Press 3 to Apply For Leave \n Press 4 to Check the Leave Response From Admin \n Press 5 to Go Back To Home Screen \n Press 6 to Exit ");
		System.out.println("=====================================================");
		String did = s.nextLine();
			try {
				int x = Integer.parseInt(did);
				deptid = x+"";
				flag = false;
				}catch(Exception e) {
					System.out.println("please enter number only");
				}
		}
		
		return deptid;
	}
	
	public static String checkChoicesForAdmin() {
		
		Scanner s = new Scanner(System.in);
		boolean flag = true;
		String deptid=null;
		while(flag) {
		System.out.println("Enter Valid Number to Run The Application\n Press 1 to Update Your Profile \n Press 2 to Add New Employee \n Press 3 to Transfer Employee from One Department To Another \n Press 4 to Get All The Employees Details \n Press 5 to Add New Department \n Press 6 to Update Department Name \n Press 7 to Get All Departments Details \n Press 8 to Get All Employees Leave Details \n Press 9 to Respond To Employee Leave \n Press 10 to Go Back to Home screen \n Press 11 to Exit");
		System.out.println("======================================================");
		String did = s.nextLine();
			try {
				int x = Integer.parseInt(did);
				deptid = x+"";
				flag = false;
				}catch(Exception e) {
					System.out.println("please enter number only");
					System.out.println("=========================");
				}
		}
		
		return deptid;
	}
	
	public static String checkPass() {
		
	
		
		boolean flag1 = true;
		Scanner sc = new Scanner(System.in);
		String password=null;
		while(flag1) {
			System.out.println("Enter Your Password");
			String pass = sc.nextLine();
			password = pass;
			String res = Verify.passwordCredentials(pass);
			if(res.equals("Strong Password")) {
				flag1 = false;
			}else {
			System.out.println(res);
			System.out.println("====================================================");
			}
		}
		
		return password;
		
	}
	
	public static String checkemail() {
		
		boolean flag2 = true;
		Scanner sc = new Scanner(System.in);
		String email=null;
		while(flag2) {
			System.out.println("Enter Your Email");
			String e = sc.nextLine();
			email = e;
			String res = Verify.emailCredentials(e);
			if(res.equals("Strong Email")) {
				flag2 = false;
			}else {
			System.out.println(res);
			System.out.println("================================================");
			}
		}
		return email;
		
	}
}
