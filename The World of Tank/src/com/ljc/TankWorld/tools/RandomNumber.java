package com.ljc.TankWorld.tools;

import java.util.Random;
/**
 * ����������Ĺ�����
 * @author JCؼ����
 *
 */
public class RandomNumber {
	/**
	 * ��������������ֵ����Сֵ
	 * @param max
	 * @param min
	 * @return
	 */
	public static int createRandomNumber(int max,int min) {
		Random r=new Random();
		return r.nextInt(max-min)+min;
	}
}
