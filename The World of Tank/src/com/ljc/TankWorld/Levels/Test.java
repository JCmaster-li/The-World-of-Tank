package com.ljc.TankWorld.Levels;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.ljc.TankWorld.tools.DatabaseConnection;
import com.ljc.TankWorld.tools.RandomNumber;

public class Test {
	private static String name="abcdefghijklmnopqrstuvwxyz";
	private static String firstName="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static void main(String[] args) {
		addPlayerMessage();
	}
	public static int getNum(int start,int end) {//������ط���ָ����Χ�������
    	//Math.random()�������0.0��1.0֮�����
        return (int)(Math.random()*(end-start+1)+start);
    }
	public static String getChineseName() {
        int index=getNum(0, firstName.length()-1);//���ȡ�����ַ����е�����λ��
        String first=firstName.substring(index, index+1);//��ȡ��λ�õ�����
        String second="";
        int length=RandomNumber.createRandomNumber(10, 4);
        for(int i=0;i<length;i++) {
        	index=getNum(0, name.length()-1);
            second=name.substring(index, index+1);
        	first=first+second;
        }
		return first;
    }
	public static int getGrade() {
		return RandomNumber.createRandomNumber(600, 100);
	}
	public static void addPlayerMessage() {
		DatabaseConnection dbcs = new DatabaseConnection();
		String sql = "insert into PlayerMessage(Name,Grade) values(?,?)";
		try (Connection conn = dbcs.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			for(int i=0;i<1000;i++) {
				pstmt.setString(1, getChineseName());
				pstmt.setInt(2, getGrade());
				pstmt.addBatch();
			}
			pstmt.executeBatch();
			JOptionPane.showMessageDialog(null, "sucess");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
