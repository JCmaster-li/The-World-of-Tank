package com.ljc.TankWorld.tools;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
/**
 * 将音效文件转为IO流的工具类
 * @author JC丶本尊
 *
 */

public class AudioTool {
	public static AudioInputStream getAudio(String path) {
		try {
			/**
			 * 利用反射传入文件
			 * class.getResource查找带有给定名称的资源。
			 */
			AudioInputStream in = AudioSystem.getAudioInputStream(AudioTool.class.getResource(path));
			return in;
		} catch (UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
