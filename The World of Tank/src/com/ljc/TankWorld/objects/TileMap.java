package com.ljc.TankWorld.objects;

import static com.ljc.TankWorld.constant.Constant.*;

import java.awt.image.BufferedImage;
/**
 * ��Ƭ��ͼ��
 * @author JCؼ����
 *
 */
public class TileMap {
	/**
	 *  ��Ƭ��ͼ��ͼƬ
	 */
	private BufferedImage img;

	/**
	 * ��Ƭ��ͼ��x������
	 */
	private int tile_x;
	/**
	 * ��Ƭ��ͼ��x������
	 */
	private int tile_y;

	/**
	 * ��Ƭ��ͼͼƬ�Ŀ�͸�
	 */
	private int tile_width;
	private int tile_height;

	/**
	 * ��Ƭ���;ö�
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
