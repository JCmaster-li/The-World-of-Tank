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
	 * ��¼��ҵĳɼ�
	 */
	private int Grade = 0;
	
	/**
	 * ��¼��ҵ�ɱ����
	 */
	private int EnemyNum = 0;
	
	/**
	 * ��¼���ʣ�������ֵ
	 */
	private int PlayerLife;
	
	/**
	 * ��Ļ�����ɴ��ڵĵ�����
	 */
	private int EXIST_ENEMYNUM_MAX = 6;
	
	/**
	 * ��¼ɱ�������ֵ�x��λ��
	 */
	private int EnemyNum_X = 1120;
	
	/**
	 * ��¼ɱ�������ֵ�y��λ��
	 */
	private int EnemyNum_Y = 79;
	
	/**
	 * ��¼���ʣ������ֵ���ֵ�x��λ��
	 */
	private int LifeNum_X = 1120;
	
	/**
	 * ��¼���ʣ������ֵ���ֵ�y��λ��
	 */
	private int LifeNum_Y = 639;
	
	/**
	 * ʵ������Ҷ���
	 */
	private Player player = new Player(542, 924, 10, 1, "UP", PLAYER_UP);
	
	/**
	 * ʵ�������˼���
	 */
	private List<Enemy> enemys = new ArrayList<Enemy>();
	
	/**
	 * ʵ������ͼ
	 */
	private Map map = new Map();
	
	/**
	 * ʵ������Ϸ����ʱ�ļƷְ�
	 */
	private RecordGrade recordG=new RecordGrade();
	
	public Level(int PlayerLife) {
		this.PlayerLife = PlayerLife;
		runLevel();
		physical();
	}

	/**
	 * ������ҶԿ�ʱ����������
	 * @param g
	 */
	public void drawMap(Graphics g) {
		/**
		 * ������ͼ
		 */
		g.drawImage(MAPBACKGROUND, 0, 0, null);
		/**
		 * �������������ͼƬ
		 */
		g.drawImage(LIFE_NUM, LifeNum_X, LifeNum_Y, null);
		/**
		 * ��������������ͼƬ
		 */
		g.drawImage(ENEMY_NUM, EnemyNum_X, EnemyNum_Y, null);
		/**
		 * ������������
		 */
		g.setFont(FONT);
		/**
		 * ���û�����ɫ
		 */
		g.setColor(Color.WHITE);
		/**
		 * ���ɼ���ǩ
		 */
		g.drawString("Grade:" + Grade, 1100, 400);
		/**
		 * ����������
		 */
		g.drawString("X" + EnemyNum, 1155, 106);
		/**
		 * �����������
		 */
		g.drawString("X" + (PlayerLife = player.getHealthPoint() / 2), 1155, 666);
		/**
		 * ����������ֵ����0�ͻ�������̹��
		 */
		if (PlayerLife >0) {
			drawAllTank(g);
		}
		/**
		 * ����ͼ
		 */
		map.drawMap(g);
		/**
		 * ����ҵ�Ѫ��
		 */
		player.drawBloodB(g);
		/**
		 * ����ҵĵ÷ִ����Ʒְ�
		 */
		recordG.setPlayerGrade(Grade);
	}

	/**
	 * �������е�̹��
	 * @param g
	 */
	public void drawAllTank(Graphics g) {
		/**
		 * �����̹��
		 */
		player.draw(g);
		/**
		 * ������̹��
		 */
		drawEnemyTank(g);
	}

	/**
	 * ��������̹��
	 * @param g
	 */
	public void drawEnemyTank(Graphics g) {
		for (int i = 0; i < enemys.size(); i++) {
			Enemy e = enemys.get(i);
			/**
			 * ����������ĵ�������ֵС��1�ͽ����˴ӵ��˼������Ƴ�
			 */
			if (e.getHealthPoint() < 1) {
				/**
				 * �Ƴ�����
				 */
				enemys.remove(i);
				/**
				 * ��ҵ�ɱ����+1
				 */
				EnemyNum++;
				/**
				 * ��ҵ÷�+20
				 */
				Grade = Grade + 20;
			}
			/**
			 * ������
			 */
			e.draw(g);
		}
	}

	/**
	 * ��������һ���߳������ɵ���
	 */
	public void runLevel() {
		new Thread() {
			@Override
			public void run() {
				/**
				 * ����������ֵ����0�Ͳ������ɵ���
				 */
				while (PlayerLife > 0) {
					/**
					 * ��Ļ�е��˵�����С�ڿɴ��ڵ���������ʱ���ɵ���
					 */
					if (enemys.size() < EXIST_ENEMYNUM_MAX) {
						Enemy enemy = Enemy.createTank();
						enemys.add(enemy);
					}
					try {
						/**
						 * ÿ2������һ������
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
	 * �ж��ӵ��͵�ͼ����ҡ�����֮�����ײ
	 */
	public void bulletCollideTank() {
		for (int i = 0; i < player.getBullets().size(); i++) {
			Bullet b = player.getBullets().get(i);
			/**
			 * �����ҵ��ӵ�û��ײ����ͼ���ж��Ƿ�ײ������
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
				 * ������˵��ӵ�û��ײ����ͼ���ж��Ƿ�ײ�����
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
	 * �ж����̹���Ƿ�͵��˵�̹�˽�����ײ
	 */
	public void enemyCollidePlayer() {
		for (int i = 0; i < enemys.size(); i++) {
			Enemy e = enemys.get(i);
			/**
			 * �������ĵ��˸պñ���һ�ɱ�������˴μ��
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
	 * ��������һ���߳̽�����ײ�ļ��
	 */
	public void physical() {
//		System.out.println("�����ɹ�");
		new Thread() {
			@Override
			public void run() {
				/**
				 * �����ҵ�����ֵ����0�ͽ��м��
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
