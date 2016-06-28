package com.tencent.qq.biz;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

import com.tencent.qq.util.ObjectUtil;
import com.tencent.qq.vo.Find;
import com.tencent.qq.vo.RegistResult;
import com.tencent.qq.vo.User;

public class SysBiz {
	private Socket s;//连接服务器的Socket

	public SysBiz(Socket s) {
		this.s = s;
	}

	// 验证用户密码是否正确
	// return ->null --错误 || true --返回user所有信息
	public User login(User u) throws IOException, ClassNotFoundException {
		// 1.将user对象写入服务端
		ObjectUtil.writeObject(s, u);
		// 2.读取服务器验证界面
		return (User)ObjectUtil.readObject(s);
	}
	
	//
	public RegistResult regedit(User u) throws IOException, ClassNotFoundException{
		//1.把User对象写进服务端
		ObjectUtil.writeObject(s, u);

		//2.读取服务端验证界面
		return (RegistResult)ObjectUtil.readObject(s);

		
	}
	
	public List<User> find(Find find) throws IOException, ClassNotFoundException{
		//查找信息写进服务端
	       ObjectUtil.writeObject(s, find);
         
		  return (List<User>)ObjectUtil.readObject(s);

		
	}
}



