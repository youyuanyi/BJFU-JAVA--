package controller;

import java.io.*;
import java.util.HashSet;
import java.util.LinkedList;


import model.Food;
import model.Shop;
import model.User;
import model.UserOrder;

/**
 * 商家控制类
 * @author HP
 *
 */
public class ShopController {
	
	private OrderController orderDao=new OrderController();
	private static HashSet<Integer> hs=new HashSet<>();
	/**
	 * 读取文件，存入链表中
	 * @return
	 */
	public LinkedList<Shop> readAllShop() {
		File f=new File("src\\dataset\\shop.txt");
		try {
			LinkedList<Shop> shopList=new LinkedList<>();
			FileReader fr=new FileReader(f);
			BufferedReader br=new BufferedReader(fr);
			String s;		
			Shop shop=new Shop();
			s=br.readLine();
			LinkedList<Food> foodList=new LinkedList<>();
			LinkedList<String> commentList=new LinkedList<>();
			while(s!=null) {
				String []array=s.split(" ");
				if(array[0].equals("shoptype:")) {
					shop=new Shop();
					foodList=new LinkedList<>();
					commentList=new LinkedList<>();
					shop.setShopType(array[1]);
					s=br.readLine();
				}
				else if(array[0].equals("shopId:")) {
					shop.setId(array[1]);	
					s=br.readLine();
				}else if(array[0].equals("shopName:")) {
					shop.setName(array[1]);
					s=br.readLine();
				}else if(array[0].equals("shopPassword:")){
					shop.setPassword(array[1]);
					s=br.readLine();
				}else if(array[0].equals("avgScore:")) {
					shop.setAvgScore(Double.parseDouble(array[1]));
					s=br.readLine();
				}else if(array[0].equals("avePrice:")) {
					shop.setAveprice(Double.parseDouble(array[1]));
					s=br.readLine();
				}else if(array[0].equals("address:")) {
					shop.setAddress(array[1]);
					s=br.readLine();
				}else if(array[0].equals("phone:")) {
					shop.setPhone(array[1]);
					s=br.readLine();
					if(s==null) { //对于新注册的商家，还没有food和comment信息
						shopList.add(shop);
						break;
					}
				}else if(array[0].equals("food_id:")) {
					String food_id=array[1].substring(0, array[1].length()-1);//因为food_id在文件中以,结尾
					String food_name=array[3].substring(0, array[3].length()-1);//同理
					String food_price=array[5].substring(0,array[5].length()-1);
					String food_num=array[7];
					Food food=new Food();
					food.setFood_id(food_id);
					food.setFood_name(food_name);
					food.setFood_price(food_price);
					food.setFood_nums(Integer.parseInt(food_num));
					foodList.add(food);
					s=br.readLine();
					
					String ss=s; //ss是当前food行的下一行
					if(ss!=null) { //如果food信息的下一行还有信息，则表示这个商家不是最后一个
						String []checkArray=ss.split(" ");
						if(checkArray[0].equals("shoptype:")) {
							shop.setFood(foodList);
							shop.setComment(commentList);
							shopList.add(shop);
						}
					}else { //对于新商家，他注册了菜品信息，且要注册下一个菜品时
						shop.setFood(foodList);
						shop.setComment(commentList);
						shopList.add(shop);
					}
				
					
				}else if(array[0].contains("Comment")) {
					String newString=s.substring(10);//截取
					commentList.add(newString);
					s=br.readLine(); //下一行，判断是不是comment，如果不是，则是下一个商家的信息

					String ss=s;
					if(ss!=null) {//是否到结尾
						String []checkArray=ss.split(" ");
						if(!checkArray[0].contains("Comment")) { //评论结束，下一行开始是新的商家							
							shop.setFood(foodList);
							shop.setComment(commentList);
							shopList.add(shop);
						}
					}else {
						shop.setFood(foodList);
						shop.setComment(commentList);
						shopList.add(shop);
						break;
					}
					
				}
			}
			return shopList;
			
		}catch(IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 把所有商家信息写入文件
	 * @param shopList
	 */
	public void wrtieAllShop(LinkedList<Shop> shopList) {
		try {
			FileWriter fw=new FileWriter("src\\dataset\\shop.txt");
			BufferedWriter bw=new BufferedWriter(fw);
			int len=0;
			int shopLen=shopList.size();//商家个数 848
			for(Shop currentShop:shopList) {
				len++; //len从1开始
				shopLen--;
				bw.write("shoptype: "+currentShop.getShopType()+"\n");
				bw.write("shopId: "+currentShop.getId()+"\n");
				bw.write("shopName: "+currentShop.getName()+"\n");
				bw.write("shopPassword: "+currentShop.getPassword()+"\n");
				bw.write("avgScore: "+currentShop.getAvgScore()+"\n");
				bw.write("avePrice: "+currentShop.getAveprice()+"\n");
				bw.write("address: "+currentShop.getAddress()+"\n");
				if(currentShop.getFood()!=null)
					bw.write("phone: "+currentShop.getPhone()+"\n");
				else
					bw.write("phone: "+currentShop.getPhone());
				if(currentShop.getFood()!=null) {
					LinkedList<Food> foodList=currentShop.getFood();
					LinkedList<String> commentList=currentShop.getComment();
					if(commentList==null) { //无评论
						commentList=new LinkedList<>();
					}
					int food_len=foodList.size();
					for(Food currentFood:foodList) {
						bw.write("food_id: "+currentFood.getFood_id()+", food_name: "+currentFood.getFood_name()+", food_price: "+currentFood.getFood_price()
								+", food_num: "+currentFood.getFood_nums());
						food_len--;
						if(commentList.size()!=0) {
							//System.out.println(currentShop.getName()+"有"+commentList.size()+"条评论");
							bw.write("\n");//如果有评论，无论怎样都要加换行符
						}else { 	
							//System.out.println(currentShop.getName()+"无评论");
							if(food_len>0 || shopLen>0) { //无评论，且下一行是新菜品或者下一行是新的商家
								bw.write("\n");
							}
						}
					}
				}
				if(currentShop.getComment()!=null) {
				int cmtSize=currentShop.getComment().size();
				if(cmtSize!=0) { //如果有评论
					if(len!=shopLen) { //不是最后一个，每一条一条评论需要换行
						int tmp=0;
						for(String cmt:currentShop.getComment()) {
							bw.write("Comment"+tmp+": "+cmt+"\n");
							tmp++;
						}
					}else { //最后一个商家，他的最后一条评论需要换行
						int tmp=0;
						for(String cmt:currentShop.getComment()) {
//							if(tmp!=cmtSize-1) {
//							bw.write("Comment"+tmp+": "+cmt+"\n");
//							}else {
//								bw.write("Comment"+tmp+": "+cmt);
//							}
							bw.write("Comment"+tmp+": "+cmt+"\n");
							tmp++;
						}
					}
				}
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
	 * 商家查询订单
	 * @param name 商家名称
	 * @return
	 */
	public LinkedList<UserOrder> searchOrder(String name) {
		LinkedList<UserOrder> orderList=orderDao.readAllOrder();
		LinkedList<UserOrder> shopOrderList=new LinkedList<>();
		for(UserOrder order:orderList) {
			if(order.getShopName().equals(name)) {
				shopOrderList.add(order);
				continue;
			}
		}
		return shopOrderList;
	}
	
	/**
	 * 注销商家,订单信息页要修改
	 * @param shop
	 * @return
	 */
	public int deleteShop(Shop shop) {
		int flag=0;
    	LinkedList<Shop> shopList=this.readAllShop();
    	LinkedList<Shop> newShopList=new LinkedList<Shop>();
    	LinkedList<UserOrder> orderLists=orderDao.readAllOrder();
    	for(Shop currentShop:shopList) {
    		if(currentShop.getId().equals(shop.getId())) {
    			this.deleteOrder(orderLists, currentShop.getName()); //删除用户订单
    			flag=1;
    			continue;
    		}else {
    			newShopList.add(currentShop);
    		}
    	}
    	this.wrtieAllShop(newShopList);
    	return flag;
	}
	/**
	 * 商家注册,商家名和密码由商家自己填写
	 * shopType,address,phone,food都是在个人信息修改中进行
	 * shopId用hashset随机生成
	 * 其他的属性都默认为无
	 * @param shopName
	 * @param pwd
	 * @return
	 */
	public int registerShop(String shopName,String pwd) {
		Shop shop=new Shop();
		shop.setName(shopName);
		shop.setPassword(pwd);
		Integer newId=(int)(Math.random()*10000)+1;
		while(!hs.add(newId)) {
			newId=(int)(Math.random()*100000)+100;
		}
		shop.setId(newId.toString());
		shop.setShopType("未知");
		shop.setAddress("未知");
		shop.setPhone("未知");
		shop.setAvgScore(0.);
		shop.setAveprice(0.);
		shop.setFood(new LinkedList<Food>());
		shop.setComment(new LinkedList<String>());
		int flag=0;
		File f=new File("src\\dataset\\shop.txt");
		try {
			FileWriter fw=new FileWriter(f,true);
	        BufferedWriter bw=new BufferedWriter(fw);
	        bw.write("\n");
			bw.write("shoptype: "+shop.getShopType());
			bw.write("\n");
			bw.write("shopId: "+shop.getId());
			bw.write("\n");
			bw.write("shopName: "+shop.getName());
			bw.write("\n");
			bw.write("shopPassword: "+shop.getPassword());
			bw.write("\n");
			bw.write("avgScore: "+shop.getAvgScore());
			bw.write("\n");
			bw.write("avePrice: "+shop.getAveprice());
			bw.write("\n");
			bw.write("address: "+shop.getAddress());
			bw.write("\n");
			bw.write("phone: "+shop.getPhone());
			flag=1;
			bw.close();
			fw.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return newId;
	}
	/**
	 * 注销用户前要先清除订单信息
	 * @param orderLists
	 * @param name
	 */
	private void deleteOrder(LinkedList<UserOrder> orderLists, String name) {
		// TODO Auto-generated method stub
		File f=new File("src\\dataset\\order.txt");
    	FileWriter fw;
    	int line=0;
		try {
			fw = new FileWriter(f);
			BufferedWriter bw=new BufferedWriter(fw);
			for(UserOrder currentOrder:orderLists) {
				if(currentOrder.getShopName().equals(name)) { //和商家名进行匹配
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
	 * 如果修改了菜品信息中的菜名或者菜品单价，需要修改order.txt文件中的信息
	 * @param shopName
	 * @param oldName
	 * @param oldPrice
	 * @param newName
	 * @param newPrice
	 */
	public void changeOrderTxt(String shopName,String oldName,String oldPrice,String newName,String newPrice) {
		LinkedList<UserOrder> orderList=orderDao.readAllOrder();
		for(UserOrder currentOrder:orderList) {
			if(currentOrder.getShopName().equals(shopName)) {
				if(!currentOrder.getOrderFood().equals("菜品信息:")) { //目前是定了菜的
				String s=currentOrder.getOrderFood().substring(5,currentOrder.getOrderFood().length()-1);
				String []array=s.split(",");
				StringBuffer sb=new StringBuffer("菜品信息:");
				for(String ss:array) {
					String []twoInfo=ss.split(":");
					if(twoInfo[0].equals(oldName)) { //找到对应的菜品
						sb.append(newName+":");
					}else {
						sb.append(twoInfo[0]+":");
					}
					if(twoInfo[1].equals(oldPrice) && twoInfo[0].equals(oldName)) {
						sb.append(newPrice+",");
					}else {
						sb.append(twoInfo[1]+",");
					}
				}
				currentOrder.setOrderFood(new String(sb));
			}
			}
		}
		orderDao.writeAllOrders(orderList);
	}
	
	/**
	 * 商家删除菜品信息后，需要删除对应的order.txt文件中的菜品信息
	 * @param shopName
	 * @param food_name
	 */
	public void deletePartialOrder(String shopName,String food_name) {
		LinkedList<UserOrder> orderList=orderDao.readAllOrder();
		for(UserOrder currentOrder:orderList) {
			if(currentOrder.getShopName().equals(shopName)) {
				if(!currentOrder.getOrderFood().equals("菜品信息:")) { //目前是定了菜的
					String s=currentOrder.getOrderFood().substring(5,currentOrder.getOrderFood().length()-1);
					System.out.println(s);
					String []array=s.split(",");
					StringBuffer sb=new StringBuffer("菜品信息:");
					for(String ss:array) {
						String []twoInfo=ss.split(":");
						if(twoInfo[0].equals(food_name)) {
							continue;
						}else {
							sb.append(ss+",");
						}
					}
					currentOrder.setOrderFood(new String(sb));
				}
			}
		}
		orderDao.writeAllOrders(orderList);
	}
}
