package com.tencent.qq.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.tencent.qq.biz.SysBiz;
import com.tencent.qq.vo.Find;
import com.tencent.qq.vo.User;

import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FindJFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	ButtonGroup bg;
	JRadioButton radioButton = new JRadioButton("\u7CBE\u786E\u67E5\u627E");
	JRadioButton radioButton_1 = new JRadioButton("\u67E5\u627E\u6240\u6709");

	private FindJFrame frame;
	private SysBiz sBiz;
//	private Socket s;
//    private User u;
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public FindJFrame(Socket s,User u) { 
//		this.s=s;
//		this.u=u;
		sBiz=new SysBiz(s);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 380, 272);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 191, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		radioButton.setBackground(new Color(0, 191, 255));
		radioButton.setBounds(27, 38, 121, 23);
		contentPane.add(radioButton);
		
		textField = new JTextField();
		textField.setBounds(27, 74, 312, 31);
		contentPane.add(textField);
		textField.setColumns(10);
		
		radioButton_1.setBackground(new Color(0, 191, 255));
		radioButton_1.setBounds(27, 136, 121, 23);
		contentPane.add(radioButton_1);
		
		radioButton_1.setSelected(true);//默认
		
		JButton button = new JButton("\u67E5\u627E");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				find(evt);
					}
		});
		button.setBounds(64, 198, 93, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("\u5173\u95ED");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				frame=new FindJFrame(s,u);
				frame.setVisible(false);
				}
		});
		button_1.setBounds(229, 198, 93, 23);
		contentPane.add(button_1);
		
		bg=new ButtonGroup();
		bg.add(radioButton);
		bg.add(radioButton_1);
	}
	private void find(MouseEvent evt) {
		//精确查找    查找所有
		Find find=new Find();
		if(radioButton.isSelected())
		{//精确
			find.setType(Find.ONE);
			find.setFaccount(textField.getText().trim());//设置账号
		}else{//所有
			find.setType(Find.ALL);
		}
		//把查找的信息发送给服务器
		try {
			List <User> ulist=sBiz.find(find);
			System.out.println("查询结果数为："+ulist.size());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
