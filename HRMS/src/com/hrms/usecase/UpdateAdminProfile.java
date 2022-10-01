package com.hrms.usecase;

import java.util.Scanner;

import com.hrms.bean.Admin;
import com.hrms.dao.AdminDaoImpl;
import com.hrms.startapp.AdminOrEmployee;

public class UpdateAdminProfile {

	String password;
	String email;

	public UpdateAdminProfile() {
		// TODO Auto-generated constructor stub
	}

	public UpdateAdminProfile(String pass, String email) {
		super();
		this.password = pass;
		this.email = email;
	}

	public void updateProfile() {

		Scanner s = new Scanner(System.in);

		AdminDaoImpl adi = new AdminDaoImpl(password, email);

		System.out.println("enter the name");
		String name = s.nextLine();
		System.out.println("enter the password");
		String pass = s.nextLine();
		System.out.println("enter the email");
		String email = s.nextLine();

		Admin admin = new Admin(name, pass, email);

		String res = adi.updateProfile(admin);
		System.out.println(res);
		AdminOrEmployee.choice();

	}
}
