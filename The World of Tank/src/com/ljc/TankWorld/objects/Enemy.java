package com.ljc.TankWorld.objects;

import static com.ljc.TankWorld.constant.Constant.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

import com.ljc.TankWorld.tools.CollisionChecking;
import com.ljc.TankWorld.tools.RandomNumber;
/**
 * ������
 * @author JCؼ����
 *
 */
public class Enemy extends Tank{
	/**
	 * ���˵���Ϊģʽ
	 */
	private int moveModel;
	/**
	 * �����˵����г�������ַ�������
	 */
	private String moveface[]= {"Up","Down","Left","Right"};
	/**
	 * ���˿�ʼ�ƶ���ʱ��
	 */
	private long moveStartTime;
	/**
	 * ���˿�ʼ�����ʱ��
	 */
	private long fireStartTime;
	public Enemy(int x,int y,int HealthPoint,int Hurt,String Face,int moveModel,BufferedImage img) {
		super(x,y,HealthPoint,Hurt,Face,img);
		this.moveModel=moveModel;
		moveStartTime=System.currentTimeMillis();
		fireStartTime=System.currentTimeMillis();
		super.setSpeed(5);
	}
	/**
	 * ̹�˵��ƶ�����
	 */
	public void move() {
		if(moveModel==1) {
			aiFirst();
		}else if(moveModel==2) {
			aiSecond();
		}else if(moveModel==3) {
			aiThird();
		}
	}
	/**
	 * ��һ��AI��Ŀ��Ϊ��ҵ�ǰ������λ��ȡ���·�������ƶ����������
	 */
	public void aiFirst() {
		
	}
	
	/**
	 * �ڶ���AI��Ŀ��Ϊ��ң�ȡ���·�������ƶ��������֮��û�з����赲ʱ����
	 */
	public void aiSecond() {
		
	}
	/**
	 * ������AI�������������ƶ����������
	 */
	public void aiThird() {
		super.setTank_Before_x(super.getTank_After_x());
		super.setTank_Before_y(super.getTank_After_y());
		if(super.getFace()=="Up") {
			playAnim(ENEMY1_UP);
			super.setFace("Up");
			if(super.getTank_Before_y()-super.getSpeed()<=19) {
				super.setFace("Down");
				return;
			}
			super.setTank_After_y(super.getTank_Before_y()-super.getSpeed());
		}else if(super.getFace()=="Down") {
			playAnim(ENEMY1_DOWN);
			super.setFace("Down");
			if(super.getTank_Before_y()+super.getSpeed()>=924) {
				super.setFace("Up");
				return;
			}
			super.setTank_After_y(super.getTank_Before_y()+super.getSpeed());
		}else if(super.getFace()=="Left") {
			playAnim(ENEMY1_LEFT);
			super.setFace("Left");
			if(super.getTank_Before_x()-super.getSpeed()<=93) {
				super.setFace("Right");
				return;
			}
			super.setTank_After_x(super.getTank_Before_x()-super.getSpeed());
		}else if(super.getFace()=="Right") {
			playAnim(ENEMY1_RIGHT);
			super.setFace("Right");
			if(super.getTank_Before_x()+super.getSpeed()>=999) {
				super.setFace("Left");
				return;
			}
			super.setTank_After_x(super.getTank_Before_x()+super.getSpeed());
		}
		if(System.currentTimeMillis()-moveStartTime>2000) {
			super.setFace(moveface[RandomNumber.createRandomNumber(3, 0)]);
			moveStartTime=System.currentTimeMillis();
		}
		if(System.currentTimeMillis()-fireStartTime>2500) {
			super.Shoot();
			fireStartTime=System.currentTimeMillis();
		}
	}
	
	/**
	 * ��������̹�ˣ�������ai�н������ѡ�񴴽�
	 * @return
	 */
	public static Enemy createTank() {
//		if(1<=RandomNumber.createRandomNumber(1000,1)&&RandomNumber.createRandomNumber(1000,1)<=300) {
//			Enemy enemy=new Enemy(96,22,1,1,"Down", RandomNumber.createRandomNumber(3,1),ENEMY1_DOWN);
//			return enemy;
//		}else if(301<=RandomNumber.createRandomNumber(1000,1)&&RandomNumber.createRandomNumber(1000,1)<=600) {
//			Enemy enemy=new Enemy(532,22,1,1,"Down", RandomNumber.createRandomNumber(3,1),ENEMY1_DOWN);
//			return enemy;
//		}else {
//			Enemy enemy=new Enemy(991,22,1,1,"Down", RandomNumber.createRandomNumber(3,1),ENEMY1_DOWN);
//			return enemy;
//		}
		
		
		return new Enemy(998,20,1,1,"Down", 3,ENEMY1_DOWN);
	}
	
	/**
	 * ����̹�˵ĳ����л�̹��ͼƬ
	 */
	public void playAnim(BufferedImage img) {
		super.setImg(img);
	}
	
	/**
	 * ����̹�ˣ������ڻ�̹��ǰ����̹�˵��ƶ�����
	 */
	public void drawTank(Graphics g) {
		move();
		g.drawImage(super.getImg(), super.getTank_After_x(), super.getTank_After_y(), null);
	}	
}
