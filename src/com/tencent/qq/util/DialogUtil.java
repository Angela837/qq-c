package com.tencent.qq.util;

import javax.swing.JOptionPane;

public class DialogUtil {
	public static void showAlarm(String str) {
		JOptionPane.showMessageDialog(null, str, "ϵͳ����", JOptionPane.WARNING_MESSAGE);
	}

	public static void showInfo(String str) {
		JOptionPane.showMessageDialog(null, str, "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
	}
}
