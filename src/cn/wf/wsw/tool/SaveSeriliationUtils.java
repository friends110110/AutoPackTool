package cn.wf.wsw.tool;

import java.io.File;
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
			oos.close();
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static Object restoreDataFromFile(){
		File f=new File(saveFile);
		if(!f.exists()){
			return null;
		}
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis=new FileInputStream(saveFile);
			ois=new ObjectInputStream(fis);
			
			return ois.readObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;

			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;

		}finally{
			if(null!=fis){
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(null!=ois){
				try {
					ois.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
