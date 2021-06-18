package controller;

import java.io.*;
import java.util.LinkedList;



import model.UserOrder;
import model.Food;
import model.Recover;
import model.Shop;
import model.User;
public class UserController {

	private OrderController orderDao=new OrderController();
	private ShopController shopDao=new ShopController();
	private RecoverController recoverDao=new RecoverController();
	/**
	 * 用户登录
	 * @param user
	 * @return
	 */
    public static User userLogin(User user){
        File f=new File("src\\dataset\\user.txt");
        User tmp=null;
        try{
            FileReader fr=new FileReader(f);
            BufferedReader br=new BufferedReader(fr);
            String str;

            while((str=br.readLine())!=null){
                String[]array=str.split(" ");
                if(array[0].equals(user.getName()) && array[1].equals(user.getPassword())) {
                    tmp=new User(array[0],array[1],array[2],0);
                    break;
                }else
                    continue;
            }
            br.close();
            fr.close();
            return tmp;
        } catch(IOException ioe){
            ioe.printStackTrace();
            return tmp;
        }
    }
    
    /**
     * 用户注册
     * @param name
     * @param password
     * @param phone
     * @return
     */
    public int userRegister(String name,String password,String phone){
        File f=new File("src\\dataset\\user.txt");
        try{
            FileWriter fw=new FileWriter(f,true);
            BufferedWriter bw=new BufferedWriter(fw);
            String s=name+" "+password+" "+phone;
            bw.write("\n");
            bw.write(s);
            bw.close();
            fw.close();
            return 1;
        }catch (IOException ioe){
            ioe.printStackTrace();
            return 0;
        }
    }
    
    public User getUser(String name) {
    	File f=new File("src\\dataset\\user.txt");
    	try {
    		FileReader fr=new FileReader(f);
    		BufferedReader br=new BufferedReader(fr);
    		String s=null;
    		User user=null;
    		while((s=br.readLine())!=null) {
    			String []array=s.split(" ");
    			if(array[0].equals(name)) {
    				System.out.println("查找到该用户!");
    				user=new User(array[0],array[1],array[2],0);
    				return user;
    			}
    		}
    		
    	}catch(IOException ioe) {
    		ioe.printStackTrace();
    		return null;
    	}
		return null;
    }
    
    /**
     * 从user.txt读取所有用户信息，存入链表中
     * @return
     */
    public LinkedList<User> readAllUser(){
    	LinkedList<User>userLink=new LinkedList<>();
    	File f=new File("src\\dataset\\user.txt");
    	try {
    		FileReader fr=new FileReader(f);
    		BufferedReader br=new BufferedReader(fr);
    		String s=null;
    		User user=null;
    		while((s=br.readLine())!=null) {
    			String []array=s.split(" ");
    			user=new User(array[0],array[1],array[2],0);
    			userLink.add(user);//生成User链表
    		}
    		return userLink;
    		
    	}catch(IOException ioe) {
    		ioe.printStackTrace();
    		return null;
    	}
    }
    
    /**
     * 将链表中的所有User写入文件
     * @param userLink
     */
    public void writeAllUser(LinkedList<User> userLink) {
 
    	File f=new File("src\\dataset\\user.txt");
    	int line=0;
        try{
            FileWriter fw=new FileWriter(f);
            BufferedWriter bw=new BufferedWriter(fw);
            for(User user:userLink) {
            	String s=user.getName()+" "+user.getPassword()+" "+user.getPhone();
            	if(line!=0) {    	
            	bw.newLine();
            	bw.write(s);
            	}else {
            		bw.write(s);//第一行起始不用换行
            	}
            	line++;
            }
            bw.close();
            fw.close();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
    
    /**
     * 删除用户，需要更新订单信息和用户表
     * @param user
     * @return
     */
    public int deleteUser(User user) {
    	int flag=0;
    	LinkedList<User> Users=readAllUser();
    	LinkedList<User> newUsers=new LinkedList<User>();
    	LinkedList<UserOrder> orderList=orderDao.readAllOrder();
    	for(User currentUser:Users) {
    		if(currentUser.getName().equals(user.getName())) {
    			this.recoverFoodNum(user.getName()); //恢复shop.txt中的菜品
    			this.deleteOrder(orderList, currentUser.getName()); //删除用户订单
    			flag=1;
    			continue;
    		}else {
    			newUsers.add(currentUser);
    		}
    	}
    	writeAllUser(newUsers);
    	return flag;
    }
    
    /**
     * 用户查询订单
     * @param name
     * @return
     */
    public LinkedList<UserOrder> searchOrder(String name) {
    	File f=new File("src\\dataset\\order.txt");
    	LinkedList<UserOrder> orderList=new LinkedList<>();
    	try {
    		String userName=name;
    		FileReader fr=new FileReader(f);
    		BufferedReader br=new BufferedReader(fr);
    		String s=null;
    		UserOrder order=null;
    		while((s=br.readLine())!=null) {
    			String []array=s.split(" ");
    			if(name.equals(array[0])) { //找到订单
    				order=new UserOrder(array[0],array[1],array[2],array[3]);
    				orderList.add(order);
    			}
    		}
    		return orderList;
    		
    	}catch(IOException ioe) {
    		ioe.printStackTrace();
    		return null;
    	}
    }
    
  
    
    /**
     * 消费者注销信息后订单也要取消，同时还要修改shop.txt中的food_num
     * @param orderList
     * @param name :消费者用户名
     */
    public void deleteOrder(LinkedList<UserOrder>orderList,String name) {
    	File f=new File("src\\dataset\\order.txt");
    	FileWriter fw;
    	int line=0;
		try {
			fw = new FileWriter(f);
			BufferedWriter bw=new BufferedWriter(fw);
			for(UserOrder currentOrder:orderList) {
				if(currentOrder.getUserName().equals(name)) {
					continue;
				}else {
					String s=currentOrder.getUserName()+" "+currentOrder.getShopName()+" "+currentOrder.getOrderTime()+" "+currentOrder.getOrderFood();
	            	if(line!=0) {    	
	            	bw.newLine();
	            	bw.write(s);
	            	}else {
	            		bw.write(s);//第一行起始不用换行
	            	}
	            	line++;
				}
			}
			bw.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    /**
     * 用户删除后恢复food_num
     */
    public void recoverFoodNum(String userName) {
    	LinkedList<UserOrder> orderList=orderDao.readAllOrder();
    	LinkedList<Recover> userOrder=new LinkedList<>();
    	for(UserOrder order:orderList) { //从orderList中获取订单信息并写入recover.txt中
    		if(order.getUserName().equals(userName)) {
    			String orderInfo=order.getOrderFood().substring(5,order.getOrderFood().length()-1);
    			System.out.println(orderInfo);
    			String []array=orderInfo.split(",");
    			for(String s:array) {
    				String []food=s.split(":");
    				Recover rc=new Recover();
    				rc.setShopName(order.getShopName());
    				rc.setOrderInfo(food[0]);
    				userOrder.add(rc);  //格式为:商家名 一道菜品
    			}
    		}
    	}
    	recoverDao.writerAllRecover(userOrder);
    	LinkedList<Recover> rcList=recoverDao.readAllRecover();
    	LinkedList<Shop> shopList=shopDao.readAllShop();	
    	for(Shop currentShop:shopList) { //去recover.txt中的信息去和shop.txt的商家菜品信息去匹配，并恢复菜品信息
    		String shopName=currentShop.getName(); //商家名
    		for(Recover rc:rcList) { //每个恢复订单
    			if(rc.getShopName().equals(shopName)) {
    				for(Food currentFood:currentShop.getFood()) {
    					if(currentFood.getFood_name().equals(rc.getOrderInfo())) {
    						int nums=currentFood.getFood_nums();
    						currentFood.setFood_nums(nums+1);
    						break;
    					}
    				}
    			}
    		}
    	}
    	shopDao.wrtieAllShop(shopList);
    			
    }
   
    
}
