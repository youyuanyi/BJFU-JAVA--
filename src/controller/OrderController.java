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
 * ��������ʵ��
 * @author HP
 *
 */
public class OrderController {
	
	 /**
     * ��ȡ���еĶ�����Ϣ
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
     * д�����ж�����Ϣ
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
                		bw.write(s);//��һ����ʼ���û���
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
	 * �޸������ƣ���ҲҪ�޸�order�ļ��е�����
	 * @param oldName
	 * @param newName
	 */
	public void EditOrderTxt(String oldName,String newName) {
		LinkedList<UserOrder> orderList=this.readAllOrder();//��ȡ���ж�����Ϣ
    	if(orderList!=null) {
    	File f=new File("src\\dataset\\order.txt");
    	int line=0;
        try{
            FileWriter fw=new FileWriter(f);
            BufferedWriter bw=new BufferedWriter(fw);
            for(UserOrder user:orderList) {
            	if(user.getUserName().equals(oldName)) {
            		user.setUserName(newName); //�����߸���
            		System.out.println("���޸Ķ�����Ϣ!");
            	}else if(user.getShopName().equals(oldName)) {
            		user.setShopName(newName); //�̼Ҹ���
            		System.out.println("���޸Ķ�����Ϣ!");
            	}
            	String s=user.getUserName()+" "+user.getShopName()+" "+user.getOrderTime()+" "+user.getOrderFood();
            	if(line!=0) {    	
            	bw.newLine();
            	bw.write(s);
            	}else {
            		bw.write(s);//��һ����ʼ���û���
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
