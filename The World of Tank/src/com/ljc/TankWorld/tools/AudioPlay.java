package com.ljc.TankWorld.tools;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
/**
 * 播放音效的工具
 * @author JC丶本尊
 *
 */

public class AudioPlay {
	/**
	 * 传入音效文件的相对路径和播放模式
	 * @param path
	 * @param model
	 */
	public static void audioPlay(String path,String model) {
		try {
			Clip clip=AudioSystem.getClip();
			AudioInputStream in=AudioTool.getAudio(path);
			clip.open(in);
			/**
			 * 循环播放
			 */
			if(model=="Loop") {
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}
			/**
			 * 单次播放
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
