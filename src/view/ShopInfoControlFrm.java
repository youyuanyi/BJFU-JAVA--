package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controller.OrderController;
import controller.ShopController;
import model.Food;
import model.Shop;
import model.User;
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
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;

public class ShopInfoControlFrm extends JFrame {

	private JPanel contentPane;
	private JTextField newTypeTxt;
	private JPasswordField newPwdTxt;
	private JTextField newNameTxt;
	private JTextField newAddressTxt;
	private JTextField newPhoneTxt;
	private JTextArea infoArea;
	private ShopController shopDao=new ShopController();
	private OrderController orderDao=new OrderController();
	int width=20,height=20;
	private ImageIcon userIcon=new ImageIcon("src//img//user.png");
    private ImageIcon pwdIcon=new ImageIcon("src//img//pwd.png");
    private ImageIcon telIcon=new ImageIcon("src//img//tel.png");
    private ImageIcon searchIcon=new ImageIcon("src//img//search.png");
    private ImageIcon backIcon=new ImageIcon("src//img//back.png");
    private ImageIcon resetIcon=new ImageIcon("src//img//reset.png");
    private ImageIcon offIcon=new ImageIcon("src//img//注销.png");
    private ImageIcon editIcon=new ImageIcon("src//img//修改.png");
    private ImageIcon typeIcon=new ImageIcon("src//img//type.png");
    private ImageIcon addressIcon=new ImageIcon("src//img//address.png");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShopInfoControlFrm frame = new ShopInfoControlFrm(new Shop());
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
	public ShopInfoControlFrm(Shop shop) {
		
		userIcon.setImage(userIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
	    pwdIcon.setImage(pwdIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		telIcon.setImage(telIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		searchIcon.setImage(searchIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		backIcon.setImage(backIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		offIcon.setImage(offIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		editIcon.setImage(editIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		resetIcon.setImage(resetIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		typeIcon.setImage(typeIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		addressIcon.setImage(addressIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		
		setTitle("\u5546\u5BB6\u4FE1\u606F\u7BA1\u7406\u754C\u9762");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 690, 623);
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
				//这里需要写一个函数，生成新的shop对象,并且要调用ReadAll方法，生成一个商家链表，然后遍历链表，修改链表信息，再写入文件
				new ShopJrm(shop).setVisible(true);
			}
		});
		backBtn.setFont(new Font("宋体", Font.BOLD, 18));
		
		JLabel lblNewLabel = new JLabel("\u4E2A\u4EBA\u4FE1\u606F\u67E5\u8BE2");
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 20));
		
		JButton searchBtn = new JButton("\u67E5\u8BE2");
		searchBtn.setBackground(new Color(204, 255, 255));
		searchBtn.setIcon(searchIcon);
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchActionPerformed(e,shop);
			}
		});
		searchBtn.setFont(new Font("宋体", Font.BOLD, 15));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel_1 = new JLabel("\u4E2A\u4EBA\u4FE1\u606F\u4FEE\u6539");
		lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 18));
		
		JButton editBtn = new JButton("\u4FEE\u6539");
		editBtn.setBackground(new Color(204, 255, 255));
		editBtn.setIcon(editIcon);
		editBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int num=EditActionPerformed(e,shop);
				if(num==1) {
					JOptionPane.showMessageDialog(null, "修改成功!请重新登录");
					dispose();
					JFrame jf=new LogOnFrm() ;
				    InitFrmTool ift=new InitFrmTool("src\\img\\bg.png");
				    ift.InitFrm(ift.getImgPath(), jf);
				}else {
					JOptionPane.showMessageDialog(null, "修改失败!");
				}
			}
		});
		editBtn.setFont(new Font("宋体", Font.BOLD, 15));
		
		JLabel typeLabel = new JLabel("\u7C7B\u578B:");
		typeLabel.setIcon(typeIcon);
		typeLabel.setFont(new Font("宋体", Font.BOLD, 18));
		
		newTypeTxt = new JTextField();
		newTypeTxt.setBorder(new LineBorder(new java.awt.Color(127,157,185),1,false));
		newTypeTxt.setColumns(10);
		
		JLabel pwdLabel = new JLabel("\u5BC6\u7801:");
		pwdLabel.setIcon(pwdIcon);
		pwdLabel.setFont(new Font("宋体", Font.BOLD, 18));
		
		newPwdTxt = new JPasswordField();
		newPwdTxt.setBorder(new LineBorder(new java.awt.Color(127,157,185),1,false));
		JLabel nameLabel = new JLabel("\u540D\u79F0:");
		nameLabel.setIcon(userIcon);
		nameLabel.setFont(new Font("宋体", Font.BOLD, 18));
		
		newNameTxt = new JTextField();
		newNameTxt.setBorder(new LineBorder(new java.awt.Color(127,157,185),1,false));
		newNameTxt.setColumns(10);
		
		JLabel addressLabel = new JLabel("\u5730\u5740:");
		addressLabel.setIcon(addressIcon);
		addressLabel.setFont(new Font("宋体", Font.BOLD, 18));
		
		newAddressTxt = new JTextField();
		newAddressTxt.setBorder(new LineBorder(new java.awt.Color(127,157,185),1,false));
		newAddressTxt.setColumns(10);
		
		JLabel telLabel = new JLabel("\u7535\u8BDD:");
		telLabel.setIcon(telIcon);
		telLabel.setFont(new Font("宋体", Font.BOLD, 18));
		
		newPhoneTxt = new JTextField();
		newPhoneTxt.setBorder(new LineBorder(new java.awt.Color(127,157,185),1,false));
		newPhoneTxt.setColumns(10);
		
		JButton resetBtn = new JButton("\u91CD\u7F6E");
		resetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValue(e);
			}
		});
		resetBtn.setBackground(new Color(204, 255, 255));
		resetBtn.setIcon(resetIcon);
		resetBtn.setFont(new Font("宋体", Font.BOLD, 15));
		
		JButton offBtn = new JButton("\u6CE8\u9500");
		offBtn.setBackground(new Color(204, 255, 255));
		offBtn.setIcon(offIcon);
		offBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteActionPerformed(e,shop);
			}
		});
		offBtn.setFont(new Font("宋体", Font.BOLD, 16));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(159)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(pwdLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(newPwdTxt))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(typeLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(newTypeTxt, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(nameLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(newNameTxt))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(telLabel)
										.addComponent(addressLabel))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(newPhoneTxt, GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
										.addComponent(newAddressTxt)))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(29)
									.addComponent(backBtn)
									.addPreferredGap(ComponentPlacement.RELATED, 439, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addContainerGap()
									.addComponent(lblNewLabel)
									.addGap(48)
									.addComponent(searchBtn)
									.addGap(54)))
							.addComponent(offBtn)
							.addGap(36))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(187)
							.addComponent(editBtn)
							.addGap(41)
							.addComponent(resetBtn))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(55)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 583, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(28, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(231)
					.addComponent(lblNewLabel_1)
					.addContainerGap(324, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGap(31)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(backBtn)
								.addComponent(offBtn))
							.addPreferredGap(ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
							.addComponent(searchBtn))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
					.addGap(26)
					.addComponent(lblNewLabel_1)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(typeLabel)
						.addComponent(newTypeTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(pwdLabel)
						.addComponent(newPwdTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(nameLabel)
						.addComponent(newNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(addressLabel)
						.addComponent(newAddressTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(newPhoneTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(telLabel))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(editBtn)
						.addComponent(resetBtn))
					.addGap(15))
		);
		
		infoArea = new JTextArea();
		infoArea.setLineWrap(true);//允许换行
		//infoArea.setWrapStyleWord(true);//激活断行不断字功能
		this.setLocationRelativeTo(null);//窗口居中
		scrollPane.setViewportView(infoArea);
		contentPane.setLayout(gl_contentPane);
	}
	
	private void resetValue(ActionEvent e) {
		// TODO Auto-generated method stub
		this.newNameTxt.setText("");
		this.newTypeTxt.setText("");
		this.newNameTxt.setText("");
		this.newPhoneTxt.setText("");
		this.newPwdTxt.setText("");
		this.newAddressTxt.setText("");
	}

	/**
	 * 注销商家
	 * @param e
	 */
	private void deleteActionPerformed(ActionEvent e,Shop shop) {
		// TODO Auto-generated method stub
		int res=JOptionPane.showConfirmDialog(null, "是否要注销?");
		if(res==0) {
			int num=shopDao.deleteShop(shop);
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

	/**
	 * 修改个人信息
	 * @param e
	 * @param shop
	 */
	private int EditActionPerformed(ActionEvent e, Shop shop) {
		// TODO Auto-generated method stub
		String newName=this.newNameTxt.getText();
		String newPwd=new String(this.newPwdTxt.getPassword());
		String newAddress=this.newAddressTxt.getText();
		String newType=this.newTypeTxt.getText();
		String newPhone=this.newPhoneTxt.getText();
		if(StringUtil.isEmpty(newName) &&StringUtil.isEmpty(newPwd) &&StringUtil.isEmpty(newAddress)
				&& StringUtil.isEmpty(newType) && StringUtil.isEmpty(newPhone)) {
			JOptionPane.showMessageDialog(null, "请至少选择一项!");
			return 0;
		}
		String name,pwd,address,type,phone;
		int isChangeName=0;
		if(newName.equals("")) {
			name=shop.getName();
		}else {
			name=newName;
			isChangeName=1; //更改了名称，order信息也要修改
		}
		if(newPwd.equals("")) {
			pwd=shop.getPassword();
		}else {
			pwd=newPwd;
		}
		if(newAddress.equals("")) {
			address=shop.getAddress();
		}else {
			address=newAddress;
		}
		if(newType.equals("")) {
			type=shop.getShopType();
		}else {
			type=newType;
		}
		if(newPhone.equals("")) {
			phone=shop.getPhone();
		}else {
			phone=newPhone;
		}
		int flag=0;
		LinkedList<Shop> shopList=shopDao.readAllShop();//先获取商家链表
		System.out.println("修改前的商家个数:"+shopList.size());
		if(isChangeName==0) { //没有修改商家名称
			for(Shop currentShop:shopList) {
				if(currentShop.getId().equals(shop.getId())) { //以Id匹配，因为Id是不能修改的
					currentShop.setShopType(type);
					currentShop.setName(name);
					currentShop.setPassword(pwd);
					currentShop.setAddress(address);
					currentShop.setPhone(phone);
					flag=1;
					break;
				}
			}
			shopDao.wrtieAllShop(shopList);
		}else {
			//修改了商家名称，还需要修改order中的商家名称
			//因为修改了用户名，所以订单信息里的信息也要修改
			String oldName=shop.getName();
			
			orderDao.EditOrderTxt(oldName, newName); //修改订单中的商家名
			
			for(Shop currentShop:shopList) {
				if(currentShop.getId().equals(shop.getId())) {
					flag=1;
					currentShop.setName(name);
					currentShop.setPassword(pwd);
					currentShop.setPhone(phone);
					break;
				}
			}
			shopDao.wrtieAllShop(shopList);
			
		}
		
		return flag;
	}
	
	/**
	 * 查询商家个人信息
	 * @param e
	 */
	private void searchActionPerformed(ActionEvent e,Shop shop) {
		// TODO Auto-generated method stub
		this.infoArea.setText("");
		this.infoArea.append("类型: "+shop.getShopType()+"\n");
		this.infoArea.append("用户名: "+shop.getName()+"\n");
		this.infoArea.append("密码: "+shop.getPassword()+"\n");
		this.infoArea.append("平均评分: "+shop.getAvgScore()+"\n");
		this.infoArea.append("平均价格: "+shop.getAveprice()+"\n");
		this.infoArea.append("地址: "+shop.getAddress()+"\n");
		this.infoArea.append("联系方式: "+shop.getPhone()+"\n");
		if(shop.getFood()!=null) {
		for(Food food:shop.getFood()) {
			this.infoArea.append("菜品Id: "+food.getFood_id()
								+" 菜品: "+food.getFood_name()
								+" 价格: "+food.getFood_price()
								+" 数量: "+food.getFood_nums()+"\n");
		}}
		int cnt=0;
		if(shop.getComment()!=null) {
		for(String cmt:shop.getComment()) {
			this.infoArea.append("Comment"+cnt+": "+cmt+"\n");
			cnt++;
		}
		}
		
	}
}
