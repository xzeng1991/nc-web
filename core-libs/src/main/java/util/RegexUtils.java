package util;

import java.util.regex.Pattern;

public class RegexUtils {
	/**
	 * 检查日月格式
	 * @param date
	 * @return
	 */
	public static boolean checkDate(String date){
		String regex = "^(0?[1-9]|1[0-2])-(0?[1-9]|[1-2][0-9]|3[0-1])$";   
        return Pattern.matches(regex, date);
	}
}
