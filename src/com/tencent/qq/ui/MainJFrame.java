package com.tencent.qq.ui;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.tencent.qq.vo.User;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.Socket;

public class MainJFrame extends JFrame {

	private JPanel contentPane;
	private JLabel fimg[];

	JPanel panel = new JPanel();
	private Socket s;

	/**
	 * Create the frame.
	 */
	public MainJFrame(Socket s,User u) {
		// User u;
		// MainJFrame mj= new MainJFrame(u);
           this.s=s;
		setTitle(u.getAccount());// 设置窗口标题
		

		List<User> ulist = u.getFriends();
		for (User f : ulist) {
			JLabel fimg = new JLabel();

			fimg.setIcon(new ImageIcon(MainJFrame.class
					.getResource("/com/tencent/qq/img/head/" + f.getImg() + ".png")));
			fimg.setToolTipText(f.getAccount());
			fimg.setText(f.getNickname());

			panel.add(fimg);
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 236, 565);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 191, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(MainJFrame.class.getResource("/com/tencent/qq/img/main/1.PNG")));
		label.setBounds(0, 0, 263, 56);
		contentPane.add(label);

		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(MainJFrame.class.getResource("/com/tencent/qq/img/main/2.png")));
		label_1.setBounds(0, 54, 110, 423);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("");
		label_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				FindJFrame fj=new FindJFrame(s,u);
				fj.setLocationRelativeTo(null);
				fj.setVisible(true);
			}
		});
		label_2.setIcon(new ImageIcon(MainJFrame.class.getResource("/com/tencent/qq/img/main/3.png")));
		label_2.setBounds(0, 477, 332, 56);
		contentPane.add(label_2);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(215, 476, -180, -420);
		contentPane.add(scrollPane);
		panel.setBackground(new Color(240, 248, 255));
		
		panel.setBounds(39, 59, 181, 408);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		contentPane.add(panel);
	}

}
