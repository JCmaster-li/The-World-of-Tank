package com.ljc.TankWorld.GUI;

import static com.ljc.TankWorld.constant.Constant.GAME_MENU;
import static com.ljc.TankWorld.constant.Constant.GAME_START;
import static com.ljc.TankWorld.constant.Constant.GAME_STOP;
import static com.ljc.TankWorld.constant.Constant.PLAYER_RIGHT;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.ljc.TankWorld.tools.MySqlTool;
import com.ljc.TankWorld.tools.PictureTool;

public class RecordGrade {
	/**
	 * 初始化计分板的状态
	 */
	private int GameState = GAME_STOP;
	
	/**
	 * 初始化玩家得分
	 */
	private int playerGrade = 0;
	
	/**
	 * 计分板中坦克图标的x轴位置
	 */
	private int Tank_x = 183;
	
	/**
	 * 计分板中坦克图标的y轴位置
	 */
	private int Tank_y = 821;
	
	/**
	 * 计分板中前七名文字的x轴位置
	 */
	private int word_x=286;
	
	/**
	 * 计分板中前七名文字的y轴位置
	 */
	private int word_y=200;
	
	/**
	 * 定义玩家名字符串变量
	 */
	private String playerName = "";
	
	/**
	 * 计分板背景图片
	 */
	private BufferedImage recordGrade = PictureTool.getImg("/com/ljc/TankWorld/picture/RecordGrade.png");
	
	/**
	 * 初始化玩家输入姓名的状态
	 */
	private String State = "Stop";
	
	/**
	 * 计分板前七名玩家名和分数的字符串集合
	 */
	private List<String> message=new ArrayList<String>();
	public RecordGrade() {
		
	}
	
	/**
	 * 画出计分板中的所有内容
	 * @param g
	 */
	public void drawGrade(Graphics g) {
		/**
		 * 计分板背景图
		 */
		g.drawImage(recordGrade, 0, 0, null);
		/**
		 * 坦克标志
		 */
		g.drawImage(PLAYER_RIGHT, Tank_x, Tank_y, null);
		/**
		 * 画笔颜色
		 */
		g.setColor(Color.WHITE);
		/**
		 * 设置字体
		 */
		g.setFont(new Font("Press Start 2P", Font.BOLD, 40));
		/**
		 * 实例化数据库工具
		 */
		MySqlTool mysqlTool = new MySqlTool();
		/**
		 * 从数据库读取前七名玩家的姓名和分数
		 */
		message=mysqlTool.readGrade();
		for(int i=0;i<message.size();i++) {
			String s=message.get(i);
			/**
			 * 画出最高分
			 */
			g.drawString(message.get(0), word_x+400, word_y-70);
			/**
			 * 画出前七名玩家姓名和分数
			 */
			if((i+1)%2!=0) {
				g.drawString(s, word_x+230, (word_y+i*30));
			}else {
				g.drawString(s, word_x, (word_y+(i-1)*30));
			}
			
		}
		/**
		 * 画出玩家得分
		 */
		g.drawString(Integer.toString(playerGrade), 580, 745);
		/**
		 * 画出玩家姓名
		 */
		g.drawString(playerName, 580, 785);
	}

	/**
	 * 计分板的按键总控
	 * @param e
	 */
	public void controller(KeyEvent e) {
		/**
		 * 如果玩家输入完名字就进行按钮选择的按键监控
		 */
		if (State == "Stop") {
			buttonChooseControl(e);
		} else {
			enterPlayerName(e);
		}
	}

	/**
	 * 选择按钮的按键监控
	 * @param e
	 */
	public void buttonChooseControl(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_A) {
			Tank_x = Tank_x - 449;
			if (Tank_y == 749) {
				Tank_y = 821;
			}
			if (Tank_x < 183) {
				Tank_x = 632;
			}
		} else if (keyCode == KeyEvent.VK_D) {
			Tank_x = Tank_x + 449;
			if (Tank_y == 749) {
				Tank_y = 821;
			}
			if (Tank_x > 632) {
				Tank_x = 183;
			}
		} else if (keyCode == KeyEvent.VK_W) {
			Tank_x = 183;
			Tank_y = Tank_y - 72;
			if (Tank_y < 749) {
				Tank_y = 821;
			}
		} else if (keyCode == KeyEvent.VK_S) {
			Tank_x = 183;
			Tank_y = Tank_y + 72;
			if (Tank_y > 821) {
				Tank_y = 749;
			}
		}
		if (keyCode == KeyEvent.VK_ENTER && Tank_x == 183 && Tank_y == 749) {
			/**
			 *  输入姓名
			 */
			State = "EnterName";
		} else if (keyCode == KeyEvent.VK_ENTER && Tank_x == 183 && Tank_y == 821) {
			/**
			 *  重新开始
			 */
			GameState = GAME_START;
			/**
			 * 如果玩家输入的姓名不为空，就将玩家的姓名和得分记录进数据库
			 */
			if(playerName!="") {
				MySqlTool mysqlTool = new MySqlTool();
				mysqlTool.writeGrade(playerName, playerGrade);
			}
			
		} else if (keyCode == KeyEvent.VK_ENTER && Tank_x == 632 && Tank_y == 821) {
			/**
			 *  返回菜单
			 */
			GameState = GAME_MENU;
			/**
			 * 如果玩家输入的姓名不为空，就将玩家的姓名和得分记录进数据库
			 */
			if(playerName!="") {
				MySqlTool mysqlTool = new MySqlTool();
				mysqlTool.writeGrade(playerName, playerGrade);
			}
		}
	}

	/**
	 * 监听玩家输入姓名时的按键
	 * @param e
	 */
	public void enterPlayerName(KeyEvent e) {
		/**
		 * 将输入的按键转化为字符串
		 */
		playerName = playerName + String.valueOf(e.getKeyChar());
		/**
		 * 按回车结束输入姓名
		 */
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			State = "Stop";
		}
		//System.out.println(playerName);
	}

	public int getPlayerGrade() {
		return playerGrade;
	}

	public void setPlayerGrade(int playerGrade) {
		this.playerGrade = playerGrade;
	}

	public int getGameState() {
		return GameState;
	}

	public void setGameState(int gameState) {
		GameState = gameState;
	}

	public BufferedImage getRecordGrade() {
		return recordGrade;
	}

	public void setRecordGrade(BufferedImage recordGrade) {
		this.recordGrade = recordGrade;
	}

}
