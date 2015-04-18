package qgb.project.tool;

import java.io.File;
import java.util.Arrays;

import qgb.*;


/**
 * UTF-8 BOM
 * T.format("%x,%x,%x", yba[0], yba[1], yba[2]).equals(
					"ef,bb,bf")
 * */
public class HexReplace {
	static String gstPath = "D:\\java\\Fmb_applianceRepair_System/";

	public static void main(String[] args) {
		U.print(HexReplace.class.getName());
		String[] yst = F.getFilesStringArray(gstPath, ".java");
		String sta = "", stb = "";
		byte[] yba;
		int ia = 0;
		for (int i = 0; i < yst.length; i++) {
			yba = U.readBytes(yst[i]);
			if (T.format("%x,%x,%x", yba[0], yba[1], yba[2]).equals(
					"ef,bb,bf")) {
				U.print("[%d]%s\n", i, yst[i]);
				yba = Arrays.copyOfRange(yba, 3, yba.length);
				U.delFile(yst[i]);
				U.write(yst[i], yba);

			}

		}

	}

}
