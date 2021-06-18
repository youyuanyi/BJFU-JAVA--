package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Food;
import model.Shop;
import model.UserOrder;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.OrderController;
import controller.ShopController;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ShopMenuControlFrm extends JFrame {
	
	int width=20,height=20;
	private JPanel contentPane;
	private JTextField foodNameTxt;
	private JTextField foodPriceTxt;
	private JTextField foodNumTxt;
	private JTable infoTable;
	private ShopController shopDao=new ShopController();
	private OrderController orderDao=new OrderController();
	private Food food=new Food();
	private Food changeFood=new Food();
	private static HashSet<Integer> hs=new HashSet<>(); //用来存放food_id随机数
	
	private ImageIcon searchIcon=new ImageIcon("src//img//search.png");
    private ImageIcon backIcon=new ImageIcon("src//img//back.png");
    private ImageIcon editIcon=new ImageIcon("src//img//修改.png");
    private ImageIcon addIcon=new ImageIcon("src//img//add.png");
    private ImageIcon deleteIcon=new ImageIcon("src//img//delete.png");
    private ImageIcon foodIcon=new ImageIcon("src//img//food.png");
    private ImageIcon priceIcon=new ImageIcon("src//img//price.png");
    private ImageIcon numIcon=new ImageIcon("src//img//num.png");
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShopMenuControlFrm frame = new ShopMenuControlFrm(new Shop());
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
	public ShopMenuControlFrm(Shop shop) {
		
		searchIcon.setImage(searchIcon.getImage().getScaledInstance(width+5, height+5, Image.SCALE_DEFAULT));
		backIcon.setImage(backIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		editIcon.setImage(editIcon.getImage().getScaledInstance(width-5, height-5, Image.SCALE_DEFAULT));
		deleteIcon.setImage(deleteIcon.getImage().getScaledInstance(width-5, height-5, Image.SCALE_DEFAULT));
		addIcon.setImage(addIcon.getImage().getScaledInstance(width-5, height-5, Image.SCALE_DEFAULT));
		foodIcon.setImage(foodIcon.getImage().getScaledInstance(width+5, height+5, Image.SCALE_DEFAULT));
		priceIcon.setImage(priceIcon.getImage().getScaledInstance(width+5, height+5, Image.SCALE_DEFAULT));
		numIcon.setImage(numIcon.getImage().getScaledInstance(width+5, height+5, Image.SCALE_DEFAULT));
		
		setTitle("\u5546\u5BB6\u83DC\u54C1\u7BA1\u7406\u754C\u9762");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 667, 631);
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
		
		JLabel lblNewLabel = new JLabel("\u83DC\u54C1\u4FE1\u606F");
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 22));
		
		JButton searchBtn = new JButton("\u67E5\u8BE2");
		searchBtn.setBackground(new Color(204, 255, 255));
		searchBtn.setIcon(searchIcon);
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchFoodActionPerformed(e,shop);
			}
		});
		searchBtn.setFont(new Font("宋体", Font.BOLD, 16));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 204));
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "\u4FEE\u6539\u83DC\u54C1\u4FE1\u606F", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(194)
							.addComponent(lblNewLabel)
							.addGap(18)
							.addComponent(searchBtn))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(25)
							.addComponent(backBtn))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(55)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 529, GroupLayout.PREFERRED_SIZE)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 539, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(49, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(29)
					.addComponent(backBtn)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(searchBtn))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(29, Short.MAX_VALUE))
		);
		
		infoTable = new JTable();
		infoTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseActionPerformed(e);
			}
		});
		infoTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "\u83DC\u540D", "\u5355\u4EF7", "\u6570\u91CF"
			}
		));
		scrollPane.setViewportView(infoTable);
		
		JLabel foodNameLabel = new JLabel("\u83DC\u540D\uFF1A");
		foodNameLabel.setIcon(foodIcon);
		foodNameLabel.setFont(new Font("宋体", Font.BOLD, 18));
		
		foodNameTxt = new JTextField();
		foodNameTxt.setBorder(new LineBorder(new java.awt.Color(127,157,185),1,false));
		foodNameTxt.setText("");
		foodNameTxt.setColumns(10);
		
		JLabel foodPriceLabel = new JLabel("\u5355\u4EF7\uFF1A");
		foodPriceLabel.setIcon(priceIcon);
		foodPriceLabel.setFont(new Font("宋体", Font.BOLD, 18));
		
		foodPriceTxt = new JTextField();
		foodPriceTxt.setBorder(new LineBorder(new java.awt.Color(127,157,185),1,false));
		foodPriceTxt.setColumns(10);
		
		JLabel foodNumLabel = new JLabel("\u6570\u91CF\uFF1A");
		foodNumLabel.setIcon(numIcon);
		foodNumLabel.setFont(new Font("宋体", Font.BOLD, 18));
		
		foodNumTxt = new JTextField();
		foodNumTxt.setBorder(new LineBorder(new java.awt.Color(127,157,185),1,false));
		foodNumTxt.setColumns(10);
		
		JButton editBtn = new JButton("\u4FEE\u6539");
		editBtn.setBackground(new Color(204, 255, 255));
		editBtn.setIcon(editIcon);
		editBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int num=EditActionPerformed(e,shop);
				if(num==1) {
					JOptionPane.showMessageDialog(null, "修改成功!");
					return;
				}else if(num==0){
					JOptionPane.showMessageDialog(null, "修改失败!");
					return;
				}
			}
		});
		editBtn.setFont(new Font("宋体", Font.BOLD, 18));
		
		JButton addBtn = new JButton("\u589E\u52A0");
		addBtn.setBackground(new Color(204, 255, 255));
		addBtn.setIcon(addIcon);
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int num=AddActionPerformed(e,shop);
				if(num==1) {
					JOptionPane.showMessageDialog(null, "添加成功!");
					return;
				}else {
					JOptionPane.showMessageDialog(null, "添加失败!");
					return;
				}
			}
			
		});
		addBtn.setFont(new Font("宋体", Font.BOLD, 18));
		
		JButton deleteBtn = new JButton("\u5220\u9664");
		deleteBtn.setBackground(new Color(204, 255, 255));
		deleteBtn.setIcon(deleteIcon);
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int num=deleteActionPerformed(e,shop);
				if(num==1) {
					JOptionPane.showMessageDialog(null, "删除成功!");
					return;
				}else {
					JOptionPane.showMessageDialog(null, "删除失败!");
					return;
				}
			}
		});
		deleteBtn.setFont(new Font("宋体", Font.BOLD, 18));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(111, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(editBtn)
							.addGap(29))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(foodPriceLabel)
								.addComponent(foodNameLabel)
								.addComponent(foodNumLabel))
							.addPreferredGap(ComponentPlacement.UNRELATED)))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(addBtn)
							.addGap(32)
							.addComponent(deleteBtn))
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(foodNumTxt, Alignment.LEADING)
							.addComponent(foodPriceTxt, Alignment.LEADING)
							.addComponent(foodNameTxt, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)))
					.addContainerGap(142, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(25)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(foodNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(foodNameLabel))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(foodPriceTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(foodPriceLabel))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(foodNumTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(foodNumLabel))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(deleteBtn)
						.addComponent(editBtn)
						.addComponent(addBtn))
					.addContainerGap(14, Short.MAX_VALUE))
		);
		fillTable(new LinkedList<Food>());
		panel.setLayout(gl_panel);
		this.setLocationRelativeTo(null);//窗口居中
		contentPane.setLayout(gl_contentPane);
	}
	
	/**
	 * 删除菜品信息
	 * @param e
	 * @param shop
	 */
	private int deleteActionPerformed(ActionEvent e, Shop shop) {
		// TODO Auto-generated method stub
		int flag=0;
		try {
			String shopName=shop.getName();
			String newName=this.foodNameTxt.getText();
			String newPrice=this.foodPriceTxt.getText();
			String newNum=this.foodNumTxt.getText();
			if(newName.equals("")&&newPrice.equals("")&&newNum.equals("")) {
				JOptionPane.showMessageDialog(null, "请至少选择一项修改对象!");
				return 2;
			}
			LinkedList<Shop> newShopList=new LinkedList<Shop>(); //新的链表
	
			LinkedList<Shop> shopList=shopDao.readAllShop();
			for(Shop currentShop:shopList) {
				if(currentShop.getId().equals(shop.getId())) { //如果是当前商家
					LinkedList<Food> foodList=new LinkedList<>();
					for(Food currentFood:currentShop.getFood()) { //删除菜品信息
						if(currentFood.getFood_id().equals(food.getFood_id())) {
							String food_name=currentFood.getFood_name();
							shopDao.deletePartialOrder(shopName, food_name); //删除订单中的信息
							flag=1;
							continue;
						}else {
							foodList.add(currentFood);
						}
					}
					currentShop.setFood(foodList);
					break;
				}else {
					newShopList.add(currentShop);
				}
			}
			shopDao.wrtieAllShop(shopList);
			
			return flag;
			
		}catch(Exception evt) {
			evt.printStackTrace();
		}
		return flag;
	}

	/**
	 * 新增菜品信息
	 * @param e
	 * @param shop
	 */
	private int  AddActionPerformed(ActionEvent e, Shop shop) {
		// TODO Auto-generated method stub
		int flag=0;
		try {
			String newName=this.foodNameTxt.getText();
			String newPrice=this.foodPriceTxt.getText();
			String newNum=this.foodNumTxt.getText();
			Integer newId=(int)(Math.random()*10000)+1;
			while(!hs.add(newId)) { //如果一直重复，则重新生成Id
				newId=(int)(Math.random()*10000)+1;
			}
		
			if(newName.equals("")||newPrice.equals("")||newNum.equals("")) {
				JOptionPane.showMessageDialog(null, "请填写完整的信息!");
				return flag;
			}
			String Id=newId.toString();
			Food food=new Food();
			food.setFood_id(Id);
			food.setFood_name(newName);
			food.setFood_price(newPrice);
			food.setFood_nums(Integer.parseInt(newNum));
			
			LinkedList<Shop> shopList=shopDao.readAllShop();
			System.out.println("添加菜品前的商家个数:"+shopList.size());
			for(Shop currentShop:shopList) {
				if(currentShop.getId().equals(shop.getId())) {
					flag=1;
					if(currentShop.getFood()!=null) { //有食物
						LinkedList<Food> foodList=currentShop.getFood();
						System.out.println(currentShop.getName()+"目前有:"+foodList.size()+"个菜品");
						foodList.add(food);
						currentShop.setFood(foodList);
						break;
					}else {
						LinkedList<Food> foodList=new LinkedList<>();
						foodList.add(food);
						currentShop.setFood(foodList);
					}
				}
			}
			shopDao.wrtieAllShop(shopList);
			
		}catch(Exception evt) {
			evt.printStackTrace();
		}
		return flag;
	}
	
	
	/**
	 * 修改菜品信息
	 * @param e
	 * @param shop
	 * @return
	 */
	private int EditActionPerformed(ActionEvent e,Shop shop) {
		// TODO Auto-generated method stub
		int flag=0;
		try {
			String shopName=shop.getName();
			String oldName=changeFood.getFood_name();
			String oldPrice=changeFood.getFood_price();
			String newName=this.foodNameTxt.getText();
			String newPrice=this.foodPriceTxt.getText();
			String newNum=this.foodNumTxt.getText();
			if(newName.equals("")&&newPrice.equals("")&&newNum.equals("")) {
				JOptionPane.showMessageDialog(null, "请至少选择一项修改对象!");
				return flag;
			}
			 //没有修改菜品名和菜品价格,则不需要修改order.txt文件
			String name,price,num;
			if(newName.equals("")) {
				name=food.getFood_name();
			}else {
				name=newName;
			}
			if(newPrice.equals("")) {
				price=food.getFood_price();
			}else {
				price=newPrice;
			}
			if(newNum.equals("")) {
				num=food.getFood_nums().toString();
			}else {
				num=newNum.toString();
			}
			
			LinkedList<Shop> shopList=shopDao.readAllShop();
			for(Shop currentShop:shopList) {
				if(currentShop.getId().equals(shop.getId())){ //如果是这个商家
					for(Food currentFood:currentShop.getFood()) {
						if(currentFood.getFood_id().equals(food.getFood_id())) {
							food.setFood_name(name);
							food.setFood_price(price);
							food.setFood_nums(Integer.parseInt(num));
							currentFood.setFood_name(name);
							currentFood.setFood_price(price);
							currentFood.setFood_nums(Integer.parseInt(num));
							flag=1;
							break;
						}
					}
				}
			}
			shopDao.wrtieAllShop(shopList);
				
			if(!newName.equals(oldName) || !newPrice.equals(oldPrice)) {
					shopDao.changeOrderTxt(shopName, oldName, oldPrice, name, price); //修改order.txt中的菜品信息
			}
		return flag;
			
		}catch(Exception evt) {
			evt.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 把选中行的内容放入text中
	 * @param e
	 */
	private void mouseActionPerformed(MouseEvent e) {
		// TODO Auto-generated method stub
		int row=infoTable.getSelectedRow();//当前选中行
		String id=(String)infoTable.getValueAt(row,0);
		String name=(String)infoTable.getValueAt(row, 1);
		String price=(String)infoTable.getValueAt(row, 2);
		Integer num=(Integer)infoTable.getValueAt(row, 3);
		changeFood.setFood_id(id);
		changeFood.setFood_name(name);
		changeFood.setFood_price(price);
		changeFood.setFood_nums(num);
		this.foodNameTxt.setText(name);
		this.foodPriceTxt.setText((String)infoTable.getValueAt(row, 2));
		this.foodNumTxt.setText(String.valueOf(infoTable.getValueAt(row, 3)));
		food.setFood_id(id);
		food.setFood_name(name);
		food.setFood_price(price);
		food.setFood_nums(num);
		
	}

	/**
	 * 商家查询菜品信息
	 * @param e
	 * @param shop
	 */
	private void  searchFoodActionPerformed(ActionEvent e, Shop shop) {
		// TODO Auto-generated method stub
		String id=shop.getId();
		LinkedList<Shop> shopList=shopDao.readAllShop(); //从文件中读取商家信息并生成链表
		LinkedList<Food> foodList=new LinkedList<>();
		for(Shop currentShop:shopList) {
			if(id.equals(currentShop.getId())) {
				if(currentShop.getFood()!=null) {
				for(Food food:currentShop.getFood()) {
					foodList.add(food);
				}
				break;
				}else {
					JOptionPane.showMessageDialog(null, "目前无菜品信息，请先添加菜品!");
					return;
				}
			}
		}
		this.fillTable(foodList);

	}
	
	/**
	 * 填写table
	 * @param list :Food链表
	 */
	private void fillTable(LinkedList<Food> list) {
		// TODO Auto-generated method stub
		DefaultTableModel dtm=(DefaultTableModel) infoTable.getModel();
		dtm.setRowCount(0);//清空表格
		for(Food order:list) {
			Vector v=new Vector();
			v.add(order.getFood_id());
			v.add(order.getFood_name());
			v.add(order.getFood_price());
			v.add(order.getFood_nums());
			dtm.addRow(v);
		}
	}

}
