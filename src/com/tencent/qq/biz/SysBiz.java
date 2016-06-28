package com.tencent.qq.biz;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

import com.tencent.qq.util.ObjectUtil;
import com.tencent.qq.vo.Find;
import com.tencent.qq.vo.RegistResult;
import com.tencent.qq.vo.User;

public class SysBiz {
	private Socket s;//���ӷ�������Socket

	public SysBiz(Socket s) {
		this.s = s;
	}

	// ��֤�û������Ƿ���ȷ
	// return ->null --���� || true --����user������Ϣ
	public User login(User u) throws IOException, ClassNotFoundException {
		// 1.��user����д������
		ObjectUtil.writeObject(s, u);
		// 2.��ȡ��������֤����
		return (User)ObjectUtil.readObject(s);
	}
	
	//
	public RegistResult regedit(User u) throws IOException, ClassNotFoundException{
		//1.��User����д�������
		ObjectUtil.writeObject(s, u);

		//2.��ȡ�������֤����
		return (RegistResult)ObjectUtil.readObject(s);

		
	}
	
	public List<User> find(Find find) throws IOException, ClassNotFoundException{
		//������Ϣд�������
	       ObjectUtil.writeObject(s, find);
         
		  return (List<User>)ObjectUtil.readObject(s);

		
	}
}



