package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import model.UserOrder;

/**
 * 订单控制实体
 * @author HP
 *
 */
public class OrderController {
	
	 /**
     * 读取所有的订单信息
     * @return
     */
    public LinkedList<UserOrder> readAllOrder(){
    	File f=new File("src\\dataset\\order.txt");
    	LinkedList<UserOrder> orderList=new LinkedList<>();
    	try {
    		FileReader fr=new FileReader(f);
    		BufferedReader br=new BufferedReader(fr);
    		String s=null;
    		UserOrder order=null;
    		while((s=br.readLine())!=null) {
    			String []array=s.split(" ");
    			order=new UserOrder(array[0],array[1],array[2],array[3]);
    			orderList.add(order);
    		}
    		br.close();
    		fr.close();
    		return orderList;
    		
    	}catch(IOException ioe) {
    		ioe.printStackTrace();
    		return null;
    	}
    }
    
    /**
     * 写入所有订单信息
     * @param orderList
     */
    public void writeAllOrders(LinkedList<UserOrder> orderList) {
    	if(orderList.size()!=0) {
    		File f=new File("src\\dataset\\order.txt");
        	int line=0;
            try{
                FileWriter fw=new FileWriter(f);
                BufferedWriter bw=new BufferedWriter(fw);
                for(UserOrder user:orderList) {
                	String s=user.getUserName()+" "+user.getShopName()+" "+user.getOrderTime()+" "+user.getOrderFood();
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
    }
    /**
	 * 修改了名称，则也要修改order文件中的名称
	 * @param oldName
	 * @param newName
	 */
	public void EditOrderTxt(String oldName,String newName) {
		LinkedList<UserOrder> orderList=this.readAllOrder();//获取所有订单信息
    	if(orderList!=null) {
    	File f=new File("src\\dataset\\order.txt");
    	int line=0;
        try{
            FileWriter fw=new FileWriter(f);
            BufferedWriter bw=new BufferedWriter(fw);
            for(UserOrder user:orderList) {
            	if(user.getUserName().equals(oldName)) {
            		user.setUserName(newName); //消费者改名
            		System.out.println("已修改订单信息!");
            	}else if(user.getShopName().equals(oldName)) {
            		user.setShopName(newName); //商家改名
            		System.out.println("已修改订单信息!");
            	}
            	String s=user.getUserName()+" "+user.getShopName()+" "+user.getOrderTime()+" "+user.getOrderFood();
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
	}
}
