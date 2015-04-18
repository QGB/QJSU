package qgb.project.swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;

import qgb.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ratio extends JFrame {

	private JPanel contentPane;
	private JTextField[] txt1=new JTextField[6];
	private JTextField[] txt2=new JTextField[txt1.length];
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ratio frame = new Ratio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Ratio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 448, 181);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnRatio = new JButton("Ratio!");
		btnRatio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ratio();
			}
		});
		btnRatio.setBounds(153, 119, 95, 25);
		contentPane.add(btnRatio);
		
		for (int i = 0; i < txt1.length; i++) {
			txt1[i] = new JTextField();
			txt1[i].setBounds(10+70*i, 10, 60, 20);
			contentPane.add(txt1[i]);
			txt1[i].setColumns(10);
		}
		
		for (int i = 0; i < txt2.length; i++) {
			txt2[i] = new JTextField();
			txt2[i].setBounds(10+70*i, 10+20+10, 60, 20);
			contentPane.add(txt2[i]);
			txt2[i].setColumns(10);
		}

	}

	protected void ratio() {
		double dr=Double.valueOf(txt1[0].getText())/Double.valueOf(txt2[0].getText());
		for (int i =1; i < txt1.length; i++) {
			try {
				double dra=Double.valueOf(txt1[i].getText())/dr;
				txt2[i].setText(T.getBegins(String.valueOf(dra), 8));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
	}
}
