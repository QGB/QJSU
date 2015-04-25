package qgb.project.net;

import java.io.UnsupportedEncodingException;

import qgb.*;

public class PingIP {
	public static void main(String[] args) throws UnsupportedEncodingException {
		for (int i =0; i < 256; i++) {
			if(i%11==0)U.sleep(5999);
			final int ia=i;
			new Thread(){
				@Override
				public void run() {
					String st= ping("10.90.91."+ia,4);
					if(st.contains("100%"))
						return;
					U.print(ia+T.sub(st,"的 Ping 统计信息:","丢失)，"));
				}
			}.start();

		}

		//U.print(os);
	}

	public static String ping(String as, int aiTimes) {
		return U.exec(T.format("cmd /c ping -n %s %s",aiTimes,as));
	}

}
