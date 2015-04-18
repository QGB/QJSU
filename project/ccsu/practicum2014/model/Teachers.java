package qgb.project.ccsu.practicum2014.model;

import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import javax.swing.JOptionPane;

import com.sun.swing.internal.plaf.metal.resources.metal;

import static qgb.file.Serialize.gXStream;
import qgb.*;

import qgb.project.ccsu.practicum2014.view.Main;
import qgb.project.ccsu.practicum2014.view.TeacherModel;
import static qgb.project.ccsu.practicum2014.view.Utils.*;
public class Teachers {
	public static void main(String[] args) throws ClassNotFoundException {
		Main.main(null);
//		Main.LoadClass();
//		statisWork();
//		statisDepatM();
		//U.print(galT);
//		for (int i = 0; i <9; i++) {
//			galT.add(new Teacher(i,"name" +i));
//		}
		//save();
	}
	//////////////////////////////
	public static ArrayList<Teacher> galT=new ArrayList<Teacher>();
	private final static String gstPath="Practicum2014/Teacher.galT.xml";
	
	static{
		if (F.isExist(gstPath)) {
			galT=(ArrayList<Teacher>)gXStream.fromXML(U.readSt(gstPath));
		}
	}
	/**�ѱ��ⲿ���ã���Ҫ������*/
	public static int innerFind(String ast) {
		if (ast==null||ast.length()<1) {
			return -1;
		}
		for (int i = 0; i <galT.size(); i++) {
			if( galT.get(i).contains(ast)){
				return i;
			}
		}
		return -1;
	}
	
	public static void add() {
		try {
			Teacher t= new Teacher();
			galT.add(t);
		} catch (Exception e) {
			msgbox("��ӽ�ʦʧ�ܣ�");
		}
	}
	
	public static void find() {
		try {
			String str=input("���������ҵĽ�ʦ��Ϣ��");
			final int index=innerFind(str);
			if (index==-1) {
				msgbox("�����ڴ˽�ʦ��");
				return;
			}
			msgbox("�ҵ���ʦ��\n"+galT.get(index));
		} catch (Exception e) {
			msgbox("���ҽ�ʦ��Ϣʧ�ܣ�");
		}
	}
	
	public static void modify() {
		try {
			String str=input("��������޸ĵĽ�ʦ��Ϣ��");
			final int index=innerFind(str);
			if (index==-1) {
				msgbox("�����ڴ˽�ʦ��");
				return;
			}
			galT.get(index).modify();
		} catch (Exception e) {
			//e.printStackTrace();
			msgbox("�޸Ľ�ʦ��Ϣʧ�ܣ�");
		}
		
	}
	
	public static void delete() {
		try {
			String str=input("�������h��Ľ�ʦ��Ϣ��");
			final int index=innerFind(str);
			if (index==-1) {
				msgbox("�����ڴ˽�ʦ��");
				return;
			}
			if(msgbox("�h���ʦ��\n"+galT.get(index))==JOptionPane.OK_OPTION) 
				galT.remove(index);
		} catch (Exception e) {
			msgbox("�h���ʦ��Ϣʧ�ܣ�");
		}
	}
	
	public static void save() {
		U.write(gstPath, gXStream.toXML(galT));
	}

	public static void statisWork() {
		//Map<K, V>;
		//TreeMap<Integer, String> tm=new TreeMap<Integer, String>();
		final int im=DepartMents.galS.size();
		//Map.Entry<Integer, String>[] mE=new Map.Entry<Integer, String>[im];
		DW[] ydw=new DW[im];
		/**0-999**/
		for (int i = 0; i <im ; i++) {
			String st= DepartMents.galS.get(i);
			int iw=0;
			for (int j = 0; j < galT.size(); j++) {
				Teacher t= galT.get(j);
				if (t.getGiDM()==i) {
					iw+=t.getGiWork();
				}
			}
			ydw[i]=new DW(st,iw);
		}
		//U.print(ydw);
		for (int i = 0; i <im ; i++) {
			for (int j = 0; j < im-i-1; j++) {
				if (ydw[j+1].i<ydw[j].i) {
					DW dw=ydw[j];
					ydw[j]=ydw[j+1];
					ydw[j+1]=dw;
				}
			}
		}
		//U.print(ydw);
		
		StringBuilder sb=new StringBuilder();
		for (int i = 0; i < ydw.length; i++) {
			sb.append(ydw[i].s+":"+ydw[i].i+"\n");	
		}
		msgbox(sb.toString());
	}


	public static void statisDepatM() {
		final int im=DepartMents.galS.size();
		//Map.Entry<Integer, String>[] mE=new Map.Entry<Integer, String>[im];
		DW[] ydw=new DW[im];
		/**0-999**/
		for (int i = 0; i <im ; i++) {
			String st= DepartMents.galS.get(i);
			int iw=0;
			for (int j = 0; j < galT.size(); j++) {
				Teacher t= galT.get(j);
				if (t.getGiDM()==i) {
					iw+=1;
				}
			}
			ydw[i]=new DW(st,iw);
		}
		//U.print(ydw);
		for (int i = 0; i <im ; i++) {
			for (int j = 0; j < im-i-1; j++) {
				if (ydw[j+1].i<ydw[j].i) {
					DW dw=ydw[j];
					ydw[j]=ydw[j+1];
					ydw[j+1]=dw;
				}
			}
		}
		//U.print(ydw);
		
		StringBuilder sb=new StringBuilder();
		for (int i = 0; i < ydw.length; i++) {
			sb.append(ydw[i].s+":"+ydw[i].i+"\n");	
		}
		msgbox(sb.toString());	
	}

}
