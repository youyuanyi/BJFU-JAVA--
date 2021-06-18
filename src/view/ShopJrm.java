package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ShopController;
import model.Shop;
import util.InitFrmTool;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ShopJrm extends JFrame {

	private JPanel contentPane;
	private ShopController shopDao=new ShopController();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShopJrm frame = new ShopJrm(new Shop());
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
	public ShopJrm(Shop shop) {
		setTitle("\u5546\u5BB6\u754C\u9762");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 440);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("\u6B22\u8FCE\u60A8!\u7F8E\u56E2\u7528\u6237 ");
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 25));
		
		JLabel shopIdLabel = new JLabel(shop.getId());
		shopIdLabel.setFont(new Font("宋体", Font.BOLD, 22));
		
		JButton btnNewButton = new JButton("\u4FE1\u606F\u7BA1\u7406");
		btnNewButton.setBackground(new Color(192, 192, 192));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new ShopInfoControlFrm(shop).setVisible(true);;
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.BOLD, 22));
		
		JButton btnNewButton_1 = new JButton("\u83DC\u54C1\u5904\u7406");
		btnNewButton_1.setBackground(new Color(192, 192, 192));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new ShopMenuControlFrm(shop).setVisible(true);;
			}
		});
		btnNewButton_1.setFont(new Font("宋体", Font.BOLD, 22));
		
		JButton btnNewButton_2 = new JButton("\u9884\u5B9A\u7BA1\u7406");
		btnNewButton_2.setBackground(new Color(192, 192, 192));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new ShopOrderProcessFrm(shop).setVisible(true);;
			}
		});
		btnNewButton_2.setFont(new Font("宋体", Font.BOLD, 22));
		
		JButton btnNewButton_3 = new JButton("\u8FD4\u56DE");
		btnNewButton_3.setBackground(new Color(192, 192, 192));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				JFrame jf=new LogOnFrm() ;
			    InitFrmTool ift=new InitFrmTool("src\\img\\bg.png");
			    ift.InitFrm(ift.getImgPath(), jf);
			}
		});
		btnNewButton_3.setFont(new Font("宋体", Font.BOLD, 18));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnNewButton_3))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(48)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnNewButton)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewButton_1)
								.addComponent(btnNewButton_2))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(shopIdLabel)))
					.addContainerGap(55, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(12)
					.addComponent(btnNewButton_3)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(shopIdLabel))
					.addGap(38)
					.addComponent(btnNewButton)
					.addGap(52)
					.addComponent(btnNewButton_1)
					.addGap(51)
					.addComponent(btnNewButton_2)
					.addContainerGap(65, Short.MAX_VALUE))
		);
		this.setLocationRelativeTo(null);//窗口居中
		contentPane.setLayout(gl_contentPane);
	}
}
