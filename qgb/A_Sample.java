package qgb;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import mh.struct.Voice;
import qgb.file.F;
import qgb.media.MP3player;
import qgb.net.Get;

public class A_Sample {
	public void main(String[] args) throws Exception {
		T.print(T.getCmdToRun());
		// InputStream is=
		// Get.urlfile("http://cs.58.com/qzxueshengjianzhi/?param8426=1&PGTID=14167135996990.2586963609792292&ClickID=6");
		// T.write("58.txt", is);
		class A {
			public void print(Object ayo[]) {
				if (ayo == null) {
					return;
				}
				if (ayo.length > 0) {
					print("%s[%d]", ayo[0].getClass().getName(), ayo.length);
				}
				String stFormat = "[%-" + get_intDigit(ayo.length - 1)
						+ "s]=%s";
				for (int i = 0; i < ayo.length; i++) {
					print(stFormat, i, ayo[i]);
				}
			}

			public int get_intDigit(int ai) {
				return String.valueOf(ai).length();
			}

			public void print(String format, Object... args) {
				System.out.printf(format + "\n", args);
			}

			public String[] getFilesStringArray(String filePath,
					String astFileType) {
				Object[] yo = getFilesStringList(filePath, astFileType)
						.toArray();
				return ObjectsToYStr(yo);
			}

			public String[] ObjectsToYStr(Object[] ayo) {
				String[] yst = new String[ayo.length];
				for (int i = 0; i < ayo.length; i++) {
					yst[i] = ayo[i].toString();
				}
				return yst;
			}

			public ArrayList<String> getFilesStringList(String filePath,
					String astFileType) {
				File root = new File(filePath);
				File[] files = root.listFiles();
				ArrayList<String> filelist = new ArrayList<String>();
				String sta = "";
				for (File file : files) {
					if (file.isDirectory()) {
						/*
						 * �ݹ����
						 */
						filelist.addAll(getFilesStringList(
								file.getAbsolutePath(), astFileType));
					} else {
						sta = file.getAbsolutePath();
						if (accept(sta, astFileType)) {
							filelist.add(sta);
						}
					}
				}
				return filelist;
			}

			public boolean accept(String astf, String astFileType) {
				return astf.toLowerCase().endsWith(astFileType.toLowerCase());
			}
		}
		A a = new A();
		a.print(a.getFilesStringArray("d:/test/", ".txt"));
	}

}
