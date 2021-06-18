package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import model.Recover;
import model.User;

/**
 * �ָ���ʵ��
 * @author HP
 *
 */
public class RecoverController {
	/**
	 * ��ȡ���еĻָ���Ϣ
	 * @return
	 */
	public LinkedList<Recover> readAllRecover() {
		LinkedList<Recover> recoverList=new LinkedList<>();
    	File f=new File("src\\dataset\\recover.txt");
    	try {
    		FileReader fr=new FileReader(f);
    		BufferedReader br=new BufferedReader(fr);
    		String s=null;
    		Recover rc=null;
    		while((s=br.readLine())!=null) {
    			String []array=s.split(" ");
    			rc=new Recover(array[0],array[1]);
    			recoverList.add(rc);//����User����
    		}
    		return recoverList;
    		
    	}catch(IOException ioe) {
    		ioe.printStackTrace();
    		return null;
    	}
	}
	
	public void writerAllRecover(LinkedList<Recover> recoverList) {
		File f=new File("src\\dataset\\recover.txt");
    	int line=0;
        try{
            FileWriter fw=new FileWriter(f);
            BufferedWriter bw=new BufferedWriter(fw);
            for(Recover rc:recoverList) {
            	String s=rc.getShopName()+" "+rc.getOrderInfo();
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
