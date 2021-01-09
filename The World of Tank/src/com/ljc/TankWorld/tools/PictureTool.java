package com.ljc.TankWorld.tools;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 * 读取图片的工具
 * @author JC丶本尊
 *
 */
public class PictureTool {
	/**
	 * 提供图片的相对路径（path）
	 * @param path
	 * @return
	 */
	public static BufferedImage getImg(String path) {
		try {
			BufferedImage img=ImageIO.read(PictureTool.class.getResource(path));
			return img;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
