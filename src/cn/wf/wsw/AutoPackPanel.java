package cn.wf.wsw;

import java.awt.Dialog;
import java.awt.FileDialog;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;

import cn.wf.wsw.tool.EditFileTools;
import cn.wf.wsw.tool.SaveBean;
import cn.wf.wsw.tool.SaveSeriliationUtils;

import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.FormText;

public class AutoPackPanel {

	protected Shell shlAutoPackageIntegration;
	private Text packageParam;
	private Text versionCodeParam;
	private Text versionNameParam;
	private Combo baseAddress;
	private Text appNameParam;
	private Combo pictureAddress;
	private Combo configFileAddress;
	private Button directOverride;
	private Combo manifestAddress;
	private Combo stringAddress;
	private Text commandStr;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AutoPackPanel window = new AutoPackPanel();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlAutoPackageIntegration.open();
		shlAutoPackageIntegration.layout();
		restoreDataToView();
		while (!shlAutoPackageIntegration.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	private void restoreDataToView(){
		SaveBean obj=(SaveBean) SaveSeriliationUtils.restoreDataFromFile();
		if(null==obj)
			return;
		if(obj.getBaseAddress()!=null){
			baseAddress.setItems(obj.getBaseAddress());
		}
		if(obj.getPictureAddress()!=null){
			pictureAddress.setItems(obj.getPictureAddress());
		}
		if(obj.getConfigFileAddress()!=null){
			configFileAddress.setItems(obj.getConfigFileAddress());
		}
		if(obj.isDirectOverride()!=false){
			directOverride.setEnabled(obj.isDirectOverride());
		}
		if(obj.getManifestAddress()!=null){
			manifestAddress.setItems(obj.getManifestAddress());
		}
		if(obj.getStringAddress()!=null){
			stringAddress.setItems(obj.getStringAddress());
		}
	}
	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlAutoPackageIntegration = new Shell();
		shlAutoPackageIntegration.addShellListener(new ShellAdapter() {
			@Override
			public void shellClosed(ShellEvent e) {
				//save the data to the file
				SaveBean sb=new SaveBean(baseAddress.getItems(),pictureAddress.getItems(),configFileAddress.getItems(),directOverride.isEnabled(),manifestAddress.getItems(),stringAddress.getItems());
				SaveSeriliationUtils.saveDataToFile(sb);
			}
		});
		shlAutoPackageIntegration.setSize(812, 536);
		shlAutoPackageIntegration.setText("Auto Package Integration Tool");
		shlAutoPackageIntegration.setLayout(null);

		
		Group input = new Group(shlAutoPackageIntegration, SWT.NONE);
		input.setText("\u8F93\u5165");
		input.setBounds(10, 10, 773, 340);
		
		Label lblNewLabel = new Label(input, SWT.NONE);
		lblNewLabel.setBounds(43, 24, 80, 20);
		lblNewLabel.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		lblNewLabel.setText("\u57FA\u7EBF\u5730\u5740\uFF1A");
		
		Label label = new Label(input, SWT.NONE);
		label.setBounds(75, 50, 48, 20);
		label.setText("\u56FE\u7247\uFF1A");
		label.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		
		Label label_1 = new Label(input, SWT.NONE);
		label_1.setBounds(43, 76, 80, 20);
		label_1.setText("\u914D\u7F6E\u6587\u4EF6\uFF1A");
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		

		
		
		Button configFileSearch = new Button(input, SWT.NONE);
		configFileSearch.setBounds(644, 75, 80, 27);
		configFileSearch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				chooseFile("文件查找", configFileAddress);
				
			}
		});
		configFileSearch.setText("\u6D4F\u89C8");
		
		directOverride = new Button(input, SWT.CHECK);
		directOverride.setBounds(128, 108, 98, 17);
		directOverride.setText("\u76F4\u63A5\u8986\u76D6");
		
		Label label_2 = new Label(input, SWT.NONE);
		label_2.setBounds(43, 215, 80, 20);
		label_2.setText("\u4FEE\u6539\u53C2\u6570\uFF1A");
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		
		packageParam = new Text(input, SWT.BORDER);
		packageParam.setBounds(253, 216, 250, 23);
		
		Label lblPackage = new Label(input, SWT.NONE);
		lblPackage.setBounds(167, 215, 80, 23);
		lblPackage.setText("package =");
		lblPackage.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		
		Label lblVersioncode = new Label(input, SWT.NONE);
		lblVersioncode.setBounds(134, 246, 117, 23);
		lblVersioncode.setText("version_code =");
		lblVersioncode.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		
		versionCodeParam = new Text(input, SWT.BORDER);
		versionCodeParam.setBounds(253, 246, 250, 23);
		
		versionNameParam = new Text(input, SWT.BORDER);
		versionNameParam.setBounds(253, 275, 250, 23);
		
		Label lblVersionname = new Label(input, SWT.NONE);
		lblVersionname.setBounds(134, 276, 117, 23);
		lblVersionname.setText("version_name=");
		lblVersionname.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		
		Label lblappname = new Label(input, SWT.NONE);
		lblappname.setBounds(43, 303, 208, 20);
		lblappname.setText("\u5BA2\u6237\u7AEF\u540D\u79F0\uFF08app_name\uFF09=");
		lblappname.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		
		appNameParam = new Text(input, SWT.BORDER);
		appNameParam.setBounds(253, 304, 250, 23);
		
		baseAddress = new Combo(input, SWT.NONE);
		baseAddress.setBounds(129, 19, 509, 25);
		baseAddress.setItems(new String[] {});
		
		pictureAddress = new Combo(input, SWT.NONE);
		pictureAddress.setItems(new String[] {});
		pictureAddress.setBounds(129, 51, 509, 25);
		
		configFileAddress = new Combo(input, SWT.NONE);
		configFileAddress.setItems(new String[] {});
		configFileAddress.setBounds(129, 82, 509, 25);
		
		Group output = new Group(shlAutoPackageIntegration, SWT.NONE);
		output.setText("\u8F93\u51FA");
		output.setBounds(10, 356, 773, 130);
		output.setLayout(new FormLayout());
		
		
		Button pictureSearch = new Button(input, SWT.NONE);
		pictureSearch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				chooseFile("搜索图片", pictureAddress);
			
			}
		});

		pictureSearch.setBounds(644, 49, 80, 27);
		pictureSearch.setText("\u6D4F\u89C8");
		
		manifestAddress = new Combo(input, SWT.NONE);
		manifestAddress.setItems(new String[] {});
		manifestAddress.setBounds(129, 131, 509, 25);
		
		Label lblAndroidmanifest = new Label(input, SWT.NONE);
		lblAndroidmanifest.setText("Manifest");
		lblAndroidmanifest.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		lblAndroidmanifest.setBounds(43, 136, 80, 20);
		
		Button manifestSearch = new Button(input, SWT.NONE);
		manifestSearch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				chooseFile("搜索manifest", manifestAddress);
                
			}
		});
		manifestSearch.setText("\u6D4F\u89C8");
		manifestSearch.setBounds(644, 129, 80, 27);
		
		Button inteButton = new Button(input, SWT.NONE);
		inteButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//first  replace androidmanifest  the three params
				JFrame jf=new JFrame();
				if(configFileAddress.getText().equals("")){
//					outputList.add("把内容给我输进"+"\n");
//					outputList.setItems(new String[]{"sbsbs"});
					
					JOptionPane.showMessageDialog(null, "把内容给我输进去");
				
					return;
				}
				EditFileTools.editManifest(configFileAddress.getText(), packageParam.getText(), versionCodeParam.getText(), versionNameParam.getText());
				EditFileTools.editAppName(stringAddress.getText(),appNameParam.getText());
				//second replace the images locations and config files 
				String picAddress=pictureAddress.getText();
				String confFileAddress=configFileAddress.getText();
			// run the script to ant compile
				BufferedReader br=null;
				try {
					Process p = Runtime.getRuntime().exec(commandStr.getText());
					  br = new BufferedReader(new InputStreamReader(p.getInputStream()));  
			            String line = null;  
			            StringBuilder sb = new StringBuilder();  
			            while ((line = br.readLine()) != null) {  
//			            	outputList.add(line+"\n");
			            	//这里
			            	//sb.append(line + "\n");  
			            }  
			            System.out.println(sb.toString());  
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}   finally  
		        {  
		            if (br != null)  
		            {  
		                try {  
		                    br.close();  
		                } catch (Exception e2) {  
		                    e2.printStackTrace();  
		                }  
		            }  
		        }  
			}
		});
		inteButton.setText("\u96C6\u6210");
		inteButton.setBounds(644, 303, 80, 27);
		
		Label lblStringxml = new Label(input, SWT.NONE);
		lblStringxml.setText("string.xml");
		lblStringxml.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		lblStringxml.setBounds(43, 169, 80, 27);
		
		Button stringSearch = new Button(input, SWT.NONE);
		stringSearch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				chooseFile("查找string.xml", stringAddress);
			}
		});
		stringSearch.setText("\u6D4F\u89C8");
		stringSearch.setBounds(644, 162, 80, 27);
		
		stringAddress = new Combo(input, SWT.NONE);
		stringAddress.setItems(new String[] {});
		stringAddress.setBounds(129, 164, 509, 25);
		stringAddress.setText("ant");
		
		commandStr = new Text(input, SWT.BORDER);
		commandStr.setText("ant");
		commandStr.setBounds(642, 246, 85, 23);
	}
	private void chooseFile(String title,Combo combo){
		Frame f=new Frame();
		FileDialog fd=new FileDialog(f, title,FileDialog.LOAD);
		fd.setVisible(true);
	  String dirPath = fd.getDirectory();//获取文件路径  
      String fileName = fd.getFile();//获取文件名称  
        //System.out.println(dirPath +"++"+ fileName);  
          
        //如果打开路径 或 目录为空 则返回空  
        if(dirPath == null || fileName == null)  
                return ;  
        String [] historyStrings=combo.getItems();
        String [] newHis=new String[historyStrings.length+1];
        newHis[0]=dirPath+fileName;
        if(null!=historyStrings){
        	for(int i=0;i<historyStrings.length;i++){
        		newHis[i+1]=historyStrings[i];
        	}
        	/*
        	 * 加入到下拉框
        	 * */
        	combo.setItems(newHis);
        }
        combo.setText(dirPath+fileName);
	}
}
