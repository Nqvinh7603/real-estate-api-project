package site.rentofficevn.utils;

public class CheckInputSearchUtils {
	public static boolean strIsNullOrEmpty(Object obj) {
		if(obj != null && obj != "") {
			return false;
		}
		return true;
	}
	public static boolean numIsNullInt(Integer obj) {
		if(obj != null) {
			return false;
		}
		return true;
	}
	public static boolean numIsNullLong(Long obj) {
		if(obj != null) {
			return false;
		}
		return true;
	}

}
