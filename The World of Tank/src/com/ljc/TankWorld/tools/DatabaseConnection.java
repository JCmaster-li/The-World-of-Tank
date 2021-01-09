package com.ljc.TankWorld.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * �������ݿ�Ĺ�����
 * @author JCؼ����
 *
 */
public class DatabaseConnection {
	private static final String DBDRIVER="com.mysql.jdbc.Driver";
	/**
	 * ���ӵ����ݿ��ַ
	 */
	private static final String DBURL="jdbc:mysql://localhost:3306/tankworld?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
	/**
	 * MySQL���ݿ������û���
	 */
	private static final String DBUSER="root";
	/**
	 * MySQL���ݿ������û���������
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
