package cn.wf.wsw.tool;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class EditFileTools {
	 public static void editAppName(String filePath,String appname){
	    	if(null==filePath||"".equals(filePath))
	    		return;
	    	try{
	        File file =  new File(filePath);
	        FileInputStream fis = new FileInputStream(file);
	        InputStreamReader isr = new InputStreamReader(fis);
	        BufferedReader br = new BufferedReader(isr);
	        StringBuffer buf = new StringBuffer();
	        String temp;
	        //	<string name="app_name">�°�֤ȯ</string>

	        while((temp=br.readLine())!=null){
	            if(temp.trim().startsWith("app_name")) {
	                temp="\t"+"<string name=\"app_name\">"+appname+"</string>";
	            }
	            buf=buf.append(temp);         
	            buf = buf.append(System.getProperty("line.separator"));

	        }
	        
	        br.close();
	        FileOutputStream fos = new FileOutputStream(file);
	        PrintWriter pw = new PrintWriter(fos);
	        pw.write(buf.toString().toCharArray());
	        pw.flush();
	        pw.close();
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	   }
    public static void editManifest(String filePath,String packageName,String versionCode,String versionName){
    	if(null==filePath||"".equals(filePath))
    		return;
    	try{
        File file =  new File(filePath);
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        StringBuffer buf = new StringBuffer();
        String temp;
        while((temp=br.readLine())!=null){
            if(temp.trim().startsWith("package")) {
                temp="\tpackage=\""+packageName+"\"";
            }else if(temp.trim().startsWith("android:versionCode")){
                temp="\tandroid:versionCode=\""+versionCode+"\"";
            }else if(temp.trim().startsWith("android:versionName")){
                temp="\tandroid:versionName=\""+versionName+"\""+" >";
            }
            buf=buf.append(temp);         
            buf = buf.append(System.getProperty("line.separator"));

        }
        
        br.close();
        FileOutputStream fos = new FileOutputStream(file);
        PrintWriter pw = new PrintWriter(fos);
        pw.write(buf.toString().toCharArray());
        pw.flush();
        pw.close();
    }catch(Exception e){
    	e.printStackTrace();
    }
   }
}
