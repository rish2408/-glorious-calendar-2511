package com.hrms.usecase;

import java.util.Scanner;

import com.hrms.bean.Employee;
import com.hrms.dao.AdminDaoImpl;

public class AddEmployee {

	public static void main(String[] args) {
		AdminDaoImpl adi = new AdminDaoImpl();
		Scanner s = new Scanner(System.in);
		boolean flag1 = true;
		boolean flag2 = true;
		System.out.println("Enter Employee Name ");
		String name = s.nextLine();
		System.out.println("Enter Employee Salary");
		String salary = s.nextLine();
		System.out.println("Enter Employee Role");
		String role = s.nextLine();
		System.out.println("Enter Employee department ID");
		String deptid = s.nextLine();
		String password = null;
		while (flag1) {
			System.out.println("Give Employee Default Password");
			String pass = s.nextLine();
			password = pass;
			String res = Verify.checkPass(pass);
			if (res.equals("Strong Password")) {
				flag1 = false;
			}
			System.out.println(res);
		}
		String email = null;
		while (flag2) {
			System.out.println("Enter Employee Email");
			String e = s.nextLine();
			email = e;
			String res = Verify.checkEmail(e);
			if (res.equals("Strong Email")) {
				flag2 = false;
			}
			System.out.println(res);

		}

		Employee emp = new Employee(name, salary, role, password, email, deptid);
		String res = adi.addEmployee(emp);
		System.out.println(res);
	}
}
