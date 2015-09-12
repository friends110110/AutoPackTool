package cn.wsw.wf;

import java.awt.AWTEvent;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class AutoPackKit {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AutoPackKit window = new AutoPackKit();
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
	public AutoPackKit() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 586, 639);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	
		Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {
		    public void eventDispatched(AWTEvent event) {
		        if (((KeyEvent) event).isControlDown()&&((KeyEvent) event).getKeyCode()==KeyEvent.VK_S) {
		        
		            //放入自己的键盘监听事件
		//((KeyEvent) event).getKeyCode();// 获取按键的code
		            //((KeyEvent) event).getKeyChar();// 获取按键的字符
		        }
		    }
		}, AWTEvent.KEY_EVENT_MASK);
		
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(25, 0, 32, 24);
		frame.getContentPane().add(panel);
		
		JLabel label = new JLabel("input");
		label.setBounds(0, 0, 32, 24);
		panel.add(label);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 13, 552, 325);
		frame.getContentPane().add(panel_1);
		
		JLabel label_1 = new JLabel("base address");
		label_1.setBounds(21, 28, 83, 26);
		panel_1.add(label_1);
		
		JLabel label_2 = new JLabel("pic location");
		label_2.setBounds(21, 64, 83, 26);
		panel_1.add(label_2);
		
		JLabel label_3 = new JLabel("conf location");
		label_3.setBounds(21, 102, 83, 26);
		panel_1.add(label_3);
		
		JLabel label_4 = new JLabel("manifest.xml");
		label_4.setBounds(21, 132, 83, 26);
		panel_1.add(label_4);
		
		JLabel label_5 = new JLabel("string.xml");
		label_5.setBounds(21, 170, 83, 26);
		panel_1.add(label_5);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setEditable(true);
		comboBox.setBounds(105, 31, 437, 21);
		panel_1.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setEditable(true);
		comboBox_1.setBounds(105, 67, 437, 21);
		panel_1.add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setEditable(true);
		comboBox_2.setBounds(105, 105, 437, 21);
		panel_1.add(comboBox_2);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setEditable(true);
		comboBox_3.setBounds(105, 138, 437, 21);
		panel_1.add(comboBox_3);
		
		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setEditable(true);
		comboBox_4.setBounds(105, 173, 437, 21);
		panel_1.add(comboBox_4);
		
		JLabel label_6 = new JLabel("replace params:");
		label_6.setBounds(10, 197, 108, 15);
		panel_1.add(label_6);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(105, 219, 160, 21);
		panel_1.add(textField);
		
		JLabel label_7 = new JLabel("package=");
		label_7.setBounds(31, 222, 221, 15);
		panel_1.add(label_7);
		
		JLabel label_8 = new JLabel("version_code=");
		label_8.setBounds(275, 222, 85, 15);
		panel_1.add(label_8);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(370, 219, 172, 21);
		panel_1.add(textField_1);
		
		JLabel label_9 = new JLabel("version_name=");
		label_9.setBounds(21, 250, 85, 15);
		panel_1.add(label_9);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(105, 250, 160, 21);
		panel_1.add(textField_2);
		
		JLabel label_10 = new JLabel("app_name=");
		label_10.setBounds(285, 253, 58, 15);
		panel_1.add(label_10);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(370, 260, 172, 21);
		panel_1.add(textField_3);
		
		JButton button = new JButton("integration");
		button.setBounds(426, 291, 116, 23);
		panel_1.add(button);
		
		textField_4 = new JTextField();
		textField_4.setText("ant");
		textField_4.setColumns(10);
		textField_4.setBounds(323, 291, 93, 21);
		panel_1.add(textField_4);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(94, 281, 93, 23);
		panel_1.add(btnNewButton);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(25, 340, 54, 15);
		frame.getContentPane().add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblOutput = new JLabel("output");
		lblOutput.setBounds(0, 0, 54, 15);
		panel_4.add(lblOutput);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 348, 552, 234);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 10, 532, 214);
		panel_2.add(textArea);
		

	}

}
