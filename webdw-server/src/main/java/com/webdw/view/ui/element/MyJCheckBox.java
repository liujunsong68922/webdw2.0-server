package com.webdw.view.ui.element;

import java.util.ArrayList;

import com.webdw.view.ui.MyUIComponentConst;
import com.webdw.view.ui.container.MyUIContainer;

/**
 * �Զ����JPanel��,�����򻯴����Ǩ�ƹ���
 * 
 * @author liujunsong
 * 
 */
public class MyJCheckBox extends MyUIElement {
	public String _ReadMe="MyJCheckBox";
	
	public MyJCheckBox(String s1, String name, ArrayList targetControls, MyUIContainer parent) {
		super(s1);
		super.classname = MyUIComponentConst.UITYPE_CHECKBOX;
		super.setName(name);
		targetControls.add(this);
		parent.add(this);
	}

	public boolean Value = false;

	public void Value(boolean invalue) {
		this.Value = invalue;
	}

	public String get_ReadMe() {
		return _ReadMe;
	}

	public void set_ReadMe(String _ReadMe) {
		this._ReadMe = _ReadMe;
	}

	public boolean isValue() {
		return Value;
	}

	public void setValue(boolean value) {
		Value = value;
	}
	

}
