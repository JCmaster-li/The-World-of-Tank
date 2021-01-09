package com.ljc.TankWorld.objects;

import static com.ljc.TankWorld.constant.Constant.*;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import com.ljc.TankWorld.tools.RandomNumber;
/**
 * ��ͼ��
 * @author JCؼ����
 *
 */
public class Map {
	/**
	 * ��ʼ����Ƭ��ͼ����෽����
	 */
	private static final int TILE_NUM_MAX=120;
	/**
	 * ��ʼ����Ƭ��ͼ�����ٷ�����
	 */
	private static final int TILE_NUM_MIN=70;
	/**
	 * ���嵱ǰ��ͼ����Ƭ��
	 */
	private int TILE_NUM;
	/**
	 * ��Ƭ�ļ���
	 */
	private List<TileMap> tiles=new ArrayList<TileMap>();
	/**
	 * ��Ƭ��x��y������Ķ�ά���飬��������
	 */
	private int xy[][]=new int[17][17];
	public Map() {
		TILE_NUM=RandomNumber.createRandomNumber(TILE_NUM_MAX, TILE_NUM_MIN);
		createMap();
	}
	
	/**
	 * ������ͼ
	 */
	public void createMap() {
		int x,y;
		while(tiles.size()<TILE_NUM) {
			/**
			 * ���������Ƭ��λ��
			 */
				x=RandomNumber.createRandomNumber(16, 1);
				y=RandomNumber.createRandomNumber(14, 2);
				if(xy[x][y]==0) {
					xy[x][y]++;
					TileMap m=new TileMap((30+x*64),y*64,1,BRICKWALL);
					tiles.add(m);
				}
		}
	}
	
	/**
	 * �������е���Ƭ��ͼ
	 * @param g
	 */
	public void drawMap(Graphics g) {
		for(int i=0;i<tiles.size();i++) {
			TileMap tile=tiles.get(i);
			g.drawImage(tile.getImg(), tile.getTile_x(), tile.getTile_y(), null);
		}
	}

	public List<TileMap> getTiles() {
		return tiles;
	}

	public void setTiles(List<TileMap> tiles) {
		this.tiles = tiles;
	}
	
}
