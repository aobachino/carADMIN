package jp.co.Dto;

import java.util.ArrayList;
import java.util.List;

import jp.co.Entity.CarEntity;
import jp.co.Entity.CategoryEntity;
import jp.co.Entity.MemberEntity;
import jp.co.Entity.ReserveInfoEntity;

public class ReserveInfoDto {
	private List<ReserveInfoEntity> resList = new ArrayList<ReserveInfoEntity>();
	private List<MemberEntity> memList = new ArrayList<MemberEntity>();
	private List<CategoryEntity> categList = new ArrayList<CategoryEntity>();
	private List<CarEntity> carList = new ArrayList<CarEntity>();

	public List<ReserveInfoEntity> getResList() {
		return resList;
	}

	public void setResList(List<ReserveInfoEntity> resList) {
		this.resList = resList;
	}

	public List<MemberEntity> getMemList() {
		return memList;
	}

	public void setMemList(List<MemberEntity> memList) {
		this.memList = memList;
	}

	public List<CategoryEntity> getCategList() {
		return categList;
	}

	public void setCategList(List<CategoryEntity> categList) {
		this.categList = categList;
	}

	public List<CarEntity> getCarList() {
		return carList;
	}

	public void setCarList(List<CarEntity> carList) {
		this.carList = carList;
	}

}
