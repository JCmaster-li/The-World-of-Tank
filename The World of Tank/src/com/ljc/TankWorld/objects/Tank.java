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
 * 坦克类（父类）
 * @author JC丶本尊
 *
 */
public abstract class Tank {
	/**
	 *  坦克的图片
	 */
	private BufferedImage img;

	/**
	 *  坦克移动后的x轴坐标
	 */
	private int Tank_After_x;
	/**
	 * 坦克移动后的y轴坐标
	 */
	private int Tank_After_y;

	/**
	 * 坦克移动前的x轴坐标
	 */
	private int Tank_Before_x;
	/**
	 * 坦克移动前的y轴坐标
	 */
	private int Tank_Before_y;

	/**
	 * 坦克的朝向
	 */
	private String Face;
	/**
	 *  坦克的移动速度
	 */
	private int Speed = TANK_SPEED;

	/**
	 *  坦克的血量
	 */
	private int HealthPoint;

	/**
	 *  坦克的子弹库
	 */
	private List<Bullet> bullets = new ArrayList<Bullet>();

	/**
	 *  坦克的伤害
	 */
	private int Hurt;

	/**
	 * 开火冷却时间,0.5秒
	 */
	private long fireStartTime;
	private long fireCD=500;
	
	/**
	 * 播放爆炸动画的变量定义
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
	 * 绘制坦克和坦克的子弹
	 * @param g
	 */
	public void draw(Graphics g) {
		drawTank(g);
		drawBullets(g);
	}

	/**
	 * 绘制坦克的抽象类
	 * @param g
	 */
	public abstract void drawTank(Graphics g);

	/**
	 * 绘制坦克发射的子弹，并且回收失效的子弹
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
				 * 子弹销毁时播放击中音效
				 */
				new Thread() {
					public void run() {
						AudioPlay.audioPlay("/com/ljc/TankWorld/Audio/Hit.wav","Start");
					}
				}.start();
				/**
				 * 播放击中动画
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
	 * 坦克的开火方法
	 */
	public void Shoot() {
		/**
		 * 如果冷却结束，坦克可开火
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
	 * 玩家捡到血包后的特殊开火方法
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
		 * 特殊开火调用时播放获得道具的音效
		 */
		new Thread() {
			public void run() {
				AudioPlay.audioPlay("/com/ljc/TankWorld/Audio/GetBonus.wav","Start");
			}
		}.start();
	}

	/**
	 * 切换坦克图片的抽象类
	 * @param img
	 */
	public abstract void playAnim(BufferedImage img);

	/**
	 * 检测当前坦克和其他坦克的碰撞
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
	 * 坦克和地图块的碰撞
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
	 * 坦克发生碰撞时重置坦克位置
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
