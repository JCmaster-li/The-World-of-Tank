package com.ljc.TankWorld.Levels;

import static com.ljc.TankWorld.constant.Constant.*;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import com.ljc.TankWorld.GUI.RecordGrade;
import com.ljc.TankWorld.objects.Bullet;
import com.ljc.TankWorld.objects.Enemy;
import com.ljc.TankWorld.objects.Map;
import com.ljc.TankWorld.objects.Player;
import com.ljc.TankWorld.tools.AudioPlay;

public class Level{
	
	/**
	 * 记录玩家的成绩
	 */
	private int Grade = 0;
	
	/**
	 * 记录玩家的杀敌数
	 */
	private int EnemyNum = 0;
	
	/**
	 * 记录玩家剩余的生命值
	 */
	private int PlayerLife;
	
	/**
	 * 屏幕中最多可存在的敌人数
	 */
	private int EXIST_ENEMYNUM_MAX = 6;
	
	/**
	 * 记录杀敌数文字的x轴位置
	 */
	private int EnemyNum_X = 1120;
	
	/**
	 * 记录杀敌数文字的y轴位置
	 */
	private int EnemyNum_Y = 79;
	
	/**
	 * 记录玩家剩余生命值文字的x轴位置
	 */
	private int LifeNum_X = 1120;
	
	/**
	 * 记录玩家剩余生命值文字的y轴位置
	 */
	private int LifeNum_Y = 639;
	
	/**
	 * 实例化玩家对象
	 */
	private Player player = new Player(542, 924, 10, 1, "UP", PLAYER_UP);
	
	/**
	 * 实例化敌人集合
	 */
	private List<Enemy> enemys = new ArrayList<Enemy>();
	
	/**
	 * 实例化地图
	 */
	private Map map = new Map();
	
	/**
	 * 实例化游戏结束时的计分板
	 */
	private RecordGrade recordG=new RecordGrade();
	
	public Level(int PlayerLife) {
		this.PlayerLife = PlayerLife;
		runLevel();
		physical();
	}

	/**
	 * 画出玩家对抗时的所有内容
	 * @param g
	 */
	public void drawMap(Graphics g) {
		/**
		 * 画背景图
		 */
		g.drawImage(MAPBACKGROUND, 0, 0, null);
		/**
		 * 画玩家生命数的图片
		 */
		g.drawImage(LIFE_NUM, LifeNum_X, LifeNum_Y, null);
		/**
		 * 画敌人生命数的图片
		 */
		g.drawImage(ENEMY_NUM, EnemyNum_X, EnemyNum_Y, null);
		/**
		 * 设置文字字体
		 */
		g.setFont(FONT);
		/**
		 * 设置画笔颜色
		 */
		g.setColor(Color.WHITE);
		/**
		 * 画成绩标签
		 */
		g.drawString("Grade:" + Grade, 1100, 400);
		/**
		 * 画敌人数量
		 */
		g.drawString("X" + EnemyNum, 1155, 106);
		/**
		 * 画玩家生命数
		 */
		g.drawString("X" + (PlayerLife = player.getHealthPoint() / 2), 1155, 666);
		/**
		 * 如果玩家生命值大于0就画出所有坦克
		 */
		if (PlayerLife >0) {
			drawAllTank(g);
		}
		/**
		 * 画地图
		 */
		map.drawMap(g);
		/**
		 * 画玩家的血包
		 */
		player.drawBloodB(g);
		/**
		 * 将玩家的得分传给计分板
		 */
		recordG.setPlayerGrade(Grade);
	}

	/**
	 * 画出所有的坦克
	 * @param g
	 */
	public void drawAllTank(Graphics g) {
		/**
		 * 画玩家坦克
		 */
		player.draw(g);
		/**
		 * 画敌人坦克
		 */
		drawEnemyTank(g);
	}

	/**
	 * 画出敌人坦克
	 * @param g
	 */
	public void drawEnemyTank(Graphics g) {
		for (int i = 0; i < enemys.size(); i++) {
			Enemy e = enemys.get(i);
			/**
			 * 如果画出来的敌人生命值小于1就将敌人从敌人集合里移出
			 */
			if (e.getHealthPoint() < 1) {
				/**
				 * 移出敌人
				 */
				enemys.remove(i);
				/**
				 * 玩家的杀敌数+1
				 */
				EnemyNum++;
				/**
				 * 玩家得分+20
				 */
				Grade = Grade + 20;
			}
			/**
			 * 画敌人
			 */
			e.draw(g);
		}
	}

	/**
	 * 单独开启一个线程来生成敌人
	 */
	public void runLevel() {
		new Thread() {
			@Override
			public void run() {
				/**
				 * 如果玩家生命值大于0就不断生成敌人
				 */
				while (PlayerLife > 0) {
					/**
					 * 屏幕中敌人的数量小于可存在的最大敌人数时生成敌人
					 */
					if (enemys.size() < EXIST_ENEMYNUM_MAX) {
						Enemy enemy = Enemy.createTank();
						enemys.add(enemy);
					}
					try {
						/**
						 * 每2秒生成一个敌人
						 */
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

	/**
	 * 判断子弹和地图、玩家、敌人之间的碰撞
	 */
	public void bulletCollideTank() {
		for (int i = 0; i < player.getBullets().size(); i++) {
			Bullet b = player.getBullets().get(i);
			/**
			 * 如果玩家的子弹没有撞击地图就判断是否撞击敌人
			 */
			if (!b.isTouchingMap(map)) {
				b.isTouchingEnemy(enemys);
				i++;
			}
			
			i++;
		}
		for (int i = 0; i < enemys.size(); i++) {
			Enemy e = enemys.get(i);
			for (int j = 0; j < e.getBullets().size(); j++) {
				Bullet b = e.getBullets().get(j);
				/**
				 * 如果敌人的子弹没有撞击地图就判断是否撞击玩家
				 */
				if (!b.isTouchingMap(map)) {
					if (b.isTouchingPlayer(player)) {
						player.setHealthPoint(player.getHealthPoint() - 1);
						j++;
					}
				}
				j++;
			}
		}
	}

	/**
	 * 判断玩家坦克是否和敌人的坦克进行碰撞
	 */
	public void enemyCollidePlayer() {
		for (int i = 0; i < enemys.size(); i++) {
			Enemy e = enemys.get(i);
			/**
			 * 如果传入的敌人刚好被玩家击杀就跳过此次检测
			 */
			if (e == null) {
				continue;
			}
			if (e.isTouchingTank(player)) {
				player.back();
			}
			e.isTouchingMap(map);
			player.isTouchingMap(map);
		}
	}

	/**
	 * 单独开启一个线程进行碰撞的检测
	 */
	public void physical() {
//		System.out.println("创建成功");
		new Thread() {
			@Override
			public void run() {
				/**
				 * 如果玩家的生命值大于0就进行检测
				 */
				while (PlayerLife > 0) {
					bulletCollideTank();
					enemyCollidePlayer();
					player.isTouchBloodB();
					try {
						Thread.sleep(15);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

	public RecordGrade getRecordG() {
		return recordG;
	}

	public void setRecordG(RecordGrade recordG) {
		this.recordG = recordG;
	}

	public int getGrade() {
		return Grade;
	}

	public void setGrade(int grade) {
		Grade = grade;
	}

	public int getEnemyNum() {
		return EnemyNum;
	}

	public void setEnemyNum(int enemyNum) {
		EnemyNum = enemyNum;
	}

	public int getPlayerLife() {
		return PlayerLife;
	}

	public void setPlayerLife(int playerLife) {
		PlayerLife = playerLife;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public List<Enemy> getEnemys() {
		return enemys;
	}

	public void setEnemys(List<Enemy> enemys) {
		this.enemys = enemys;
	}
}
