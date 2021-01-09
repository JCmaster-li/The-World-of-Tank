package com.ljc.TankWorld.objects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.ljc.TankWorld.tools.PictureTool;
import com.ljc.TankWorld.tools.RandomNumber;
/**
 * 血包类
 * @author JC丶本尊
 *
 */
public class BloodBag {
	/**
	 * 血包的图片
	 */
	private BufferedImage img;
	
	/**
	 * 血包的x轴位置
	 */
	private int BloodBagX;
	
	/**
	 * 血包的y轴位置
	 */
	private int BloodBagY;
	
	/**
	 * 血包图片的宽度
	 */
	private int BloodBagWidth;
	
	/**
	 * 血包图片的高度
	 */
	private int BloodBagHeight;

	public BloodBag() {
		createBloodB();
	}
	
	/**
	 * 生成血包同时初始化血包的各项属性
	 */
	public void createBloodB() {
		this.img = PictureTool.getImg("/com/ljc/TankWorld/picture/Life.png");
		this.BloodBagX = RandomNumber.createRandomNumber(990, 95);
		this.BloodBagY = RandomNumber.createRandomNumber(920, 22);
		this.BloodBagWidth = img.getWidth();
		this.BloodBagHeight = img.getHeight();
	}

	public int getBloodBagX() {
		return BloodBagX;
	}

	public void setBloodBagX(int bloodBagX) {
		BloodBagX = bloodBagX;
	}

	public int getBloodBagY() {
		return BloodBagY;
	}

	public void setBloodBagY(int bloodBagY) {
		BloodBagY = bloodBagY;
	}

	public int getBloodBagWidth() {
		return BloodBagWidth;
	}

	public void setBloodBagWidth(int bloodBagWidth) {
		BloodBagWidth = bloodBagWidth;
	}

	public int getBloodBagHeight() {
		return BloodBagHeight;
	}

	public void setBloodBagHeight(int bloodBagHeight) {
		BloodBagHeight = bloodBagHeight;
	}

	public BufferedImage getImg() {
		return img;
	}

	public void setImg(BufferedImage img) {
		this.img = img;
	}
	
}
