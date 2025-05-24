package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class Jdbc {
	private Connection conn = null;
	public Jdbc(){
		try {
			String name="com.mysql.cj.jdbc.Driver";
			Class.forName(name);
			System.out.println("加载驱动成功！");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public boolean DBconnect() throws SQLException{
		String url = "jdbc:mysql:" // 数据库名称
				+ "//localhost:3306/user?" // 端口号+数据库
				+ "useUnicode=true&characterEncoding=utf-8" 
				+ "&serverTimezone=GMT%2B8&useSSL=false"; // 服务器时区
		Properties info = new Properties();
		info.setProperty("user", "root");
		info.setProperty("password", "123456");
		try {
			conn = DriverManager.getConnection(url,info);
			if (conn != null) {
				System.out.println("链接数据库成功！");
				return true;
			}
			else {
				System.out.println("链接数据库失败！");
				}
			} 
		catch (SQLException e) {
				e.printStackTrace();
		}
		return false;
	}	
	public boolean add(String phone,String name,String password) throws SQLException{
		if (conn != null) {
			String sql="insert into user(Phone,Name,password) values(?,?,?)";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, phone);
			pstmt.setString(2, name);
			pstmt.setString(3, password);
			int n=pstmt.executeUpdate();
			if(n>0) {
				System.out.println("添加成功");
			}
		}
		return false;
	}
}

