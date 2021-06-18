package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import model.Admin;
import model.Shop;
import model.User;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.ShopController;
import java.awt.event.MouseAdapter;
import java.awt.Color;

/**
 * 管理员控制商家界面
 * @author HP
 *
 */
public class AdminShopFrm extends JFrame {
	int width=20,height=20;
	private JPanel contentPane;
	private JTextField keywordTxt;
	private JTable infoTable;
	private JTextField shopIdTxt;
	private JTextField shopNameTxt;
	private ShopController shopDao=new ShopController();
	private ImageIcon userIcon=new ImageIcon("src//img//canteen.png");
    private ImageIcon idIcon=new ImageIcon("src//img//id.png");
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
					AdminShopFrm frame = new AdminShopFrm(new Admin());
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
	public AdminShopFrm(Admin admin) {
		userIcon.setImage(userIcon.getImage().getScaledInstance(width+8, height+8, Image.SCALE_DEFAULT));
	    idIcon.setImage(idIcon.getImage().getScaledInstance(width+3, height+3, Image.SCALE_DEFAULT));
	    searchIcon.setImage(searchIcon.getImage().getScaledInstance(width+5, height+5, Image.SCALE_DEFAULT));
		backIcon.setImage(backIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		deleteIcon.setImage(deleteIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		
		setTitle("\u7BA1\u7406\u5458\u5546\u5BB6\u7BA1\u7406\u754C\u9762");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 651, 504);
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
		
		JLabel lblNewLabel = new JLabel("\u8BF7\u8F93\u5165\u5546\u5BB6Id\uFF1A");
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 15));
		
		keywordTxt = new JTextField();
		keywordTxt.setBorder(new LineBorder(new java.awt.Color(127,157,185),1,false));
		keywordTxt.setColumns(10);
		
		JButton searchBtn = new JButton("\u67E5\u8BE2");
		searchBtn.setBackground(new Color(204, 255, 255));
		searchBtn.setIcon(searchIcon);
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int num=searchActionPerformed(e);
				if(num==0) {
					JOptionPane.showMessageDialog(null, "无该商家信息!");
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
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(129)
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(keywordTxt, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(searchBtn))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(47)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
								.addComponent(backBtn)
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
					.addContainerGap(70, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(35)
					.addComponent(backBtn)
					.addGap(10)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(keywordTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(searchBtn))
					.addGap(12)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(48, Short.MAX_VALUE))
		);
		
		JLabel shopIdLabel = new JLabel("\u5546\u5BB6Id\uFF1A");
		shopIdLabel.setIcon(idIcon);
		shopIdLabel.setFont(new Font("宋体", Font.BOLD, 18));
		
		shopIdTxt = new JTextField();
		shopIdTxt.setEnabled(false);
		shopIdTxt.setColumns(10);
		
		JLabel shopNameLabel = new JLabel("\u5546\u5BB6\u540D\uFF1A");
		shopNameLabel.setIcon(userIcon);
		shopNameLabel.setFont(new Font("宋体", Font.BOLD, 18));
		
		shopNameTxt = new JTextField();
		shopNameTxt.setEnabled(false);
		shopNameTxt.setColumns(10);
		
		JButton deleteBtn = new JButton("\u5220\u9664");
		deleteBtn.setBackground(new Color(204, 255, 255));
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int res=JOptionPane.showConfirmDialog(null, "是否删除该商家?");
				if(res==0) {
					int num=deleteActionPerformed(e);
					if(num==1) {
						JOptionPane.showMessageDialog(null, "删除成功!");
						return;
					}else if(num==0){
						JOptionPane.showMessageDialog(null, "删除失败!");
						return;
					}else {
						JOptionPane.showMessageDialog(null, "无该商家信息!");
						return;
					}
				}
				
			}
		});
		deleteBtn.setFont(new Font("宋体", Font.BOLD, 18));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(127, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(shopNameLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(shopNameTxt, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(shopIdLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(shopIdTxt)))
					.addGap(103))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(190)
					.addComponent(deleteBtn)
					.addContainerGap(272, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(shopIdLabel)
						.addComponent(shopIdTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(shopNameLabel)
						.addComponent(shopNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(deleteBtn)
					.addContainerGap(26, Short.MAX_VALUE))
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
				"\u5546\u5BB6Id", "\u5546\u5BB6\u540D", "\u5BC6\u7801"
			}
		));
		scrollPane.setViewportView(infoTable);
		this.setLocationRelativeTo(null);//窗口居中
		contentPane.setLayout(gl_contentPane);
	}
	

	/**
	 * 管理员删除用户
	 * @param e
	 * @return 0:删除失败,1:删除成功,2:未选择商家
	 */
	private int deleteActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String shopId=this.shopIdTxt.getText();
		if(shopId.equals("")) {
			return 2;
		}
		Shop shop=new Shop();
		shop.setId(shopId);
		int num=shopDao.deleteShop(shop);
		return num;
	}

	/**
	 * 管理员查看信息
	 * @param e
	 */
	private int searchActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int flag=0;
		String keyword=this.keywordTxt.getText();
		LinkedList<Shop> shopList=shopDao.readAllShop();
		if(keyword.equals("")) { //查询所有
			flag=1;
			this.fillTable(shopList);
		}else {
			LinkedList<Shop> newShopList=new LinkedList<>();
			for(Shop currentShop:shopList) {
				if(currentShop.getId().equals(keyword)) {
					newShopList.add(currentShop);
					flag=1;
					this.fillTable(newShopList);
					break;
				}
			}
		}
		return flag;
		
	}
	
	/**
	 * 点击后显示到label
	 * @param e
	 */
	private void mousePressedActionPerformed(MouseEvent e) {
		// TODO Auto-generated method stub
		int row=infoTable.getSelectedRow();//当前选中行
		String id=(String)infoTable.getValueAt(row,0);
		String name=(String)infoTable.getValueAt(row, 1);
		this.shopIdTxt.setText(id);
		this.shopNameTxt.setText(name);
	}
	
	/**
	 * 填写table
	 * @param list :Food链表
	 */
	private void fillTable(LinkedList<Shop> list) {
		// TODO Auto-generated method stub
		DefaultTableModel dtm=(DefaultTableModel) infoTable.getModel();
		dtm.setRowCount(0);//清空表格
		for(Shop currentShop:list) {
			Vector v=new Vector();
			v.add(currentShop.getId());
			v.add(currentShop.getName());
			v.add(currentShop.getPassword());
			dtm.addRow(v);
		}
	}
}
