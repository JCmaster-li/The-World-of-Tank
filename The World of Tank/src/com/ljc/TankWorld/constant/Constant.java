package com.ljc.TankWorld.constant;

import java.awt.Font;
import java.awt.image.BufferedImage;

import com.ljc.TankWorld.tools.PictureTool;

public class Constant {
	/****************窗体相关常量********************/
	//游戏标题
	public static final String FRAME_TITLE="坦克世界";
	
	// 窗体的宽和高
	public static final int FRAME_WIDTH=1340;
	public static final int FRAME_HEIGHT=1030;
	
	//游戏状态
	public static final int GAME_MENU=1;
	public static final int GAME_START=2;
	public static final int GAME_RUN=3;
	public static final int GAME_STOP=4;
	public static final int GAME_OVER=5;
	
	/***************菜单界面相关常量********************/
	public static final String WORDS[]= {
			"START THE GAME",
			"QUIT GAME"
	};
	
	//字体
	public static final Font FONT=new Font("Press Start 2P",Font.BOLD,30);
	//行间距
	public static final int LINESPACE=80;
	
	//文字起始的坐标点
	public static final int WORDS_X=597;
	public static final int WORDS_Y=580;
	/***************坦克相关********************/
	//坦克速度
	public static final int TANK_SPEED=8;
	
	//玩家坦克图片
	public static final BufferedImage PLAYER_UP=PictureTool.getImg("/com/ljc/TankWorld/picture/Player1_Up_1.png");	
	public static final BufferedImage PLAYER_DOWN=PictureTool.getImg("/com/ljc/TankWorld/picture/Player1_Down_1.png");
	public static final BufferedImage PLAYER_RIGHT=PictureTool.getImg("/com/ljc/TankWorld/picture/Player1_Right_1.png");
	public static final BufferedImage PLAYER_LEFT=PictureTool.getImg("/com/ljc/TankWorld/picture/Player1_Left_1.png");
	
	//敌方坦克图片
	public static final BufferedImage ENEMY1_UP=PictureTool.getImg("/com/ljc/TankWorld/picture/Enemy_1_Up_1.png");
			
	public static final BufferedImage ENEMY1_DOWN=PictureTool.getImg("/com/ljc/TankWorld/picture/Enemy_1_Down_1.png");
	public static final BufferedImage ENEMY1_RIGHT=PictureTool.getImg("/com/ljc/TankWorld/picture/Enemy_1_Right_1.png");
	public static final BufferedImage ENEMY1_LEFT=PictureTool.getImg("/com/ljc/TankWorld/picture/Enemy_1_Left_1.png");
	
	//子弹图片
	public static final BufferedImage BULLET_UP=PictureTool.getImg("/com/ljc/TankWorld/picture/Bullet_Up.png");
	public static final BufferedImage BULLET_DOWN=PictureTool.getImg("/com/ljc/TankWorld/picture/Bullet_Down.png");
	public static final BufferedImage BULLET_RIGHT=PictureTool.getImg("/com/ljc/TankWorld/picture/Bullet_Right.png");
	public static final BufferedImage BULLET_LEFT=PictureTool.getImg("/com/ljc/TankWorld/picture/Bullet_Left.png");
	
	/***************地图相关********************/
	//开始菜单背景
	public static final BufferedImage MENUBACKGROUND=PictureTool.getImg("/com/ljc/TankWorld/picture/MenuBackground.png");
	
	//地图背景
	public static final BufferedImage MAPBACKGROUND=PictureTool.getImg("/com/ljc/TankWorld/picture/Background.png");	
	
	//游戏结束
	public static final BufferedImage GAMEOVER=PictureTool.getImg("/com/ljc/TankWorld/picture/GameOver.png");
	
	//玩家生命图标
	public static final BufferedImage LIFE_NUM=PictureTool.getImg("/com/ljc/TankWorld/picture/Life_Num.png");
		
	//敌人数量图标
	public static final BufferedImage ENEMY_NUM=PictureTool.getImg("/com/ljc/TankWorld/picture/Enemy_Num.png");
		
	//砖墙
	public static final BufferedImage BRICKWALL=PictureTool.getImg("/com/ljc/TankWorld/picture/BrickWall.png");
	
	//血包
	//public static final BufferedImage BLOODBAG=PictureTool.getImg("/com/ljc/TankWorld/picture/Life");
	/***************效果相关********************/
	//爆炸
	//public static final String EXPLODE= "/com/ljc/TankWorld/picture/Explode1.png";
}
