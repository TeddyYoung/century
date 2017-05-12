package com.century.modules.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;

import org.apache.commons.lang.RandomStringUtils;

public class NumberUtils {
	
	public static boolean isBlank(Integer num){
		return num == null || num.intValue() == 0;
	}
	/**
	 * 保留二位小数金额
	 * @return
	 */
	public static DecimalFormat getDecimalFormat2(){
		DecimalFormat myformat = new DecimalFormat();
		myformat.applyPattern("#####0.00");
		return myformat;
	}
	/**
	 * 格式化两位金额
	 * @param num
	 * @return
	 */
	public static String format2(Object num){
		return getDecimalFormat2().format(num);
	}
	public static BigDecimal parseNum(String numSttr){
		try {
			Number num = getDecimalFormat2().parse(numSttr);
			return new BigDecimal(num.doubleValue());
		} catch (ParseException e) {
			 
		}
		return null;
	}
	
	/**
	 * 生成邀请码
	 * @param count
	 * @return
	 */
	public static String getRefereeCode(){
		return RandomStringUtils.randomAlphanumeric(2)+RandomStringUtils.random(6, false, true);
	}

}
