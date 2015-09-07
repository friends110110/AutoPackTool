package cn.wf.wsw.tool;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveSeriliationUtils {
	private static String saveFile="wishisb";
	public static void saveDataToFile(Object obj){
		try {
			FileOutputStream fos=new FileOutputStream(saveFile);
			ObjectOutputStream oos=new ObjectOutputStream(fos);
			oos.writeObject(obj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static Object restoreDataFromFile(){

		try {
			FileInputStream fis=new FileInputStream(saveFile);
			ObjectInputStream ois=new ObjectInputStream(fis);
			return ois.readObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;

			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;

		}
	}
}
