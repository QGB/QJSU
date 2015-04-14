package qgb;

import qgb.os.OsType;

public class OS {
	public static void main(String[] args) {
		U.print(getCurrent());
	}
	public static OsType getCurrent() {
		// 根据系统打开网址  
        if (gsOS_NAME.indexOf("win") > -1) {  
        	return OsType.WIN;
        } else if (gsOS_NAME.indexOf("mac") > -1) {  
           return OsType.MAC;
        } else if (gsOS_NAME.indexOf("linux") > -1) {
        	return OsType.LINUX;
        } else if (gsOS_NAME.indexOf("unix") > -1) {  
        	return OsType.UNIX;
        } else if (gsOS_NAME.indexOf("android") > -1) {  
        	return OsType.ANDROID;
        } else {  
            return OsType.OTHER;  
        }  
	}
	public static final String gsOS_NAME = System.getProperty("os.name").toLowerCase();  
	public static final OsType gos=getCurrent();
	public static boolean isWin() {
		return gos==OsType.WIN;
	}
	public static boolean isMac() {
		return gos==OsType.MAC;
	}
	public static boolean isLinux() {
		return gos==OsType.LINUX;
	}
	public static boolean isUnix() {
		return gos==OsType.UNIX;
	}
	public static boolean isAndroid() {
		return gos==OsType.ANDROID;
	}
	
}
