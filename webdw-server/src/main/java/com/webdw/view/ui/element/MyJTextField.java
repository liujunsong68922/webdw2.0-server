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
public class MyJTextField extends MyUIElement {

	public String ReadMe = "MyJTextField";
	public boolean Locked = false;

	/**
	 * ����һ��ArrayList�Ĺ��캯��,�ڴ����˶����Ժ�,���Լ����뵽���ArrayList����
	 * 
	 * @param targetControls
	 */
	public MyJTextField(String stext, String name, ArrayList targetControls, MyUIContainer parent) {
		super(stext);
		super.classname = MyUIComponentConst.UITYPE_TEXTFIELD;
		super.setText(stext);
		super.setName(name);
//		Locked = !super.isEditable();
		targetControls.add(this);
		parent.add(this);
	}

	public void Locked(boolean b) {
//		super.setEditable(!b);
//		Locked = !super.isEditable();

	}
}
