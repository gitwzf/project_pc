package com.unit.time;

import java.util.Calendar;

public class TimeUse {
	
	
	/**
	 * @description :�Զ����ӳ�ʱ�䣨SAE��֧��java�ӳ٣�
	 * @param calendarType
	 * @param amount
	 * void
	 */
	public static void waitTime(int calendarType,int amount){
		Calendar calendar1=Calendar.getInstance();
		calendar1.add(calendarType, amount);
		while(calendar1.after(Calendar.getInstance())){
		}
	}

}
