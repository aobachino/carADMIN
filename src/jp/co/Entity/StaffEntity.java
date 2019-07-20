package jp.co.Entity;

import java.sql.Date;

public class StaffEntity {
	private int staffId;
	private String clerck;
	private String pass;
	private String name;
	private String age;
	private String sex;
	private Date regTime;
	private  String updStaff;
	private Date updTime;
	public int getStaffId() {
		return staffId;
	}
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}
	public String getClerck() {
		return clerck;
	}
	public void setClerck(String clerck) {
		this.clerck = clerck;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public Date getRegTime() {
		return regTime;
	}
	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}
	public String getUpdStaff() {
		return updStaff;
	}
	public void setUpdStaff(String updStaff) {
		this.updStaff = updStaff;
	}
	public Date getUpdTime() {
		return updTime;
	}
	public void setUpdTime(Date updTime) {
		this.updTime = updTime;
	}
}
