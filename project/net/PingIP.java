package qgb.project.net;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import qgb.*;

public class PingIP {
	public static void main(String[] args) throws UnsupportedEncodingException {
		for (int i = 0; i < 256; i++) {
			String st= ping("172.22.17."+i,1);
			if(st.contains("100%"))
				continue;
			U.print(i+T.sub(st,"的 Ping 统计信息:","丢失)，"));
		}

		//U.print(os);
	}

	public static String ping(String as, int aiTimes) {
		return U.exec(T.format("cmd /c ping -n %s %s",aiTimes,as));
	}

}
