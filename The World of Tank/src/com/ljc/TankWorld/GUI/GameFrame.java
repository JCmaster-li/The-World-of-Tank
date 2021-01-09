package com.ljc.TankWorld.GUI;

import static com.ljc.TankWorld.constant.Constant.*;

import java.awt.Graphics;

import javax.swing.JFrame;

/**
 * 设置游戏窗体的属性
 * @author JC丶本尊
 *
 */
public class GameFrame extends JFrame{
	private MenuPanel menuPanel=new MenuPanel(this);
	public GameFrame() {
		initFrame();
	}
	private void initFrame() {
		setTitle(FRAME_TITLE);
		setSize(FRAME_WIDTH,FRAME_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(menuPanel);
		setVisible(true);
	}
}
