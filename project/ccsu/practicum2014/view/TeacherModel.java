package qgb.project.ccsu.practicum2014.view;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import qgb.*;
import qgb.project.ccsu.practicum2014.model.DW;
import qgb.project.ccsu.practicum2014.model.DepartMents;
import qgb.project.ccsu.practicum2014.model.Teacher;
import qgb.project.ccsu.practicum2014.model.Teachers;

public class TeacherModel extends DefaultTableModel {
	public static void main(String[] args) throws ClassNotFoundException {
		Main.main(null);
	}
	// ////////////////////////////////////////////////////
	public final static String[] gysTableCol=Teacher.getFields();
	public final static int giDM_Col=2;
	public final static int giWork_Col=3;
	public final static int giDeg_Col=4;
	public final static int giDate_Col=5;
	String gsTest = "kanw";
	// Vector gVcolumn=new Vector<String>();
	public TeacherModel() {
		super(0,gysTableCol.length);
		setColumnIdentifiers(gysTableCol);
		addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				int ir=e.getLastRow(),ic=e.getColumn();
				if (ir<0) {
					return;
				}
				Teacher t=Teachers.galT.get(ir);
				Field[] yf =t.getClass().getDeclaredFields();
				int iD=0;
				for (int i = 0; i < yf.length; i++) {
					if (yf[i].getModifiers()>Modifier.STATIC) {
						iD++;
						continue;
					}
					yf[i].setAccessible(true);
					try {
						if (ic==i-iD) {
							if (ic==giDM_Col) {
								yf[i].set(t, DepartMents.getIndexByName( Main.gTable.getValueAt(ir,ic).toString()));	
							}else
								yf[i].set(t, Main.gTable.getValueAt(ir,ic));	
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					} 
				}
			}
		});
		//U.print(getColumnCount());
	}
	
	public int addTeacher(Teacher aT){
		String[] yst = new String[gysTableCol.length];
		Field[] yf =aT.getClass().getDeclaredFields();
		int iD=0;
		for (int i = 0; i < yf.length; i++) {
			if (yf[i].getModifiers()>Modifier.STATIC) {
				iD++;
				continue;
			}
			yf[i].setAccessible(true);
			try {
				if(yf[i].getName().endsWith(Teacher.gsFDM)){
					int inumOfDM=yf[i].getInt(aT);
					yst[i-iD]=DepartMents.galS.get(inumOfDM);
					}
				else {
					yst[i-iD]=String.valueOf(yf[i].get(aT));
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}		
		//U.print(yst);
		final int index=getRowCount();
		addRow(yst);
		return index;
	}

	public Class getColumnClass(int c) {
		if ( c ==giDM_Col) {
			return DW.class;
		}
		if (c==giWork_Col||c==giDate_Col) {
			return Integer.class;
		}
		return String.class;
	}
	
	@Override
	public boolean isCellEditable(int r, int c) {
		return c >=1;
	}

	// public void setValueAt(Object value, int r, int c) {
	//
	// // data[r] = (ColorName) value;


}
