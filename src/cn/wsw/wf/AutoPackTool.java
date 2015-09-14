package cn.wsw.wf;

import java.awt.EventQueue;
import java.awt.FileDialog;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import cn.wf.wfw.widget.MDeleteComBox;
import cn.wf.wfw.widget.MDeleteComBoxEntry;
import cn.wf.wfw.widget.MItemListener;
import cn.wf.wsw.tool.AFrame;
import cn.wf.wsw.tool.EditFileTools;
import cn.wf.wsw.tool.SaveBean;
import cn.wf.wsw.tool.SaveSeriliationUtils;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.awt.event.AWTEventListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.DropMode;

public class AutoPackTool {

	private JFrame frame;
	Process process;
	PrintWriter processPw;
	InputStream processIs;
	MDeleteComBox baseAddressCombox;
	MDeleteComBox destAddressCombox;
	MDeleteComBox picLocationCombox;
	MDeleteComBox confLocationCombox;
	MDeleteComBox manifetCombox;
	MDeleteComBox stringCombox;
	private JTextField packageTextField;
	private JTextField versionCodeTextField;
	private JTextField versionNameTextField;
	private JTextField appNameTextField;
	private JTextField commandTextField;
	JTextArea outputArea;
	FileDialog filedialog;
	JFileChooser chooser; 
	
	
	JLabel baseAddress;
	JLabel picLocation;
	JLabel confLocation;
	JLabel manifestLocation;
	JLabel stringLocation;
	
	JLabel lblPackage ;
	JLabel lblVersioncode ;
	JLabel lblVersionname;
	JLabel lblAppname ;
	private JButton btnExecute;
	
	JScrollPane jsp;
	private JLabel lblDestaddress;
	private JButton clearbtn;
	
	
	public static String username = null;
	public static String password = null;
	private JButton btnNewButton;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AutoPackTool window = new AutoPackTool();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AutoPackTool() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				String []baseStrs=new String[baseAddressCombox.getItemCount()];
				for(int i=0;i<baseAddressCombox.getItemCount();i++){
					baseStrs[i]=baseAddressCombox.getItemAt(i).toString();
				}
				
				String []destStrs=new String[destAddressCombox.getItemCount()];
				for(int i=0;i<destAddressCombox.getItemCount();i++){
					destStrs[i]=destAddressCombox.getItemAt(i).toString();
				}
				String []picStrs=new String[picLocationCombox.getItemCount()];
				for(int i=0;i<picLocationCombox.getItemCount();i++){
					picStrs[i]=picLocationCombox.getItemAt(i).toString();
				}
				String []confStrs=new String[confLocationCombox.getItemCount()];
				for(int i=0;i<confLocationCombox.getItemCount();i++){
					confStrs[i]=confLocationCombox.getItemAt(i).toString();
				}
				
				String []manifestStrs=new String[manifetCombox.getItemCount()];
				for(int i=0;i<manifetCombox.getItemCount();i++){
					manifestStrs[i]=manifetCombox.getItemAt(i).toString();
				}
				String []stringStrs=new String[stringCombox.getItemCount()];
				for(int i=0;i<stringCombox.getItemCount();i++){
					stringStrs[i]=stringCombox.getItemAt(i).toString();
				}
				SaveBean sb=new SaveBean(baseStrs,baseAddressCombox.getSelectedIndex(),destStrs,destAddressCombox.getSelectedIndex(),
						picStrs,picLocationCombox.getSelectedIndex(),confStrs,confLocationCombox.getSelectedIndex(),
						manifestStrs,manifetCombox.getSelectedIndex(),stringStrs,stringCombox.getSelectedIndex(),packageTextField.getText(),
						versionCodeTextField.getText(),versionNameTextField.getText(),appNameTextField.getText()
						);
				SaveSeriliationUtils.saveDataToFile(sb);
				
				try {
					processPw.close();
					processIs.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		frame.setBounds(100, 100, 588, 753);
		frame.getContentPane().setLayout(null);
		
		filedialog=new FileDialog(new JFrame(), "",FileDialog.LOAD);
		chooser = new JFileChooser();
		
		Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {
		    public void eventDispatched(AWTEvent event) {
		        if (((KeyEvent) event).isControlDown()) {
		            //放入自己的键盘监听事件
		//((KeyEvent) event).getKeyCode();// 获取按键的code
		            //((KeyEvent) event).getKeyChar();// 获取按键的字符
		        	if(((KeyEvent) event).getKeyCode()==KeyEvent.VK_S){
		        		importFile("导入文件");
		        	}else if(((KeyEvent) event).getKeyCode()==KeyEvent.VK_B){
		        		try {
		        			File f=new File("1.txt");
		        			if(f.exists()){
		        				fillDataToWidget("1.txt");
		        				return ;
		        			}
		        			f= new File("a.txt");
		        			if(f.exists()){
		        				fillDataToWidget("a.txt");
		        				return;
		        			}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		        	}
		        }
		    }
		}, AWTEvent.KEY_EVENT_MASK);
		
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(25, 436, 54, 15);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JLabel label = new JLabel("output");
		label.setBounds(0, 0, 54, 15);
		panel_3.add(label);
		

		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(25, 10, 32, 24);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblInput = new JLabel("input");
		lblInput.setBounds(0, 0, 32, 24);
		panel_1.add(lblInput);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 23, 552, 412);
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		baseAddress = new JLabel("baseAddress");
		baseAddress.setBounds(21, 10, 83, 26);
		panel.add(baseAddress);
		
		picLocation = new JLabel("picLocation");
		picLocation.setBounds(21, 82, 83, 26);
		panel.add(picLocation);
		
		confLocation = new JLabel("confLocation");
		confLocation.setBounds(21, 120, 83, 26);
		panel.add(confLocation);
		
		manifestLocation = new JLabel("manifest.xml");
		manifestLocation.setBounds(21, 150, 83, 26);
		panel.add(manifestLocation);
		
		stringLocation = new JLabel("string.xml");
		stringLocation.setBounds(21, 188, 83, 26);
		panel.add(stringLocation);
		MDeleteComBoxEntry[] items = new MDeleteComBoxEntry[] {
	               new MDeleteComBoxEntry(null, "浏览....")
	               };

		SaveBean sb=(SaveBean) SaveSeriliationUtils.restoreDataFromFile();
		MDeleteComBoxEntry []baseEntries=items;
		 int baseIndex=0;
		 
		MDeleteComBoxEntry []destEntries=items;
		int destIndex=0;
		 int picIndex=0;
		 int confIndex=0;
		 int manifestIndex=0;
		 int stringIndex=0;
		MDeleteComBoxEntry []picEntries=items;
		MDeleteComBoxEntry []confEntries=items;
		MDeleteComBoxEntry []manifestEntries=items;
		MDeleteComBoxEntry []stringEntries=items;

		String appName="";
		String versionCode="";
		String versionName="";
		String packageName="";
		if(sb!=null){
			String []baseStrs=sb.getBaseAddress();
			baseEntries=new MDeleteComBoxEntry[baseStrs.length];
			for(int i=0;i<baseStrs.length;i++){
				baseEntries[i]=new MDeleteComBoxEntry(baseStrs[i]);
			}
			baseIndex=sb.getBaseIndex();
			
			String []destStrs=sb.getDestAddress();
			destEntries=new MDeleteComBoxEntry[destStrs.length];
			for(int i=0;i<destStrs.length;i++){
				destEntries[i]=new MDeleteComBoxEntry(destStrs[i]);
			}
			destIndex=sb.getDestIndex();
			
			String []picStrs=sb.getPicLocation();
			picEntries=new MDeleteComBoxEntry[picStrs.length];
			for(int i=0;i<picStrs.length;i++){
				picEntries[i]=new MDeleteComBoxEntry(picStrs[i]);
			}
			picIndex=sb.getPicIndex();
			
			String []confStrs=sb.getConfLocation();
			confEntries=new MDeleteComBoxEntry[confStrs.length];
			for(int i=0;i<confStrs.length;i++){
				confEntries[i]=new MDeleteComBoxEntry(confStrs[i]);
			}
			confIndex=sb.getConfIndex();
			
			String []manifestStrs=sb.getManifestLocation();
			manifestEntries=new MDeleteComBoxEntry[manifestStrs.length];
			for(int i=0;i<manifestStrs.length;i++){
				manifestEntries[i]=new MDeleteComBoxEntry(manifestStrs[i]);
			}
			manifestIndex=sb.getManifestIndex();
			
			String []stringStrs=sb.getStringLocation();
			stringEntries=new MDeleteComBoxEntry[stringStrs.length];
			for(int i=0;i<stringStrs.length;i++){
				stringEntries[i]=new MDeleteComBoxEntry(stringStrs[i]);
			}
			stringIndex=sb.getStringIndex();
			
			appName=sb.getAppName();
			versionCode=sb.getVersionCode();
			versionName=sb.getVersionName();
			packageName=sb.getPackageName();
		}
		
		
		
		baseAddressCombox = new MDeleteComBox(baseEntries);
		baseAddressCombox.setEditable(true);
		baseAddressCombox.setBounds(105, 10, 437, 21);
		addItemEvent("choose base Location",baseAddressCombox);
		baseAddressCombox.setSelectedIndex(baseIndex);
		panel.add(baseAddressCombox);
		
		
		destAddressCombox = new MDeleteComBox(destEntries);
		destAddressCombox.setEditable(true);
		destAddressCombox.setBounds(105, 51, 437, 21);
		addItemEvent("choose dest Location", destAddressCombox);
		destAddressCombox.setSelectedIndex(destIndex);
		panel.add(destAddressCombox);
		
		lblDestaddress = new JLabel("destAddress");
		lblDestaddress.setBounds(21, 46, 83, 26);
		panel.add(lblDestaddress);
		
		
		picLocationCombox = new MDeleteComBox(picEntries);
		picLocationCombox.setEditable(true);
		picLocationCombox.setBounds(105, 85, 437, 21);
		addItemEvent("choose pic Location",picLocationCombox);
		picLocationCombox.setSelectedIndex(picIndex);

		panel.add(picLocationCombox);
		
		confLocationCombox = new MDeleteComBox(confEntries);
		confLocationCombox.setEditable(true);
		confLocationCombox.setBounds(105, 119, 437, 21);
		addItemEvent("choose conf Location",confLocationCombox);
		confLocationCombox.setSelectedIndex(confIndex);
		panel.add(confLocationCombox);
		
		manifetCombox = new MDeleteComBox(manifestEntries);
		manifetCombox.setEditable(true);
		manifetCombox.setBounds(105, 156, 437, 21);
		addItemEvent("choose manifest location",manifetCombox);
		manifetCombox.setSelectedIndex(manifestIndex);
		panel.add(manifetCombox);
		
		stringCombox = new MDeleteComBox(stringEntries);
		stringCombox.setEditable(true);
		stringCombox.setBounds(105, 191, 437, 21);
		addItemEvent("choose string.xml Location",stringCombox);
		stringCombox.setSelectedIndex(stringIndex);
		panel.add(stringCombox);
		
		JLabel lblReplaceParams = new JLabel("replace params:");
		lblReplaceParams.setBounds(21, 213, 108, 15);
		panel.add(lblReplaceParams);
		
		packageTextField = new JTextField(packageName);
		packageTextField.setBounds(105, 238, 437, 21);
		panel.add(packageTextField);
		packageTextField.setColumns(10);
		
		lblPackage = new JLabel("package");
		lblPackage.setBounds(31, 241, 58, 15);
		panel.add(lblPackage);
		
		lblVersioncode = new JLabel("version_code");
		lblVersioncode.setBounds(10, 275, 85, 15);
		panel.add(lblVersioncode);
		
		versionCodeTextField = new JTextField(versionCode);
		versionCodeTextField.setColumns(10);
		versionCodeTextField.setBounds(105, 272, 437, 21);
		panel.add(versionCodeTextField);
		
		lblVersionname = new JLabel("version_name");
		lblVersionname.setBounds(4, 306, 85, 15);
		panel.add(lblVersionname);
		
		versionNameTextField = new JTextField(versionName);
		versionNameTextField.setColumns(10);
		versionNameTextField.setBounds(105, 303, 437, 21);
		panel.add(versionNameTextField);
		
		lblAppname = new JLabel("app_name");
		lblAppname.setBounds(10, 340, 79, 15);
		panel.add(lblAppname);
		
		appNameTextField = new JTextField(appName);
		appNameTextField.setColumns(10);
		appNameTextField.setBounds(105, 337, 437, 21);
		panel.add(appNameTextField);
		
		JButton integrationBtn = new JButton("integration");
		integrationBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//first  replace androidmanifest  the three params
				JFrame jf=new JFrame();
				if(isAllComboxHasEntered()==false){
//					outputList.add("把内容给我输进"+"\n");
//					outputList.setItems(new String[]{"sbsbs"});
					
					JOptionPane.showMessageDialog(null, "把内容给我输进去");
				
					return;
				}
//				if(username==null||password==null||"".equals(username)||"".equals(password)){
//					AFrame af=new AFrame();
//					af.setVisible(true);
//				//username=af.username;
//				//username=af.password;
//					return;
//				}
				//second cheout svn to dest Address
				// svn address
//				processPw.println("svn checkout "+baseAddressCombox.getSelectedItem().toString()+" "+"-r HEAD —depth=infinity —force"+destAddressCombox.getSelectedItem().toString()
//							+"--username "+username+"--password "+password);
//				processPw.flush();
				
				EditFileTools.editManifest(manifetCombox.getSelectedItem().toString(), packageTextField.getText(), versionCodeTextField.getText(), versionNameTextField.getText());
				EditFileTools.editAppName(stringCombox.getSelectedItem().toString(),appNameTextField.getText());
				//second replace the images locations and config files 
				//String picAddress=pictureAddress.getText();
				String confDirectoryPath=confLocationCombox.getSelectedItem().toString();
				String picDirectoryPath=picLocationCombox.getSelectedItem().toString();
			// run the script to ant compile
				processPw.println("@echo off");
				processPw.println("set codepath="+confDirectoryPath);
				processPw.println("set resourcepath="+picDirectoryPath);
				processPw.println("xcopy %resourcepath%\\AndroidManifest\\*.* %codepath%\\AndroidManifest\\/e/h/y");
				processPw.println("xcopy %resourcepath%\\config\\*.* %codepath%\\res\raw\\/e/h/y");
				processPw.println("xcopy %resourcepath%\\picture\\*.* %codepath%\\res\\drawable-hdpi\\/e/h/y");
				processPw.println("xcopy %resourcepath%\\other\\*.* %codepath%\\res\\values\\/e/h/y");
				processPw.println("call D:\\android5.0packet\\ant.bat");
				processPw.println("xcopy %codepath%\\packet\\*.* %resourcepath%\\/e/h/y");
				processPw.println("echo. & pause");
				processPw.flush();
				
				
					  // 将CMD的输入流绑定到显示框中  
		   
				
				
//				
			}
		});
		integrationBtn.setBounds(434, 372, 108, 23);
		panel.add(integrationBtn);
		
		commandTextField = new JTextField();
		commandTextField.setText("dir");
		commandTextField.setColumns(10);
		commandTextField.setBounds(105, 373, 192, 21);
		panel.add(commandTextField);
		
		btnExecute = new JButton("execute");
		btnExecute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				processPw.println(commandTextField.getText());	
				processPw.flush();
			}
		});
		btnExecute.setBounds(299, 372, 75, 23);
		panel.add(btnExecute);
		
		clearbtn = new JButton("clear");
		clearbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearAllItems();
			}
		});
		clearbtn.setBounds(0, 372, 93, 23);
		panel.add(clearbtn);
		
		btnNewButton = new JButton("svn");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(username==null||password==null||"".equals(username)||"".equals(password)){
				AFrame af=new AFrame();
				af.setVisible(true);
			//username=af.username;
			//username=af.password;
				return;
			}
				processPw.println("svn checkout "+baseAddressCombox.getSelectedItem().toString()+" "+"-r HEAD —depth=infinity —force "+destAddressCombox.getSelectedItem().toString()
				+"--username "+username+"--password "+password);
				processPw.flush();
				System.out.println("svn checkout "+baseAddressCombox.getSelectedItem().toString()+" "+"-r HEAD —depth=infinity —force "+destAddressCombox.getSelectedItem().toString()
						+"--username "+username+"--password "+password);
			}
		});
		btnNewButton.setBounds(373, 372, 58, 23);
		panel.add(btnNewButton);
		

		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 445, 552, 245);
		frame.getContentPane().add(panel_2);
		
		outputArea = new JTextArea();
		outputArea.setLineWrap(true);
		outputArea.setWrapStyleWord(true);
		outputArea.setDropMode(DropMode.INSERT);
		outputArea.setBounds(10, 10, 532, 225);
		jsp=new JScrollPane(outputArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		jsp.setBounds(10, 10, 532, 225);
		jsp.setVisible(true);
		panel_2.add(jsp);
		
//		if(sb==null){
//			clearAllItems();
//		}
//		
		try{
			process = Runtime.getRuntime().exec("cmd");
//			processPw=new PrintWriter(process.getOutputStream());
//			processPw.println("chcp 65001");
			processPw.flush();
			  // 将CMD的输入流绑定到显示框中  
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    int size;
                    byte[] buf=new byte[1024];
                    processIs = process.getInputStream();
                    try{
                    while((size = processIs.read(buf)) != -1) {  
                    	String str=new String(buf,0,size,"utf-8");
                    	outputArea.append(str);  
                    	//area显示位置
                    	outputArea.setCaretPosition(outputArea.getDocument().getLength());
                    	System.out.println(str);
                    }                  
                    }catch(Exception e1){
                        e1.printStackTrace();
                        }
                    }
            }).start();    
			}catch(Exception e1){
				e1.printStackTrace();
			}
		
	}
	private void addItemEvent(final String title,final MDeleteComBox comboBox){
		
		MItemListener itemListener=new MItemListener() {
	          @Override
	          public void itemDeleted(MDeleteComBoxEntry entry) {
	              System.out.println("entry deleted:" + entry.getText());
	          }

	          @Override
	          public void itemSelected(MDeleteComBoxEntry entry) {
	        	  if(comboBox.getSelectedItem().toString().equals("浏览....")){
	                  System.out.println("entry selected:" + entry.getText());
	                  if(comboBox==manifetCombox||comboBox==stringCombox){
	                	  chooseFile(title, comboBox);
	                	  return;
	                  }
	                  chooseDirectory("选择文件夹",comboBox);
	        	  	}
	        	  }
	          };
		comboBox.addMItemListener(itemListener);
		
		
	}
	private void importFile(String title){
		JFrame jf=new JFrame();
		FileDialog filedialog=new FileDialog(jf, "",FileDialog.LOAD);
		filedialog.setTitle(title);
		filedialog.setVisible(true);
		String dirPath = filedialog.getDirectory();//获取文件路径  
		String fileName = filedialog.getFile();//获取文件名称  
		String fullFileName=dirPath+fileName;
		try {
			fillDataToWidget(fullFileName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void fillDataToWidget(String filePath) throws Exception{
		if(filePath==null||"".equals(filePath)){
			throw new Exception("文件路径不能为空或者啥");
		}

        File file =  new File(filePath);
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        StringBuffer buf = new StringBuffer();
        StringBuffer originBuf=new StringBuffer();
        String temp;
        while((temp=br.readLine())!=null){
        	
            originBuf=originBuf.append(temp);         
            originBuf = originBuf.append(System.getProperty("line.separator"));
           temp=temp.replace("\\", "\\\\");
            buf=buf.append(temp);         
            buf = buf.append(System.getProperty("line.separator"));

        }
        br.close();
        FileOutputStream fos = new FileOutputStream(file);
        PrintWriter pw = new PrintWriter(fos);
        pw.write(buf.toString().toCharArray());
        pw.flush();
        pw.close();
        fos.close();
        

		
		
		
		Properties properties=new Properties();
		FileInputStream inputFile;
		try {
			inputFile = new FileInputStream(filePath);
	        properties.load(inputFile); 
	        Enumeration e= properties.keys();
	        Iterator it=properties.entrySet().iterator();
	        while(it.hasNext()){
	            Map.Entry entry=(Map.Entry)it.next();
	            String key = (String) entry.getKey();
	            if(key.equals(baseAddress.getText())){
	            	assolateComboxText(baseAddressCombox,entry.getValue().toString());
	            }else if(key.equals(lblDestaddress.getText())){
	            	assolateComboxText(destAddressCombox, entry.getValue().toString());
	            }else if(key.equals(picLocation.getText())){
	            	assolateComboxText(picLocationCombox,entry.getValue().toString());
	            }else if(key.equals(confLocation.getText())){
	            	assolateComboxText(confLocationCombox,entry.getValue().toString());
	            }else if(key.equals(manifestLocation.getText())){
	            	assolateComboxText(manifetCombox,entry.getValue().toString());
	            }else if(key.equals(stringLocation.getText())){
	            	assolateComboxText(stringCombox,entry.getValue().toString());
	            }else if(key.equals(lblPackage.getText())){
	            	assolateTextField(packageTextField, entry.getValue().toString());
	            }else if(key.equals(lblAppname.getText())){
	            	assolateTextField(appNameTextField, entry.getValue().toString());
	            }else if(key.equals(lblVersioncode.getText())){
	            	assolateTextField(versionCodeTextField, entry.getValue().toString());
	            }else if(key.equals(lblVersionname.getText())){
	            	assolateTextField(versionNameTextField, entry.getValue().toString());
	            }else{
	            	System.out.println(key +"   "+ entry.getValue());
	            	throw new Exception("the config file has some problem");
	            }
	            Object value = entry.getValue();
	            System.out.println(key +":"+value);
	        }
	        
	        fos=new FileOutputStream(file);
	        pw=new PrintWriter(fos);
	        pw.write(originBuf.toString().toCharArray());
	        pw.close();		
			fos.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	private void clearAllItems(){
        	baseAddressCombox.clearItems();
//    		addItemEvent("choose base Location",baseAddressCombox);
    		destAddressCombox.clearItems();
//    		addItemEvent("choose dest Location", destAddressCombox);
    		picLocationCombox.clearItems();
//    		addItemEvent("choose pic Location",picLocationCombox);
    		confLocationCombox.clearItems();
//    		addItemEvent("choose conf Location",confLocationCombox);
    		manifetCombox.clearItems();
//    		addItemEvent("choose manifest location",manifetCombox);
    		stringCombox.clearItems();
//    		addItemEvent("choose string.xml Location",stringCombox);
        	
        	packageTextField.setText("");
        	appNameTextField.setText("");
        	versionCodeTextField.setText("");
        	versionNameTextField.setText("");
	}
	private void assolateComboxText(MDeleteComBox comboBox,String text){
		 MDeleteComBoxEntry boxEntry=new MDeleteComBoxEntry(text);
		comboBox.addItem(boxEntry);
		comboBox.setSelectedItem(boxEntry);
	}
	private void assolateTextField(JTextField jTextField,String text){
		jTextField.setText(text);
	}
	private void chooseFile(String title,MDeleteComBox comboBox){


		filedialog.setTitle(title);
		filedialog.setVisible(true);
		String dirPath = filedialog.getDirectory();//获取文件路径  
		String fileName = filedialog.getFile();//获取文件名称  
        //System.out.println(dirPath +"++"+ fileName);  
          
        //如果打开路径 或 目录为空 则返回空  
        if(dirPath == null || fileName == null)  
                return ;  
        MDeleteComBoxEntry insertItem=new MDeleteComBoxEntry(dirPath+fileName);
        comboBox.addItem(insertItem);
        comboBox.setSelectedItem(insertItem);
        filedialog.setVisible(false);
	}
	private void chooseDirectory(String title, MDeleteComBox comboBox) {
		// TODO Auto-generated method stub
//		Shell shell=new Shell();
//		DirectoryDialog folderdlg=new DirectoryDialog(shell);
//		  //设置文件对话框的标题
//	      folderdlg.setText("文件选择");
//	      //设置初始路径
//	      //设置对话框提示文本信息
//	      folderdlg.setMessage(title);
//	      //打开文件对话框，返回选中文件夹目录
//	      String selecteddir=folderdlg.open();
		String selecteddir=null;
		
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);//设置只能选择目录
		int returnVal = chooser.showOpenDialog(new JPanel());
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			selecteddir =chooser.getSelectedFile().getPath() ;

		        MDeleteComBoxEntry insertItem=new MDeleteComBoxEntry(selecteddir);
		        comboBox.addItem(insertItem);
		        comboBox.setSelectedItem(insertItem);
//				  System.out.println ( comboBox.getName() );
				  chooser.hide();
		        return;
			}
	        //如果打开路径 或 目录为空 则返回空  
//	        if(selecteddir == null )  
//	                return ;  
//	        MDeleteComBoxEntry insertItem=new MDeleteComBoxEntry(selecteddir);
//	        comboBox.addItem(insertItem);
//	        comboBox.setSelectedItem(insertItem);
////	        comboBox.insertItemAt(item, index);
////	        for(int i=0;i<comboBox.getItemCount();i++){
////	        	System.out.println(comboBox.getItemAt(i).toString());
////	        }
////	        System.out.println("-------------------------");
	}
	private boolean isAllComboxHasEntered(){
		 boolean flag= (picLocationCombox.getItemCount()!=1) &&
				 (confLocationCombox.getItemCount()!=1)&&(manifetCombox.getItemCount()!=1)&&(stringCombox.getItemCount()!=1);
		 if(false==flag){
			 return flag;
		 }
		 flag=!packageTextField.getText().equals("")&& !versionCodeTextField.getText().equals("")&&!versionNameTextField.getText().equals("")
				 &&!appNameTextField.getText().equals("")&&!commandTextField.getText().equals("");
		return flag;
	}

}
