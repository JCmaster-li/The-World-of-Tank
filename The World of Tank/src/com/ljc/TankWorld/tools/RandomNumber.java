package com.ljc.TankWorld.tools;

import java.util.Random;
/**
 * 生成随机数的工具类
 * @author JC丶本尊
 *
 */
public class RandomNumber {
	/**
	 * 传入随机数的最大值和最小值
	 * @param max
	 * @param min
	 * @return
	 */
	public static int createRandomNumber(int max,int min) {
		Random r=new Random();
		return r.nextInt(max-min)+min;
	}
}
