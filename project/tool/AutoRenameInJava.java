package qgb.project.tool;

import java.io.File;

import qgb.*;

public class AutoRenameInJava {
	static String gstPath = "E:/SourceCode/eclipse/QJSU/src/qgb/project/";
	public final static String stOld = "QText.", stNew = "T.";

	public static void main(String[] args) {
		U.print(AutoRenameInJava.class.getName().replace("", "@@@"));
		// U.exit();
		String[] yst = F.getFilesStringArray(gstPath, ".java");
		U.print(yst);
		U.exit();
		String sta = "";
		int ia = 0;
		for (int i = 0; i < yst.length; i++) {
			if(i<=41)continue;
			sta = U.readSt(yst[i]);
			U.print("Replace [%d]%s/n", i, yst[i]);
			sta = T.replace(sta, "import qgb.T;", "import qgb.*;");
			sta = T.replace(sta, "read_st(", "readSt(");
			sta = T.replace(sta, "read_bis(", "readBis(");
			sta = T.replace(sta, "read_is(", "readIs(");
			sta = T.replace(sta, "read_byteArray(", "readBytes(");
			sta = T.replace(sta, "get_first_text(", "getFirst(");
			sta = T.replace(sta, "import qgb.text.Text;", "");
			sta = T.replace(sta, "import qgb.text.QText;", "");
			sta = T.replace(sta, "import qgb.file.F;", "");
			sta = T.replace(sta, "InputStreamToYByte(", "InputStreamToBytes(");
			sta = T.replace(sta, "ByteToInputStream(", "BytesToInputStream(");
			sta = T.replace(sta, "import qgb.Array;", "");
			sta = T.replace(sta, "import qgb.Y;", "");
			sta = T.replace(sta, "Array.", "Y.");
			sta = T.replace(sta, ".del_muti(", ".delMuti(");
			sta=sta.replace("cst_test_path", "gstTestPath");
			sta = T.replace(sta, "get_text_array(", "getYst(");
			//sta = T.replace(sta, "T.", "U.");
			sta = T.replace(sta, "T.", "U.");
			sta = T.replace(sta, "QText.", "T.");
			
			// U.print(sta);
			U.write(yst[i], sta);
			U.msgbox(yst[i]);

		}

	}

}
