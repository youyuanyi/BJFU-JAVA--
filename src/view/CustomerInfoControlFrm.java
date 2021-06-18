package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controller.OrderController;
import controller.UserController;
import model.User;
import model.UserOrder;
import util.InitFrmTool;
import util.StringUtil;

import javax.print.attribute.SetOfIntegerSyntax;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JSplitPane;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.SystemColor;

/**
 * 消费者个人信息管理界面
 * @author HP
 *
 */
public class CustomerInfoControlFrm extends JFrame {

	private JPanel contentPane;
	private JTextField userTxt;
	private JTextField pwdTxt;
	private JTextField phoneTxt;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JTextField newUserTxt;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JTextField newPhoneTxt;
	private JButton EditBtn;
	private JButton resetBtn;
	private JButton offBtn;
	int width=25,height=25;
	private ImageIcon EditIcon=new ImageIcon("src//img//修改.png");
	private ImageIcon ResetIcon=new ImageIcon("src//img//reset.png");
	private JPasswordField newPwdTxt;
	private ImageIcon deleteIcon=new ImageIcon("src//img//注销.png");
	private UserController userDao=new UserController();
	private OrderController orderDao=new OrderController();
	private JButton backBtn;
	
	
	private ImageIcon logoIcon=new ImageIcon("src//img//logo.png");
    private ImageIcon userIcon=new ImageIcon("src//img//user.png");
    private ImageIcon pwdIcon=new ImageIcon("src//img//pwd.png");
    private ImageIcon telIcon=new ImageIcon("src//img//tel.png");
    private ImageIcon searchIcon=new ImageIcon("src//img//search.png");
    private ImageIcon backIcon=new ImageIcon("src//img//back.png");
    private JTable orderTable;
 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerInfoControlFrm frame = new CustomerInfoControlFrm(new User());
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
	public CustomerInfoControlFrm(User user) {
		setResizable(false);
		EditIcon.setImage(EditIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		ResetIcon.setImage(ResetIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		deleteIcon.setImage(deleteIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		logoIcon.setImage(logoIcon.getImage().getScaledInstance(width+5, height, Image.SCALE_DEFAULT));
	    userIcon.setImage(userIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
	    pwdIcon.setImage(pwdIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
	    telIcon.setImage(telIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
	    searchIcon.setImage(searchIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
	    backIcon.setImage(backIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
	    
		setTitle("\u6D88\u8D39\u8005\u4E2A\u4EBA\u4FE1\u606F\u7BA1\u7406\u754C\u9762");
		System.out.println(user);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 770, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 204));
		//contentPane.setBackground(SystemColor.window);
		//contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("\u8D26\u53F7:");
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 15));
		lblNewLabel.setIcon(userIcon);
		lblNewLabel.setSize(width-5,height-5);
		userTxt = new JTextField(user.getName());	
		userTxt.setEditable(false);
		userTxt.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\u5BC6\u7801:");
		lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 15));
		lblNewLabel_1.setIcon(pwdIcon);
		lblNewLabel_1.setSize(width-5,height-5);
		pwdTxt = new JTextField(user.getPassword());
		pwdTxt.setEditable(false);
		pwdTxt.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("\u8054\u7CFB\u65B9\u5F0F:");
		lblNewLabel_2.setFont(new Font("宋体", Font.BOLD, 15));
		lblNewLabel_2.setIcon(telIcon);
		lblNewLabel_2.setSize(width-5,height-5);
		phoneTxt = new JTextField(user.getPhone());
		phoneTxt.setEditable(false);
		phoneTxt.setColumns(10);
		
		lblNewLabel_3 = new JLabel("\u4E2A\u4EBA\u4FE1\u606F\u67E5\u770B");
		lblNewLabel_3.setFont(new Font("宋体", Font.BOLD, 22));
		
		lblNewLabel_4 = new JLabel("\u4FEE\u6539\u4E2A\u4EBA\u4FE1\u606F");
		lblNewLabel_4.setFont(new Font("宋体", Font.BOLD, 22));
		
		lblNewLabel_5 = new JLabel("\u65B0\u7684\u8D26\u53F7:");
		lblNewLabel_5.setFont(new Font("宋体", Font.BOLD, 15));
		
		newUserTxt = new JTextField();
		newUserTxt.setBorder(new LineBorder(new java.awt.Color(127,157,185),1,false));
		newUserTxt.setColumns(10);
		
		lblNewLabel_6 = new JLabel("\u65B0\u7684\u5BC6\u7801:");
		lblNewLabel_6.setFont(new Font("宋体", Font.BOLD, 15));
		
		lblNewLabel_7 = new JLabel("\u65B0\u7684\u7535\u8BDD:");
		lblNewLabel_7.setFont(new Font("宋体", Font.BOLD, 15));
		
		newPhoneTxt = new JTextField();
		newPhoneTxt.setBorder(new LineBorder(new java.awt.Color(127,157,185),1,false));
		newPhoneTxt.setColumns(10);
		
		EditBtn = new JButton("\u4FEE\u6539");
		EditBtn.setBackground(new Color(224, 255, 255));
		//EditBtn.setContentAreaFilled(false);
		//EditBtn.setBorder(BorderFactory.createRaisedBevelBorder());
		EditBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editActionPerformed(e,user);
			}
		});
		EditBtn.setIcon(EditIcon);
		EditBtn.setSize(width-8,height-8);
		EditBtn.setFont(new Font("宋体", Font.BOLD, 15));
		
		resetBtn = new JButton("\u91CD\u7F6E");
		resetBtn.setBackground(new Color(224, 255, 255));
		//resetBtn.setContentAreaFilled(false);
		//resetBtn.setBorder(BorderFactory.createRaisedBevelBorder());
		resetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetActionPerformed(e);
			}
		});
		resetBtn.setFont(new Font("宋体", Font.BOLD, 15));
		resetBtn.setIcon(ResetIcon);
		resetBtn.setSize(width-8,height-8);
		
		offBtn = new JButton("\u6CE8\u9500");
		offBtn.setBackground(new Color(224, 255, 255));
		offBtn.setIcon(deleteIcon);
		offBtn.setSize(width,height);
		//offBtn.setContentAreaFilled(false);
		//offBtn.setBorder(BorderFactory.createRaisedBevelBorder());
		offBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteUserActionPerformed(e,user);
			}
		});
		offBtn.setFont(new Font("宋体", Font.BOLD, 15));
		
		newPwdTxt = new JPasswordField();
		newPwdTxt.setBorder(new LineBorder(new java.awt.Color(127,157,185),1,false));
		
		JLabel lblNewLabel_9 = new JLabel("\u6211\u7684\u8BA2\u5355");
		lblNewLabel_9.setFont(new Font("宋体", Font.BOLD, 22));
		
		JButton searchBtn = new JButton("\u67E5\u8BE2");
		searchBtn.setBackground(new Color(224, 255, 255));
		//searchBtn.setContentAreaFilled(false);
		//searchBtn.setBorder(BorderFactory.createRaisedBevelBorder());
		searchBtn.setIcon(searchIcon);
		searchBtn.setSize(width-8,height-8);
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				orderSearchActionPerformed(e);
			}
		});
		searchBtn.setFont(new Font("宋体", Font.BOLD, 15));
		
		JScrollPane scrollPane = new JScrollPane();
		
		backBtn = new JButton("\u8FD4\u56DE");
		backBtn.setBackground(new Color(224, 255, 255));
		backBtn.setIcon(backIcon);
		//backBtn.setContentAreaFilled(false);
		//backBtn.setBorder(BorderFactory.createRaisedBevelBorder());
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new CustomerFrm(user).setVisible(true);
			}
		});
		backBtn.setFont(new Font("宋体", Font.BOLD, 15));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(210)
					.addComponent(lblNewLabel_9, GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(searchBtn)
					.addGap(340))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(185)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel_5)
									.addGap(18)
									.addComponent(newUserTxt, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE))
								.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_6)
										.addComponent(lblNewLabel_7))
									.addGap(18)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(newPhoneTxt, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
										.addComponent(newPwdTxt))))
							.addGap(131))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(199)
							.addComponent(EditBtn)
							.addGap(45)
							.addComponent(resetBtn))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(31)
									.addComponent(lblNewLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(userTxt, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblNewLabel_1)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(pwdTxt, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(21)
									.addComponent(backBtn)
									.addGap(124)
									.addComponent(lblNewLabel_3)))
							.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel_2)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(phoneTxt, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(offBtn)
									.addGap(16)))))
					.addGap(150))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(227)
					.addComponent(lblNewLabel_4)
					.addContainerGap(383, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(44)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 625, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(77, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(offBtn)
								.addComponent(backBtn))
							.addGap(18))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_3)
							.addPreferredGap(ComponentPlacement.UNRELATED)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(userTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1)
						.addComponent(pwdTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2)
						.addComponent(phoneTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(29)
					.addComponent(lblNewLabel_4)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_5)
						.addComponent(newUserTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(newPwdTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_6))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_7)
						.addComponent(newPhoneTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(EditBtn)
						.addComponent(resetBtn))
					.addGap(41)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_9)
						.addComponent(searchBtn))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addGap(37))
		);
		
		orderTable = new JTable();
		orderTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7528\u6237\u540D", "\u9910\u9986", "\u9884\u5B9A\u65F6\u95F4", "\u9884\u5B9A\u83DC\u54C1"
			}
		));
		scrollPane.setViewportView(orderTable);
		this.setLocationRelativeTo(null);//窗口居中
		contentPane.setLayout(gl_contentPane);
		int num=this.fillTable(new LinkedList<UserOrder>());
	}
	
	/**
	 * 显示订单信息
	 * @param e
	 */
	private void orderSearchActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String name=this.userTxt.getText();
		LinkedList<UserOrder> list=userDao.searchOrder(name);
		int num=this.fillTable(list);
		if(num==0) {
			JOptionPane.showMessageDialog(null, "无订单信息!");
		}
		
	}
	
	/**
	 * 填写table
	 * @param list
	 */
	private int fillTable(LinkedList<UserOrder> list) {
		// TODO Auto-generated method stub
		int flag=0;
		if(list.size()!=0) {
			flag=1;
		}
		DefaultTableModel dtm=(DefaultTableModel) orderTable.getModel();
		dtm.setRowCount(0);//清空表格
		for(UserOrder order:list) {
			Vector v=new Vector();
			v.add(order.getUserName());
			v.add(order.getShopName());
			v.add(order.getOrderTime());
			String food=order.getOrderFood();
			v.add(food);
			dtm.addRow(v);
		}
		return flag;
	}

	/**
	 * 注销用户
	 * @param e
	 * @param user
	 */
	private void deleteUserActionPerformed(ActionEvent e,User user) {
		// TODO Auto-generated method stub
		int res=JOptionPane.showConfirmDialog(null, "是否要注销?");
		if(res==0) {
			int num=userDao.deleteUser(user);
			if(num==1) {
				JOptionPane.showMessageDialog(null, "注销成功!");
				dispose();
				JFrame jf=new LogOnFrm() ;
			    InitFrmTool ift=new InitFrmTool("src\\img\\bg.png");
			    ift.InitFrm(ift.getImgPath(), jf);
			}else {
				JOptionPane.showMessageDialog(null, "注销失败!");
				return;
			}
		}
	}

	private void resetActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.newUserTxt.setText("");
		this.newPhoneTxt.setText("");
		this.newPwdTxt.setText("");
	}
	
	/**
	 * 修改信息
	 * @param e
	 * @param user
	 */
	private void editActionPerformed(ActionEvent e,User user) {
		// TODO Auto-generated method stub
		if(StringUtil.isEmpty(this.newUserTxt.getText()) && StringUtil.isEmpty(new String(this.newPwdTxt.getPassword())) && StringUtil.isEmpty(this.newPhoneTxt.getText())) {
			JOptionPane.showMessageDialog(null, "请至少选择一项!");
			return;
		}
		String name,pwd,phone;
		String newName=this.newUserTxt.getText();
		String newPwd=new String(this.newPwdTxt.getPassword());
		String newPhone=this.newPhoneTxt.getText();
		
		int isChangeName=0;
		if(newName.equals("")) {
			name=user.getName();
		}else {
			name=newName;
			isChangeName=1;
		}
		
		if(newPwd.equals("")) {
			pwd=new String(user.getPassword());
		}else {
			pwd=newPwd;
		}
		
		if(newPhone.equals("")) {
			phone=user.getPhone();
		}else {
			phone=newPhone;
		}
		
		
		LinkedList<User>userLink=userDao.readAllUser();
		
		int flag=0;
		if(isChangeName==0) { //修改了其他选项
			for(User currentUser:userLink) {
				if(currentUser.getName().equals(user.getName())) {
					flag=1;
					currentUser.setName(name);
					currentUser.setPassword(pwd);
					currentUser.setPhone(phone);
					break;
				}
			}
			userDao.writeAllUser(userLink);
		}else {
			//因为修改了用户名，所以订单信息里的信息也要修改
			String oldName=user.getName();
			
			orderDao.EditOrderTxt(oldName, newName); //修改订单中的用户名
			
			for(User currentUser:userLink) {
				if(currentUser.getName().equals(user.getName())) {
					flag=1;
					currentUser.setName(name);
					currentUser.setPassword(pwd);
					currentUser.setPhone(phone);
					break;
				}
			}
			userDao.writeAllUser(userLink);
			
		}
		JOptionPane.showMessageDialog(null, "修改成功,请重新登录!");
		dispose();
		JFrame jf=new LogOnFrm() ;
	    InitFrmTool ift=new InitFrmTool("src\\img\\bg.png");
	    ift.InitFrm(ift.getImgPath(), jf);
	}
}
