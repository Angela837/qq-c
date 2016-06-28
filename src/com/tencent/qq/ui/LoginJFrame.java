package com.tencent.qq.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.tencent.qq.biz.SysBiz;
import com.tencent.qq.util.DialogUtil;
import com.tencent.qq.util.PropertiesUtil;
import com.tencent.qq.vo.User;

import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.Font;
import java.net.Socket;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginJFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	
    private static Socket s;//连接服务器的Socket
    private static  SysBiz sys;
    private JPasswordField passwordField;
    static LoginJFrame frame = new LoginJFrame();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					try {
						s=new Socket(PropertiesUtil.readPro("ip"),Integer.parseInt(PropertiesUtil.readPro("port")));
						sys=new SysBiz(s);
					} catch (Exception e) {
						DialogUtil.showAlarm("连接服务器失败！");
						e.printStackTrace();
						
					}
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);//窗口居中显示

					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginJFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 437, 351);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(LoginJFrame.class.getResource("/com/tencent/qq/img/login/afternoon.jpg")));
		lblNewLabel.setBounds(0, 0, 434, 169);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u5BC6\u7801\uFF1A");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(48, 242, 54, 15);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(107, 195, 165, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel label = new JLabel("\u8D26\u53F7\uFF1A");
		label.setFont(new Font("宋体", Font.PLAIN, 14));
		label.setBounds(48, 198, 54, 15);
		contentPane.add(label);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_2.setIcon(new ImageIcon(LoginJFrame.class.getResource("/com/tencent/qq/img/login/qh.png")));
		lblNewLabel_2.setBounds(308, 242, 66, 15);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				regedit(evt);
			}
		});
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_3.setIcon(new ImageIcon(LoginJFrame.class.getResource("/com/tencent/qq/img/login/reg.png")));
		lblNewLabel_3.setBounds(308, 196, 66, 18);
		contentPane.add(lblNewLabel_3);
		
		JButton button = new JButton("\u767B\u5F55");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				login(evt);
			}
		});
		button.setBounds(74, 280, 106, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("\u8BBE\u7F6E");
		button_1.setBounds(240, 280, 106, 23);
		contentPane.add(button_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(107, 239, 165, 21);
		contentPane.add(passwordField);
	}
	public void login(ActionEvent evt){
		String name=textField.getText().trim();
		String password=passwordField.getText().trim();
		//使用对象序列化
		//1.类必须一致（包名，类名）
		//2.实现序列化接口
		User u=new User();
		u.setAccount(name);
		u.setPassword(password);
        try {
			User u1=sys.login(u);
			if(u1==null){
				//失败
				DialogUtil.showAlarm("用户名或密码错误！");;

			}else{
				//成功
				System.out.println("好友数量"+u1.getFriends().size());
//			   DialogUtil.showAlarm("登录成功！");
				frame.setVisible(false);
	           MainJFrame mj=new MainJFrame(s,u1);
	           mj.setLocationRelativeTo(null);
	           mj.setVisible(true);

			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			DialogUtil.showAlarm("服务器异常！");;
		} catch (IOException e) {
			e.printStackTrace();
			DialogUtil.showAlarm("服务器异常！");;

		}
        
		

		
	}
	public void regedit(MouseEvent evt){
		frame.setVisible(false);
		RegistJFrame rf=new RegistJFrame(s);
		rf.setVisible(true);
		rf.setLocationRelativeTo(null);//窗口居中显
	}
}
