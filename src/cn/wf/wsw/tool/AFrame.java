package cn.wf.wsw.tool;
import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JTextField;

import cn.wsw.wf.AutoPackTool;

import javax.swing.JButton;
import java.awt.Point;
import java.awt.Dimension;
import javax.swing.JPasswordField;

public class AFrame extends JFrame
{

    private static final long serialVersionUID = 1L;

    private JPanel jContentPane = null;

    private JLabel jLabelUser = null;

    private JLabel jLabelPassword = null;

    private JTextField jTextFieldUser = null;

    private JButton jButtonConfirm = null;

    private JButton jButtonCancle = null;
    
    private AFrame mainFrame=null;

    private JPasswordField jPasswordField = null;

    public static String username=null;
    public static String password=null;
    /**
     * This is the default constructor
     */
    public AFrame()
    {
        super();
        initialize();
        mainFrame=this;
    }

    /**
     * This method initializes this
     * 
     * @return void
     */
    private void initialize()
    {
        this.setSize(248, 192);
        this.setContentPane(getJContentPane());
        this.setTitle("GUI-SWING");
    }

    /**
     * This method initializes jContentPane
     * 
     * @return javax.swing.JPanel
     */
    private JPanel getJContentPane()
    {
        if (jContentPane == null)
        {
            jLabelPassword = new JLabel();
            jLabelPassword.setBounds(new Rectangle(11, 63, 66, 18));
            jLabelPassword.setText("登录密码:");
            jLabelUser = new JLabel();
            jLabelUser.setBounds(new Rectangle(8, 30, 67, 18));
            jLabelUser.setText("登录帐号:");
            jContentPane = new JPanel();
            jContentPane.setLayout(null);
            jContentPane.add(jLabelUser, null);
            jContentPane.add(jLabelPassword, null);
            jContentPane.add(getJTextFieldUser(), null);
            jContentPane.add(getJButtonConfirm(), null);
            jContentPane.add(getJButtonCancle(), null);
            jContentPane.add(getJPasswordField(), null);
        }
        return jContentPane;
    }

    /**
     * This method initializes jTextFieldUser    
     *     
     * @return javax.swing.JTextField    
     */
    private JTextField getJTextFieldUser()
    {
        if (jTextFieldUser == null)
        {
            jTextFieldUser = new JTextField();
            jTextFieldUser.setBounds(new Rectangle(87, 29, 121, 22));
        }
        return jTextFieldUser;
    }

    /**
     * This method initializes jButtonConfirm    
     *     
     * @return javax.swing.JButton    
     */
    private JButton getJButtonConfirm()
    {
        if (jButtonConfirm == null)
        {
            jButtonConfirm = new JButton();
            jButtonConfirm.setText("确定");
            jButtonConfirm.setSize(new Dimension(60, 28));
            jButtonConfirm.setLocation(new Point(36, 97));
            jButtonConfirm.addActionListener(new java.awt.event.ActionListener()
            {
                public void actionPerformed(java.awt.event.ActionEvent e)
                {
                     username=mainFrame.jTextFieldUser.getText().trim().toLowerCase();//获取用户名
                     password=new String(mainFrame.jPasswordField.getPassword());//获取密码
                    System.out.println(username+"  "+password);
                    AutoPackTool.username=username;
                    AutoPackTool.password=password;

                    setVisible(false);
                }
            });
        }
        return jButtonConfirm;
    }

    /**
     * This method initializes jButtonCancle    
     *     
     * @return javax.swing.JButton    
     */
    private JButton getJButtonCancle()
    {
        if (jButtonCancle == null)
        {
            jButtonCancle = new JButton();
            jButtonCancle.setBounds(new Rectangle(140, 98, 60, 28));
            jButtonCancle.setText("取消");
            jButtonCancle.addActionListener(new java.awt.event.ActionListener()
            {
                public void actionPerformed(java.awt.event.ActionEvent e)
                {
                    mainFrame.jTextFieldUser.setText("");
                    mainFrame.jPasswordField.setText("");
                }
            });
        }
        return jButtonCancle;
    }

    /**
     * This method initializes jPasswordField    
     *     
     * @return javax.swing.JPasswordField    
     */
    private JPasswordField getJPasswordField()
    {
        if (jPasswordField == null)
        {
            jPasswordField = new JPasswordField();
            jPasswordField.setBounds(new Rectangle(86, 61, 123, 22));
            jPasswordField.setEchoChar('*');
        }
        return jPasswordField;
    }

//    public static void main(String args[])
//    {
//        new AFrame().setVisible(true);
//    }
}  //  @jve:decl-index=0:visual-constraint="10,10"

