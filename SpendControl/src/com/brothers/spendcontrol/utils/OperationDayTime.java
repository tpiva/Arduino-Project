package com.brothers.spendcontrol.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class OperationDayTime {
	
	private static String regexDay = "dd/MM/yyyy";
	private static SimpleDateFormat sdf = new SimpleDateFormat(regexDay, Locale.US);
	
	public static String dayInitialPeriodOfMounth() {
		String valueOfDayString = null;
		Calendar calendar = Calendar.getInstance();
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
		
		valueOfDayString = sdf.format(calendar.getTime());
		
		return valueOfDayString;
	}
	
	public static String dayFinalPeriodOfMounth() {
		String valueOfDayString = null;
		Calendar calendar = Calendar.getInstance();
		//set first day of mounth
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
		int lastDay = calendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), lastDay);
		valueOfDayString = sdf.format(calendar.getTime());
		
		return valueOfDayString;
	}
	
}
