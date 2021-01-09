package com.ljc.TankWorld.tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * 数据库的操作工具
 * @author JC丶本尊
 *
 */
public class MySqlTool {
	private DatabaseConnection dbcs = new DatabaseConnection();
	public MySqlTool() {

	}

	/**
	 * 向数据库写入玩家姓名和成绩
	 * @param name
	 * @param grade
	 */
	public void writeGrade(String name, int grade) {
		String sql = "insert into PlayerMessage(Name,Grade) values(?,?)";
		try (Connection conn = dbcs.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, name);
			pstmt.setInt(2, grade);
			pstmt.addBatch();
			pstmt.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 从数据库查找成绩最高的前5名
	 * @return
	 */
	public List<String> readGrade() {
		int i = 1;
		List<String> gradeList = new ArrayList<String>();
		String message = "";
		String sql = "select Name,Grade from PlayerMessage order by Grade desc limit 7";
		try (Connection conn = dbcs.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				message = Integer.toString(i) + "." + rs.getString(1);
				gradeList.add(Integer.toString(rs.getInt(2)));
				gradeList.add(message);
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return gradeList;
	}
}
