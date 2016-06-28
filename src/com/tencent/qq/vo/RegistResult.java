package com.tencent.qq.vo;

import java.io.Serializable;

public class RegistResult implements Serializable{
	private boolean rr;//注册成功还是失败
	private String msg;//注册结果的消息
	
	
	
	public boolean isRr() {
		return rr;
	}
	public void setRr(boolean rr) {
		this.rr = rr;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	
}
