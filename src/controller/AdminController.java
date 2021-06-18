package controller;

import java.io.*;
import java.util.LinkedList;

import model.Admin;

/**
 * 管理员控制类
 * @author HP
 *
 */
public class AdminController {
	public LinkedList<Admin> readAllAdmin(){
		File f=new File("src\\dataset\\admin.txt");
		LinkedList<Admin> adminList=new LinkedList<Admin>();
		try {
			FileReader fr=new FileReader(f);
			BufferedReader br=new BufferedReader(fr);
			String s;
			while((s=br.readLine())!=null) {
				String[]array=s.split(" ");
				Admin admin=new Admin(array[0],array[1]);
				adminList.add(admin);
				
			}
			br.close();
			fr.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return adminList;
	}
	
	public void writeAllAdmin(LinkedList<Admin> adminList) {
		File f=new File("src\\dataset\\admin.txt");
		int line=0;
		try {
			FileWriter fw=new FileWriter(f);
			BufferedWriter bw=new BufferedWriter(fw);
			for(Admin currentAdmin:adminList) {
				String s=currentAdmin.getName()+" "+currentAdmin.getPassword();
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
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
