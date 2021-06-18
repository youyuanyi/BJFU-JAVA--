package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controller.AdminController;
import model.Admin;
import util.InitFrmTool;
import util.StringUtil;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
/**
 * 管理员个人信息管理页面
 * @author HP
 *
 */
public class AdminInfoFrm extends JFrame {
	int width=20,height=20;
	private JPanel contentPane;
	private JTextField nameTxt;
	private JTextField pwdTxt;
	private AdminController adminDao=new AdminController();
	private ImageIcon userIcon=new ImageIcon("src//img//user.png");
    private ImageIcon pwdIcon=new ImageIcon("src//img//pwd.png");
    private ImageIcon backIcon=new ImageIcon("src//img//back.png");
    private ImageIcon searchIcon=new ImageIcon("src//img//search.png");
    private ImageIcon editIcon=new ImageIcon("src//img//修改.png");
   
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminInfoFrm frame = new AdminInfoFrm(new Admin());
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
	public AdminInfoFrm(Admin admin) {
		
		userIcon.setImage(userIcon.getImage().getScaledInstance(width+5, height+5, Image.SCALE_DEFAULT));
	    pwdIcon.setImage(pwdIcon.getImage().getScaledInstance(width+5, height+5, Image.SCALE_DEFAULT));
	    searchIcon.setImage(searchIcon.getImage().getScaledInstance(width+5, height+5, Image.SCALE_DEFAULT));
		backIcon.setImage(backIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		editIcon.setImage(editIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		
		setTitle("\u7BA1\u7406\u5458\u4E2A\u4EBA\u4FE1\u606F\u7BA1\u7406\u754C\u9762");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 469, 397);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton backBtn = new JButton("\u8FD4\u56DE");
		backBtn.setBackground(new Color(224, 255, 255));
		backBtn.setIcon(backIcon);
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new AdminJrm(admin).setVisible(true);
			}
		});
		backBtn.setFont(new Font("宋体", Font.BOLD, 18));
		
		JLabel userLabel = new JLabel("\u8D26\u53F7\uFF1A");
		userLabel.setIcon(userIcon);
		userLabel.setFont(new Font("宋体", Font.BOLD, 18));
		
		nameTxt = new JTextField();
		nameTxt.setBorder(new LineBorder(new java.awt.Color(127,157,185),1,false));
		nameTxt.setColumns(10);
		
		JLabel pwdTxt_1 = new JLabel("\u5BC6\u7801\uFF1A");
		pwdTxt_1.setIcon(pwdIcon);
		pwdTxt_1.setFont(new Font("宋体", Font.BOLD, 18));
		
		pwdTxt = new JTextField();
		pwdTxt.setBorder(new LineBorder(new java.awt.Color(127,157,185),1,false));
		pwdTxt.setColumns(10);
		
		JButton searchBtn = new JButton("\u67E5\u8BE2");
		searchBtn.setBackground(new Color(224, 255, 255));
		searchBtn.setIcon(searchIcon);
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchActionPerformed(e,admin);
			}
		});
		searchBtn.setFont(new Font("宋体", Font.BOLD, 18));
		
		JButton editBtn = new JButton("\u4FEE\u6539");
		editBtn.setBackground(new Color(224, 255, 255));
		editBtn.setIcon(editIcon);
		editBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int num=EditActionPerformed(e,admin);
				if(num==1) {
					JOptionPane.showMessageDialog(null, "修改成功,请重新登录!");
					dispose();
					JFrame jf=new LogOnFrm() ;
				    InitFrmTool ift=new InitFrmTool("src\\img\\bg.png");
				    ift.InitFrm(ift.getImgPath(), jf);
					
				}else {
					JOptionPane.showMessageDialog(null, "修改失败!");
					return;
				}
			}
		});
		editBtn.setFont(new Font("宋体", Font.BOLD, 18));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(24)
							.addComponent(backBtn))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(111)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(pwdTxt_1)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(pwdTxt))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(userLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(nameTxt, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(133)
							.addComponent(searchBtn)
							.addGap(18)
							.addComponent(editBtn)))
					.addContainerGap(232, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(29)
					.addComponent(backBtn)
					.addGap(41)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(userLabel)
						.addComponent(nameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(35)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(pwdTxt_1)
						.addComponent(pwdTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(37)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(searchBtn)
						.addComponent(editBtn))
					.addContainerGap(190, Short.MAX_VALUE))
		);
		this.setLocationRelativeTo(null);//窗口居中
		contentPane.setLayout(gl_contentPane);
	}
	
	/**
	 * 修改个人信息
	 * @param e
	 * @param admin
	 */
	private int EditActionPerformed(ActionEvent e, Admin admin) {
		// TODO Auto-generated method stub
		String newName=this.nameTxt.getText();
		String newPwd=this.pwdTxt.getText();
		int flag=0;
		if(StringUtil.isEmpty(newName)&&StringUtil.isEmpty(newPwd)) {
			JOptionPane.showMessageDialog(null, "请至少修改一项!");
			return 2;
		}
		String name,pwd;
		if(newName.equals("")) {
			name=admin.getName();
		}else {
			name=newName;
		}
		if(newPwd.equals("")) {
			pwd=admin.getPassword();
		}else {
			pwd=newPwd;
		}
		
		LinkedList<Admin> adminList=adminDao.readAllAdmin();
		for(Admin currentAdmin:adminList) {
			if(currentAdmin.getName().equals(admin.getName())) {
				currentAdmin.setName(name);
				currentAdmin.setPassword(pwd);
				flag=1;
			}
		}
		adminDao.writeAllAdmin(adminList);
		return flag;
		
	}

	/**
	 * 管理员查询个人信息
	 * @param e
	 */
	private void searchActionPerformed(ActionEvent e,Admin admin) {
		// TODO Auto-generated method stub
		this.nameTxt.setText(admin.getName());
		this.pwdTxt.setText(admin.getPassword());
		
	}
}
