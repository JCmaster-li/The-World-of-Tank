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
	 * ��ʼ���Ʒְ��״̬
	 */
	private int GameState = GAME_STOP;
	
	/**
	 * ��ʼ����ҵ÷�
	 */
	private int playerGrade = 0;
	
	/**
	 * �Ʒְ���̹��ͼ���x��λ��
	 */
	private int Tank_x = 183;
	
	/**
	 * �Ʒְ���̹��ͼ���y��λ��
	 */
	private int Tank_y = 821;
	
	/**
	 * �Ʒְ���ǰ�������ֵ�x��λ��
	 */
	private int word_x=286;
	
	/**
	 * �Ʒְ���ǰ�������ֵ�y��λ��
	 */
	private int word_y=200;
	
	/**
	 * ����������ַ�������
	 */
	private String playerName = "";
	
	/**
	 * �Ʒְ屳��ͼƬ
	 */
	private BufferedImage recordGrade = PictureTool.getImg("/com/ljc/TankWorld/picture/RecordGrade.png");
	
	/**
	 * ��ʼ���������������״̬
	 */
	private String State = "Stop";
	
	/**
	 * �Ʒְ�ǰ����������ͷ������ַ�������
	 */
	private List<String> message=new ArrayList<String>();
	public RecordGrade() {
		
	}
	
	/**
	 * �����Ʒְ��е���������
	 * @param g
	 */
	public void drawGrade(Graphics g) {
		/**
		 * �Ʒְ屳��ͼ
		 */
		g.drawImage(recordGrade, 0, 0, null);
		/**
		 * ̹�˱�־
		 */
		g.drawImage(PLAYER_RIGHT, Tank_x, Tank_y, null);
		/**
		 * ������ɫ
		 */
		g.setColor(Color.WHITE);
		/**
		 * ��������
		 */
		g.setFont(new Font("Press Start 2P", Font.BOLD, 40));
		/**
		 * ʵ�������ݿ⹤��
		 */
		MySqlTool mysqlTool = new MySqlTool();
		/**
		 * �����ݿ��ȡǰ������ҵ������ͷ���
		 */
		message=mysqlTool.readGrade();
		for(int i=0;i<message.size();i++) {
			String s=message.get(i);
			/**
			 * ������߷�
			 */
			g.drawString(message.get(0), word_x+400, word_y-70);
			/**
			 * ����ǰ������������ͷ���
			 */
			if((i+1)%2!=0) {
				g.drawString(s, word_x+230, (word_y+i*30));
			}else {
				g.drawString(s, word_x, (word_y+(i-1)*30));
			}
			
		}
		/**
		 * ������ҵ÷�
		 */
		g.drawString(Integer.toString(playerGrade), 580, 745);
		/**
		 * �����������
		 */
		g.drawString(playerName, 580, 785);
	}

	/**
	 * �Ʒְ�İ����ܿ�
	 * @param e
	 */
	public void controller(KeyEvent e) {
		/**
		 * ���������������־ͽ��а�ťѡ��İ������
		 */
		if (State == "Stop") {
			buttonChooseControl(e);
		} else {
			enterPlayerName(e);
		}
	}

	/**
	 * ѡ��ť�İ������
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
			 *  ��������
			 */
			State = "EnterName";
		} else if (keyCode == KeyEvent.VK_ENTER && Tank_x == 183 && Tank_y == 821) {
			/**
			 *  ���¿�ʼ
			 */
			GameState = GAME_START;
			/**
			 * �����������������Ϊ�գ��ͽ���ҵ������͵÷ּ�¼�����ݿ�
			 */
			if(playerName!="") {
				MySqlTool mysqlTool = new MySqlTool();
				mysqlTool.writeGrade(playerName, playerGrade);
			}
			
		} else if (keyCode == KeyEvent.VK_ENTER && Tank_x == 632 && Tank_y == 821) {
			/**
			 *  ���ز˵�
			 */
			GameState = GAME_MENU;
			/**
			 * �����������������Ϊ�գ��ͽ���ҵ������͵÷ּ�¼�����ݿ�
			 */
			if(playerName!="") {
				MySqlTool mysqlTool = new MySqlTool();
				mysqlTool.writeGrade(playerName, playerGrade);
			}
		}
	}

	/**
	 * ���������������ʱ�İ���
	 * @param e
	 */
	public void enterPlayerName(KeyEvent e) {
		/**
		 * ������İ���ת��Ϊ�ַ���
		 */
		playerName = playerName + String.valueOf(e.getKeyChar());
		/**
		 * ���س�������������
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
