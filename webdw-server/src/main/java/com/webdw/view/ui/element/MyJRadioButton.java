package com.webdw.view.ui.element ;

import java.util.ArrayList;

import com.webdw.view.ui.MyUIComponentConst;
import com.webdw.view.ui.container.MyUIContainer;

/**
 * �Զ����JPanel��,�����򻯴����Ǩ�ƹ���
 * 
 * @author liujunsong
 * 
 */
public class MyJRadioButton extends MyUIElement {
	public String _ReadMe="My JRadioButton";

	public MyJRadioButton(String s1, String name, ArrayList targetControls,
			MyUIContainer parent) {
		super(s1);
		super.classname = MyUIComponentConst.UITYPE_RADIOBUTTON;
		super.setName(name);
		Refresh();
		//RadioButton Ӧ��������һ��parent�����д�������ֱ�Ӽ��뵽Ŀ��targetControls
		//RadioButton ��һ��������UIԪ�ض���
		//targetControls.add(this);
		
		parent.add(this);
	}

	public boolean Value = false;

	public boolean Enabled = false;

	private boolean selected = false;
	
	private String valuestring="";
	
	//这个radio选中时的value值
	private String radiovalue="";
	
	
	
	public String getRadiovalue() {
		return radiovalue;
	}

	public void setRadiovalue(String radiovalue) {
		this.radiovalue = radiovalue;
	}

	public String getValuestring() {
		return valuestring;
	}

	public void setValuestring(String valuestring) {
		this.valuestring = valuestring;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	void Refresh() {
		Value = this.isSelected();
//		Enabled = super.isEnabled();
	}

	public void Value(boolean invalue) {
		this.setSelected(invalue);
	}
	
	public void Enabled(boolean invalue){
//		super.setEnabled(invalue);
	}

}
