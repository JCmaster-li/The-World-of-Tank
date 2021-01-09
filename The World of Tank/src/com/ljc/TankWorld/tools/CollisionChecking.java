package com.ljc.TankWorld.tools;
/**
 * 碰撞检测的工具类
 * @author JC丶本尊
 *
 */
public class CollisionChecking {
	public static final Boolean isTouching(int ObjectA_x,int ObjectA_y,int ObjectA_width,int ObjectA_height,int ObjectB_x,int ObjectB_y,int ObjectB_width,int ObjectB_height) {
		/**
		 * 判断物体A的右下角是否与物体B的左上角碰撞
		 */
		if(ObjectA_x+ObjectA_width>ObjectB_x&&ObjectA_x+ObjectA_width<=ObjectB_x+ObjectB_width&&ObjectA_y+ObjectA_height>=ObjectB_y&&ObjectA_y+ObjectA_height<=ObjectB_y+ObjectB_height) {
			return true;
		}
		/**
		 * 判断物体A的左下角是否与物体B的右上角碰撞
		 */
		else if(ObjectA_x>ObjectB_x&&ObjectA_x<ObjectB_x+ObjectB_width&&ObjectA_y+ObjectA_height>ObjectB_y&&ObjectA_y+ObjectA_height<ObjectB_y+ObjectB_height) {
			return true;
		}
		/**
		 * 判断物体A的左上角是否与物体B的右下角碰撞
		 */
		else if(ObjectA_x>ObjectB_x&&ObjectA_x<ObjectB_x+ObjectB_width&&ObjectA_y>ObjectB_y&&ObjectA_y<ObjectB_y+ObjectB_height) {
			return true;
		}
		/**
		 * 判断物体A的右上角是否与物体B的左下角碰撞
		 */
		else if(ObjectA_x+ObjectA_width>ObjectB_x&&ObjectA_x+ObjectA_width<ObjectB_x+ObjectB_width&&ObjectA_y>ObjectB_y&&ObjectA_y<ObjectB_y+ObjectB_height) {
			return true;
		}
		else return false;
	}
}
