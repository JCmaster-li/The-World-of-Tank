package com.ljc.TankWorld.tools;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
/**
 * ���Ŷ����Ĺ�����
 * @author JCؼ����
 *
 */
public class PlayAnim {
	/**
	 * ֡������ͼƬ��
	 */
	private BufferedImage img[];
	/**
	 * ������x������
	 */
	private int AnimX;
	/**
	 * ������y������
	 */
	private int AnimY;
	public PlayAnim() {}
	public PlayAnim(int x,int y,BufferedImage img[]) {
		this.AnimX=x;
		this.AnimY=y;
		this.img=img;
	}
	
	/**
	 * ����һ���������߳�ȥ���Ŷ���
	 * @param g
	 */
	public void playAnim(Graphics g) {
		new Thread() {
			public void run() {
				for(int i=0;i<img.length;i++) {
					g.drawImage(img[i], AnimX, AnimY, null);
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}.start();
		
	}
	
	
	public BufferedImage[] getImg() {
		return img;
	}
	public void setImg(BufferedImage[] img) {
		this.img = img;
	}
	public int getAnimX() {
		return AnimX;
	}
	public void setAnimX(int animX) {
		AnimX = animX;
	}
	public int getAnimY() {
		return AnimY;
	}
	public void setAnimY(int animY) {
		AnimY = animY;
	}
	
}
