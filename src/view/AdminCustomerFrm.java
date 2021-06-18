package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import model.Admin;
import model.Food;
import model.User;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.OrderController;
import controller.UserController;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

/**
 * 管理员管理消费者信息界面
 * @author HP
 *
 */
public class AdminCustomerFrm extends JFrame {
	int width=20,height=20;
	private JPanel contentPane;
	private JTextField customerIdTxt;
	private JTable infoTable;
	private JTextField userIdTxt;
	private JTextField pwdTxt;
	private UserController userDao=new UserController();
	private OrderController orderDao=new OrderController();
	private ImageIcon userIcon=new ImageIcon("src//img//user.png");
    private ImageIcon pwdIcon=new ImageIcon("src//img//pwd.png");
    private ImageIcon backIcon=new ImageIcon("src//img//back.png");
    private ImageIcon searchIcon=new ImageIcon("src//img//search.png");
    private ImageIcon deleteIcon=new ImageIcon("src//img//delete.png");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminCustomerFrm frame = new AdminCustomerFrm(new Admin());
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
	public AdminCustomerFrm(Admin admin) {
		userIcon.setImage(userIcon.getImage().getScaledInstance(width+5, height+5, Image.SCALE_DEFAULT));
	    pwdIcon.setImage(pwdIcon.getImage().getScaledInstance(width+5, height+5, Image.SCALE_DEFAULT));
	    searchIcon.setImage(searchIcon.getImage().getScaledInstance(width+5, height+5, Image.SCALE_DEFAULT));
		backIcon.setImage(backIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		deleteIcon.setImage(deleteIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		
		setTitle("\u7BA1\u7406\u5458\u6D88\u8D39\u8005\u7BA1\u7406\u754C\u9762");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 730, 507);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton backBtn = new JButton("\u8FD4\u56DE");
		backBtn.setBackground(new Color(204, 255, 255));
		backBtn.setIcon(backIcon);
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new AdminJrm(admin).setVisible(true);
			}
		});
		backBtn.setFont(new Font("宋体", Font.BOLD, 18));
		
		JLabel lblNewLabel = new JLabel("\u8BF7\u8F93\u5165\u6D88\u8D39\u8005Id\uFF1A");
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 15));
		
		customerIdTxt = new JTextField();
		customerIdTxt.setColumns(10);
		customerIdTxt.setBorder(new LineBorder(new java.awt.Color(127,157,185),1,false));
		
		JButton searchBtn = new JButton("\u67E5\u8BE2");
		searchBtn.setBackground(new Color(204, 255, 255));
		searchBtn.setIcon(searchIcon);
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int num=searchActionPerformed(e);
				if(num==0) {
					JOptionPane.showMessageDialog(null, "查无此人!");
					return;
				}
			}
		});
		searchBtn.setFont(new Font("宋体", Font.BOLD, 15));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 204));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(111)
					.addComponent(lblNewLabel)
					.addGap(18)
					.addComponent(customerIdTxt, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
					.addComponent(searchBtn)
					.addGap(196))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(38)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(backBtn))
					.addContainerGap(45, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(47)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 573, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(86, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(35)
					.addComponent(backBtn)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(searchBtn)
						.addComponent(customerIdTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(22, Short.MAX_VALUE))
		);
		
		JLabel userLabel = new JLabel("\u6D88\u8D39\u8005Id\uFF1A");
		userLabel.setIcon(userIcon);
		userLabel.setFont(new Font("宋体", Font.BOLD, 18));
		
		userIdTxt = new JTextField();
		userIdTxt.setEditable(false);
		userIdTxt.setColumns(10);
		
		JLabel pwdLabel = new JLabel("\u5BC6\u7801\uFF1A");
		pwdLabel.setIcon(pwdIcon);
		pwdLabel.setFont(new Font("宋体", Font.BOLD, 18));
		
		pwdTxt = new JTextField();
		pwdTxt.setEditable(false);
		pwdTxt.setColumns(10);
		
		JButton deleteBtn = new JButton("\u5220\u9664");
		deleteBtn.setBackground(new Color(204, 255, 255));
		deleteBtn.setIcon(deleteIcon);
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int res=JOptionPane.showConfirmDialog(null, "是否删除该用户?");
				if(res==0) {
					int num=deleteActionPerformed(e);
					if(num==1) {
						JOptionPane.showMessageDialog(null, "删除成功!");
						return;
					}else {
						JOptionPane.showMessageDialog(null, "删除失败!");
						return;
					}
				}
				
			}
		});
		deleteBtn.setFont(new Font("宋体", Font.BOLD, 20));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(36)
							.addComponent(userLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(userIdTxt, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
							.addGap(34)
							.addComponent(pwdLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(pwdTxt, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(228)
							.addComponent(deleteBtn, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(88, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(43)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(userLabel)
						.addComponent(userIdTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(pwdLabel)
						.addComponent(pwdTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(37)
					.addComponent(deleteBtn)
					.addContainerGap(49, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		infoTable = new JTable();
		infoTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mousePressedActionPerformed(e);
			}
		});
		infoTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u8D26\u53F7", "\u5BC6\u7801"
			}
		));
		scrollPane.setViewportView(infoTable);
		this.setLocationRelativeTo(null);//窗口居中
		contentPane.setLayout(gl_contentPane);
	}
	
	/**
	 * 管理员删除用户
	 * @param e
	 */
	private int deleteActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		User user=new User();
		user.setName(this.userIdTxt.getText());
		user.setPassword(this.pwdTxt.getText());
		int num=userDao.deleteUser(user);
		return num;
	}

	/**
	 * 点击后显示到label
	 * @param e
	 */
	private void mousePressedActionPerformed(MouseEvent e) {
		// TODO Auto-generated method stub
		int row=infoTable.getSelectedRow();//当前选中行
		String name=(String)infoTable.getValueAt(row,0);
		String pwd=(String)infoTable.getValueAt(row, 1);
		this.userIdTxt.setText(name);
		this.pwdTxt.setText(pwd);
	}

	/**
	 * 管理员查询消费者信息
	 * @param e
	 */
	private int  searchActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int flag=0;
		String keyword=this.customerIdTxt.getText();
		LinkedList<User> userList=userDao.readAllUser();
		if(keyword.equals("")) { //查询所有的信息
			userList.remove(0);
			this.fillTable(userList);
			flag=1;
		}else {
			LinkedList<User> newUserList=new LinkedList<>();
			for(User currentUser:userList) {
				if(currentUser.getName().equals(keyword)) {
					newUserList.add(currentUser);
					flag=1;
					this.fillTable(newUserList);
					break;
				}
			}
		}
		return flag;
		
	}
	
	/**
	 * 填写table
	 * @param list :Food链表
	 */
	private void fillTable(LinkedList<User> list) {
		// TODO Auto-generated method stub
		DefaultTableModel dtm=(DefaultTableModel) infoTable.getModel();
		dtm.setRowCount(0);//清空表格
		for(User currentUser:list) {
			Vector v=new Vector();
			v.add(currentUser.getName());
			v.add(currentUser.getPassword());
			dtm.addRow(v);
		}
	}
}
