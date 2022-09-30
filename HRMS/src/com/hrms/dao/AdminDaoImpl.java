package com.hrms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.hrms.bean.Admin;
import com.hrms.bean.Department;
import com.hrms.bean.Employee;
import com.hrms.bean.Leave;
import com.hrms.exception.AdminException;
import com.hrms.exception.DepartmentException;
import com.hrms.exception.EmployeeException;
import com.hrms.exception.LeaveException;
import com.hrms.usecase.Verify;
import com.hrms.utility.DBUtil;

public class AdminDaoImpl implements AdminDao {

	String pass;
	String email;

	public AdminDaoImpl() {

	}

	public AdminDaoImpl(String pass, String email) {
		super();
		this.pass = pass;
		this.email = email;
	}
	
	@Override
	public String updateProfile(Admin admin) {

		String message = "Not Updated";

		try (Connection conn = DBUtil.connection()) {

			PreparedStatement ps = conn.prepareStatement(
					"UPDATE admin SET name = ?,password = ?,email = ? WHERE password =? AND email = ? ");

			ps.setString(1, admin.getName());
			ps.setString(2, admin.getPassword());
			ps.setString(3, admin.getEmail());
			ps.setString(4, pass);
			ps.setString(5, email);

			int i = ps.executeUpdate();

			if (i > 0) {
				message = "Updated Successfully";
			}

		} catch (SQLException e) {

			message = e.getMessage();
		}

		return message;

	}

	public Admin loginForAdmin(String pass, String email) throws AdminException {

		Admin admin = null;

		try (Connection conn = DBUtil.connection()) {

			PreparedStatement ps = conn
					.prepareStatement("SELECT * from Admin WHERE password like binary  ? AND email = ?");

			ps.setString(1, pass);
			ps.setString(2, email);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				String n = rs.getString("name");
				String p = rs.getString("password");
				String e = rs.getString("email");

				admin = new Admin(n, p, e);
			} else {

				throw new AdminException("Invalid Password or Email...");
			}

		} catch (SQLException e) {
			throw new AdminException(e.getMessage());
		}

		return admin;
	}

	public String addEmployee(Employee emp) {

		String message = "Not Insetered";

		try (Connection conn = DBUtil.connection()) {

			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO employee(empName,empSalary,empRole,empPassword,empEmail,did) values(?,?,?,?,?,?)");
			ps.setString(1, emp.getEmpName());
			ps.setDouble(2, Double.parseDouble(emp.getEmpSalary()));
			ps.setString(3, emp.getEmpRole());
			ps.setString(4, emp.getEmpPassword());
			ps.setString(5, emp.getEmpEmail());
			ps.setInt(6, Integer.parseInt(emp.getDeptId()));

			int x = ps.executeUpdate();
			if (x > 0) {
				message = "Employee Successfully Inserted";
			}

			PreparedStatement ps2 = conn.prepareStatement("SELECT empId FROM employee where empPassword = ?");
			ps2.setString(1, emp.getEmpPassword());

			ResultSet rs = ps2.executeQuery();
			int id = -1;
			if (rs.next()) {
				int i = rs.getInt("empId");
				id = i;
			}

			System.out.println("employee id is " + id);

			PreparedStatement ps3 = conn.prepareStatement("INSERT INTO leaveTable(emplName,empId) values (?,?)");
			ps3.setString(1, emp.getEmpName());
			ps3.setInt(2, id);

			ps3.executeUpdate();

		} catch (SQLException e) {

			message = e.getMessage();

		}

		return message;
	}

	public String addDepartment(String deptId, String deptName) {

		String message = "Not Added";

		try (Connection conn = DBUtil.connection()) {

			PreparedStatement ps = conn.prepareStatement("INSERT INTO department values(?,?)");
			ps.setInt(1, Integer.parseInt(deptId));
			ps.setString(2, deptName);

			int x = ps.executeUpdate();
			if (x > 0) {
				message = "New Department Added";
			}

		} catch (Exception e) {

			message = e.getMessage();
		}

		return message;
	}

	public String updateDepartment(String name) {

		String message = "Not Updated";
		boolean flag = true;
		String id = null;
		while (flag) {

			System.out.println("Department Id in which you want to update");
			String deptid = Verify.checkDeptid();
			id = deptid;

			try (Connection conn = DBUtil.connection()) {

				PreparedStatement ps = conn.prepareStatement("Select depName from department where depId = ?");
				ps.setInt(1, Integer.parseInt(deptid));

				ResultSet rs = ps.executeQuery();

				if (rs.next()) {
					flag = false;
				} else {
					System.out.println("No department available for this Id");

				}

			} catch (Exception e) {

			}
		}

		try (Connection conn = DBUtil.connection()) {

			PreparedStatement ps = conn.prepareStatement("UPDATE department SET depName = ? WHERE depId = ?");
			ps.setString(1, name);
			ps.setInt(2, Integer.parseInt(id));

			int x = ps.executeUpdate();

			if (x > 0) {
				message = "Department Updated";
			}

		} catch (Exception e) {
			message = e.getMessage();
		}

		return message;

	}

	public String transferEmployee() {
		Scanner s = new Scanner(System.in);
		String message = "Transfer Failed";
		boolean flag1 = true;
		boolean flag2 = true;
		String empId = null;
		String deptId = null;

		while (flag1) {

			System.out.println("Enter the Employee Id that you want to transfer");
			String em = Verify.checkEmpId();
			empId = em;
			try (Connection conn = DBUtil.connection()) {

				PreparedStatement ps = conn.prepareStatement("SELECT * FROM employee WHERE empId = ?");
				ps.setInt(1, Integer.parseInt(em));

				ResultSet rs = ps.executeQuery();

				if (rs.next()) {
					flag1 = false;
				} else {
					System.out.println("Employee not present with this Id");
				}

			} catch (Exception e) {
				message = e.getMessage();
			}

		}

		while (flag2) {

			System.out.println("Department Id in which you want to transfer Employee");
			String de = Verify.checkDeptid();
			deptId = de;
			try (Connection conn = DBUtil.connection()) {

				PreparedStatement ps = conn.prepareStatement("SELECT * FROM department WHERE depId = ?");
				ps.setInt(1, Integer.parseInt(de));

				ResultSet rs = ps.executeQuery();

				if (rs.next()) {
					flag2 = false;
				} else {
					System.out.println("No department available for this Id");
				}

			} catch (Exception e) {
				message = e.getMessage();

			}

		}

		try (Connection conn = DBUtil.connection()) {

			PreparedStatement ps = conn.prepareStatement("UPDATE employee SET did = ? WHERE empId = ?");
			ps.setInt(1, Integer.parseInt(deptId));
			ps.setInt(2, Integer.parseInt(empId));

			int x = ps.executeUpdate();

			if (x > 0) {
				message = "Employee with Id " + empId + " has been tranfered to Department Id " + deptId + "";
			}

		} catch (SQLException e) {
			message = e.getMessage();
		}

		return message;
	}

	public List<Department> getAllDepartments() throws DepartmentException {

		List<Department> list = new ArrayList<>();

		try (Connection conn = DBUtil.connection()) {

			PreparedStatement ps = conn.prepareStatement("SELECT * FROM department");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				int id = rs.getInt("depId");
				String name = rs.getString("depName");

				Department dep = new Department(id, name);
				list.add(dep);

			}

		} catch (Exception e) {
			throw new DepartmentException(e.getMessage());
		}

		if (list.size() == 0) {
			System.out.println("No record found");
		}

		return list;
	}

	public List<Employee> getAllEmployees() throws EmployeeException {

		List<Employee> list = new ArrayList<>();

		try (Connection conn = DBUtil.connection()) {

			PreparedStatement ps = conn.prepareStatement("SELECT * FROM employee");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				int id = rs.getInt("empId");
				String name = rs.getString("empName");
				String sal = rs.getString("empSalary");
				String role = rs.getString("empRole");
				String did = rs.getString("did");

				Employee emp = new Employee(id, name, sal, role, did);
				list.add(emp);
			}

		} catch (SQLException e) {
			throw new EmployeeException(e.getMessage());
		}

		if (list.size() == 0) {
			System.out.println("No data found");
		}

		return list;
	}

	public List<Leave> getAllLeavesRequest() throws LeaveException {

		List<Leave> list = new ArrayList<>();

		try (Connection conn = DBUtil.connection()) {

			PreparedStatement ps = conn
					.prepareStatement("SELECT * FROM leaveTable WHERE reason IS NOT Null AND permission IS null");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				String ln = rs.getString("leaveNum");
				String id = rs.getString("empId");
				String name = rs.getString("emplName");
				String dur = rs.getString("leavedura");
				String reason = rs.getString("reason");

				Leave l = new Leave(ln, name, dur, reason, id);

				list.add(l);

			}

		} catch (SQLException e) {
			throw new LeaveException(e.getMessage());
		}

		if (list.size() == 0) {
			System.out.println("No record found");
		}

		return list;
	}

}
