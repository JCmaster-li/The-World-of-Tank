package com.ljc.TankWorld.objects;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.ljc.TankWorld.tools.AudioPlay;
import com.ljc.TankWorld.tools.CollisionChecking;

import static com.ljc.TankWorld.constant.Constant.*;
/**
 * �����
 * @author JCؼ����
 *
 */
public class Player extends Tank {
	/**
	 * ��Ϸ������״̬
	 */
	private int GameState = GAME_RUN;
	/**
	 * Ѫ������
	 */
	private List<BloodBag> bBag = new ArrayList<BloodBag>();

	public Player(int x, int y, int HealthPoint, int Hurt, String Face, BufferedImage img) {
		super(x, y, HealthPoint, Hurt, Face, img);
	}

	/**
	 * ��ҵ��ƶ�����
	 * @param e
	 */
	public void moveController(KeyEvent e) {
		int keyCode = e.getKeyCode();
		super.setTank_Before_x(super.getTank_After_x());
		super.setTank_Before_y(super.getTank_After_y());
		if (keyCode == KeyEvent.VK_W) {
			playAnim(PLAYER_UP);
			super.setFace("Up");
			if (super.getTank_Before_y() - super.getSpeed() <= 19) {
				return;
			}
			super.setTank_After_y(super.getTank_Before_y() - super.getSpeed());
		} else if (keyCode == KeyEvent.VK_S) {
			playAnim(PLAYER_DOWN);
			super.setFace("Down");
			if (super.getTank_Before_y() + super.getSpeed() >= 924) {
				return;
			}
			super.setTank_After_y(super.getTank_Before_y() + super.getSpeed());
		} else if (keyCode == KeyEvent.VK_A) {
			playAnim(PLAYER_LEFT);
			super.setFace("Left");
			if (super.getTank_Before_x() - super.getSpeed() <= 93) {
				return;
			}
			super.setTank_After_x(super.getTank_Before_x() - super.getSpeed());
		} else if (keyCode == KeyEvent.VK_D) {
			playAnim(PLAYER_RIGHT);
			super.setFace("Right");
			if (super.getTank_Before_x() + super.getSpeed() >= 999) {
				return;
			}
			super.setTank_After_x(super.getTank_Before_x() + super.getSpeed());
		} else if (keyCode == KeyEvent.VK_E) {
			if (bBag.size() == 0) {
				createBloodB();
			}
		}
		if (keyCode == KeyEvent.VK_SPACE) {
			super.Shoot();
		}
		if (keyCode == KeyEvent.VK_ESCAPE) {
			GameState = GAME_MENU;
		}

	}

	/**
	 * ����Ѫ��
	 */
	public void createBloodB() {
		BloodBag b = new BloodBag();
		bBag.add(b);
	}

	/**
	 * �ж�����Ƿ�Ե�Ѫ��
	 * ����Ե�Ѫ�������Ѫ��+1
	 * ���ҽ���һ�����ĸ��������ӵ������⹥��
	 */
	public void isTouchBloodB() {
		for (int i = 0; i < bBag.size(); i++) {
			BloodBag b = bBag.get(i);
			if (CollisionChecking.isTouching(super.getTank_After_x(), super.getTank_After_y(),
					super.getImg().getWidth(), super.getImg().getHeight(), b.getBloodBagX(), b.getBloodBagY(),
					b.getBloodBagWidth(), b.getBloodBagHeight())) {
				bBag.remove(b);
				super.setHealthPoint(super.getHealthPoint()+2);
				super.specialShoot();
				return;
			}
		}
	}
	
	
	/**
	 * ������ͼ�ϵ�Ѫ��
	 * @param g
	 */
	public void drawBloodB(Graphics g) {
		for (int i = 0; i < bBag.size(); i++) {
			BloodBag b = bBag.get(i);
			g.drawImage(b.getImg(), b.getBloodBagX(),b.getBloodBagY(), null);
		}
	}

	/**
	 * �л�̹�˵�ͼƬ
	 */
	public void playAnim(BufferedImage img) {
		super.setImg(img);
	}

	/**
	 * ����̹��
	 */
	public void drawTank(Graphics g) {

		g.drawImage(super.getImg(), super.getTank_After_x(), super.getTank_After_y(), null);
	}

	
	public int getGameState() {
		return GameState;
	}

	public void setGameState(int gameState) {
		GameState = gameState;
	}

	public List<BloodBag> getbBag() {
		return bBag;
	}

	public void setbBag(List<BloodBag> bBag) {
		this.bBag = bBag;
	}

}
