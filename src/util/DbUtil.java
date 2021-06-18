package util;

import java.sql.Connection;
import java.sql.DriverManager;

/*
 * 数据库工具类
 */
public class DbUtil {
	private String dbURL="jdbc:mysql://localhost:3306/n192517?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
	private String dbUserName="root";
	private String dbPassword="YJC706989";
	private String jdbcName="com.mysql.cj.jdbc.Driver";
	
	/*
	 * 获取数据库连接
	 * 针对mysql 8.0以上的连接方式
	 */
	public Connection getCon() throws Exception{
		Class.forName(jdbcName);
		Connection con=DriverManager.getConnection(dbURL,dbUserName,dbPassword);
		return con;
	}
	
	/*
	 * 关闭数据库
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
			System.out.println("连接成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("数据库连接失败");
		}
	}
}
