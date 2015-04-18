package qgb.project.ccsu.practicum2014.model;

import java.util.ArrayList;

import qgb.*;
import qgb.project.ccsu.practicum2014.view.Main;

import static qgb.file.Serialize.gXStream;
import static qgb.project.ccsu.practicum2014.view.Utils.*;
public class DepartMents {
	public static void main(String[] args) {
		Main.main(null);
	}
///////////////////////////////////////////////////
	public static ArrayList<String> galS=new ArrayList<String>();
	private final static String gstPath="Practicum2014/DepartMents.galS.xml";
	
	static{
		if (F.isExist(gstPath)) {
			galS=(ArrayList<String>)gXStream.fromXML(U.readSt(gstPath));
		}else {
			galS.add("Default");
		}
	}
	
	public static void add() {
		try {
			String str=input("�����������Ĳ��ţ�");
			if (galS.contains(str)) {
				msgbox("�����Ѿ����ڣ�");
			}else{
				galS.add(str);
			}
		} catch (Exception e) {
			msgbox("��������ʧ�ܣ�");
		}
	}
	
	public static void find() {
		try {
			String str=input("���������Ҳ��ţ�");
			boolean notFind=true;
			for (int i = 0; i <galS.size(); i++) {
				if( galS.get(i).contains(str)){
					msgbox(T..format("�ҵ����ţ�No:%s %s", i,galS.get(i)) );
					notFind=false;
				}
			}
			if (notFind) {
				msgbox("�����ڴ˲��ţ�");
				return;
			}
		} catch (Exception e) {
			msgbox("���Ҳ���ʧ�ܣ�");
		}
	}
	
	public static void modify() {
		try {
			final int index=Integer.valueOf(input("��������޸ĵĲ��ű�ţ�"));
			if (index<1||index>=galS.size()) {//0ΪĬ�ϲ���
				msgbox("�����ڴ˲��ţ�");
				return;
			}
			galS.set(index, input("�������²�������,ԭ����Ϊ��"+galS.get(index))) ;
		} catch (Exception e) {
			msgbox("�޸Ĳ���ʧ�ܣ�");
		}
	}
	
	public static void save() {
		U.write(gstPath,gXStream.toXML(galS));
	}

	public static int getIndexByName(String st) {
		for (int i = 0; i < galS.size(); i++) {
			if (st.equals(galS.get(i))) {
				return i;
			}
		}
		U.argsError(st);
		return -1;
	}

}
