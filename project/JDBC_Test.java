package qgb.project;
//<pre name="code" class="java">package chp07;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import qgb.*;


public class JDBC_Test {
	// ������̬ȫ�ֱ���
	static Connection conn;
	static Statement st;
	static String sql;
	public static void main(String[] args){
		getConnection();	// ����Ҫ��ȡ���ӣ������ӵ���ݿ�
		sql="INSERT INTO lib (inum,st_name,st_id,st_sex,st_addr,st_zip,st_emai,st_tele,st_offi,st_good) VALUES (2006111111,'������','43072619870408183x','Ů','','','','','06��ľ1','');";
		insert(sql);
		//createTable();
		//deleteTable();
		
		//insert();	//������Ӽ�¼
		//update();	//���¼�¼���
		//delete();	//ɾ���¼
		//query();	//��ѯ��¼����ʾ
	}
	
	/* ��ȡ��ݿ����ӵĺ���*/
	public static void getConnection() {
		try {
			//Class.forName("com.mysql.jdbc.Driver");// ����Mysql�����
			
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ccsu", "root", "123456");// �����������
			st = (Statement) conn.createStatement();	
		} catch (Exception e) {
			System.out.println("��ݿ�����ʧ��" + e.getMessage());
			
		}
		//return con;	//������������ݿ�����
	}
	
	private static void deleteTable() {
		sql="DROP TABLE lib;";
		SQL_execute(sql);
	}

	public static void createTable() {
		sql ="CREATE TABLE lib (inum integer NOT NULL,"
				+ "st_name varchar(20) NOT NULL,"
				+ "st_id varchar(20) NOT NULL,"
				+ "st_sex varchar(2) NOT NULL,"
				+ "st_addr varchar(50),"
				+ "st_zip varchar(10),"
				+ "st_emai varchar(50),"
				+ "st_tele varchar(50),"
				+ "st_offi varchar(50),"
				+ "st_good varchar(50),"
				+ "PRIMARY KEY (inum));";
		SQL_execute(sql);
		
	}

	public static void SQL_execute(String sql) {
		try {
			st.execute(sql);
		} catch (NullPointerException e) {
			U.error(e,"����δ������ݿ⣡");
		} catch (SQLException e) {
			U.error(e,sql+"| execute error ��");
		}	
	}

	/* ������ݼ�¼��������������ݼ�¼��*/
	public static void insert(String asql) {
		
		SQL_execute(asql);
	}
	
	/* ���·��Ҫ��ļ�¼�������ظ��µļ�¼��Ŀ*/
	public static void update() {
		try {
			String sql = "update staff set wage='2200' where name = 'lucy'";// ������ݵ�sql���
			
			int count = st.executeUpdate(sql);// ִ�и��²�����sql��䣬���ظ�����ݵĸ���
			
			System.out.println("staff���и��� " + count + " �����");		//������²����Ĵ�����
			
			conn.close();	//�ر���ݿ�����
			
		} catch (SQLException e) {
			System.out.println("�������ʧ��");
		}
	}

	/* ��ѯ��ݿ⣬������Ҫ��ļ�¼�����*/
	public static void query(String sql) {
		try {
			//String sql = "select * from lib where inum>2011000001 and inum<2012123456;";		// ��ѯ��ݵ�sql���
			
			ResultSet rs = st.executeQuery(sql);	//ִ��sql��ѯ��䣬���ز�ѯ��ݵĽ��
			System.out.println("���Ĳ�ѯ���Ϊ��");
			while (rs.next()) {	// �ж��Ƿ�����һ�����
				
				// ����ֶ����ȡ��Ӧ��ֵ
				//CountryCode | Language | IsOfficial | Percentage
				String name =String.valueOf(rs.getInt("inum"));
				//int age = rs.getInt("age");
				String sex = rs.getString("st_name");
				String address = rs.getString("st_id");
				String depart = rs.getString("st_sex");
				//String worklen = rs.getString("worklen");
				//String wage = rs.getString("wage");
				
				//����鵽�ļ�¼�ĸ����ֶε�ֵ
				System.out.println(name + " "  + " " + sex + " " + address
						+ " " + depart );
			
			}
			conn.close();	//�ر���ݿ�����
			
		} catch (SQLException e) {
			System.out.println("��ѯ���ʧ��");
		}
	}

	/* ɾ����Ҫ��ļ�¼��������*/
	public static void delete() {
		try {
			String sql = "delete from staff  where name = 'lili'";// ɾ����ݵ�sql���
			
			int count = st.executeUpdate(sql);// ִ��sqlɾ����䣬����ɾ����ݵ�����
			
			System.out.println("staff����ɾ�� " + count + " �����\n");	//���ɾ������Ĵ�����
			
			conn.close();	//�ر���ݿ�����
			
		} catch (SQLException e) {
			System.out.println("ɾ�����ʧ��");
		}
		
	}
	

}
//</pre><br>
//<pre></pre>
//<p></p>
//<p><strong>��Ŀ���𵽷�������Ȼ�����н��</strong></p>
//<p></p>
//<p><a href="http://hi.csdn.net/attachment/201110/12/0_1318394342H718.gif" target="_blank"><img src="http://hi.csdn.net/attachment/201110/12/0_1318394342H718.gif" width="800" height="150" alt=""></a></p>
//<pre></pre>
//<pre></pre>
//<pre></pre>

