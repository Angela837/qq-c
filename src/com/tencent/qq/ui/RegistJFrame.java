package com.tencent.qq.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.Socket;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.tencent.qq.biz.SysBiz;
import com.tencent.qq.util.DialogUtil;
import com.tencent.qq.vo.RegistResult;
import com.tencent.qq.vo.User;

public class RegistJFrame extends JFrame {

	//private static Socket s;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
    private RegistJFrame frame;
	
	JComboBox comboBox = new JComboBox();
	JLabel lblNewLabel = new JLabel("");

	private static  SysBiz sBiz;
	private JPasswordField passwordField;
	private LoginJFrame lf;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

//	public RegistJFrame(Socket s) {
//		super();
//		sBiz = new SysBiz(s);
//		
//
//	}

	/**
	 * Create the frame.
	 */
	public RegistJFrame(Socket s) {
        sBiz=new SysBiz(s);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 445, 272);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(64, 224, 208));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("\u6635\u79F0\uFF1A");
		label.setFont(new Font("宋体", Font.PLAIN, 15));
		label.setBounds(43, 40, 54, 15);
		contentPane.add(label);

		JLabel label_1 = new JLabel("\u5BC6\u7801\uFF1A");
		label_1.setFont(new Font("宋体", Font.PLAIN, 15));
		label_1.setBounds(43, 87, 54, 15);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("\u5E74\u9F84\uFF1A");
		label_2.setFont(new Font("宋体", Font.PLAIN, 15));
		label_2.setBounds(43, 134, 54, 15);
		contentPane.add(label_2);

		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setFont(new Font("宋体", Font.PLAIN, 15));
		lblEmail.setBounds(33, 184, 74, 15);
		contentPane.add(lblEmail);

		textField = new JTextField();
		textField.setBounds(91, 37, 125, 21);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(91, 131, 125, 21);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setBounds(91, 181, 125, 21);
		contentPane.add(textField_3);
		textField_3.setColumns(10);

		JLabel label_3 = new JLabel("\u5934\u50CF\uFF1A");
		label_3.setFont(new Font("宋体", Font.PLAIN, 15));
		label_3.setBounds(237, 40, 54, 15);
		contentPane.add(label_3);

		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
				int index = comboBox.getSelectedIndex() + 1;
				lblNewLabel.setIcon(
						new ImageIcon(RegistJFrame.class.getResource("/com/tencent/qq/img/head/" + index + ".png")));
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "狐狸", "小鸡", "熊猫", "猫咪" }));

		comboBox.setBounds(278, 37, 131, 21);
		contentPane.add(comboBox);

		JButton button = new JButton("\u6CE8\u518C");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				regedit(evt);
			}
		});
		button.setBounds(237, 181, 74, 21);
		contentPane.add(button);

		JButton button_1 = new JButton("\u5173\u95ED");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
		    frame=new RegistJFrame(s);
		    
		    frame.setVisible(false);
			lf=new LoginJFrame();
		    lf.setVisible(true);
			lf.setLocationRelativeTo(null);// 窗口居中显示

				
			}
		});
//		button_1.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				frame.setVisible(false);
//				frame.lf=lf;
//				lf.setVisible(true);
//				lf.setLocationRelativeTo(null);// 窗口居中显示
//
//			}
//		});
		
		button_1.setBounds(341, 181, 78, 21);
		contentPane.add(button_1);

		lblNewLabel.setIcon(new ImageIcon(RegistJFrame.class.getResource("/com/tencent/qq/img/head/1.png")));
		lblNewLabel.setBounds(306, 68, 74, 84);
		contentPane.add(lblNewLabel);

		passwordField = new JPasswordField();
		passwordField.setBounds(91, 84, 125, 21);
		contentPane.add(passwordField);
	}

	// 注册
	public void regedit(ActionEvent evt) {
		String nickname = textField.getText().trim();
		String password = new String(passwordField.getText().trim());
		String age = textField_2.getText().trim();
		String email = textField_3.getText().trim();
		String img = String.valueOf(comboBox.getSelectedIndex());
		User u = new User();
		u.setNickname(nickname);
		u.setAge(Integer.parseInt(age));
		u.setEmail(email);
		u.setPassword(password);
		u.setImg(img);// 数据库保存的是1,2,3

		try {
//            sBiz=new SysBiz(s);

			RegistResult rr =sBiz.regedit(u);
			if (rr.isRr()) {
				// 注册成功
				System.out.println("注册成功");
				this.dispose();// 销毁当前窗体
				DialogUtil.showInfo(rr.getMsg());
			    lf=new LoginJFrame();
				lf.setVisible(true);
			} else {
				// 注册失败
				DialogUtil.showAlarm(rr.getMsg());
				
			}
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}
}
