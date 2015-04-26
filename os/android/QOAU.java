package qgb.os.android;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.json.JSONObject;

import qgb.F;
import qgb.T;
import qgb.U;

import android.R.integer;

import frame.User;

public class QOAU {
	public static void main(String[] args) {

		User u = new User(1, " asName", " asPw");
		U.print(u);
		String st = objToHex(u);
		U.print(st);
		byte[] yb=F.hexToBytes(st);
		U.print(yb);
		U.write("yb.bin",yb);
		st=F.bytesToHex(yb);
		U.print(st);
		yb=F.hexToBytes(st);
		
		U.print(objDeserialize(yb));
		U.print(HexToObj(st));
		//U.write("yb1.bin",yb);
		//U.print(Integer.toString(0x0, 16));
	}

	public static byte[] objSerialize(Object ao) {
		byte[] yb;
		try {
			// FileOutputStream fos = new FileOutputStream(U.autoPath("u.obj"));
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(ao);
			oos.close();
			return bos.toByteArray();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static Object objDeserialize(byte[] ayb) {
		Object o;
		try {
			// FileOutputStream fos = new FileOutputStream(U.autoPath("u.obj"));
			ObjectInputStream ois=new ObjectInputStream(U.BytesToInputStream(ayb));
			o= ois.readObject();
			ois.close();
			return o;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public static String objToHex(Object ao) {
		return F.bytesToHex(objSerialize(ao));
	}
	
	public static Object HexToObj(String as) {
		return objDeserialize(F.hexToBytes(as));
	}
}
