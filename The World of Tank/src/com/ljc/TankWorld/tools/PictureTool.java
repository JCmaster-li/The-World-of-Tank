package com.ljc.TankWorld.tools;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 * ��ȡͼƬ�Ĺ���
 * @author JCؼ����
 *
 */
public class PictureTool {
	/**
	 * �ṩͼƬ�����·����path��
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
