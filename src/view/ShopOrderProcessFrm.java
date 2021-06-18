package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Shop;
import model.UserOrder;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.OrderController;
import controller.ShopController;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;

public class ShopOrderProcessFrm extends JFrame {
	
	int width=20,height=20;
	private JPanel contentPane;
	private JTable infoTable;
	private OrderController orderDao=new OrderController();
	private ShopController shopDao=new ShopController();
	private JTextField userTxt;
	private JTextField timeTxt;
	private JTextArea orderInfoArea;
	private ImageIcon searchIcon=new ImageIcon("src//img//search.png");
    private ImageIcon backIcon=new ImageIcon("src//img//back.png");
    private ImageIcon deleteIcon=new ImageIcon("src//img//delete.png");
    private ImageIcon userIcon=new ImageIcon("src//img//user.png");
    private ImageIcon timeIcon=new ImageIcon("src//img//time.png");
    private ImageIcon foodIcon=new ImageIcon("src//img//food.png");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShopOrderProcessFrm frame = new ShopOrderProcessFrm(new Shop());
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
	public ShopOrderProcessFrm(Shop shop) {
		searchIcon.setImage(searchIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		backIcon.setImage(backIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		deleteIcon.setImage(deleteIcon.getImage().getScaledInstance(width-5, height-5, Image.SCALE_DEFAULT));
		userIcon.setImage(userIcon.getImage().getScaledInstance(width+1, height+1, Image.SCALE_DEFAULT));
		timeIcon.setImage(timeIcon.getImage().getScaledInstance(width+3, height+1, Image.SCALE_DEFAULT));
		foodIcon.setImage(foodIcon.getImage().getScaledInstance(width+3, height+3, Image.SCALE_DEFAULT));
		
		setTitle("\u5546\u5BB6\u8BA2\u5355\u7BA1\u7406\u754C\u9762");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 716, 520);
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
		
		JLabel lblNewLabel = new JLabel("\u8BA2\u5355\u67E5\u8BE2");
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 22));
		
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
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 204));
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "\u8BA2\u5355\u5904\u7406", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(27)
							.addComponent(backBtn)
							.addGap(106)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(searchBtn))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(45)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 542, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(94, GroupLayout.PREFERRED_SIZE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(26)
							.addComponent(backBtn))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(40)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
								.addComponent(searchBtn))))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
					.addGap(26)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JLabel userLabel = new JLabel("\u7528\u6237\u8D26\u53F7\uFF1A");
		userLabel.setIcon(userIcon);
		userLabel.setFont(new Font("宋体", Font.BOLD, 15));
		
		userTxt = new JTextField();
		userTxt.setBorder(new LineBorder(new java.awt.Color(127,157,185),1,false));
		userTxt.setColumns(10);
		
		JLabel timeLabel = new JLabel("\u9884\u5B9A\u65F6\u95F4\uFF1A");
		timeLabel.setIcon(timeIcon);
		timeLabel.setFont(new Font("宋体", Font.BOLD, 15));
		
		timeTxt = new JTextField();
		timeTxt.setBorder(new LineBorder(new java.awt.Color(127,157,185),1,false));
		timeTxt.setColumns(10);
		
		JButton deleteBtn = new JButton("\u5220\u9664");
		deleteBtn.setBackground(new Color(204, 255, 255));
		deleteBtn.setIcon(deleteIcon);
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int res=JOptionPane.showConfirmDialog(null, "是否要删除改订单?");
				if(res==0) {
					int num=deleteActionPerformed(e);
					if(num!=0) {
						JOptionPane.showMessageDialog(null,"删除成功!");
						return;
					}else {
						JOptionPane.showMessageDialog(null,"删除失败!");
						return;
					}
				}
			}
		});
		deleteBtn.setFont(new Font("宋体", Font.BOLD, 18));
		
		JLabel foodLabel = new JLabel("\u9884\u5B9A\u83DC\u54C1\uFF1A");
		foodLabel.setIcon(foodIcon);
		foodLabel.setFont(new Font("宋体", Font.BOLD, 15));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(42)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(userLabel)
							.addGap(18)
							.addComponent(userTxt, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
							.addGap(35)
							.addComponent(timeLabel))
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(foodLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane_1)))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(timeTxt, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(27)
							.addComponent(deleteBtn, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(27, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(userLabel)
						.addComponent(userTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(timeLabel)
						.addComponent(timeTxt, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(foodLabel)
							.addGap(13)
							.addComponent(deleteBtn)
							.addGap(19))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
							.addContainerGap())))
		);
		
		orderInfoArea = new JTextArea();
		orderInfoArea.setBorder(new LineBorder(new java.awt.Color(127,157,185),1,false));
		scrollPane_1.setViewportView(orderInfoArea);
		panel.setLayout(gl_panel);
		
		infoTable = new JTable();
		infoTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				tablePressedActionPerformed(e);
			}
		});
		infoTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7528\u6237\u8D26\u53F7", "\u5546\u5BB6\u540D", "\u9884\u5B9A\u65F6\u95F4", "\u9884\u5B9A\u83DC\u54C1"
			}
		));
		scrollPane.setViewportView(infoTable);
		contentPane.setLayout(gl_contentPane);
		this.setLocationRelativeTo(null);//窗口居中
		this.fillTable(new LinkedList<UserOrder>());
	}
	
	/**
	 * 删除订单
	 * @param e
	 */
	private int deleteActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		LinkedList<UserOrder> orderList=orderDao.readAllOrder();//先获取所有的订单信息
		LinkedList<UserOrder> newOrderList=new LinkedList<>();//返回的新的订单信息
		String name=this.userTxt.getText();
		String time=this.timeTxt.getText();
		int flag=0;
		for(UserOrder currentOrder:orderList) {
			if(currentOrder.getUserName().equals(name)&&currentOrder.getOrderTime().equals(time)) {
				flag=1;
				continue;
			}else {
				newOrderList.add(currentOrder);
			}
		}
		orderDao.writeAllOrders(newOrderList);
		return flag;
	}

	/**
	 * 点击表单中的一行信息后把内容返回到text中
	 * @param e
	 */
	private void tablePressedActionPerformed(MouseEvent e) {
		// TODO Auto-generated method stub
		this.orderInfoArea.setText("");
		int row=infoTable.getSelectedRow();//当前选中行
		this.userTxt.setText((String)infoTable.getValueAt(row, 0));
		this.timeTxt.setText((String)infoTable.getValueAt(row, 2));
		String orderInfo=(String)infoTable.getValueAt(row, 3);
		String info=orderInfo.substring(5,orderInfo.length()-1);//为了去掉字符串的最后一个逗号和最前面的菜品信息提示符
		String []array=info.split(",");
		for(String order:array) {
			this.orderInfoArea.append(order+"元\n");
		}
	}
	
	/**
	 * 查询订单信息
	 * @param e
	 * @param shop
	 */
	private void searchActionPerformed(ActionEvent e, Shop shop) {
		// TODO Auto-generated method stub
		String name=shop.getName();
		LinkedList<UserOrder> orderList=shopDao.searchOrder(name);
		if(orderList.size()==0) {
			JOptionPane.showMessageDialog(null, "目前无订单需求!");
			this.fillTable(new LinkedList<UserOrder>());
			return;
		}else {
			this.fillTable(orderList);
		}
		
	}
	
	/**
	 * 填写table
	 * @param list
	 */
	private void fillTable(LinkedList<UserOrder> list) {
		// TODO Auto-generated method stub
		DefaultTableModel dtm=(DefaultTableModel) infoTable.getModel();
		dtm.setRowCount(0);//清空表格
		for(UserOrder order:list) {
			Vector v=new Vector();
			v.add(order.getUserName());
			v.add(order.getShopName());
			v.add(order.getOrderTime());
			v.add(order.getOrderFood());
			dtm.addRow(v);
		}
	}
}
