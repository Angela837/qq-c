package com.tencent.qq.vo;

import java.io.Serializable;

public class Find implements Serializable{
	private int type;//查找类型
	private String faccount;//查找账号
	public static final int ONE=1;//精确
	public static final int ALL=2;//所有
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getFaccount() {
		return faccount;
	}
	public void setFaccount(String faccount) {
		this.faccount = faccount;
	}
	
	

}
