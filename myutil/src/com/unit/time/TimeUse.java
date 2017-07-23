package com.unit.time;

import java.util.Calendar;

public class TimeUse {
	
	
	/**
	 * @description :自定义延迟时间（SAE不支持java延迟）
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
