package com.ljc.TankWorld.GUI;

import static com.ljc.TankWorld.constant.Constant.*;

import java.awt.Color;
import java.awt.Graphics;

public class Menu {
	
	/**
	 * ̹��ͼ���x��λ��
	 */
	private int TankX=516;
	
	/**
	 * ̹��ͼ���y��λ��
	 */
	private int TankY=540;
	
	/**
	 * �˵����������ֵ�x��λ��
	 */
	private int wordX=WORDS_X;
	
	/**
	 * �˵����������ֵ�y��λ��
	 */
	private int wordY=WORDS_Y;
	public Menu() {
		
	}
	/**
	 * ���Ʋ˵�������
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
