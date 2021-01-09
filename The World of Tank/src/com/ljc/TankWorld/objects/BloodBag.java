package com.ljc.TankWorld.objects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.ljc.TankWorld.tools.PictureTool;
import com.ljc.TankWorld.tools.RandomNumber;
/**
 * Ѫ����
 * @author JCؼ����
 *
 */
public class BloodBag {
	/**
	 * Ѫ����ͼƬ
	 */
	private BufferedImage img;
	
	/**
	 * Ѫ����x��λ��
	 */
	private int BloodBagX;
	
	/**
	 * Ѫ����y��λ��
	 */
	private int BloodBagY;
	
	/**
	 * Ѫ��ͼƬ�Ŀ��
	 */
	private int BloodBagWidth;
	
	/**
	 * Ѫ��ͼƬ�ĸ߶�
	 */
	private int BloodBagHeight;

	public BloodBag() {
		createBloodB();
	}
	
	/**
	 * ����Ѫ��ͬʱ��ʼ��Ѫ���ĸ�������
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
