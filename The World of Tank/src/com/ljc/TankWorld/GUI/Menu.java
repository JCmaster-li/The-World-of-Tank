package com.ljc.TankWorld.GUI;

import static com.ljc.TankWorld.constant.Constant.*;

import java.awt.Color;
import java.awt.Graphics;

public class Menu {
	
	/**
	 * 坦克图标的x轴位置
	 */
	private int TankX=516;
	
	/**
	 * 坦克图标的y轴位置
	 */
	private int TankY=540;
	
	/**
	 * 菜单界面里文字的x轴位置
	 */
	private int wordX=WORDS_X;
	
	/**
	 * 菜单界面里文字的y轴位置
	 */
	private int wordY=WORDS_Y;
	public Menu() {
		
	}
	/**
	 * 绘制菜单的内容
	 * @param g
	 */
	public void drawMenu(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		g.drawImage(MENUBACKGROUND, 0, 0, null);
		g.setColor(Color.WHITE);
		g.setFont(FONT);
		for(int i=0;i<WORDS.length;i++) {
			g.drawString(WORDS[i], wordX, wordY+(i*LINESPACE));
		}
		g.drawImage(PLAYER_RIGHT, TankX, TankY, null);
	}
	public int getTankX() {
		return TankX;
	}
	public void setTankX(int tankX) {
		TankX = tankX;
	}
	public int getTankY() {
		return TankY;
	}
	public void setTankY(int tankY) {
		TankY = tankY;
	}	
}
