package qgb.project.ccsu.practicum2014.model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import qgb.*;
import qgb.project.ccsu.practicum2014.view.Main;

import static qgb.project.ccsu.practicum2014.view.Utils.*;

public class Teacher implements Serializable {
	public static final String gsFDM = "giDM";

	public static void main(String[] args) throws ClassNotFoundException {
		Main.main(null);
		U.print(getFields());
		U.print(U.getCurrentMethod());
	}

	public static String[] getFields() {
		Class<Teacher> cT = Teacher.class;
		Field[] yf = cT.getDeclaredFields();
		ArrayList<String> als = new ArrayList<String>();
		for (int i = 0; i < yf.length; i++) {
			/**getModifiers() �����ֶ����Ե����ֵ*/
			if (yf[i].getModifiers()> Modifier.STATIC) {
				continue;
			}
			//U.print("%s-%s",yf[i].getModifiers(),yf[i].getName());
			als.add(yf[i].getName());
		}
		return als.toArray(new String[1]);
	}

	// ///////////////////////////////////
	private int giNum = -1;
	private String gsname;
	private int giDM = 0;// Ĭ�ϲ���
	private int giWork = 0;//
	private String gsDegree="����";
	private int giDate=19780605;
	public String getGsDegree() {
		return gsDegree;
	}

	public void setGsDegree(String gsDegree) {
		this.gsDegree = gsDegree;
	}

	public int getGiDate() {
		return giDate;
	}

	public void setGiDate(int giDate) {
		this.giDate = giDate;
	}
	
	public int getGiWork() {
		return giWork;
	}

	public void setGiWork(int giWork) {
		this.giWork = giWork;
	}

	public int getGiDM() {
		return giDM;
	}

	public void setGiDM(int giDM) {
		this.giDM = giDM;
	}

	public int getGiNum() {
		return giNum;
	}

	public void setGiNum(int giNum) {
		if (giNum < 0
				|| Teachers.innerFind(T.format("Num:%s\n", giNum)) != -1) {
			msgbox("��ų��?" + giNum);
			U.argsError(giNum);
		}
		this.giNum = giNum;
	}

	public String getGsname() {
		return gsname;
	}

	public void setGsname(String gsname) {
		if (gsname == null || gsname.length() < 1) {
			U.argsError(gsname);
		}
		this.gsname = gsname;
	}

	public Teacher() throws Exception {
		modify();
	}

	public Teacher(int giNum, String gsname) {
		setGiNum(giNum);
		setGsname(gsname);
	}

	public boolean contains(String ast) {
		return toString().contains(ast);
	}

	@Override
	public String toString() {
		return T.format("Num:%s\n" + "Name:%s\n", giNum, gsname);
	}

	public void modify() throws Exception {
		setGiNum(Integer.valueOf(input("�������ʦ���",
				giNum == -1 ? Teachers.galT.size() + 1 : giNum)));
		setGsname(input("�������ʦ����", "����"));
	}

}
