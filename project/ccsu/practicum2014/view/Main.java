package qgb.project.ccsu.practicum2014.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import qgb.*;
import qgb.project.ccsu.practicum2014.model.DW;
import qgb.project.ccsu.practicum2014.model.DepartMents;
import qgb.project.ccsu.practicum2014.model.Teacher;
import qgb.project.ccsu.practicum2014.model.Teachers;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.List;

import javax.swing.JTable;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;

import static qgb.project.ccsu.practicum2014.view.Utils.*;

public class Main extends JFrame {
	private JPanel contentPane;
	public static JTable gTable;
	public static List gDMlist;
	public static TeacherModel gTModel = new TeacherModel();
	
	/**
	 * Launch the application.
	 * 
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) {
		LoadClass();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
					frame.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent e) {
							Teachers.save();
							DepartMents.save();
							msgbox("��¼�Ѿ����棡");
							U.exit();
						}

					});
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void LoadClass() {
		try {
			Class.forName(Teachers.class.getName());
			Class.forName(DepartMents.class.getName());
			Class.forName(TeacherModel.class.getName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 485, 374);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnAddtecher = new JButton("AddTeacher");
		btnAddtecher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Teachers.add();
				loadData();
			}
		});
		btnAddtecher.setBounds(361, 10, 112, 25);
		contentPane.add(btnAddtecher);

		JButton btnModifytecher = new JButton("ModifyTeacher");
		btnModifytecher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Teachers.modify();
				loadData();
			}
		});
		btnModifytecher.setBounds(244, 10, 112, 25);
		contentPane.add(btnModifytecher);

		JButton btnFindtecher = new JButton("FindTeacher");
		btnFindtecher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Teachers.find();
				loadData();
			}
		});
		btnFindtecher.setBounds(127, 10, 112, 25);
		contentPane.add(btnFindtecher);

		JButton btnDeletetech = new JButton("DeleteTech");
		btnDeletetech.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Teachers.delete();
				loadData();
			}
		});
		btnDeletetech.setBounds(10, 10, 112, 25);
		contentPane.add(btnDeletetech);

		JButton btnAdddepartm = new JButton("AddDepartM");
		btnAdddepartm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DepartMents.add();
				loadData();
			}
		});
		btnAdddepartm.setBounds(19, 203, 95, 25);
		contentPane.add(btnAdddepartm);

		JButton btnModifydepartm = new JButton("ModifyDepartM");
		btnModifydepartm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DepartMents.modify();
				loadData();
			}
		});
		btnModifydepartm.setBounds(124, 203, 122, 25);
		contentPane.add(btnModifydepartm);

		JButton btnFinddepartm = new JButton("FindDepartM");
		btnFinddepartm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DepartMents.find();
				loadData();
			}
		});
		btnFinddepartm.setBounds(256, 203, 109, 25);
		contentPane.add(btnFinddepartm);

		gDMlist = new List();
		gDMlist.setBounds(19, 244, 454, 103);
		contentPane.add(gDMlist);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 45, 447, 140);
		contentPane.add(scrollPane);

		gTable = new JTable(gTModel);
		scrollPane.setViewportView(gTable);
		gTable.setFillsViewportHeight(true);
		gTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
		
		JButton btnWork = new JButton("Work");
		btnWork.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Teachers.statisWork();
			}
		});
		btnWork.setBounds(375, 189, 95, 25);
		contentPane.add(btnWork);
		
		JButton btnTeacher = new JButton("Teacher");
		btnTeacher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Teachers.statisDepatM();
			}
		});
		btnTeacher.setBounds(375, 213, 95, 25);
		contentPane.add(btnTeacher);
		
		loadData();
	}
 
	private void loadData() {
		gTModel.setRowCount(0);
		for (Teacher t : Teachers.galT) {
			gTModel.addTeacher(t);
		}
		DW[] ydw =new DW[DepartMents.galS.size()];
		for (int i = 0; i < ydw.length; i++) {
			ydw[i]=new DW(DepartMents.galS.get(i), i);
		}
		
		final JComboBox cb=new JComboBox(ydw);
		gTable.setDefaultEditor(DW.class, new DefaultCellEditor(cb));
 
		gDMlist.removeAll();
		int i = -1;
		for (String st : DepartMents.galS) {
			// U.get_intDigit(DepartMents.galS.size())
			gDMlist.add(T.padNum(++i, DepartMents.galS.size()) + ":" + st);
		}
	}
}
