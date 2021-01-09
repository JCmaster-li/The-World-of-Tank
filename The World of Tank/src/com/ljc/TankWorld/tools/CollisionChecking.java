package com.ljc.TankWorld.tools;
/**
 * ��ײ���Ĺ�����
 * @author JCؼ����
 *
 */
public class CollisionChecking {
	public static final Boolean isTouching(int ObjectA_x,int ObjectA_y,int ObjectA_width,int ObjectA_height,int ObjectB_x,int ObjectB_y,int ObjectB_width,int ObjectB_height) {
		/**
		 * �ж�����A�����½��Ƿ�������B�����Ͻ���ײ
		 */
		if(ObjectA_x+ObjectA_width>ObjectB_x&&ObjectA_x+ObjectA_width<=ObjectB_x+ObjectB_width&&ObjectA_y+ObjectA_height>=ObjectB_y&&ObjectA_y+ObjectA_height<=ObjectB_y+ObjectB_height) {
			return true;
		}
		/**
		 * �ж�����A�����½��Ƿ�������B�����Ͻ���ײ
		 */
		else if(ObjectA_x>ObjectB_x&&ObjectA_x<ObjectB_x+ObjectB_width&&ObjectA_y+ObjectA_height>ObjectB_y&&ObjectA_y+ObjectA_height<ObjectB_y+ObjectB_height) {
			return true;
		}
		/**
		 * �ж�����A�����Ͻ��Ƿ�������B�����½���ײ
		 */
		else if(ObjectA_x>ObjectB_x&&ObjectA_x<ObjectB_x+ObjectB_width&&ObjectA_y>ObjectB_y&&ObjectA_y<ObjectB_y+ObjectB_height) {
			return true;
		}
		/**
		 * �ж�����A�����Ͻ��Ƿ�������B�����½���ײ
		 */
		else if(ObjectA_x+ObjectA_width>ObjectB_x&&ObjectA_x+ObjectA_width<ObjectB_x+ObjectB_width&&ObjectA_y>ObjectB_y&&ObjectA_y<ObjectB_y+ObjectB_height) {
			return true;
		}
		else return false;
	}
}
