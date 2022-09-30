package com.hrms.usecase;

import java.util.List;

import com.hrms.bean.Leave;
import com.hrms.dao.AdminDao;
import com.hrms.dao.AdminDaoImpl;
import com.hrms.exception.LeaveException;

public class GetAllLeaves {

	public static void main(String[] args) {
		AdminDao ad = new AdminDaoImpl();

		try {
			List<Leave> list = ad.getAllLeavesRequest();

			for (Leave l : list) {
				System.out.println("Leave Number is :  " + l.getLeaveNum());
				System.out.println("Employee Id is :  " + l.getEmpId());
				System.out.println("Employee Name is :  " + l.getName());
				System.out.println("Leave Duration in days :  " + l.getDuration());
				System.out.println("Leave Reason :  " + l.getReason());
				System.out.println("=================================================");
			}

		} catch (LeaveException e) {
			System.out.println(e.getMessage());
		}

	}
}
