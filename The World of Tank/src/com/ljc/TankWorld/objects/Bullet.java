package com.ljc.TankWorld.objects;

import static com.ljc.TankWorld.constant.Constant.*;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;

import com.ljc.TankWorld.tools.CollisionChecking;
/**
 * 子弹类
 * @author JC丶本尊
 *
 */
public class Bullet {
	/**
	 *  子弹的图片
	 */
	private BufferedImage img;

	/**
	 * 子弹的x轴坐标
	 */
	private int Bullet_x;
	/**
	 * 子弹的y轴坐标
	 */
	private int Bullet_y;

	/**
	 * 子弹的朝向
	 */
	private String Face = "Up";

	/**
	 *  子弹的速度
	 */
	private int Speed = 2 * TANK_SPEED;
	/**
	 * 子弹的伤害
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
	 * 子弹的移动
	 */
	public void moveController() {
		/**
		 * 如果子弹的朝向向上，子弹的y轴坐标就自减
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
		 * 如果子弹的朝向向下，子弹的y轴坐标就自增
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
		 * 如果子弹的朝向向右，子弹的x轴坐标就自增
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
		 * 如果子弹的朝向向左，子弹的x轴坐标就自减
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
	 * 子弹的移动逻辑
	 */
	public void logic() {
		moveController();
	}

	/**
	 * 画出子弹，同时调用子弹的逻辑方法
	 * @param g
	 */
	public void draw(Graphics g) {
		logic();
		g.drawImage(img, Bullet_x, Bullet_y, null);
	}

	/**
	 * 子弹和敌人坦克的碰撞判断
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
	 * 子弹和玩家坦克的碰撞判断
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
	 * 子弹和地图块的碰撞判断
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
