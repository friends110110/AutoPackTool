package cn.wf.wsw;

import java.awt.FileDialog;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.Arrays;

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

import cn.wf.wsw.tool.SaveSeriliationUtils;

public class AutoPackPanel {

	protected Shell shlAutoPackageIntegration;
	private Text packageParam;
	private Text versionCodeParam;
	private Text versionNameParam;
	private Text appNameParam;
	private Combo pictureAddress;
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
		String[] obj=(String[]) SaveSeriliationUtils.restoreDataFromFile();
		if(null==obj)
			return;
		pictureAddress.setItems(obj);
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
				String []save=pictureAddress.getItems();
				SaveSeriliationUtils.saveDataToFile(save);
			}
		});
		shlAutoPackageIntegration.setSize(812, 499);
		shlAutoPackageIntegration.setText("Auto Package Integration Tool");
		shlAutoPackageIntegration.setLayout(null);

		
		Group input = new Group(shlAutoPackageIntegration, SWT.NONE);
		input.setText("\u8F93\u5165");
		input.setBounds(10, 10, 773, 264);
		
		Label lblNewLabel = new Label(input, SWT.NONE);
		lblNewLabel.setBounds(54, 24, 80, 20);
		lblNewLabel.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 12, SWT.NORMAL));
		lblNewLabel.setText("\u57FA\u7EBF\u5730\u5740\uFF1A");
		
		Label label = new Label(input, SWT.NONE);
		label.setBounds(85, 50, 48, 20);
		label.setText("\u56FE\u7247\uFF1A");
		label.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 12, SWT.NORMAL));
		
		Label label_1 = new Label(input, SWT.NONE);
		label_1.setBounds(54, 82, 80, 20);
		label_1.setText("\u914D\u7F6E\u6587\u4EF6\uFF1A");
		label_1.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 12, SWT.NORMAL));
		

		
		
		Button configFileSearch = new Button(input, SWT.NONE);
		configFileSearch.setBounds(644, 75, 80, 27);
		configFileSearch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		configFileSearch.setText("\u6D4F\u89C8");
		
		Button directOverride = new Button(input, SWT.CHECK);
		directOverride.setBounds(128, 108, 98, 17);
		directOverride.setText("\u76F4\u63A5\u8986\u76D6");
		
		Label label_2 = new Label(input, SWT.NONE);
		label_2.setBounds(54, 129, 80, 20);
		label_2.setText("\u4FEE\u6539\u53C2\u6570\uFF1A");
		label_2.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 12, SWT.NORMAL));
		
		packageParam = new Text(input, SWT.BORDER);
		packageParam.setBounds(264, 130, 250, 23);
		
		Label lblPackage = new Label(input, SWT.NONE);
		lblPackage.setBounds(178, 129, 80, 23);
		lblPackage.setText("package =");
		lblPackage.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 12, SWT.NORMAL));
		
		Label lblVersioncode = new Label(input, SWT.NONE);
		lblVersioncode.setBounds(145, 160, 117, 23);
		lblVersioncode.setText("version_code =");
		lblVersioncode.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 12, SWT.NORMAL));
		
		versionCodeParam = new Text(input, SWT.BORDER);
		versionCodeParam.setBounds(264, 160, 250, 23);
		
		versionNameParam = new Text(input, SWT.BORDER);
		versionNameParam.setBounds(264, 189, 250, 23);
		
		Label lblVersionname = new Label(input, SWT.NONE);
		lblVersionname.setBounds(145, 190, 117, 23);
		lblVersionname.setText("version_name=");
		lblVersionname.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 12, SWT.NORMAL));
		
		Label lblappname = new Label(input, SWT.NONE);
		lblappname.setBounds(54, 217, 208, 20);
		lblappname.setText("\u5BA2\u6237\u7AEF\u540D\u79F0\uFF08app_name\uFF09=");
		lblappname.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 12, SWT.NORMAL));
		
		appNameParam = new Text(input, SWT.BORDER);
		appNameParam.setBounds(264, 218, 250, 23);
		
		Combo baseAddress = new Combo(input, SWT.NONE);
		baseAddress.setBounds(129, 19, 509, 25);
		baseAddress.setItems(new String[] {});
		
		pictureAddress = new Combo(input, SWT.NONE);
		pictureAddress.setItems(new String[] {});
		pictureAddress.setBounds(129, 51, 509, 25);
		
		Combo configFileAddress = new Combo(input, SWT.NONE);
		configFileAddress.setItems(new String[] {});
		configFileAddress.setBounds(129, 82, 509, 25);
		
		Group output = new Group(shlAutoPackageIntegration, SWT.NONE);
		output.setText("\u8F93\u51FA");
		output.setBounds(10, 280, 773, 153);
		output.setLayout(new FormLayout());
		
		Label label_3 = new Label(output, SWT.NONE);
		label_3.setText("\u5305\u540D\uFF1A");
		label_3.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 12, SWT.NORMAL));
		FormData fd_label_3 = new FormData();
		fd_label_3.top = new FormAttachment(0, 10);
		fd_label_3.left = new FormAttachment(0, 51);
		label_3.setLayoutData(fd_label_3);
		
		
		Button pictureSearch = new Button(input, SWT.NONE);
		pictureSearch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Frame f=new Frame();
				FileDialog fd=new FileDialog(f, "Í¼Æ¬²éÕÒ",FileDialog.LOAD);
				fd.setVisible(true);
			  String dirPath = fd.getDirectory();//»ñÈ¡ÎÄ¼þÂ·¾¶  
              String fileName = fd.getFile();//»ñÈ¡ÎÄ¼þÃû³Æ  
                //System.out.println(dirPath +"++"+ fileName);  
                  
                //Èç¹û´ò¿ªÂ·¾¶ »ò Ä¿Â¼Îª¿Õ Ôò·µ»Ø¿Õ  
                if(dirPath == null || fileName == null)  
                        return ;  
                String [] historyStrings=pictureAddress.getItems();
                String [] newHis=new String[historyStrings.length+1];
                newHis[0]=dirPath+fileName;
                if(null!=historyStrings){
                	for(int i=0;i<historyStrings.length;i++){
                		newHis[i+1]=historyStrings[i];
                	}
                pictureAddress.setItems(newHis);
                }
                pictureAddress.setText(dirPath+fileName);
			}
			
		});

		pictureSearch.setBounds(644, 49, 80, 27);
		pictureSearch.setText("\u6D4F\u89C8");
	}
}
