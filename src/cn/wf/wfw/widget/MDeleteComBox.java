package cn.wf.wfw.widget;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

import java.util.List;
import java.util.*;

import javax.swing.*;
import javax.swing.plaf.basic.*;

/**
 * 具有删除功能的下拉菜单按钮。类似于QQ登录框的用户输入框
 * 
 * @Author Michael.Wang
 * @Email wqjsir@foxmail.com
 * @version 1.0 2011-5-16上午10:15:27
 **/
public class MDeleteComBox extends JComboBox {

    private static final long serialVersionUID = 1L;

    private boolean bDefaultIconShow;   //是否默认显示图标如果有的话
    private Icon defaultIcon;           //默认图标
    private int maxIconSize = 24;
    private int mixIconSize = 16;
    private List<MItemListener> itemListeners = new ArrayList<MItemListener>();

    public MDeleteComBox(ComboBoxModel aModel) {
        super(aModel);
        init();
    }

    public MDeleteComBox(MDeleteComBoxEntry[] entrys) {
        super(entrys);
        init();
    }

    public MDeleteComBox(Vector<MDeleteComBoxEntry> items) {
        super(items);
        init();
    }

    void init() {
        //设置弹出框自己改写
        setUI(new MDeleteComBoxUI());
        setEditor(new MDeleteComBoxEditor());
    }

    public Icon getDefaultIcon() {
        return defaultIcon;
    }

    public void setDefaultIcon(Icon defaultIcon) {
        this.defaultIcon = defaultIcon;
    }

    /**
     * 调整图片大小。该方法为一个工具类中的方法。这里直接copy过来
     * 
     * @param icon
     * @param maxSize
     * @return
     */
    public static ImageIcon resizeImageIcon(ImageIcon icon, int maxSize) {
        Image orgImage = icon.getImage();
        Image newImage = orgImage;
        if (icon.getIconHeight() > maxSize && icon.getIconWidth() > maxSize) {
            // 缩小图像，使其高或宽为maxSize
            if (icon.getIconHeight() > icon.getIconWidth()) {
                newImage = newImage.getScaledInstance(maxSize, -1,
                        Image.SCALE_SMOOTH);
            } else {
                newImage = newImage.getScaledInstance(-1, maxSize,
                        Image.SCALE_SMOOTH);
            }

        }

        // 等待Image载入完成。
        while (!Toolkit.getDefaultToolkit()
                .prepareImage(newImage, -1, -1, null)) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }

        }
        ;
        int width = newImage.getWidth(null);
        int height = newImage.getHeight(null);
        if (height > maxSize || width > maxSize) {
            width = width > maxSize ? maxSize : width;
            height = height > maxSize ? maxSize : height;
            // 对图像进行裁剪，使其高和宽一致
            CropImageFilter cropFilter = new CropImageFilter(0, 0, width,
                    height);
            newImage = Toolkit.getDefaultToolkit().createImage(
                    new FilteredImageSource(newImage.getSource(), cropFilter));
        }

        if (newImage != orgImage) {
            // 如果image发生改变，则重新生成icon
            icon = new ImageIcon(newImage);
        }
        return icon;
    }

    public boolean isBDefaultIconShow() {
        return bDefaultIconShow;
    }

    public void setBDefaultIconShow(boolean defaultIconShow) {
        bDefaultIconShow = defaultIconShow;
    }

    public void addMItemListener(MItemListener listener) {
        itemListeners.add(listener);
    }

    public void removeMItemListener(MItemListener listener) {
        itemListeners.remove(listener);
    }

    protected void fireMItemSelected(MDeleteComBoxEntry entry) {
        for (MItemListener _listener : itemListeners) {
            _listener.itemSelected(entry);
        }
    }

    protected void fireMItemDeleted(MDeleteComBoxEntry entry) {
        for (MItemListener _listener : itemListeners) {
            _listener.itemDeleted(entry);
        }
    }

    class MDeleteComBoxUI extends BasicComboBoxUI {
        //创建
        @Override
        protected ListCellRenderer createRenderer() {
            return new ComboBoxListCellRender();
        }
        //改写弹出框 自己panel实现 改写 是否显示隐藏等
        @Override
        protected ComboPopup createPopup() {
            return new MDeleteComBoxPopup(comboBox);
        }
    }

    private class ComboBoxListCellRender extends DefaultListCellRenderer {
        private static final long serialVersionUID = 1L;

        public ComboBoxListCellRender() {

        }

        public Component getListCellRendererComponent(JList list, Object value,
                int index, boolean isSelected, boolean cellHasFocus) {
           
            this.setText(value == null ? "" : value.toString());
            return this;
        }
    }

    class MDeleteComBoxEditor implements ComboBoxEditor {
        final protected JTextField editor;
        protected JPanel editorPanel;
        protected JLabel lbIcon;
        protected Object objItem;

        public MDeleteComBoxEditor() {
            editor = new JTextField("");
            editorPanel = new JPanel();
            lbIcon = new JLabel();

            editorPanel.setBackground(Color.white);

            editor.setBorder(null);

            editorPanel.setLayout(new BorderLayout(2, 2));
            editorPanel.add(lbIcon, BorderLayout.WEST);
            editorPanel.add(editor, BorderLayout.CENTER);
        }

        public void addActionListener(ActionListener l) {
            listenerList.add(ActionListener.class, l);
        }

        public Component getEditorComponent() {
            return editorPanel;
        }

        public Object getItem() {
            return objItem;
        }

        public void removeActionListener(ActionListener l) {
            listenerList.remove(ActionListener.class, l);
        }

        public void selectAll() {
            // Ignore
        }

        public void setItem(Object newValue) {
            objItem = newValue;
            if (newValue != null) {
                MDeleteComBoxEntry _entry = (MDeleteComBoxEntry) newValue;
                editor.setText(_entry.getText());
                lbIcon.setIcon(_entry.getIcon());
            } else {
                editor.setText(null);
                lbIcon.setIcon(null);
            }
        }

    }

    private class MDeleteComBoxPopup extends BasicComboPopup {
        /**
		 * 
		 */
        private static final long serialVersionUID = 1L;
        protected Color selectedBackground;
        protected Color selectedForeground;
        protected Color background;
        protected Color foreground;

        private JPanel panelList;
        private Map<MDeleteComBoxEntry, JComponent> values;

        private Dimension maxDimension = new Dimension(-1, 40);
        private Dimension mixDimension = new Dimension(-1, 25);

        public MDeleteComBoxPopup(JComboBox comboBox) {
            super(comboBox);
            comboBox.setMaximumRowCount(5);
            background = UIManager.getColor("ComboBox.background");
            foreground = UIManager.getColor("ComboBox.foreground");
            //所选项的背景和前景
            selectedBackground = new Color(48,131,251);//UIManager.getColor("ComboBox.selectionBackground");
            selectedForeground = Color.yellow;//UIManager.getColor("ComboBox.selectionForeground");
            initializePopup();
        }

        private void initializePopup() {
            setLayout(new BorderLayout());
            panelList = new JPanel();
            panelList.setLayout(new GridBagLayout());
            values = new HashMap<MDeleteComBoxEntry, JComponent>();
            values.clear();
            DefaultComboBoxModel _model = (DefaultComboBoxModel) comboBox
                    .getModel();
            int size = _model == null ? 0 : _model.getSize();
            this.removeAll();
            for (int i = 0; i < size; i++) {
                MDeleteComBoxEntry _entry = (MDeleteComBoxEntry) _model
                        .getElementAt(i);

                JPanel _panelItem = new JPanel(new GridBagLayout());
                _panelItem.setBackground(Color.white);
                _panelItem.setPreferredSize(mixDimension);

                JLabel _lbIcon = new JLabel(_entry.getIcon());
                _lbIcon.setHorizontalAlignment(JLabel.LEFT);
                _lbIcon.setText(_entry.getText());
                values.put(_entry, _panelItem);
                _lbIcon.setFont(new Font(Font.DIALOG, Font.PLAIN, 13));

                //删除的按钮
                JLabel _lbDel = new JLabel("x");
                _lbDel.setHorizontalTextPosition(JLabel.CENTER);
                _lbDel.setHorizontalAlignment(JLabel.CENTER);
                _lbDel.setPreferredSize(new Dimension(15, 15));
                _lbDel.setVisible(false);

                _panelItem.add(_lbIcon, new GridBagConstraints(0, 0, 1, 1, 1,
                        1, GridBagConstraints.WEST, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));
                _panelItem.add(_lbDel, new GridBagConstraints(1, 0, 1, 1, 0, 0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(5, 5, 5, 10), 0, 0));

                ItemMoseListener _itemMoseListener = new ItemMoseListener(
                        _entry, _panelItem, _lbDel);
                _panelItem.addMouseListener(_itemMoseListener);
                _panelItem.addMouseMotionListener(_itemMoseListener);
                panelList.add(_panelItem, new GridBagConstraints(0, i, 1, 1, 1,
                        1, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));
            }

            if (size == 0 && isBDefaultIconShow() && getDefaultIcon() != null) {
                MDeleteComBoxEntry _entry = new MDeleteComBoxEntry(
                        getDefaultIcon(), "");
                _model.insertElementAt(_entry, 0);
                _model.setSelectedItem(_entry);
            }
            JScrollPane _scrollPane = new JScrollPane(panelList);
            _scrollPane.setBorder(null);
            add(_scrollPane, BorderLayout.CENTER);

        }

        @Override
        public Dimension getPreferredSize() {
            Dimension _dimension = super.getPreferredSize();
            _dimension.width = comboBox.getWidth();
            if (values.size() > 1) {
                _dimension.height = (int) (panelList.getPreferredSize()
                        .getHeight() + 10);
            }
            return _dimension;
        }

        @Override
        protected void firePopupMenuWillBecomeVisible() {
            DefaultComboBoxModel _model = (DefaultComboBoxModel) comboBox
                    .getModel();
            MDeleteComBoxEntry _entry = (MDeleteComBoxEntry) _model
                    .getSelectedItem();
            if (_entry != null) {
                JComponent _comp = values.get(_entry);
                if (_comp != null && _comp instanceof JPanel) {
                    updateSelectItem((JPanel) _comp, true);
                }
            }
            super.firePopupMenuWillBecomeVisible();
        }

        private void updateSelectItem(JPanel panel, boolean select) {
            Component _comp;
            if (select) {
                panel.setBackground(selectedBackground);
                panel.setPreferredSize(maxDimension);
                for (int i = 0; i < panel.getComponentCount(); i++) {
                    _comp = panel.getComponent(i);
                    _comp.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
                    _comp.setForeground(selectedForeground);
                    if (_comp instanceof JLabel) {
                        JLabel _label = (JLabel) _comp;
                        if ("x".equals(_label.getText())) {
                            _label.setVisible(true);
                        } else if (_label.getIcon() != null) {
                            resizeImageIcon((ImageIcon) _label.getIcon(),
                                    maxIconSize);
                        }
                    }
                }
            } else {
                panel.setBackground(Color.white);
                panel.setPreferredSize(mixDimension);
                for (int i = 0; i < panel.getComponentCount(); i++) {
                    _comp = panel.getComponent(i);
                    _comp.setFont(new Font(Font.DIALOG, Font.PLAIN, 13));
                    _comp.setForeground(Color.black);
                    if (_comp instanceof JLabel) {
                        JLabel _label = (JLabel) _comp;
                        if ("x".equals(_label.getText())) {
                            _label.setVisible(false);
                        } else if (_label.getIcon() != null) {
                            resizeImageIcon((ImageIcon) _label.getIcon(),
                                    mixIconSize);
                        }
                    }
                }
            }
            panel.updateUI();

        }

        private class ItemMoseListener extends MouseAdapter {

            private MDeleteComBoxEntry entry;
            private JPanel panel;
            private JLabel lbDel;

            public ItemMoseListener(MDeleteComBoxEntry entry, JPanel panel,
                    JLabel lbDel) {
                this.entry = entry;
                this.panel = panel;
                this.lbDel = lbDel;
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                DefaultComboBoxModel _model = (DefaultComboBoxModel) comboBox
                        .getModel();
                if (e.getPoint().getX() > lbDel.getX()
                        && e.getPoint().getX() < (lbDel.getX() + lbDel
                                .getWidth())) {
                    _model.removeElement(entry);
                    fireMItemDeleted(entry);
                    initializePopup();
                } else {
                    _model.setSelectedItem(entry);
                    fireMItemSelected(entry);
                }

                MDeleteComBoxPopup.this.hide();

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                DefaultComboBoxModel _model = (DefaultComboBoxModel) comboBox
                        .getModel();
                MDeleteComBoxEntry _entry = (MDeleteComBoxEntry) _model
                        .getSelectedItem();
                JComponent _comp = values.get(_entry);
                if (_comp != null && _comp instanceof JPanel) {
                    updateSelectItem((JPanel) _comp, false);
                }
                updateSelectItem(panel, true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                updateSelectItem(panel, false);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                if (e.getPoint().getX() > lbDel.getX()
                        && e.getPoint().getX() < (lbDel.getX() + lbDel
                                .getWidth())) {
                    lbDel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
                            Color.gray));
                } else {
                    lbDel.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
                }
                panel.updateUI();

            }

        }
    }

    /***
     * 测试代码
     * 
     * @param args
     */
//    public static void main(String[] args) {
//
//        SwingUtilities.invokeLater(new Runnable() {
//
//            @Override
//            public void run() {
//                try {
//                    UIManager.setLookAndFeel(UIManager
//                            .getSystemLookAndFeelClassName());
//                } catch (ClassNotFoundException e) {
//                    e.printStackTrace();
//                } catch (InstantiationException e) {
//                    e.printStackTrace();
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                } catch (UnsupportedLookAndFeelException e) {
//                    e.printStackTrace();
//                }
//                JFrame _frame = new JFrame();
//                
//                
//                
//                Icon _icon1 = new ImageIcon("/Users/wyj/Documents/workspace/mytest/resources/a.png");
//                Icon _icon2 = new ImageIcon("/Users/wyj/Documents/workspace/mytest/resources/b.png");
//                Icon _icon3 = new ImageIcon("/Users/wyj/Documents/workspace/mytest/resources/c.png");
//
//                MDeleteComBoxEntry[] items = new MDeleteComBoxEntry[] {
//                        new MDeleteComBoxEntry(null, "Wang"),
//                        new MDeleteComBoxEntry(null, "Zhang"),
//                        new MDeleteComBoxEntry(null, "Zhong") };
//                JComboBox _comBox = new MDeleteComBox(items);
//                _comBox.setPreferredSize(new Dimension(150, 25));
//                _comBox.setEditable(true);
//                //_comBox.setDefaultIcon(_icon1);
//               // _comBox.setBDefaultIconShow(true);
////                _comBox.addMItemListener(new MItemListener() {
////                    @Override
////                    public void itemDeleted(MDeleteComBoxEntry entry) {
////                        System.out.println("entry deleted:" + entry.getText());
////                    }
////
////                    @Override
////                    public void itemSelected(MDeleteComBoxEntry entry) {
////                        System.out.println("entry selected:" + entry.getText());
////                    }
////
////                });
//                System.out.println(_comBox.getItemListeners());
//                
//                JPanel _panel = new JPanel();
//                _panel.add(_comBox);
//
//                _frame.add(_panel);
//                _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                _frame.setSize(new Dimension(400, 300));
//                _frame.setVisible(true);
//            }
//
//        });
//    }
}
