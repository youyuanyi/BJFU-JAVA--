package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.User;
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

/**
 * 消费者主界面
 * @author HP
 *
 */
public class CustomerFrm extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerFrm frame = new CustomerFrm(new User());
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
	public CustomerFrm(User user) {
		setTitle("\u6D88\u8D39\u8005\u754C\u9762");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 596, 518);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("\u6B22\u8FCE\u60A8 \u7F8E\u56E2\u7528\u6237");
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 20));
		
		JLabel userLabel = new JLabel(user.getName());
		userLabel.setFont(new Font("Arial Black", Font.BOLD, 20));
		
		JButton btnNewButton = new JButton("\u4E2A\u4EBA\u4FE1\u606F\u7BA1\u7406");
		btnNewButton.setBackground(new Color(192, 192, 192));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new CustomerInfoControlFrm(user).setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.BOLD, 20));
		
		JButton btnNewButton_1 = new JButton("\u67E5\u770B\u9910\u9986");
		btnNewButton_1.setBackground(new Color(192, 192, 192));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new CustomerSearchShopJrm(user).setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("宋体", Font.BOLD, 20));
		
		JLabel lblNewLabel_1 = new JLabel("\u8BF7\u9009\u62E9\u60A8\u7684\u64CD\u4F5C");
		lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 20));
		
		JButton btnNewButton_2 = new JButton("\u83DC\u54C1\u9884\u5B9A");
		btnNewButton_2.setBackground(new Color(192, 192, 192));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				CustomerOrderFrm jf=new CustomerOrderFrm(user) ;//创建Runnable实现类的实例
				Thread r=new Thread(jf);//以此实例作为Thread的target来创建Thread对象
				r.start();//调用线程对象的start方法来启动该线程。 				
				jf.setVisible(true);
			}
		});
		btnNewButton_2.setFont(new Font("宋体", Font.BOLD, 20));
		
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
							.addGap(154)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel_1)
								.addComponent(lblNewLabel))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(userLabel))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(172)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnNewButton_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(21)
							.addComponent(btnNewButton_3)))
					.addContainerGap(130, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(28)
					.addComponent(btnNewButton_3)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(userLabel))
					.addGap(18)
					.addComponent(lblNewLabel_1)
					.addGap(54)
					.addComponent(btnNewButton)
					.addGap(48)
					.addComponent(btnNewButton_1)
					.addGap(48)
					.addComponent(btnNewButton_2)
					.addContainerGap(94, Short.MAX_VALUE))
		);
		this.setLocationRelativeTo(null);//窗口居中
		contentPane.setLayout(gl_contentPane);
	}

}
