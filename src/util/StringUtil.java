package util;
/**
 * �ַ���������
 * @author HP
 *
 */
public class StringUtil {
	/**
	 * �ж��Ƿ��ǿ�
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
