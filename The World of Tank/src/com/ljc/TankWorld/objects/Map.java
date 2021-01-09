package com.ljc.TankWorld.objects;

import static com.ljc.TankWorld.constant.Constant.*;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import com.ljc.TankWorld.tools.RandomNumber;
/**
 * 地图类
 * @author JC丶本尊
 *
 */
public class Map {
	/**
	 * 初始化瓦片地图的最多方块数
	 */
	private static final int TILE_NUM_MAX=120;
	/**
	 * 初始化瓦片地图的最少方块数
	 */
	private static final int TILE_NUM_MIN=70;
	/**
	 * 定义当前地图的瓦片数
	 */
	private int TILE_NUM;
	/**
	 * 瓦片的集合
	 */
	private List<TileMap> tiles=new ArrayList<TileMap>();
	/**
	 * 瓦片的x，y轴坐标的二维数组，用于排重
	 */
	private int xy[][]=new int[17][17];
	public Map() {
		TILE_NUM=RandomNumber.createRandomNumber(TILE_NUM_MAX, TILE_NUM_MIN);
		createMap();
	}
	
	/**
	 * 创建地图
	 */
	public void createMap() {
		int x,y;
		while(tiles.size()<TILE_NUM) {
			/**
			 * 随机生成瓦片的位置
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
	 * 画出所有的瓦片地图
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
