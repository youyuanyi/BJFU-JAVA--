package controller;

import java.io.*;
import java.util.HashSet;
import java.util.LinkedList;


import model.Food;
import model.Shop;
import model.User;
import model.UserOrder;

/**
 * �̼ҿ�����
 * @author HP
 *
 */
public class ShopController {
	
	private OrderController orderDao=new OrderController();
	private static HashSet<Integer> hs=new HashSet<>();
	/**
	 * ��ȡ�ļ�������������
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
					if(s==null) { //������ע����̼ң���û��food��comment��Ϣ
						shopList.add(shop);
						break;
					}
				}else if(array[0].equals("food_id:")) {
					String food_id=array[1].substring(0, array[1].length()-1);//��Ϊfood_id���ļ�����,��β
					String food_name=array[3].substring(0, array[3].length()-1);//ͬ��
					String food_price=array[5].substring(0,array[5].length()-1);
					String food_num=array[7];
					Food food=new Food();
					food.setFood_id(food_id);
					food.setFood_name(food_name);
					food.setFood_price(food_price);
					food.setFood_nums(Integer.parseInt(food_num));
					foodList.add(food);
					s=br.readLine();
					
					String ss=s; //ss�ǵ�ǰfood�е���һ��
					if(ss!=null) { //���food��Ϣ����һ�л�����Ϣ�����ʾ����̼Ҳ������һ��
						String []checkArray=ss.split(" ");
						if(checkArray[0].equals("shoptype:")) {
							shop.setFood(foodList);
							shop.setComment(commentList);
							shopList.add(shop);
						}
					}else { //�������̼ң���ע���˲�Ʒ��Ϣ����Ҫע����һ����Ʒʱ
						shop.setFood(foodList);
						shop.setComment(commentList);
						shopList.add(shop);
					}
				
					
				}else if(array[0].contains("Comment")) {
					String newString=s.substring(10);//��ȡ
					commentList.add(newString);
					s=br.readLine(); //��һ�У��ж��ǲ���comment��������ǣ�������һ���̼ҵ���Ϣ

					String ss=s;
					if(ss!=null) {//�Ƿ񵽽�β
						String []checkArray=ss.split(" ");
						if(!checkArray[0].contains("Comment")) { //���۽�������һ�п�ʼ���µ��̼�							
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
	 * �������̼���Ϣд���ļ�
	 * @param shopList
	 */
	public void wrtieAllShop(LinkedList<Shop> shopList) {
		try {
			FileWriter fw=new FileWriter("src\\dataset\\shop.txt");
			BufferedWriter bw=new BufferedWriter(fw);
			int len=0;
			int shopLen=shopList.size();//�̼Ҹ��� 848
			for(Shop currentShop:shopList) {
				len++; //len��1��ʼ
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
					if(commentList==null) { //������
						commentList=new LinkedList<>();
					}
					int food_len=foodList.size();
					for(Food currentFood:foodList) {
						bw.write("food_id: "+currentFood.getFood_id()+", food_name: "+currentFood.getFood_name()+", food_price: "+currentFood.getFood_price()
								+", food_num: "+currentFood.getFood_nums());
						food_len--;
						if(commentList.size()!=0) {
							//System.out.println(currentShop.getName()+"��"+commentList.size()+"������");
							bw.write("\n");//��������ۣ�����������Ҫ�ӻ��з�
						}else { 	
							//System.out.println(currentShop.getName()+"������");
							if(food_len>0 || shopLen>0) { //�����ۣ�����һ�����²�Ʒ������һ�����µ��̼�
								bw.write("\n");
							}
						}
					}
				}
				if(currentShop.getComment()!=null) {
				int cmtSize=currentShop.getComment().size();
				if(cmtSize!=0) { //���������
					if(len!=shopLen) { //�������һ����ÿһ��һ��������Ҫ����
						int tmp=0;
						for(String cmt:currentShop.getComment()) {
							bw.write("Comment"+tmp+": "+cmt+"\n");
							tmp++;
						}
					}else { //���һ���̼ң��������һ��������Ҫ����
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
	 * �̼Ҳ�ѯ����
	 * @param name �̼�����
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
	 * ע���̼�,������ϢҳҪ�޸�
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
    			this.deleteOrder(orderLists, currentShop.getName()); //ɾ���û�����
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
	 * �̼�ע��,�̼������������̼��Լ���д
	 * shopType,address,phone,food�����ڸ�����Ϣ�޸��н���
	 * shopId��hashset�������
	 * ���������Զ�Ĭ��Ϊ��
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
		shop.setShopType("δ֪");
		shop.setAddress("δ֪");
		shop.setPhone("δ֪");
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
	 * ע���û�ǰҪ�����������Ϣ
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
				if(currentOrder.getShopName().equals(name)) { //���̼�������ƥ��
					continue;
				}else {
					String s=currentOrder.getUserName()+" "+currentOrder.getShopName()+" "+currentOrder.getOrderTime()+" "+currentOrder.getOrderFood();
	            	if(line!=0) {    	
	            	bw.newLine();
	            	bw.write(s);
	            	}else {
	            		bw.write(s);//��һ����ʼ���û���
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
	 * ����޸��˲�Ʒ��Ϣ�еĲ������߲�Ʒ���ۣ���Ҫ�޸�order.txt�ļ��е���Ϣ
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
				if(!currentOrder.getOrderFood().equals("��Ʒ��Ϣ:")) { //Ŀǰ�Ƕ��˲˵�
				String s=currentOrder.getOrderFood().substring(5,currentOrder.getOrderFood().length()-1);
				String []array=s.split(",");
				StringBuffer sb=new StringBuffer("��Ʒ��Ϣ:");
				for(String ss:array) {
					String []twoInfo=ss.split(":");
					if(twoInfo[0].equals(oldName)) { //�ҵ���Ӧ�Ĳ�Ʒ
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
	 * �̼�ɾ����Ʒ��Ϣ����Ҫɾ����Ӧ��order.txt�ļ��еĲ�Ʒ��Ϣ
	 * @param shopName
	 * @param food_name
	 */
	public void deletePartialOrder(String shopName,String food_name) {
		LinkedList<UserOrder> orderList=orderDao.readAllOrder();
		for(UserOrder currentOrder:orderList) {
			if(currentOrder.getShopName().equals(shopName)) {
				if(!currentOrder.getOrderFood().equals("��Ʒ��Ϣ:")) { //Ŀǰ�Ƕ��˲˵�
					String s=currentOrder.getOrderFood().substring(5,currentOrder.getOrderFood().length()-1);
					System.out.println(s);
					String []array=s.split(",");
					StringBuffer sb=new StringBuffer("��Ʒ��Ϣ:");
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
