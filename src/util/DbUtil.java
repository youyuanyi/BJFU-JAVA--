package util;

import java.sql.Connection;
import java.sql.DriverManager;

/*
 * ���ݿ⹤����
 */
public class DbUtil {
	private String dbURL="jdbc:mysql://localhost:3306/n192517?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
	private String dbUserName="root";
	private String dbPassword="YJC706989";
	private String jdbcName="com.mysql.cj.jdbc.Driver";
	
	/*
	 * ��ȡ���ݿ�����
	 * ���mysql 8.0���ϵ����ӷ�ʽ
	 */
	public Connection getCon() throws Exception{
		Class.forName(jdbcName);
		Connection con=DriverManager.getConnection(dbURL,dbUserName,dbPassword);
		return con;
	}
	
	/*
	 * �ر����ݿ�
	 * @param con
	 * @throws Exception
	 */
	public void closeCon(Connection con) throws Exception{
		if(con!=null) {
			con.close();
		}
	}
	
	public static void main(String[] args) {
		DbUtil dbUtil=new DbUtil();
		try {
			dbUtil.getCon();
			System.out.println("���ӳɹ�");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("���ݿ�����ʧ��");
		}
	}
}
