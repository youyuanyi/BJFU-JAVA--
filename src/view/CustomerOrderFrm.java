package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controller.OrderController;
import controller.ShopController;
import controller.UserController;
import model.Food;
import model.Shop;
import model.User;
import model.UserOrder;
import util.InitFrmTool;

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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
/**
 * ������Ԥ����Ʒ����
 * @author HP
 *
 */
public class CustomerOrderFrm extends JFrame implements Runnable{

	private JPanel contentPane;
	private UserController userDao=new UserController();
	private ShopController shopDao=new ShopController();
	private OrderController orderDao=new OrderController();
	private JTextField shopNameTxt;
	private JTable infoTable;
	private JTextField foodIdTxt;
	private JTextField foodNameTxt;
	private JTextField foodPriceTxt;
	private JTextField foodNumTxt;
	private JTextField nameTxt;
	private JTextField timeTxt;
	int width=25,height=25;
	private ImageIcon searchIcon=new ImageIcon("src//img//search.png");
	private ImageIcon backIcon=new ImageIcon("src//img//back.png");
	private ImageIcon orderIcon=new ImageIcon("src//img//order.png");

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerOrderFrm jf=new CustomerOrderFrm(new User()) ;
					jf.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void run() {
		OrderActionPerformed(new User());
	}
	/**
	 * Create the frame.
	 */
	public CustomerOrderFrm(User user) {
		System.out.println(user);
		setResizable(false);
		setTitle("\u6D88\u8D39\u8005\u83DC\u54C1\u9884\u5B9A\u529F\u80FD");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 782, 549);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 204));
		//contentPane.setBackground(new Color(255, 255, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		searchIcon.setImage(searchIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		backIcon.setImage(backIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		orderIcon.setImage(orderIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		
		JLabel lblNewLabel = new JLabel("\u8BF7\u8F93\u5165\u5546\u5BB6\uFF1A");
		lblNewLabel.setFont(new Font("����", Font.BOLD, 20));
		
		shopNameTxt = new JTextField();
		shopNameTxt.setBorder(new LineBorder(new java.awt.Color(127,157,185),1,false));
		shopNameTxt.setColumns(10);
		
		JButton searchBtn = new JButton("\u67E5\u8BE2\u83DC\u54C1");
		searchBtn.setBackground(new Color(204, 255, 255));
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchActionPerformed(e);
			}
		});
		searchBtn.setFont(new Font("����", Font.BOLD, 16));
		searchBtn.setIcon(searchIcon);
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 204));
		
		JButton backBtn = new JButton("\u8FD4\u56DE");
		backBtn.setBackground(new Color(204, 255, 255));
		backBtn.setIcon(backIcon);
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new CustomerFrm(user).setVisible(true);
			}
		});
		backBtn.setFont(new Font("����", Font.BOLD, 18));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(119)
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(shopNameTxt, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
							.addGap(31)
							.addComponent(searchBtn))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(26)
							.addComponent(backBtn))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(58)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 629, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(71, GroupLayout.PREFERRED_SIZE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(28)
					.addComponent(backBtn)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(shopNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(searchBtn)))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(31))
		);
		
		JLabel lblNewLabel_1 = new JLabel("\u83DC\u54C1Id:");
		lblNewLabel_1.setFont(new Font("����", Font.BOLD, 15));
		
		foodIdTxt = new JTextField();
		foodIdTxt.setEditable(false);
		foodIdTxt.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("\u83DC\u54C1\uFF1A");
		lblNewLabel_2.setFont(new Font("����", Font.BOLD, 15));
		
		foodNameTxt = new JTextField();
		foodNameTxt.setEditable(false);
		foodNameTxt.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("\u5355\u4EF7\uFF1A");
		lblNewLabel_3.setFont(new Font("����", Font.BOLD, 15));
		
		foodPriceTxt = new JTextField();
		foodPriceTxt.setEditable(false);
		foodPriceTxt.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("\u6570\u91CF\uFF1A");
		lblNewLabel_4.setFont(new Font("����", Font.BOLD, 15));
		
		foodNumTxt = new JTextField();
		foodNumTxt.setEditable(false);
		foodNumTxt.setColumns(10);
		
		JButton orderBtn = new JButton("\u9884\u5B9A");
		orderBtn.setBackground(new Color(204, 255, 255));
		orderBtn.setIcon(orderIcon);
		orderBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int num=OrderActionPerformed(user);
				if(num==0) {
					JOptionPane.showMessageDialog(null, "��ǰ��Ʒ����Ϊ0,��Ǹ!");
					return;
				}else if(num==1) {
					JOptionPane.showMessageDialog(null, "Ԥ���ɹ�!");
					searchActionPerformed(e);
					return;
				}else if(num==2) {
					JOptionPane.showMessageDialog(null, "����ѡ��һ���Ʒ!");
					return;
				}else if(num==3) {
					JOptionPane.showMessageDialog(null, "����ѡ��Ԥ��ʱ��!");
					return;
				}
			}
		});
		orderBtn.setFont(new Font("����", Font.BOLD, 16));
		
		JLabel lblNewLabel_5 = new JLabel("\u9910\u9986\uFF1A");
		lblNewLabel_5.setFont(new Font("����", Font.BOLD, 15));
		
		nameTxt = new JTextField();
		nameTxt.setEditable(false);
		nameTxt.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("\u65F6\u95F4\uFF1A");
		lblNewLabel_6.setFont(new Font("����", Font.BOLD, 15));
		
		timeTxt = new JTextField();
		timeTxt.setBorder(new LineBorder(new java.awt.Color(127,157,185),1,false));
		timeTxt.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(58)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(foodIdTxt, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(1)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel_3)
								.addComponent(lblNewLabel_5))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(foodPriceTxt)
								.addComponent(nameTxt, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))))
					.addGap(58)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(foodNameTxt, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel_6)
								.addComponent(lblNewLabel_4))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(timeTxt, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
								.addComponent(foodNumTxt))))
					.addGap(18)
					.addComponent(orderBtn, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(31)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_2)
							.addComponent(foodNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(foodIdTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addGap(28)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(foodPriceTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_4)
						.addComponent(foodNumTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(nameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_5)
						.addComponent(lblNewLabel_6)
						.addComponent(timeTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(orderBtn))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		
		infoTable = new JTable();
		infoTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				selectFoodActionPerformed(e);
			}
		});
		infoTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u9910\u9986", "\u83DC\u54C1Id", "\u83DC\u54C1", "\u5355\u4EF7", "\u6570\u91CF"
			}
		));
		scrollPane.setViewportView(infoTable);
		contentPane.setLayout(gl_contentPane);
		this.fillTable(new LinkedList<Shop>());
	}
	
	/**
	 * �����߲�ƷԤ��,ͬ��������food
	 * �ȼ��shop.txt�е��̼Ҷ�Ӧ�Ĳ�Ʒ�������������0�����ܽ���ͬ��
	 * �û�Ԥ������Ϣ������order.txt��û�е�
	 * @param e
	 * @param user
	 * @return 0:Ԥ��ʧ��,1:�ɹ�Ԥ��,2:δѡ���̼�,3:δѡ��ʱ��
	 */
	private  synchronized int OrderActionPerformed(User user) {
		// TODO Auto-generated method stub
		
		String userName=user.getName(); //��������
		String shopName=this.nameTxt.getText();//�Ȼ�ȡ�̼���
		String orderTime=this.timeTxt.getText();//��ȡԤ��ʱ��
		String foodId=this.foodIdTxt.getText();//food_id
		LinkedList<UserOrder> orderList=orderDao.readAllOrder();
		int flag=0;
		if(shopName.equals("")) {
			return 2;
		}
		if(orderTime.equals("")) {
			return 3;
		}
		int isNewOrder=1;//1�������µĶ�����0�������µĶ���
		StringBuffer orderInfo=new StringBuffer();
		for(UserOrder currentOrder:orderList) {
			if(currentOrder.getUserName().equals(userName) && currentOrder.getShopName().equals(shopName)
					&& currentOrder.getOrderTime().equals(orderTime)) { //�ҵ�����
				orderInfo.append(currentOrder.getOrderFood());
				isNewOrder=0;//˵���û��Ѿ����������,�����µĶ���
				break;
			}
		}
		if(isNewOrder==1) {
			orderInfo.append("��Ʒ��Ϣ:");
		}
		int hasChange=0;
		//�޸�shop.txt�еĲ�Ʒ����
		LinkedList<Shop> shopList=shopDao.readAllShop();
		for(Shop currentShop:shopList) {
			if(currentShop.getName().equals(shopName)) { //�ҵ��̼�
				for(Food currentFood:currentShop.getFood()) {
					if(currentFood.getFood_id().equals(foodId)) { //����ҵ��ò�Ʒ
						if(currentFood.getFood_nums()>0) {
							synchronized(currentFood){
							int nums=currentFood.getFood_nums();
							String s=currentFood.getFood_name()+":"+currentFood.getFood_price()+",";
							currentFood.setFood_nums(nums-1);//��ǰfood�����޸�					
							orderInfo.append(s);//��ʽΪ"��Ʒ��Ϣ:��Ʒ1:��Ǯ,��Ʒ2:��Ǯ,..."
							hasChange=1;
							break;
							}
						}else { //��Ʒ����Ϊ0
							return flag;
						}
					}
				}
			}
		}	
		shopDao.wrtieAllShop(shopList);
		
		if(hasChange==1) {
		//�Ѿ���ȡ�˲�Ʒ����ϸ��Ϣ,������Ҫд��order.txt
		if(isNewOrder==0) {
			for(UserOrder currentOrder:orderList) {
				if(currentOrder.getUserName().equals(userName) && currentOrder.getShopName().equals(shopName)
						&& currentOrder.getOrderTime().equals(orderTime)) { //�ҵ�����
					currentOrder.setOrderFood(new String(orderInfo));
					flag=1;
					break;
				}
			}
		}else {//���µĶ���
			UserOrder newOrder=new UserOrder();//����һ���µĶ���
			newOrder.setUserName(userName);
			newOrder.setShopName(shopName);
			newOrder.setOrderTime(orderTime);
			newOrder.setOrderFood(new String(orderInfo));
			orderList.add(newOrder);
			flag=1;
		}
		orderDao.writeAllOrders(orderList);
		}
		return flag;
	}

	/**
	 * ��table��Ϣ����label��
	 * @param e
	 */
	private void selectFoodActionPerformed(MouseEvent e) {
		// TODO Auto-generated method stub
		int row=infoTable.getSelectedRow();//��ǰѡ����
		this.nameTxt.setText((String)infoTable.getValueAt(row, 0));
		this.foodIdTxt.setText((String)infoTable.getValueAt(row, 1));
		this.foodNameTxt.setText((String)infoTable.getValueAt(row, 2));
		this.foodPriceTxt.setText((String)infoTable.getValueAt(row, 3));
		this.foodNumTxt.setText((String)infoTable.getValueAt(row, 4));
	}
	
	
	/**
	 * �û�����ؼ��ʲ�ѯ�̼Ҳ�Ʒ��Ϣ
	 * @param e
	 */
	private void searchActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String keyword=this.shopNameTxt.getText();
		if(keyword.equals("")){
			JOptionPane.showMessageDialog(null, "��������ؼ���!");
			return;
		}
		LinkedList<Shop> shopList=shopDao.readAllShop();
		LinkedList<Shop> newShopList=new LinkedList<>();
		try {
			for(Shop currentShop:shopList) {
				if(currentShop.getName().contains(keyword)){ //��������ƥ��
					newShopList.add(currentShop);  //����̼ҽ�����
				}
			}
			if(newShopList.size()==0) {
				JOptionPane.showMessageDialog(null, "��������Ч�ؼ���!");
				return;
			}
			this.fillTable(newShopList);
		}catch(Exception evt) {
			evt.printStackTrace();
		}
		
		
	}
	
	/**
	 * ��дtable
	 * @param list
	 */
	private void fillTable(LinkedList<Shop> list) {
		// TODO Auto-generated method stub
		DefaultTableModel dtm=(DefaultTableModel) infoTable.getModel();
		dtm.setRowCount(0);//��ձ��
		for(Shop currentShop:list) {
			Vector v=new Vector();
			for(Food currentFood:currentShop.getFood()) {
				v=new Vector();
				v.add(currentShop.getName());
				v.add(currentFood.getFood_id());
				v.add(currentFood.getFood_name());
				v.add(currentFood.getFood_price());
				v.add(currentFood.getFood_nums().toString());	
				dtm.addRow(v);
			}
			
		}
	}
}
