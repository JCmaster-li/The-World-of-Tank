package com.ljc.TankWorld.tools;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
/**
 * ������Ч�Ĺ���
 * @author JCؼ����
 *
 */

public class AudioPlay {
	/**
	 * ������Ч�ļ������·���Ͳ���ģʽ
	 * @param path
	 * @param model
	 */
	public static void audioPlay(String path,String model) {
		try {
			Clip clip=AudioSystem.getClip();
			AudioInputStream in=AudioTool.getAudio(path);
			clip.open(in);
			/**
			 * ѭ������
			 */
			if(model=="Loop") {
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}
			/**
			 * ���β���
			 */
			else if(model=="Start") {
				clip.start();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
