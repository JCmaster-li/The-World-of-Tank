package com.ljc.TankWorld.objects;

import static com.ljc.TankWorld.constant.Constant.*;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;

import com.ljc.TankWorld.tools.CollisionChecking;
/**
 * �ӵ���
 * @author JCؼ����
 *
 */
public class Bullet {
	/**
	 *  �ӵ���ͼƬ
	 */
	private BufferedImage img;

	/**
	 * �ӵ���x������
	 */
	private int Bullet_x;
	/**
	 * �ӵ���y������
	 */
	private int Bullet_y;

	/**
	 * �ӵ��ĳ���
	 */
	private String Face = "Up";

	/**
	 *  �ӵ����ٶ�
	 */
	private int Speed = 2 * TANK_SPEED;
	/**
	 * �ӵ����˺�
	 */
	private int Hurt;

	private Boolean isTouched = false;

	public Bullet(int Bullet_x, int Bullet_y, String Face, int Hurt) {
		this.Bullet_x = Bullet_x;
		this.Bullet_y = Bullet_y;
		this.Face = Face;
		this.Hurt = Hurt;
	}

	/**
	 * �ӵ����ƶ�
	 */
	public void moveController() {
		/**
		 * ����ӵ��ĳ������ϣ��ӵ���y��������Լ�
		 */
		if (Face == "Up") {
			img = BULLET_UP;
			if (Bullet_y - Speed < 20) {
				isTouched = true;
				return;
			}
			Bullet_y = Bullet_y - Speed;
		}
		/**
		 * ����ӵ��ĳ������£��ӵ���y�����������
		 */
		else if (Face == "Down") {
			img = BULLET_DOWN;
			if (Bullet_y + Speed > 960) {
				isTouched = true;
				return;
			}
			Bullet_y = Bullet_y + Speed;
		}
		/**
		 * ����ӵ��ĳ������ң��ӵ���x�����������
		 */
		else if (Face == "Right") {
			img = BULLET_RIGHT;
			if (Bullet_x + Speed > 1040) {
				isTouched = true;
				return;
			}
			Bullet_x = Bullet_x + Speed;
		}
		/**
		 * ����ӵ��ĳ��������ӵ���x��������Լ�
		 */
		else if (Face == "Left") {
			img = BULLET_LEFT;
			if (Bullet_x - Speed < 95) {
				isTouched = true;
				return;
			}
			Bullet_x = Bullet_x - Speed;
		}

	}

	/**
	 * �ӵ����ƶ��߼�
	 */
	public void logic() {
		moveController();
	}

	/**
	 * �����ӵ���ͬʱ�����ӵ����߼�����
	 * @param g
	 */
	public void draw(Graphics g) {
		logic();
		g.drawImage(img, Bullet_x, Bullet_y, null);
	}

	/**
	 * �ӵ��͵���̹�˵���ײ�ж�
	 * @param enemys
	 * @return
	 */
	public Boolean isTouchingEnemy(List<Enemy> enemys) {
		for (Enemy enemy : enemys) {
			if (CollisionChecking.isTouching(Bullet_x, Bullet_y, 16, 16, enemy.getTank_After_x(),
					enemy.getTank_After_y(), enemy.getImg().getWidth(), enemy.getImg().getHeight())) {
				isTouched = true;
				enemy.setHealthPoint(enemy.getHealthPoint()-1);
			}
		}
		return null;

	}

	/**
	 * �ӵ������̹�˵���ײ�ж�
	 * @param player
	 * @return
	 */
	public Boolean isTouchingPlayer(Player player) {
		if (CollisionChecking.isTouching(Bullet_x, Bullet_y, 16, 16, player.getTank_After_x(), player.getTank_After_y(),
				player.getImg().getWidth(), player.getImg().getHeight())) {
			isTouched = true;
			return true;
		}
		return false;

	}
	
	/**
	 * �ӵ��͵�ͼ�����ײ�ж�
	 * @param m
	 * @return
	 */
	public Boolean isTouchingMap(Map m) {
		for(TileMap tile:m.getTiles()) {
			if(CollisionChecking.isTouching(Bullet_x, Bullet_y, 16, 16,tile.getTile_x(),tile.getTile_y(),tile.getTile_width(),tile.getTile_width())) {
				tile.setDurability(tile.getDurability()-1);
				if(tile.getDurability()<=0) {
					m.getTiles().remove(tile);
				}
				isTouched = true;
				return true;
			}
		}
		return false;
	}

	public int getBullet_x() {
		return Bullet_x;
	}

	public void setBullet_x(int bullet_x) {
		Bullet_x = bullet_x;
	}

	public int getBullet_y() {
		return Bullet_y;
	}

	public void setBullet_y(int bullet_y) {
		Bullet_y = bullet_y;
	}

	public String getFace() {
		return Face;
	}

	public void setFace(String face) {
		Face = face;
	}

	public int getHurt() {
		return Hurt;
	}

	public void setHurt(int hurt) {
		Hurt = hurt;
	}

	public Boolean getIsTouched() {
		return isTouched;
	}

	public void setIsTouched(Boolean isTouched) {
		this.isTouched = isTouched;
	}

	public BufferedImage getImg() {
		return img;
	}

	public void setImg(BufferedImage img) {
		this.img = img;
	}

}
