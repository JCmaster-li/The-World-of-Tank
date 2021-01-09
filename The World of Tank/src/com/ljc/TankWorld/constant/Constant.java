package com.ljc.TankWorld.constant;

import java.awt.Font;
import java.awt.image.BufferedImage;

import com.ljc.TankWorld.tools.PictureTool;

public class Constant {
	/****************������س���********************/
	//��Ϸ����
	public static final String FRAME_TITLE="̹������";
	
	// ����Ŀ�͸�
	public static final int FRAME_WIDTH=1340;
	public static final int FRAME_HEIGHT=1030;
	
	//��Ϸ״̬
	public static final int GAME_MENU=1;
	public static final int GAME_START=2;
	public static final int GAME_RUN=3;
	public static final int GAME_STOP=4;
	public static final int GAME_OVER=5;
	
	/***************�˵�������س���********************/
	public static final String WORDS[]= {
			"START THE GAME",
			"QUIT GAME"
	};
	
	//����
	public static final Font FONT=new Font("Press Start 2P",Font.BOLD,30);
	//�м��
	public static final int LINESPACE=80;
	
	//������ʼ�������
	public static final int WORDS_X=597;
	public static final int WORDS_Y=580;
	/***************̹�����********************/
	//̹���ٶ�
	public static final int TANK_SPEED=8;
	
	//���̹��ͼƬ
	public static final BufferedImage PLAYER_UP=PictureTool.getImg("/com/ljc/TankWorld/picture/Player1_Up_1.png");	
	public static final BufferedImage PLAYER_DOWN=PictureTool.getImg("/com/ljc/TankWorld/picture/Player1_Down_1.png");
	public static final BufferedImage PLAYER_RIGHT=PictureTool.getImg("/com/ljc/TankWorld/picture/Player1_Right_1.png");
	public static final BufferedImage PLAYER_LEFT=PictureTool.getImg("/com/ljc/TankWorld/picture/Player1_Left_1.png");
	
	//�з�̹��ͼƬ
	public static final BufferedImage ENEMY1_UP=PictureTool.getImg("/com/ljc/TankWorld/picture/Enemy_1_Up_1.png");
			
	public static final BufferedImage ENEMY1_DOWN=PictureTool.getImg("/com/ljc/TankWorld/picture/Enemy_1_Down_1.png");
	public static final BufferedImage ENEMY1_RIGHT=PictureTool.getImg("/com/ljc/TankWorld/picture/Enemy_1_Right_1.png");
	public static final BufferedImage ENEMY1_LEFT=PictureTool.getImg("/com/ljc/TankWorld/picture/Enemy_1_Left_1.png");
	
	//�ӵ�ͼƬ
	public static final BufferedImage BULLET_UP=PictureTool.getImg("/com/ljc/TankWorld/picture/Bullet_Up.png");
	public static final BufferedImage BULLET_DOWN=PictureTool.getImg("/com/ljc/TankWorld/picture/Bullet_Down.png");
	public static final BufferedImage BULLET_RIGHT=PictureTool.getImg("/com/ljc/TankWorld/picture/Bullet_Right.png");
	public static final BufferedImage BULLET_LEFT=PictureTool.getImg("/com/ljc/TankWorld/picture/Bullet_Left.png");
	
	/***************��ͼ���********************/
	//��ʼ�˵�����
	public static final BufferedImage MENUBACKGROUND=PictureTool.getImg("/com/ljc/TankWorld/picture/MenuBackground.png");
	
	//��ͼ����
	public static final BufferedImage MAPBACKGROUND=PictureTool.getImg("/com/ljc/TankWorld/picture/Background.png");	
	
	//��Ϸ����
	public static final BufferedImage GAMEOVER=PictureTool.getImg("/com/ljc/TankWorld/picture/GameOver.png");
	
	//�������ͼ��
	public static final BufferedImage LIFE_NUM=PictureTool.getImg("/com/ljc/TankWorld/picture/Life_Num.png");
		
	//��������ͼ��
	public static final BufferedImage ENEMY_NUM=PictureTool.getImg("/com/ljc/TankWorld/picture/Enemy_Num.png");
		
	//שǽ
	public static final BufferedImage BRICKWALL=PictureTool.getImg("/com/ljc/TankWorld/picture/BrickWall.png");
	
	//Ѫ��
	//public static final BufferedImage BLOODBAG=PictureTool.getImg("/com/ljc/TankWorld/picture/Life");
	/***************Ч�����********************/
	//��ը
	//public static final String EXPLODE= "/com/ljc/TankWorld/picture/Explode1.png";
}
