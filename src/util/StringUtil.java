package util;
/**
 * 字符串工具类
 * @author HP
 *
 */
public class StringUtil {
	/**
	 * 判断是否是空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if(str==null || "".equals(str.trim())) {
			return true;
		}
		else {
			return false;
		}
	}
	public static boolean isNotEmpty(String str) {
		if(str!=null &&!"".equals(str.trim())) {
			return true;
		}else {
			return false;
		}
	}
}
