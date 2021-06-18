package view;


import model.Admin;
import model.Shop;
import model.User;
import util.InitFrmTool;
import util.StringUtil;

import javax.swing.*;
import javax.swing.border.LineBorder;

import controller.AdminController;
import controller.ShopController;
import controller.UserController;

import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;


public class LogOnFrm extends JFrame {
    private JTextField userTxt;
    private JPasswordField pwdTxt;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private JRadioButton customerJrb;
    private JRadioButton shopJrb;
    private JRadioButton adminJrb;
    int width=25,height=25;
    private UserController userDao=new UserController();
    private ShopController shopDao=new ShopController();
    private AdminController adminDao=new AdminController();
    
    private ImageIcon logoIcon=new ImageIcon("src//img//logo.png");
    private ImageIcon userIcon=new ImageIcon("src//img//user.png");
    private ImageIcon pwdIcon=new ImageIcon("src//img//pwd.png");
    private ImageIcon loginIcon=new ImageIcon("src//img//login.png");
    private ImageIcon registerIcon=new ImageIcon("src//img//register.png");
    private ImageIcon resetIcon=new ImageIcon("src//img//reset.png");
    private ImageIcon bgIcon=new  ImageIcon("src//img//bg.png");
    
    
   
    public LogOnFrm(){
    	setResizable(false);
        //改变系统默认字体
        Font font=new Font("Dialog",Font.PLAIN,12);
        java.util.Enumeration keys=UIManager.getDefaults().keys();
        while(keys.hasMoreElements()) {
            Object key=keys.nextElement();
            Object value=UIManager.get(key);
            if(value instanceof javax.swing.plaf.FontUIResource) {
                UIManager.put(key, font);
            }
        }
        
     

        logoIcon.setImage(logoIcon.getImage().getScaledInstance(width+5, height, Image.SCALE_DEFAULT));
        userIcon.setImage(userIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        pwdIcon.setImage(pwdIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        loginIcon.setImage(loginIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        registerIcon.setImage(registerIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        resetIcon.setImage(resetIcon.getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT));

        Box box1;
        Box box2;
        Box box3;
        Box box4;
        Box box5;
        box1=Box.createHorizontalBox();
        box2=Box.createHorizontalBox();
        box3=Box.createHorizontalBox();
        box4=Box.createHorizontalBox();
        box5=Box.createHorizontalBox();

        JLabel menu = new JLabel("欢迎来到美团");
        menu.setIcon(logoIcon);

        //menu.setIcon(Book);
        menu.setBounds(300,100,width,height);
        menu.setFont(new Font("宋体", Font.BOLD, 25));
        box1.add(menu);
        box1.add(Box.createVerticalStrut(40));

        JLabel userLabel = new JLabel("账号:");
        userLabel.setIcon(userIcon);
        userLabel.setSize(width,height);
        userLabel.setFont(new Font("宋体", Font.BOLD, 18));

        userTxt = new JTextField();
        userTxt.setColumns(20);
        userTxt.setBorder(new LineBorder(new java.awt.Color(127,157,185),1,false));
        box2.add(userLabel);
        box2.add(Box.createHorizontalStrut(10));
        box2.add(userTxt);
        box2.add(Box.createVerticalStrut(30));


        JLabel pwdLabel = new JLabel("密码:");
        pwdLabel.setIcon(pwdIcon);
        pwdLabel.setSize(width,height);
        pwdLabel.setFont(new Font("宋体", Font.BOLD, 18));
        pwdTxt = new JPasswordField();
        pwdTxt.setBorder(new LineBorder(new java.awt.Color(127,157,185),1,false));
        pwdTxt.setColumns(20);
        box3.add(pwdLabel);
        box3.add(Box.createHorizontalStrut(10));
        box3.add(pwdTxt);
        box3.add(Box.createVerticalStrut(30));


        customerJrb = new JRadioButton("消费者");
        customerJrb.setSelected(true);
        customerJrb.setOpaque(false); //设置背景透明
        buttonGroup.add(customerJrb);
        customerJrb.setFont(new Font("宋体", Font.BOLD, 15));

        shopJrb = new JRadioButton("商家");
        shopJrb.setOpaque(false);
        buttonGroup.add(shopJrb);
        shopJrb.setFont(new Font("宋体", Font.BOLD, 15));

        adminJrb = new JRadioButton("管理员");
        adminJrb.setOpaque(false);
        buttonGroup.add(adminJrb);
        adminJrb.setFont(new Font("宋体", Font.BOLD, 15));
        box4.add(customerJrb);
        box4.add(Box.createHorizontalStrut(5));
        box4.add(shopJrb);
        box4.add(Box.createHorizontalStrut(5));
        box4.add(adminJrb);
        box4.add(Box.createVerticalStrut(30));

        JButton loginBtn = new JButton(loginIcon);
        loginBtn.setSize(width-5,height-5);
        loginBtn.setContentAreaFilled(false);
        loginBtn.setBorder(BorderFactory.createRaisedBevelBorder());
        //loginBtn.setBorder(new LineBorder(new java.awt.Color(255,228,225),1,false));
        loginBtn.setFont(new Font("宋体", Font.BOLD, 15));
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginActionPerformed(e);
            }
        });

        JButton registerBtn =new JButton(registerIcon);
        registerBtn.setContentAreaFilled(false);
        registerBtn.setBorder(BorderFactory.createRaisedBevelBorder());
        registerBtn.setSize(width-5,height-5);
        registerBtn.setFont(new Font("宋体", Font.BOLD, 15));
        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerActionPerformed(e);
            }
        });
        JButton resetBtn =new JButton(resetIcon);
        resetBtn.setContentAreaFilled(false);
        resetBtn.setBorder(BorderFactory.createRaisedBevelBorder());
        resetBtn.setSize(width-5,height-5);
        resetBtn.setFont(new Font("宋体", Font.BOLD, 15));

        resetBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetBtnActionPerfomred(e);
            }
        });
        box5.add(loginBtn);
        box5.add(Box.createHorizontalStrut(20));
        box5.add(registerBtn);
        box5.add(Box.createHorizontalStrut(20));
        box5.add(resetBtn);


        Box box;
        box=Box.createVerticalBox();
        box.add(box1);
        box.add(Box.createVerticalStrut(10));
        box.add(box2);
        box.add(Box.createVerticalStrut(10));
        box.add(box3);
        box.add(Box.createVerticalStrut(10));
        box.add(box4);
        box.add(Box.createVerticalStrut(10));
        box.add(box5);

        getContentPane().setLayout(new FlowLayout());
        getContentPane().add(box);
        this.setTitle("美团登陆界面");
        this.setBounds(300,100,500,270);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        


    }

    private void registerActionPerformed(ActionEvent e) {
        String name=this.userTxt.getText();
        String pwd=new String(this.pwdTxt.getPassword());
        if(this.customerJrb.isSelected()){
           dispose();
           new UserRegisterFrm().setVisible(true);
        }else if(this.shopJrb.isSelected()) {
        	dispose();
        	new ShopRegisterFrm().setVisible(true);
        }else if(this.adminJrb.isSelected()) {
        	JOptionPane.showMessageDialog(null, "无权注册管理员!");
        	return;
        }
    }

    public void loginActionPerformed(ActionEvent e) {
        String name=this.userTxt.getText();
        String pwd=new String(this.pwdTxt.getPassword());
        if(StringUtil.isEmpty(name)){
            JOptionPane.showMessageDialog(null,"账号不能为空!");
            return;
        }
        if(StringUtil.isEmpty(pwd)){
            JOptionPane.showMessageDialog(null,"密码不能为空!");
            return;
        }
        int privilege=0;//默认选中消费者
        if(customerJrb.isSelected()){
            privilege=0;
            User user = new User(name, pwd, null, 0);
            User currentUser = UserController.userLogin(user);
            if (currentUser != null) {
                JOptionPane.showMessageDialog(null, "登录成功!");
                dispose();
                User user2=userDao.getUser(name);
                new CustomerFrm(user2).setVisible(true);;
            } else {
                JOptionPane.showMessageDialog(null, "账号或密码错误!");
                return;
            }
        }else if(shopJrb.isSelected()){
            privilege=1;
            LinkedList<Shop> shopList=shopDao.readAllShop(); //原文件845个商家
            System.out.println("商家个数:"+shopList.size());
            int flag=0;
            if(!shopList.equals(null)) {
            	for(Shop currentShop:shopList) {
            		if(currentShop.getId().equals(name) &&currentShop.getPassword().equals(pwd)) {
            			flag=1;
            			JOptionPane.showMessageDialog(null, "登录成功!");
            			dispose();
            			new ShopJrm(currentShop).setVisible(true);;
            		}
            	}
            	if(flag==0) {
            		  JOptionPane.showMessageDialog(null, "账号或密码错误!");
                      return;
            	}
            }else {
            	JOptionPane.showMessageDialog(null, "文件读取错误!");
            	return;
            }
            
        }else if(adminJrb.isSelected()){//管理员登录
            privilege = 2;
            LinkedList<Admin> adminList=adminDao.readAllAdmin();
            int flag=0;
            if(adminList.size()!=0) {
            	for(Admin currentAdmin:adminList) {
            		if(currentAdmin.getName().equals(name)&&currentAdmin.getPassword().equals(pwd)) {
            			flag=1;
            			JOptionPane.showMessageDialog(null, "登录成功!");
            			dispose();
            			new AdminJrm(currentAdmin).setVisible(true);
            		}
            	}
            	if(flag==0) {
            		  JOptionPane.showMessageDialog(null, "账号或密码错误!");
                      return;
            	}
            }else {
            	JOptionPane.showMessageDialog(null, "文件读取错误!");
            	return;
            }
        }
    }

  

    private void resetBtnActionPerfomred(ActionEvent e) {
        this.userTxt.setText("");
        this.pwdTxt.setText("");
        this.customerJrb.setSelected(true);
    }
    
    
    public static void main(String[] args) {
        JFrame jf=new LogOnFrm() ;
        InitFrmTool ift=new InitFrmTool("src\\img\\bg.png");
        ift.InitFrm(ift.getImgPath(), jf);
    }
}
