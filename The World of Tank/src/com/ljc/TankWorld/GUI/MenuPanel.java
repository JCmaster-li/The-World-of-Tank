package com.ljc.TankWorld.GUI;

import static com.ljc.TankWorld.constant.Constant.*;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.ljc.TankWorld.Levels.Level;
import com.ljc.TankWorld.tools.AudioPlay;

/**
 * 菜单容器，装载游戏画面
 * 将按键监听器传给游戏窗体
 * @author JC丶本尊
 *
 */
public class MenuPanel extends JPanel implements Runnable {
	
	//实例化菜单类
	private Menu menu = new Menu();
	private int GameState = GAME_MENU;
	private List<Level> levels = new ArrayList<Level>();
	private Boolean alreadyDraw = false;

	public MenuPanel(GameFrame gameFrame) {
		new Thread(this).start();
		AudioPlay.audioPlay("/com/ljc/TankWorld/Audio/Start.wav","Start");
		KeyAdapter k = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (GameState == GAME_MENU) {
					menuControl(e);
				}
				if (GameState == GAME_RUN) {
					for (Level l : levels)
						l.getPlayer().moveController(e);
				}
				if (GameState == GAME_STOP) {
					for (Level l : levels)
						l.getRecordG().controller(e);
				}
			}
			
		};
		gameFrame.addKeyListener(k);
	}

	/**
	 * 检测游戏状态
	 * @param g
	 */
	public void checkGameState(Graphics g) {
		if (GameState == GAME_MENU) {
			menu.drawMenu(g);
		}
		if (GameState == GAME_START) {
			alreadyDraw = false;
			Level l = new Level(5);
			levels.add(l);
			GameState = GAME_RUN;
		}
		if (GameState == GAME_RUN) {
			for (Level l : levels) {
				l.drawMap(g);
				GameState = l.getPlayer().getGameState();
				if (l.getPlayerLife() <=0) {
					new Thread() {
						public void run() {
							AudioPlay.audioPlay("/com/ljc/TankWorld/Audio/Die.wav","Start");
						}
					}.start();
					GameState = GAME_STOP;
					break;
				}
				if (GameState == GAME_MENU) {
					levels.remove(l);
					break;
				}
			}
		}
		if (GameState == GAME_STOP) {
			for (Level l : levels) {				
					l.getRecordG().drawGrade(g);
				GameState = l.getRecordG().getGameState();
				if (GameState == GAME_START) {
					levels.remove(l);
					break;
				}
				if (GameState == GAME_MENU) {
					levels.remove(l);
					break;
				}
			}
		}
	}

	/**
	 * 绘制整个游戏的画面
	 */
	public void paint(Graphics g) {
		checkGameState(g);
	}

	/**
	 * 当游戏处于菜单界面时的按键控制
	 * @param e
	 */
	public void menuControl(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_S) {
			if (menu.getTankY() + 80 > 630) {
				menu.setTankY(540);
			} else {
				menu.setTankY(menu.getTankY() + 80);
			}
		} else if (keyCode == KeyEvent.VK_W) {
			if (menu.getTankY() - 80 < 540) {
				menu.setTankY(620);
			} else {
				menu.setTankY(menu.getTankY() - 80);
			}
		} else if (keyCode == KeyEvent.VK_ENTER && menu.getTankY() == 540) {
			GameState = GAME_START;
		} else if (keyCode == KeyEvent.VK_ENTER && menu.getTankY() == 620) {
			System.exit(0);
		}
	}

	/**
	 * 开启一个线程，按一定的时间间隔重绘游戏画面
	 */
	@Override
	public void run() {
		while (true) {
			repaint();
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
