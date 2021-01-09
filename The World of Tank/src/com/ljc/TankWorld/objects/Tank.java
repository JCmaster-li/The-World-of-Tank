package com.ljc.TankWorld.objects;

import static com.ljc.TankWorld.constant.Constant.TANK_SPEED;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.ljc.TankWorld.tools.AudioPlay;
import com.ljc.TankWorld.tools.CollisionChecking;
import com.ljc.TankWorld.tools.PictureTool;
import com.ljc.TankWorld.tools.PlayAnim;

/**
 * ̹���ࣨ���ࣩ
 * @author JCؼ����
 *
 */
public abstract class Tank {
	/**
	 *  ̹�˵�ͼƬ
	 */
	private BufferedImage img;

	/**
	 *  ̹���ƶ����x������
	 */
	private int Tank_After_x;
	/**
	 * ̹���ƶ����y������
	 */
	private int Tank_After_y;

	/**
	 * ̹���ƶ�ǰ��x������
	 */
	private int Tank_Before_x;
	/**
	 * ̹���ƶ�ǰ��y������
	 */
	private int Tank_Before_y;

	/**
	 * ̹�˵ĳ���
	 */
	private String Face;
	/**
	 *  ̹�˵��ƶ��ٶ�
	 */
	private int Speed = TANK_SPEED;

	/**
	 *  ̹�˵�Ѫ��
	 */
	private int HealthPoint;

	/**
	 *  ̹�˵��ӵ���
	 */
	private List<Bullet> bullets = new ArrayList<Bullet>();

	/**
	 *  ̹�˵��˺�
	 */
	private int Hurt;

	/**
	 * ������ȴʱ��,0.5��
	 */
	private long fireStartTime;
	private long fireCD=500;
	
	/**
	 * ���ű�ը�����ı�������
	 */
	private PlayAnim playAnim=new PlayAnim();
	private BufferedImage explode[]= {PictureTool.getImg("/com/ljc/TankWorld/picture/Explode2.png"),PictureTool.getImg("/com/ljc/TankWorld/picture/Explode2.png")};
	public Tank(int Tank_After_x, int Tank_After_y, int HealthPoint, int Hurt, String Face, BufferedImage img) {
		this.Tank_After_x = Tank_After_x;
		this.Tank_After_y = Tank_After_y;
		this.HealthPoint = HealthPoint;
		this.Hurt = Hurt;
		this.Face = Face;
		this.img = img;
		this.fireStartTime=System.currentTimeMillis();
	}

	/**
	 * ����̹�˺�̹�˵��ӵ�
	 * @param g
	 */
	public void draw(Graphics g) {
		drawTank(g);
		drawBullets(g);
	}

	/**
	 * ����̹�˵ĳ�����
	 * @param g
	 */
	public abstract void drawTank(Graphics g);

	/**
	 * ����̹�˷�����ӵ������һ���ʧЧ���ӵ�
	 * @param g
	 */
	public void drawBullets(Graphics g) {
		for (int i = 0; i < bullets.size(); i++) {
			Bullet bullet = bullets.get(i);
			bullet.draw(g);
		}
		for (int i = 0; i < bullets.size(); i++) {
			Bullet bullet = bullets.get(i);
			if (bullet.getIsTouched()) {
				/**
				 * �ӵ�����ʱ���Ż�����Ч
				 */
				new Thread() {
					public void run() {
						AudioPlay.audioPlay("/com/ljc/TankWorld/Audio/Hit.wav","Start");
					}
				}.start();
				/**
				 * ���Ż��ж���
				 */
				playAnim.setAnimX(bullet.getBullet_x()-32);
				playAnim.setAnimY(bullet.getBullet_y()-32);
				playAnim.setImg(explode);
				playAnim.playAnim(g);
				bullets.remove(i);
			}
		}
	}

	/**
	 * ̹�˵Ŀ��𷽷�
	 */
	public void Shoot() {
		/**
		 * �����ȴ������̹�˿ɿ���
		 */
		if(System.currentTimeMillis()-fireStartTime>fireCD) {
			new Thread() {
				public void run() {
					AudioPlay.audioPlay("/com/ljc/TankWorld/Audio/Fire.wav","Start");
				}
			}.start();
			Bullet bullet = new Bullet(Tank_After_x + 20, Tank_After_y + 20, Face, Hurt);
			bullets.add(bullet);
			fireStartTime=System.currentTimeMillis();
		}
		
	}

	/**
	 * ��Ҽ�Ѫ��������⿪�𷽷�
	 */
	public void specialShoot() {
		Bullet a = new Bullet(Tank_After_x, Tank_After_y, "Up", 1);
		Bullet b = new Bullet(Tank_After_x, Tank_After_y, "Down", 1);
		Bullet c = new Bullet(Tank_After_x, Tank_After_y, "Left", 1);
		Bullet d = new Bullet(Tank_After_x, Tank_After_y, "Right", 1);
		bullets.add(a);
		bullets.add(b);
		bullets.add(c);
		bullets.add(d);
		/**
		 * ���⿪�����ʱ���Ż�õ��ߵ���Ч
		 */
		new Thread() {
			public void run() {
				AudioPlay.audioPlay("/com/ljc/TankWorld/Audio/GetBonus.wav","Start");
			}
		}.start();
	}

	/**
	 * �л�̹��ͼƬ�ĳ�����
	 * @param img
	 */
	public abstract void playAnim(BufferedImage img);

	/**
	 * ��⵱ǰ̹�˺�����̹�˵���ײ
	 * @param t
	 * @return
	 */
	public Boolean isTouchingTank(Tank t) {
		if (CollisionChecking.isTouching(Tank_After_x, Tank_After_y, img.getWidth(), img.getHeight(),
				t.getTank_After_x(), t.getTank_After_y(), t.getImg().getWidth(), t.getImg().getHeight())) {
			back();
			return true;
		}
		return false;

	}

	/**
	 * ̹�˺͵�ͼ�����ײ
	 * @param m
	 */
	public void isTouchingMap(Map m) {
		for (TileMap tile : m.getTiles()) {
			if (CollisionChecking.isTouching(Tank_After_x, Tank_After_y, img.getWidth(), img.getHeight(),
					tile.getTile_x(), tile.getTile_y(), tile.getTile_width(), tile.getTile_width())) {
				back();
			}
		}

	}

	/**
	 * ̹�˷�����ײʱ����̹��λ��
	 */
	public void back() {
		Tank_After_x = Tank_Before_x;
		Tank_After_y = Tank_Before_y;
	}

	
	
	public BufferedImage getImg() {
		return img;
	}

	public void setImg(BufferedImage img) {
		this.img = img;
	}

	public int getTank_After_x() {
		return Tank_After_x;
	}

	public void setTank_After_x(int tank_After_x) {
		Tank_After_x = tank_After_x;
	}

	public int getTank_After_y() {
		return Tank_After_y;
	}

	public void setTank_After_y(int tank_After_y) {
		Tank_After_y = tank_After_y;
	}

	public int getTank_Before_x() {
		return Tank_Before_x;
	}

	public void setTank_Before_x(int tank_Before_x) {
		Tank_Before_x = tank_Before_x;
	}

	public int getTank_Before_y() {
		return Tank_Before_y;
	}

	public void setTank_Before_y(int tank_Before_y) {
		Tank_Before_y = tank_Before_y;
	}

	public String getFace() {
		return Face;
	}

	public void setFace(String face) {
		Face = face;
	}

	public int getHealthPoint() {
		return HealthPoint;
	}

	public void setHealthPoint(int healthPoint) {
		HealthPoint = healthPoint;
	}

	public int getSpeed() {
		return Speed;
	}

	public void setSpeed(int speed) {
		Speed = speed;
	}

	public List<Bullet> getBullets() {
		return bullets;
	}

	public void setBullets(List<Bullet> bullets) {
		this.bullets = bullets;
	}

}
