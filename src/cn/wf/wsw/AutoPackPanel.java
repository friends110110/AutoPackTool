package cn.wf.wsw;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class AutoPackPanel {

	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private Text text_6;

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
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(849, 525);
		shell.setText("SWT Application");
		shell.setLayout(null);
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(186, 26, 509, 23);
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 12, SWT.NORMAL));
		lblNewLabel.setBounds(95, 25, 80, 20);
		lblNewLabel.setText("\u57FA\u7EBF\u4EE3\u7801\uFF1A");
		
		Button button = new Button(shell, SWT.NONE);
		button.setBounds(697, 24, 80, 27);
		button.setText("\u6D4F\u89C8");
		
		Label label = new Label(shell, SWT.NONE);
		label.setText("\u56FE\u7247\uFF1A");
		label.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 12, SWT.NORMAL));
		label.setBounds(127, 56, 48, 20);
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBounds(186, 57, 509, 23);
		
		Button button_1 = new Button(shell, SWT.NONE);
		button_1.setText("\u6D4F\u89C8");
		button_1.setBounds(697, 55, 80, 27);
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setText("\u56FE\u7247\uFF1A");
		label_1.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 12, SWT.NORMAL));
		label_1.setBounds(127, 89, 48, 20);
		
		text_2 = new Text(shell, SWT.BORDER);
		text_2.setBounds(186, 90, 509, 23);
		
		Button button_2 = new Button(shell, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		button_2.setText("\u6D4F\u89C8");
		button_2.setBounds(697, 86, 80, 27);
		
		Button button_3 = new Button(shell, SWT.CHECK);
		button_3.setBounds(186, 119, 98, 17);
		button_3.setText("\u76F4\u63A5\u8986\u76D6");
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setText("\u4FEE\u6539\u53C2\u6570\uFF1A");
		label_2.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 12, SWT.NORMAL));
		label_2.setBounds(95, 140, 80, 20);
		
		Label lblPackage = new Label(shell, SWT.NONE);
		lblPackage.setText("package =");
		lblPackage.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 12, SWT.NORMAL));
		lblPackage.setBounds(219, 140, 80, 23);
		
		text_3 = new Text(shell, SWT.BORDER);
		text_3.setBounds(305, 141, 250, 23);
		
		Label lblVersioncode = new Label(shell, SWT.NONE);
		lblVersioncode.setText("version_code =");
		lblVersioncode.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 12, SWT.NORMAL));
		lblVersioncode.setBounds(186, 171, 117, 23);
		
		text_4 = new Text(shell, SWT.BORDER);
		text_4.setBounds(305, 171, 250, 23);
		
		Label lblVersionname = new Label(shell, SWT.NONE);
		lblVersionname.setText("version_name=");
		lblVersionname.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 12, SWT.NORMAL));
		lblVersionname.setBounds(186, 201, 117, 23);
		
		text_5 = new Text(shell, SWT.BORDER);
		text_5.setBounds(305, 200, 250, 23);
		
		Label lblappname = new Label(shell, SWT.NONE);
		lblappname.setText("\u5BA2\u6237\u7AEF\u540D\u79F0\uFF08app_name\uFF09=");
		lblappname.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 12, SWT.NORMAL));
		lblappname.setBounds(95, 228, 208, 20);
		
		text_6 = new Text(shell, SWT.BORDER);
		text_6.setBounds(305, 229, 250, 23);

	}
}
