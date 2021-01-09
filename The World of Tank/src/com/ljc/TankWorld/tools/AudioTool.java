package com.ljc.TankWorld.tools;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
/**
 * ����Ч�ļ�תΪIO���Ĺ�����
 * @author JCؼ����
 *
 */

public class AudioTool {
	public static AudioInputStream getAudio(String path) {
		try {
			/**
			 * ���÷��䴫���ļ�
			 * class.getResource���Ҵ��и������Ƶ���Դ��
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
