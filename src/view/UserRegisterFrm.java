package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controller.UserController;
import model.User;
import util.InitFrmTool;
import util.StringUtil;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class UserRegisterFrm extends JFrame {

	private JPanel contentPane;
	private JTextField userTxt;
	private JPasswordField pwdTxt;
	private JPasswordField cfmpwdTXT;
	private JTextField phoneTxt;
	private JLabel cfmpwdLabel;
	private JLabel confirmLabel;
	private UserController userDao=new UserController();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserRegisterFrm frame = new UserRegisterFrm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UserRegisterFrm() {
		setTitle("\u6D88\u8D39\u8005\u6CE8\u518C\u754C\u9762");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 733, 566);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 204));
		
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel userLabel = new JLabel("\u8D26\u53F7:");
		userLabel.setFont(new Font("宋体", Font.BOLD, 16));
		
		userTxt = new JTextField();
		userTxt.setColumns(10);
		userTxt.setBorder(new LineBorder(new java.awt.Color(127,157,185),1,false));
		
		JLabel pwdLabel = new JLabel("\u5BC6\u7801:");
		pwdLabel.setFont(new Font("宋体", Font.BOLD, 16));
		
		JButton btnNewButton = new JButton("\u6CE8\u518C");
		btnNewButton.setBackground(new Color(153, 255, 255));
		btnNewButton.setFont(new Font("宋体", Font.BOLD, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userRegisterActionPerformed(e);
				
			}
		});
		
		pwdTxt = new JPasswordField();
		pwdTxt.setBorder(new LineBorder(new java.awt.Color(127,157,185),1,false));
		cfmpwdLabel = new JLabel("\u786E\u8BA4\u5BC6\u7801:");
		cfmpwdLabel.setFont(new Font("宋体", Font.BOLD, 15));
		
		cfmpwdTXT = new JPasswordField();
		cfmpwdTXT.setBorder(new LineBorder(new java.awt.Color(127,157,185),1,false));
		JLabel phoneLabel = new JLabel("\u8054\u7CFB\u65B9\u5F0F:");
		phoneLabel.setFont(new Font("宋体", Font.BOLD, 15));
		
		phoneTxt = new JTextField();
		phoneTxt.setBorder(new LineBorder(new java.awt.Color(127,157,185),1,false));
		phoneTxt.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("\u91CD\u7F6E");
		btnNewButton_1.setBackground(new Color(204, 255, 255));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValue();
			}
		});
		btnNewButton_1.setFont(new Font("宋体", Font.BOLD, 15));
		
		confirmLabel = new JLabel("");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(176)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(phoneLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(phoneTxt))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(cfmpwdLabel)
										.addComponent(pwdLabel)
										.addComponent(userLabel))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(userTxt, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addComponent(pwdTxt)
											.addComponent(cfmpwdTXT))))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(193)
							.addComponent(btnNewButton)
							.addGap(72)
							.addComponent(btnNewButton_1))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(177)
							.addComponent(confirmLabel, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(199, GroupLayout.PREFERRED_SIZE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(103)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(userLabel)
						.addComponent(userTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(48)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(pwdLabel)
						.addComponent(pwdTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(40)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(cfmpwdLabel)
						.addComponent(cfmpwdTXT, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addComponent(confirmLabel)
					.addGap(20)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(phoneLabel)
						.addComponent(phoneTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(46)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
					.addContainerGap(142, Short.MAX_VALUE))
		);
		this.setLocationRelativeTo(null);//窗口居中
		contentPane.setLayout(gl_contentPane);
	}

	private void bgfocus(FocusEvent e) {
		// TODO Auto-generated method stub
		String pwd=new String(this.pwdTxt.getPassword());
		String pwd2=new String(this.cfmpwdTXT.getPassword());
		if(pwd==pwd2) {
			this.confirmLabel.setText("");
		}
	}

	
	private void userRegisterActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String name=this.userTxt.getText();
		String pwd=new String(this.pwdTxt.getPassword());
		String pwd2=new String(this.cfmpwdTXT.getPassword());
		String phone=this.phoneTxt.getText();
		
		if(StringUtil.isEmpty(name)) {
			JOptionPane.showMessageDialog(null, "账号不能为空!");
			return;
		}
		if(StringUtil.isEmpty(pwd)) {
			JOptionPane.showMessageDialog(null, "密码不能为空!");
			return;
		}
		if(StringUtil.isEmpty(pwd2)) {
			JOptionPane.showMessageDialog(null, "请先确认密码!");
			return;
		}
		if(StringUtil.isEmpty(phone)) {
			JOptionPane.showMessageDialog(null, "联系方式不能为空!");
			return;
		}
		int num;
		try {
			if(!pwd.equals(pwd2)) {
				JOptionPane.showMessageDialog(null, "密码不一致!");
				this.cfmpwdTXT.setText("");
				this.pwdTxt.setText("");
				return;
			}
			num=userDao.userRegister(name, pwd, phone);
			if(num==1) {
				JOptionPane.showMessageDialog(null, "注册成功");
				dispose();
			    JFrame jf=new LogOnFrm() ;
		        InitFrmTool ift=new InitFrmTool("src\\img\\bg.png");
		        ift.InitFrm(ift.getImgPath(), jf);
			}else {
				JOptionPane.showMessageDialog(null, "注册失败");
				resetValue();
				return;
			}
		}catch(Exception evt) {
			JOptionPane.showMessageDialog(null, "注册失败");
			resetValue();
			evt.printStackTrace();
		}
		
	}
	private void resetValue() {
		this.userTxt.setText("");
		this.pwdTxt.setText("");
		this.cfmpwdTXT.setText("");
		this.phoneTxt.setText("");
	}
}
