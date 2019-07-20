package jp.co.Model;

import javax.validation.constraints.NotEmpty;

public class LoginModel {
	@NotEmpty(message = "必須入力です ")
	private String staffId;
	@NotEmpty(message = "必須入力です ")
	private String pass;

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
}
