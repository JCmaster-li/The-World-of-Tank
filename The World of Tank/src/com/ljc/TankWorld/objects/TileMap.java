package com.ljc.TankWorld.objects;

import static com.ljc.TankWorld.constant.Constant.*;

import java.awt.image.BufferedImage;
/**
 * 瓦片地图类
 * @author JC丶本尊
 *
 */
public class TileMap {
	/**
	 *  瓦片地图的图片
	 */
	private BufferedImage img;

	/**
	 * 瓦片地图的x轴坐标
	 */
	private int tile_x;
	/**
	 * 瓦片地图的x轴坐标
	 */
	private int tile_y;

	/**
	 * 瓦片地图图片的宽和高
	 */
	private int tile_width;
	private int tile_height;

	/**
	 * 瓦片的耐久度
	 */
	private int Durability;
	
	public TileMap() {
	}

	public TileMap(int x, int y, int Durability,BufferedImage img) {
		this.tile_x = x;
		this.tile_y = y;
		this.Durability=Durability;
		this.img = img;
		this.tile_width = img.getWidth();
		this.tile_height = img.getHeight();
	}

	public BufferedImage getImg() {
		return img;
	}

	public void setImg(BufferedImage img) {
		this.img = img;
	}

	public int getTile_x() {
		return tile_x;
	}

	public void setTile_x(int tile_x) {
		this.tile_x = tile_x;
	}

	public int getTile_y() {
		return tile_y;
	}

	public void setTile_y(int tile_y) {
		this.tile_y = tile_y;
	}

	public int getDurability() {
		return Durability;
	}

	public void setDurability(int durability) {
		Durability = durability;
	}

	public int getTile_width() {
		return tile_width;
	}

	public void setTile_width(int tile_width) {
		this.tile_width = tile_width;
	}

	public int getTile_height() {
		return tile_height;
	}

	public void setTile_height(int tile_height) {
		this.tile_height = tile_height;
	}
	
}
