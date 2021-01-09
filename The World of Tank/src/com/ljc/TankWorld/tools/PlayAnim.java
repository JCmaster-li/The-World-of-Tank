package com.ljc.TankWorld.tools;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
/**
 * 播放动画的工具类
 * @author JC丶本尊
 *
 */
public class PlayAnim {
	/**
	 * 帧动画的图片组
	 */
	private BufferedImage img[];
	/**
	 * 动画的x轴坐标
	 */
	private int AnimX;
	/**
	 * 动画的y轴坐标
	 */
	private int AnimY;
	public PlayAnim() {}
	public PlayAnim(int x,int y,BufferedImage img[]) {
		this.AnimX=x;
		this.AnimY=y;
		this.img=img;
	}
	
	/**
	 * 创建一个单独的线程去播放动画
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
