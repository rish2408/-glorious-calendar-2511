package com.hrms.usecase;

import java.util.List;

import com.hrms.bean.Department;
import com.hrms.dao.AdminDao;
import com.hrms.dao.AdminDaoImpl;
import com.hrms.exception.DepartmentException;

public class GetAllDepartments {

	public void getAllDept() {

		AdminDao ad = new AdminDaoImpl();

		try {
			List<Department> list = ad.getAllDepartments();

			if (list.size() != 0) {

				for (Department d : list) {

					System.out.println("Department Id is => " + d.getDepId());
					System.out.println("Department Name is => " + d.getDepName());
					System.out.println("=======================================");

				}

			}

		} catch (DepartmentException e) {

			System.out.println(e.getMessage());
		}

	}
}
