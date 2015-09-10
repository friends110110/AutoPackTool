package cn.wf.wfw.widget;

import javax.swing.Icon;


public class MDeleteComBoxEntry {

	private Icon icon;
	private String text;

	public MDeleteComBoxEntry(String text){
		this.text = text;
	}
	
	public MDeleteComBoxEntry(Icon icon,String text){
		this.icon = icon;
		this.text = text;
	}

	public Icon getIcon() {
		return icon;
	}

	public void setIcon(Icon icon) {
		this.icon = icon;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	@Override
	public String toString(){
		return text;
	}
	
}

