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
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class CustomerSearchShopJrm extends JFrame {

	private JPanel contentPane;
	private JTextField keywordTxt;
	private OrderController orderDao=new OrderController();
	private ShopController shopDao=new ShopController();
	private JTextArea infoArea;
	int width=25,height=25;
	private JButton btnNewButton;
	private ImageIcon searchIcon=new ImageIcon("src//img//search.png");
	private ImageIcon backIcon=new ImageIcon("src//img//back.png");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerSearchShopJrm frame = new CustomerSearchShopJrm(new User());
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
	public CustomerSearchShopJrm(User user) {
		
		searchIcon.setImage(searchIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		backIcon.setImage(backIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		
		setTitle("\u6D88\u8D39\u8005\u67E5\u770B\u9910\u9986\u4FE1\u606F\u754C\u9762");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 654, 498);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("\u8BF7\u8F93\u5165\u5173\u952E\u5B57\uFF1A");
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 22));
		
		keywordTxt = new JTextField();
		keywordTxt.setBorder(new LineBorder(new java.awt.Color(127,157,185),1,false));
		keywordTxt.setColumns(10);
		
		JButton searchBtn = new JButton("\u67E5\u8BE2");
		searchBtn.setBackground(new Color(224, 255, 255));
		searchBtn.setIcon(searchIcon);
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchShopActionPerformed(e);
			}
		});
		searchBtn.setFont(new Font("宋体", Font.BOLD, 16));
		
		JScrollPane scrollPane = new JScrollPane();
		
		btnNewButton = new JButton("\u8FD4\u56DE");
		btnNewButton.setBackground(new Color(224, 255, 255));
		btnNewButton.setIcon(backIcon);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new CustomerFrm(user).setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.BOLD, 18));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(107)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 427, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(keywordTxt, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(searchBtn))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(26)
							.addComponent(btnNewButton)))
					.addContainerGap(96, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(21)
					.addComponent(btnNewButton)
					.addGap(29)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(keywordTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(searchBtn))
					.addGap(50)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(110, Short.MAX_VALUE))
		);
		
		infoArea = new JTextArea();
		infoArea.setBorder(new LineBorder(new java.awt.Color(127,157,185),1,false));
		scrollPane.setViewportView(infoArea);
		contentPane.setLayout(gl_contentPane);
	}

	private void searchShopActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String keyword=this.keywordTxt.getText();
		LinkedList<Shop> shopList=shopDao.readAllShop();
		LinkedList<Shop> showList=new LinkedList<>();
		if(keyword.equals("")) {
			JOptionPane.showMessageDialog(null, "请输入关键词!");
			return;
		}
		for(Shop currentShop:shopList) {
			if(currentShop.getName().contains(keyword)) {
				showList.add(currentShop);
			}
		}
		//写入textarea
		for(Shop currentShop:showList) {
			String shopName=currentShop.getName();
			infoArea.append("商家名称: "+shopName+"\n");
			for(Food food:currentShop.getFood()) {
				String s="菜品: "+food.getFood_name()+", 单价: "+food.getFood_price()+", 数量: "+food.getFood_nums()+"\n";
				infoArea.append(s);
			}
			int len=0;
			for(String cmt:currentShop.getComment()) {
				infoArea.append("Comment"+len+": "+cmt+"\n");
				len++;
			}
		}
		
	}

}
