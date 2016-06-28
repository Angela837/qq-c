package com.tencent.qq.util;

import javax.swing.JOptionPane;

public class DialogUtil {
	public static void showAlarm(String str) {
		JOptionPane.showMessageDialog(null, str, "系统警告", JOptionPane.WARNING_MESSAGE);
	}

	public static void showInfo(String str) {
		JOptionPane.showMessageDialog(null, str, "系统消息", JOptionPane.INFORMATION_MESSAGE);
	}
}
