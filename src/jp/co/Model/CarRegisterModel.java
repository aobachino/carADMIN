package jp.co.Model;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import jp.co.Model.CarRegisterModel.Group1;

@GroupSequence({ Group1.class, CarRegisterModel.class })
public class CarRegisterModel {
	public interface Group1 {
	}
	private String carNum;
	@NotEmpty(message = "必須入力です ", groups = Group1.class)
	private String categNum;
	@NotEmpty(message = "必須入力です ", groups = Group1.class)
	@Size(max = 50, message = "50文字以内で入力してください")
	private String carName;
	@NotEmpty(message = "必須入力です ", groups = Group1.class)
	@Size(max = 20 , message = "20文字以内で入力してください")
	private String maker;
	@NotEmpty(message = "必須入力です ", groups = Group1.class)
	@Size(max = 5, message = "5桁以内で入力してください")
	@Pattern(regexp = "[0-9]*", message = "半角数字で入力してください")
	private String gas;
	@NotEmpty(message = "必須入力です ", groups = Group1.class)
	@Size(max = 10, message = "10桁以内で入力してください")
	@Pattern(regexp = "[0-9]*", message = "半角数字で入力してください")
	private String price;
	@NotEmpty(message = "必須入力です ", groups = Group1.class)
	@Size(max = 50, message = "50文字以内で入力してください")
	private String memo;
	public String getCategNum() {
		return categNum;
	}
	public void setCategNum(String categNum) {
		this.categNum = categNum;
	}
	public String getCarName() {
		return carName;
	}
	public void setCarName(String carName) {
		this.carName = carName;
	}
	public String getMaker() {
		return maker;
	}
	public void setMaker(String maker) {
		this.maker = maker;
	}
	public String getGas() {
		return gas;
	}
	public void setGas(String gas) {
		this.gas = gas;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getCarNum() {
		return carNum;
	}
	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}
}
