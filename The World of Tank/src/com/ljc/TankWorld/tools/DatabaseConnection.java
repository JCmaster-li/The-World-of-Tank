package com.ljc.TankWorld.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * 连接数据库的工具类
 * @author JC丶本尊
 *
 */
public class DatabaseConnection {
	private static final String DBDRIVER="com.mysql.jdbc.Driver";
	/**
	 * 连接的数据库地址
	 */
	private static final String DBURL="jdbc:mysql://localhost:3306/tankworld?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
	/**
	 * MySQL数据库连接用户名
	 */
	private static final String DBUSER="root";
	/**
	 * MySQL数据库连接用户名的密码
	 */
	private static final String PASSWORD="dontgiveup1207";
	private Connection conn=null;
	public DatabaseConnection() {
		try {
			Class.forName(DBDRIVER);
			this.conn=DriverManager.getConnection(DBURL,DBUSER,PASSWORD);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Connection getConnection() {
		return this.conn;
	}
	public void close() {
		if(this.conn!=null) {
			try {
				this.conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
