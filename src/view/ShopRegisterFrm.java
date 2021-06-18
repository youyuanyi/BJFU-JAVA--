package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controller.ShopController;
import util.InitFrmTool;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ShopRegisterFrm extends JFrame {

	private JPanel contentPane;
	private JTextField shopNameTxt;
	private JPasswordField shopPwdTxt;
	private ShopController shopDao=new ShopController();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShopRegisterFrm frame = new ShopRegisterFrm();
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
	public ShopRegisterFrm() {
		setTitle("\u5546\u5BB6\u6CE8\u518C\u754C\u9762");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 631, 645);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("\u8D26\u53F7:");
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 18));
		
		shopNameTxt = new JTextField();
		shopNameTxt.setBorder(new LineBorder(new java.awt.Color(127,157,185),1,false));
		shopNameTxt.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\u5BC6\u7801:");
		lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 18));
		
		JButton btnNewButton = new JButton("\u6CE8\u518C");
		btnNewButton.setBackground(new Color(204, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int num=registerActionPerformed(e);
				if(num!=0) {
					JOptionPane.showMessageDialog(null, "注册成功,您的账号Id为:"+num+",请退出重新登录!");
					dispose();
				    JFrame jf=new LogOnFrm() ;
			        InitFrmTool ift=new InitFrmTool("src\\img\\bg.png");
			        ift.InitFrm(ift.getImgPath(), jf);
				}else {
					JOptionPane.showMessageDialog(null, "注册失败!");
					return;
				}
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.BOLD, 15));
		
		JButton btnNewButton_1 = new JButton("\u91CD\u7F6E");
		btnNewButton_1.setBackground(new Color(204, 255, 255));
		btnNewButton_1.setFont(new Font("宋体", Font.BOLD, 15));
		
		shopPwdTxt = new JPasswordField();
		shopPwdTxt.setBorder(new LineBorder(new java.awt.Color(127,157,185),1,false));
		JButton btnNewButton_2 = new JButton("\u8FD4\u56DE");
		btnNewButton_2.setBackground(new Color(204, 255, 255));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new LogOnFrm().setVisible(true);
			}
		});
		btnNewButton_2.setFont(new Font("宋体", Font.BOLD, 18));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(164)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_1)
						.addComponent(lblNewLabel))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(shopPwdTxt, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
						.addComponent(shopNameTxt, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
					.addGap(227))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(181)
					.addComponent(btnNewButton)
					.addGap(60)
					.addComponent(btnNewButton_1)
					.addContainerGap(236, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(19)
					.addComponent(btnNewButton_2)
					.addContainerGap(519, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(37, Short.MAX_VALUE)
					.addComponent(btnNewButton_2)
					.addGap(113)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(shopNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addGap(61)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(shopPwdTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(60)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
					.addContainerGap(225, Short.MAX_VALUE))
		);
		this.setLocationRelativeTo(null);//窗口居中
		contentPane.setLayout(gl_contentPane);
	}
	
	/**
	 * 商家注册功能
	 * @param e
	 */
	private int registerActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String shopName=this.shopNameTxt.getText();
		String pwd=new String(this.shopPwdTxt.getPassword());
		if(shopName.equals("")||pwd.equals("")) {
			return 2;
		}
		int num=shopDao.registerShop(shopName, pwd);
		return num;
	}
}
